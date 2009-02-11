package jbolo.core;

/**
 * @author Mike Heath
 */
public class Point {
	public final float x;
	public final float y;

	public Point(float x, float y) {
		this.x = x;
		this.y = y;
	}

	public Angle angleTo(Point point) {
		return angleTo(point.x, point.y);
	}

	private Angle angleTo(float x, float y) {
		// Reverse direction of y because origin is at upper left
		float dY = y - this.y;
		float dX = this.x - x;
		double radians = Math.atan2(dY, dX) + Math.PI;
		return new Angle(radians);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Point point = (Point) o;
		return x == point.x && y == point.y;
	}

	@Override
	public int hashCode() {
		return 31 * (int)x + (int)y;
	}

	@Override
	public String toString() {
		return "X: " + x + " Y:" + y;
	}
}
