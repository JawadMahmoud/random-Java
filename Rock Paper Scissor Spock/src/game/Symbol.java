// Jawad Mahmoud
// 2253483

package game;

public enum Symbol {
	ROCK, PAPER, SCISSORS, LIZARD, SPOCK;
	/**
	 * getResult checks two Symbol items together. 
	 * For every Symbol combination, it returns whether 
	 * the first Symbol WINS, LOSES or DRAWS against the other Symbol
	 */
	public GameResult getResult(Symbol other) {
		if (this == Symbol.ROCK) {
			if (other == Symbol.LIZARD) {
				return GameResult.WIN;
			} else if (other == Symbol.SCISSORS) {
				return GameResult.WIN;
			} else if (other == Symbol.ROCK) {
				return GameResult.DRAW;
			} else {
				return GameResult.LOSE;
			}
		} else if (this == Symbol.PAPER) {
			if (other == Symbol.ROCK) {
				return GameResult.WIN;
			} else if (other == Symbol.SPOCK) {
				return GameResult.WIN;
			} else if (other == Symbol.PAPER) {
				return GameResult.DRAW;
			} else {
				return GameResult.LOSE;
			}
		} else if (this == Symbol.SCISSORS) {
			if (other == Symbol.PAPER) {
				return GameResult.WIN;
			} else if (other == Symbol.LIZARD) {
				return GameResult.WIN;
			} else if (other == Symbol.SCISSORS) {
				return GameResult.DRAW;
			} else {
				return GameResult.LOSE;
			}
		} else if (this == Symbol.LIZARD) {
			if (other == Symbol.SPOCK) {
				return GameResult.WIN;
			} else if (other == Symbol.PAPER) {
				return GameResult.WIN;
			} else if (other == Symbol.LIZARD) {
				return GameResult.DRAW;
			} else {
				return GameResult.LOSE;
			}
		} else {
			if (other == Symbol.SCISSORS) {
				return GameResult.WIN;
			} else if (other == Symbol.ROCK) {
				return GameResult.WIN;
			} else if (other == Symbol.SPOCK) {
				return GameResult.DRAW;
			} else {
				return GameResult.LOSE;
			}
		}
	}
}
