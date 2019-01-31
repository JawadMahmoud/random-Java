// Jawad Mahmoud
// 2253483

package game;

import java.util.Scanner;

/**
 * Main method for Rock-Paper-Scissors-Lizard-Spock game.
 * 
 * JP2 Laboratory 7 2017.
 * 
 * @author mefoster
 */
public class GameMain {

	/**
	 * Prompts the user for the tournament parameters and then runs
	 * a tournament.
	 */
	public static void main(String[] args) {
		// Read everything from standard input
		Scanner stdin = new Scanner(System.in);

		// First player is always a computer
		GamePlayer player1 = new ComputerPlayer("Computer");
		
		// Second player may be a computer or a human
		GamePlayer player2;
		System.out.println("Enter name of human player, or empty string for two computer players");
		String name = stdin.nextLine();
		if (name.length() == 0) {
			System.out.println("Using two computer players");
			player2 = new ComputerPlayer("Computer2");
		} else {
			player2 = new HumanPlayer(name, stdin);
		}

		// Get the number of games required to win the tournament -- and be sure
		// it is a positive integer
		int numGames = -1;
		while (numGames <= 0) {
			System.out.println("Enter number of games to win: ");
			try {
				numGames = stdin.nextInt();
			} catch (NumberFormatException ex) {
				System.out.println("Invalid input!");
			}
			if (numGames <= 0) {
				System.out.println("Invalid input!");
			}
		}
		
		// Run the tournament with the given parameters
		GamePlayer winner = playTournament(player1, player2, numGames);

		System.out.println("------------------");
		System.out.println("Overall winner is: " + winner.getName());

		stdin.close();
	}

	private static GamePlayer playTournament(GamePlayer player1, GamePlayer player2, int numGames) {
		int p1Wins = 0;
		int p2Wins = 0;
		Symbol p1Symbol;
		Symbol p2Symbol;
		/*
		 * The while loop continues until either of the players wins games equal to chosen numGames. 
		 * The chooseSymbol method is called on both players,
		 * their choices are then added to the history.
		 * When a player wins, their number of wins is incremented. 
		 * It also shows the score card for each player, for keeping track.
		 * Returns the player that wins the number of games.
		 */
		while (p1Wins < numGames && p2Wins < numGames) {
			p1Symbol = player1.chooseSymbol();
			p2Symbol = player2.chooseSymbol();
			System.out.println("");
			System.out.println(player1.name + " chose " + p1Symbol);
			System.out.println(player2.name + " chose " + p2Symbol);
			System.out.println("");
			player1.addHistory(p1Symbol, p2Symbol);
			player2.addHistory(p1Symbol, p2Symbol);
			
			if (p1Symbol.getResult(p2Symbol) == GameResult.WIN) {
				p1Wins = p1Wins + 1;
				System.out.println(player1.name + " WINS!");
			} else if (p1Symbol.getResult(p2Symbol) == GameResult.LOSE) {
				p2Wins = p2Wins + 1;
				System.out.println(player2.name + " WINS!");
			} else {
				System.out.println("DRAW!");
			}
			System.out.println("");
			System.out.println("Scorecard: \n" + 
					player1.name + " has won " + p1Wins + " times.\n" + 
					player2.name + " has won " + p2Wins + " times.");
			System.out.println("");
		}
		
		if (p1Wins > p2Wins) {
			return player1;
		} else {
			return player2;
		}
	}
}
