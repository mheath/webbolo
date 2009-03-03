package jbolo.core;

/**
 * @author Mike Heath
 */
public final class Coordinate {

	public final int x;
	public final int y;

	public Coordinate(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Coordinate that = (Coordinate) o;

		return x == that.x && y == that.y;
	}

	@Override
	public int hashCode() {
		return 31 * x + y;
	}
}
