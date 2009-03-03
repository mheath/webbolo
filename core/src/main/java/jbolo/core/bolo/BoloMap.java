package jbolo.core.bolo;

import jbolo.core.DiscreteDirection;

/**
 * @author Mike Heath
 */
public class BoloMap {

	public static final int BOLO_MAP_HEIGHT = 256;
	public static final int BOLO_MAP_WIDTH = 256;

	private final StartingPosition[] startingPositions;
	private final Base[] bases;
	private final Pillbox[] pillboxes;

	private final Terrain[][] map;

	public static enum Terrain {
		BUILDING(false),
		RIVER(false),
		SWAMP(false),
		CRATER(false),
		ROAD(false),
		FOREST(false),
		RUBBLE(false),
		GRASS(false),
		SHOT_BUILDING(false),
		RIVER_WITH_BOAT(false),
		SWAMP_WITH_MINE(true),
		CRATER_WITH_CRATER(true),
		ROAD_WITH_MINE(true),
		FOREST_WITH_MINE(true),
		RUBBLE_WITH_MINE(true),
		GRASS_WITH_MINE(true);

		private final boolean mined;

		Terrain(boolean mined) {
			this.mined = mined;
		}

		public boolean isMined() {
			return mined;
		}
	}

	public static class StartingPosition {
		public final int x;
		public final int y;
		public final DiscreteDirection direction;

		public StartingPosition(int x, int y, DiscreteDirection direction) {
			this.x = x;
			this.y = y;
			this.direction = direction;
		}
	}

	public static class Base {
		public final int x;
		public final int y;
		public final int owner;
		public final int armor;
		public final int mines;
		public final int shells;

		public Base(int x, int y, int owner, int armor, int mines, int shells) {
			this.x = x;
			this.y = y;
			this.owner = owner;
			this.armor = armor;
			this.mines = mines;
			this.shells = shells;
		}
	}

	public static class Pillbox {
		public final int x;
		public final int y;
		public final int owner;
		public final int armor;
		public final int speed;

		public Pillbox(int x, int y, int owner, int armor, int speed) {
			this.x = x;
			this.y = y;
			this.owner = owner;
			this.armor = armor;
			this.speed = speed;
		}
	}

	public BoloMap(StartingPosition[] startingPositions, Base[] bases, Pillbox[] pillboxes) {
		this.startingPositions = startingPositions.clone();
		this.bases = bases.clone();
		this.pillboxes = pillboxes.clone();
		this.map = new Terrain[BOLO_MAP_WIDTH][BOLO_MAP_HEIGHT];
	}

	public void set(int x, int y, Terrain terrain) {
		map[x][y] = terrain;
	}

	public Terrain get(int x, int y) {
		return map[x][y];
	}

	public int getBaseCount() {
		return bases.length;
	}

	public Base getBase(int i) {
		return bases[i];
	}

	public int getPillboxCount() {
		return pillboxes.length;
	}

	public Pillbox getPillbox(int i) {
		return pillboxes[i];
	}

	public int getStartingPositionCount() {
		return startingPositions.length;
	}

	public StartingPosition getStartingPosition(int i) {
		return startingPositions[i];
	}
	
}