package jbolo.core;

/**
 * Configuration parameters for the type of game.
 *
 * @author Mike Heath
 */
public interface GameType {

	int getMaxArmor();

	int getMaxShells();

	int getMaxMines();

	/**
	 * Called after a game item gets created.  This allows the GameType implementation to set parameters on the object
	 * like number of shells/mines for a tank at the start of a game.
	 *
	 * @param tank
	 */
	void init(Item tank);

}
