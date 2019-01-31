// Jawad Mahmoud
// 2253483

package game;

import java.util.Scanner;

public class HumanPlayer extends GamePlayer {
	
	public Scanner humanInput;

	public HumanPlayer(String name, Scanner humanInput) {
		super(name);
		this.humanInput = humanInput;
	}

	/**
	 * The chooseSymbol method as defined in the HumanPlayer class 
	 * asks the user to input a Symbol from a list of Symbols shown. 
	 * Returns the chosen Symbol.
	 */
	@Override
	public Symbol chooseSymbol() {
		System.out.println("Choose one of the following:\n"
				+ Symbol.ROCK.name() + "\n" + Symbol.PAPER.name() + "\n"
				+ Symbol.SCISSORS.name() + "\n" + Symbol.LIZARD.name() + "\n"
				+ Symbol.SPOCK.name());
		String humanString;
		Symbol humanSymbol;
		humanString = humanInput.next();
		humanSymbol = Symbol.valueOf(humanString);
		return humanSymbol;
	}	
}
