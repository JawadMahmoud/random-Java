// Jawad Shahid Mahmoud
// 2253483

package sudoku;

import java.io.IOException;
import java.util.*;
import sudoku.Utils;

public class SudokuGrid {

	private String fileName;
	private int[][] finalGrid = new int[Utils.SIZE][Utils.SIZE];
	/*
	 * Constructor for the SudokuGrid
	 * initialises the two dimensional sudoku grid
	 * from the chosen file
	 */
	public SudokuGrid(String fileName) throws IOException {
		this.fileName = fileName;
		
		String unsolvedGridString = Utils.loadGrid(fileName);
		String unsolvedGrid[] = unsolvedGridString.split(",");
		/*
		 * adds the elements from the file
		 * to the relative positions of the sudoku grid
		 * if the position is blank, the position is kept to its initial value of 0
		 */
		int totalElementIndex = 0;
		for (int rowIndex = 0; rowIndex < Utils.SIZE; rowIndex++) {
			int elementIndex = 0;
			while (elementIndex < Utils.SIZE && totalElementIndex < unsolvedGrid.length) {
				if (unsolvedGrid[totalElementIndex].length() == 0) {

				} else {
					finalGrid[rowIndex][elementIndex] = Integer.valueOf(unsolvedGrid[totalElementIndex]);
				}
				elementIndex++;
				totalElementIndex++;
			}
		}
	}
	/*
	 * Extracts the elements from each row in the sudoku grid
	 * except for the empty positions (0)
	 * and puts them in an ArrayList
	 * Returns the ArrayList
	 */
	public static ArrayList<Integer> rowExtractor(int[] row, int annoyingBug) {
		int[] extractedRow =  row;
		ArrayList<Integer> extractedRowList = new ArrayList<Integer>(Utils.SIZE);
		for (int elementIndex = 0; elementIndex < Utils.SIZE; elementIndex++) {
			if (extractedRow[elementIndex] != 0) {
				extractedRowList.add(extractedRow[elementIndex]);
			}
		}
		return extractedRowList;
	}
	/*
	 * First, extracts the elements in the same positions in different rows to a new Array
	 * then puts all the elements, except for the 0 elements, into an ArrayList
	 * Returns the ArrayList
	 */
	public static ArrayList<Integer> columnExtractor(int[][] grid, int columnIndex) {
		int[] extractedColumn = new int[Utils.SIZE];
		int nextIndex = 0;
		for (int rowIndex = 0; rowIndex < Utils.SIZE; rowIndex++) {
			extractedColumn[nextIndex] = grid[rowIndex][columnIndex];
			nextIndex++;
		}
		ArrayList<Integer> extractedColumnList = new ArrayList<Integer>(Utils.SIZE);
		for (int elementIndex = 0; elementIndex < Utils.SIZE; elementIndex++) {
			if (extractedColumn[elementIndex] != 0) {
				extractedColumnList.add(extractedColumn[elementIndex]);
			}
		}
		return extractedColumnList;
	}
	/*
	 * Takes the entire sudoku grid and two integers maxRow and maxColumn
	 * maxRow sets the final row of the 3x3 grid
	 * maxColumn sets the final column of the 3x3 grid
	 * adds the elements (from maxRow-3 to maxRow and from maxColumn-3 to maxColumn) to a new Array
	 * then adds all the elements that are not 0 to a new ArrayList
	 * returns the ArrayList
	 */
	public static ArrayList<Integer> miniGridExtractor(int[][] grid, int maxRow, int maxColumn) {
		int[] extractedMiniGrid = new int[Utils.SIZE];
		int totalElementIndex = 0;
		for (int rowIndex = maxRow-3; rowIndex < maxRow && rowIndex >= maxRow-3; rowIndex++) {
			int columnIndex = maxColumn - 3;
			while (columnIndex < maxColumn && columnIndex >= maxColumn-3 && totalElementIndex < Utils.SIZE) {
				extractedMiniGrid[totalElementIndex] = grid[rowIndex][columnIndex];
				columnIndex++;
				totalElementIndex++;
			}
		}
		ArrayList<Integer> extractedMiniGridList = new ArrayList<Integer>(Utils.SIZE);
		for (int nextIndex = 0; nextIndex < Utils.SIZE; nextIndex++) {
			if (extractedMiniGrid[nextIndex] != 0) {
				extractedMiniGridList.add(extractedMiniGrid[nextIndex]);
			}
		}
		return extractedMiniGridList;
	}
	/*
	 * checks if an element matches to any of the elements that come after it
	 * Returns true if there is a match (duplicate found)
	 * Returns false if there isn't (no duplicates)
	 */
	public static boolean duplicateCheck(ArrayList<Integer> aList) {
		Boolean duplicates = false;
		int elementIndex = 0;
		while (duplicates == false && elementIndex < aList.size()-1) {
			int compareIndex = elementIndex + 1;
			while (duplicates == false && compareIndex < aList.size()) {
				//System.out.println(aList.get(elementIndex));
				//System.out.println(aList.get(compareIndex));
				if (aList.get(elementIndex) == aList.get(compareIndex)) {
					//System.out.println("duplicate is true");
					duplicates = true;
				}
				compareIndex++;
			}
			elementIndex++;
		}
		if (duplicates == true) {
			//System.out.println("final return for duplicate is true");
			return true;
		} else {
			//System.out.println("final return for duplicate is false");
			return false;
		}
	}
	/*
	 * checks if the ArrayList has 9 elements
	 * if the array that formed the ArrayList had a 0 element then it would not have 9 elements
	 * Returns true if 9 elements are present
	 * Returns false if 9 elements aren't present
	 */
	public static boolean completeCheck(ArrayList<Integer> aList) {
		if (aList.size() == Utils.SIZE) {
			return true;
		} else {
			return false;
		}
	}
	/*
	 * checks if the rows, columns and 3x3 grids have duplicates
	 * also checks if the rows, columns and 3x3 grids have 9 elements
	 */
	public String check() {
		boolean validity = true;
		boolean completion = true;
		int rowNumber = 0;
		int columnNumber = 0;
		/*
		 * keeps repeating the loop until all the rows and columns are checked
		 * unless validity is false for any row or column
		 * for each row and column, checks for duplicates
		 * if duplicate is found, validity is set to false
		 * and vice versa
		 */
		while (validity == true && rowNumber < Utils.SIZE && columnNumber < Utils.SIZE) {
			ArrayList<Integer> extractedRowList = rowExtractor(finalGrid[rowNumber]);
			ArrayList<Integer> extractedColumnList = columnExtractor(finalGrid, columnNumber);
			if (duplicateCheck(extractedRowList) == true || duplicateCheck(extractedColumnList) == true) {
				validity = false;
			} else {
				validity = true;
			}
			rowNumber++;
			columnNumber++;
		}
		//System.out.println("row and column validity: " + validity);
		/*
		 * if validity is still true then checks if all rows and columns have 9 elements
		 * if any row or column does not have 9 elements, the loop stops as completion is set to false
		 */
		if (validity == true) {
			rowNumber = 0;
			columnNumber = 0;
			
			while (completion == true && rowNumber < Utils.SIZE && columnNumber < Utils.SIZE) {
				ArrayList<Integer> extractedRowList = rowExtractor(finalGrid[rowNumber]);
				ArrayList<Integer> extractedColumnList = columnExtractor(finalGrid, columnNumber);
				if (completeCheck(extractedRowList) == true && completeCheck(extractedColumnList) == true) {
					completion = true;
				} else {
					completion = false;
				}
				rowNumber++;
				columnNumber++;
			}
		}
		//System.out.println("row and column completion: " + completion);
		/*
		 * only checks the validity of all the 3x3 grids if rows and columns were valid
		 * if there are any duplicates found in any grid, validity becomes false and loop stops
		 * if no duplicates are found within the 3x3 grid, validity is true
		 */
		int maxRow = 0;
		while (validity == true && maxRow < Utils.SIZE) {
			maxRow = maxRow + 3;
			int maxColumn = 3;
			while (validity == true  && maxColumn <= Utils.SIZE) {
				ArrayList<Integer> extractedMiniGridList = miniGridExtractor(finalGrid, maxRow, maxColumn);
				//System.out.println(extractedMiniGridList);
				if (duplicateCheck(extractedMiniGridList) == true) {
					validity = false;
				} else {
					validity = true;
				}
				 maxColumn = maxColumn + 3;
			}
		}
		/*
		 * if there aren't any duplicates in the entire sudoku grid and no 0 elements, returns Valid
		 * if there aren't any duplicates in the entire sudoku grid but does have 0 elements, returns Incomplete
		 * if there are duplicates, returns Invalid
		 */
		if (validity == true && completion == true) {
			return Utils.VALID;
		} else if (validity == true && completion == false) {
			return Utils.INCOMPLETE;
		} else {
			return Utils.INVALID;
		}
	}
	/*
	 * getter for the File name of the file used
	 */
	public String getFileName() {
		return fileName;
	}
	/*
	 * setter for the File name of the file used
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	/*
	 * returns each row of the grid as a separate line
	 */
	public String toString() {
		String rowOne = Arrays.toString(finalGrid[0]);
		String rowTwo = Arrays.toString(finalGrid[1]);
		String rowThree = Arrays.toString(finalGrid[2]);
		String rowFour = Arrays.toString(finalGrid[3]);
		String rowFive = Arrays.toString(finalGrid[4]);
		String rowSix = Arrays.toString(finalGrid[5]);
		String rowSeven = Arrays.toString(finalGrid[6]);
		String rowEight = Arrays.toString(finalGrid[7]);
		String rowNine = Arrays.toString(finalGrid[8]);
		String allRows = "The sudoku grid is as shown below: \n" + rowOne + "\n" + rowTwo + "\n" + rowThree + "\n" + rowFour + "\n" 
		+ rowFive + "\n" + rowSix + "\n" + rowSeven + "\n" + rowEight + "\n" + rowNine;
		return allRows;
	}
	/*
	 * a main method to test my code
	public static void main (String[] args) throws IOException {
		SudokuGrid sudokuGrid = new SudokuGrid("griderr1");
		String finalResult = sudokuGrid.check();
		String finalGrid = sudokuGrid.toString();
		System.out.println(finalResult);
		System.out.println(finalGrid);
	}
	*/
}
