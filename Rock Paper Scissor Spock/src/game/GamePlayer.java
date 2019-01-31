// Jawad Mahmoud
// 2253483

package game;

import java.util.ArrayList;
/**
 * GamePlayer represents the player of a game, either human or computer.
 */
public abstract class GamePlayer {
	public String name;
	public ArrayList<Symbol> playerSymbols = new ArrayList<Symbol>();
	public ArrayList<Symbol> opponentSymbols = new ArrayList<Symbol>();

	public GamePlayer(String name) {
		this.name = name;
	}
	/**
	 * Gets the name of the player as chosen.
	 */
	public String getName() {
		return name;
	}
	/**
	 * Set the name of the player.
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * Adds the Symbols chosen by both players too separate Arrays
	 * @param mySymbol The Symbol chosen by the current player
	 * @param otherSymbol The Symbol chosen by the opponent
	 */
	public void addHistory(Symbol mySymbol, Symbol otherSymbol) {
		playerSymbols.add(mySymbol);
		opponentSymbols.add(otherSymbol);
	}
	/**
	 * The chooseSymbol method is abstract and defined differently 
	 * in the subclasses
	 */
	public abstract Symbol chooseSymbol();
	
}
