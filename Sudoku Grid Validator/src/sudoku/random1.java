package sudoku;

import java.io.IOException;
import java.util.Arrays;

public class random1 {

	public static void main (String[] args) {
		
		String numberGrid = "1,2,3,4,,6,7,8,9";
		String newNumberGrid = numberGrid.replace("", "0");
		System.out.println(numberGrid);
		System.out.println(newNumberGrid);
		String[] numberGridString = numberGrid.split(",");
		System.out.println(Arrays.toString(numberGridString));
		
		/**
		for (int rowIndex = 0; rowIndex < randomArray.length; rowIndex++) {
			randomArray[rowIndex] = new int[9];
			System.out.println(Arrays.deepToString(randomArray));
			
		}*/
		
		System.out.println(numberGridString[0]);
		System.out.println(numberGridString[1]);
		System.out.println(numberGridString[2]);
		System.out.println(numberGridString[3]);
		System.out.println(numberGridString[4]);
		System.out.println(numberGridString[5]);
		System.out.println(numberGridString[6]);
		System.out.println(numberGridString[7]);
		System.out.println(numberGridString[8]);
		
		int[] finalArray = new int[9];
		finalArray[0] = Integer.valueOf(numberGridString[0]);
		int elementIndex = 0;
		while (elementIndex < finalArray.length) {
			if (numberGridString[elementIndex].length() == 0) {
				
			} else {
				finalArray[elementIndex] = Integer.valueOf(numberGridString[elementIndex]);
			}
			elementIndex++;
		}
		
		System.out.println(Arrays.toString(finalArray));
		
	}	
}


/**public static void main(String[] args) throws IOException {
	// SudokuGrid unsolvedGridString = new SudokuGrid("grid1");
	String unsolvedGridString = Utils.loadGrid("gridsoln");
	String unsolvedGrid[] = unsolvedGridString.split(",");
	System.out.println(Arrays.toString(unsolvedGrid));
	System.out.println(unsolvedGrid.length);

	int finalGrid[][] = new int[9][9];

	int totalElementIndex = 0;
	for (int rowIndex = 0; rowIndex < 9; rowIndex++) {
		int elementIndex = 0;
		while (elementIndex < 9 && totalElementIndex < unsolvedGrid.length) {
			if (unsolvedGrid[totalElementIndex].length() == 0) {

			} else {
				finalGrid[rowIndex][elementIndex] = Integer.valueOf(unsolvedGrid[totalElementIndex]);
			}
			elementIndex++;
			totalElementIndex++;
		}
	}		
}*/