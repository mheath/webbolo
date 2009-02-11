package jbolo.core;

import java.util.Set;
import java.util.HashSet;

/**
 * @author Mike Heath
 */
public class MapSegment {

	private final GameWorld gameWorld;

	private final Tile[][] tiles;

	private final Set<Item> items = new HashSet<Item>();

	public MapSegment(GameWorld gameWorld, int width, int height) {
		this.tiles = new Tile[width][height];
		this.gameWorld = gameWorld;
	}

}
