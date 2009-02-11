package jbolo.core;

/**
 * @author Mike Heath
 */
public enum DiscreteDirection {
	// Start with east because 0 bradians is 0 radians.
	EAST,
	NORTH_EAST_EAST,
	NORTH_EAST,
	NORTH_NORTH_EAST,
	NORTH,
	NORTH_NORTH_WEST,
	NORTH_WEST,
	NORTH_WEST_WEST,
	WEST,
	SOUTH_WEST_WEST,
	SOUTH_WEST,
	SOUTH_SOUTH_WEST,
	SOUTH,
	SOUTH_SOUTH_EAST,
	SOUTH_EAST,
	SOUTH_EAST_EAST;

	public static final int BRADIANS_GAP = 16;
	public static final int FUDGE_FACTOR = 8;

	private final int bradians;

	private DiscreteDirection() {
		this.bradians = ordinal() * BRADIANS_GAP;
	}

	public int getBradians() {
		return bradians;
	}

	public static DiscreteDirection getDirection(Angle angle) {
		return getDirection(angle.getBradians());
	}

	public static DiscreteDirection getDirection(int bradians) {
		return values()[(bradians - FUDGE_FACTOR) / BRADIANS_GAP];
	}

}

