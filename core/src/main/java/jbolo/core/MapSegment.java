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

	public static class MapSegmentBuilder {

		private final Tile[][] tiles;

		public MapSegmentBuilder(int width, int height) {
			tiles = new Tile[width][height];
		}

		public void setTile(Tile tile, int x, int y) {
			tiles[x][y] = tile;
		}

	}

	public MapSegment(GameWorld gameWorld, MapSegmentBuilder builder) {
		this.tiles = builder.tiles.clone();
		this.gameWorld = gameWorld;
	}

}
