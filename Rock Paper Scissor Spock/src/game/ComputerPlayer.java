// Jawad Mahmoud
// 2253483

package game;

import java.util.ArrayList;
import java.util.Random;

public class ComputerPlayer extends GamePlayer {
	
	public ComputerPlayer(String name) {
		super(name);
	}
	/**
	 * The chooseSymbol method as defined in the ComputerPlayer class 
	 * checks whether a random Symbol should be returned, if not, 
	 * checks which Symbols are used most by the opponent. Chooses a Symbol 
	 * that defeats the most used Symbol.
	 * Returns the chosen Symbol.
	 */
	@Override
	public Symbol chooseSymbol() {
		/*
		 * frequencyList contains the number of times each symbol occurs in 
		 * the history list of the opponent. This is done by the Stream, 
		 * which filters according to the name and then counts.
		 */
		ArrayList<Long> frequencyList = new ArrayList<Long>();
		frequencyList.add(opponentSymbols.stream().filter(Symbol -> Symbol.name() == "ROCK").count());
		frequencyList.add(opponentSymbols.stream().filter(Symbol -> Symbol.name() == "PAPER").count());
		frequencyList.add(opponentSymbols.stream().filter(Symbol -> Symbol.name() == "SCISSOR").count());
		frequencyList.add(opponentSymbols.stream().filter(Symbol -> Symbol.name() == "LIZARD").count());
		frequencyList.add(opponentSymbols.stream().filter(Symbol -> Symbol.name() == "SPOCK").count());
		/*
		 * Just a reference of which Symbols are defeated by a specific Symbol. 
		 * Also a reference for which Symbols a specific Symbol loses against. 
		 * The integers represent the position index of the Symbols
		 * ROCK = 0, PAPER = 1, SCISSORS = 2, LIZARD = 3, SPOCK = 4.
		int rockDef[] = {2,3};
		int paperDef[] = {0,4};
		int scissorsDef[] = {1,3};
		int lizardDef[] = {1,4};
		int spockDef[] = {0,2};
		
		int rockLose[] = {1,4};
		int paperLose[] = {2,3};
		int scissorsLose[] = {0,4};
		int lizardLose[] = {0,2};
		int spockLose[] = {1,3};
		*/
		int firstOccur = 0; // Position at which the first maximum occurs
		int lastOccur = 0; // Position at which the last maximum occurs
		boolean dualMaxFreq = true; // If there are two separate Symbols that are maximum, this is true
		boolean maxExists = true; // If any max exists, this is true
		/*
		 * the while loop checks if a maximum exists. 
		 * If all the frequencies are 1, maxExists is set to false.
		 */
		int eachFreq = 0;
		int numFreq = 0;
		while (eachFreq < frequencyList.size()-1) {
			if (frequencyList.get(eachFreq) == 1) {
				numFreq++;
			}
			eachFreq++;
		} if (numFreq == frequencyList.size()-1) {
			maxExists = false;
		}
		/*
		 * If there is a maximum, this checks whether it occurs on only one position. 
		 * if firstOccur and lastOccur are not equal, means there are two Symbols that are max.
		 */
		if (maxExists) {
			long maxFreq = frequencyList.get(0);
			for (int freqIndex = 1; freqIndex < frequencyList.size(); freqIndex++) {
				if (frequencyList.get(freqIndex) > maxFreq) {
					maxFreq = frequencyList.get(freqIndex);
				}
			}
			firstOccur = frequencyList.indexOf(maxFreq);
			lastOccur = frequencyList.lastIndexOf(maxFreq);

			if (firstOccur == lastOccur) {
				dualMaxFreq = false;
			}
		/*
		 * if there is no maximum, then a random number is first generated. 
		 * This random number is used with the method singleMax to return 
		 * any random Symbol.
		 */
		} else {
			Random rng = new Random();
			int randomSymbolIndex = rng.nextInt(5);
			return singleMax(randomSymbolIndex);
		}
		/*
		 * If there are two Maximums, it returns a Symbol that defeats 
		 * either of the two Symbols that are maximums.
		 * If there is only one maximum, it returns a Symbol that defeats
		 * that Symbol.
		 */
		if (dualMaxFreq) {
			return dualMax(firstOccur, lastOccur);
		} else {
			return singleMax(firstOccur);
			}
	}
	/**
	 * the dualMax function takes the two Maximum occurrences. 
	 * Then, for each Symbol that is maximum, it adds to the possibleSymbols list 
	 * the Symbols that defeat that Symbol.
	 * Then, a random number is generated and one of the possible Symbols is returned.
	 */
	public Symbol dualMax(int firstOccurence, int lastOccurence) {
		ArrayList<Symbol> possibleSymbols = new ArrayList<Symbol>();
		int occurences[] = {firstOccurence, lastOccurence};
		for (int eachOccurence = 0; eachOccurence < occurences.length; eachOccurence++) {
			if (occurences[eachOccurence] == 0) {
				possibleSymbols.add(Symbol.PAPER);
				possibleSymbols.add(Symbol.SPOCK);
			} else if (occurences[eachOccurence] == 1) {
				possibleSymbols.add(Symbol.SCISSORS);
				possibleSymbols.add(Symbol.LIZARD);
			} else if (occurences[eachOccurence] == 2) {
				possibleSymbols.add(Symbol.ROCK);
				possibleSymbols.add(Symbol.SPOCK);
			} else if (occurences[eachOccurence] == 3) {
				possibleSymbols.add(Symbol.ROCK);
				possibleSymbols.add(Symbol.SCISSORS);
			} else {
				possibleSymbols.add(Symbol.PAPER);
				possibleSymbols.add(Symbol.LIZARD);
			}
		}
		Random rng = new Random();
		int randomSymbolIndex = rng.nextInt(possibleSymbols.size());
		return possibleSymbols.get(randomSymbolIndex);
	}
	/**
	 * the singleMax function takes the two Maximum occurrence. 
	 * Then it adds the Symbols that defeat that Symbol to the possibleSymbols list.
	 * Then, a random number is generated and one of the possible Symbols is returned.
	 */
	public Symbol singleMax(int singleOccurence) {
		ArrayList<Symbol> possibleSymbols = new ArrayList<Symbol>();
		if (singleOccurence == 0) {
			possibleSymbols.add(Symbol.PAPER);
			possibleSymbols.add(Symbol.SPOCK);
		} else if (singleOccurence == 1) {
			possibleSymbols.add(Symbol.SCISSORS);
			possibleSymbols.add(Symbol.LIZARD);
		} else if (singleOccurence == 2) {
			possibleSymbols.add(Symbol.ROCK);
			possibleSymbols.add(Symbol.SPOCK);
		} else if (singleOccurence == 3) {
			possibleSymbols.add(Symbol.ROCK);
			possibleSymbols.add(Symbol.SCISSORS);
		} else {
			possibleSymbols.add(Symbol.PAPER);
			possibleSymbols.add(Symbol.LIZARD);
		}
		Random rng = new Random();
		int randomSymbolIndex = rng.nextInt(possibleSymbols.size());
		return possibleSymbols.get(randomSymbolIndex);
	}
}
