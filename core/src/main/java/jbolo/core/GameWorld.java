package jbolo.core;

/**
 * @author Mike Heath
 */
public class GameWorld {

	private final GameType gameType;

	public GameWorld(GameType gameType) {
		this.gameType = gameType;
	}

	public GameType getGameType() {
		return gameType;
	}
}
