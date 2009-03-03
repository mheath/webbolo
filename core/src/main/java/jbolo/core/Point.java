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

	public Angle angleTo(float x, float y) {
		// Reverse direction of y because origin is at upper left
		float dY = y - this.y;
		float dX = this.x - x;
		// The angle is "upside down" because we changed the sign of dY, so rotate the angle 180 degrees
		double radians = Math.atan2(dY, dX) + Math.PI;
		return Angle.createAngleFromRadians(radians);
	}

	public float distance(Point point) {
		return distance(point.x, point.y);
	}

	public float distance(float x, float y) {
		float dX = x - this.x;
		float dY = y - this.y;
		return (float)Math.sqrt(dX * dX + dY * dY);
	}

	public Point move(DiscreteDirection direction, float distance) {
		return move(Angle.bradiansToRadians(direction.getBradians()), distance);
	}

	public Point move(Angle angle, float distance) {
		return move(angle.getRadians(), distance);
	}

	public Point move(double radians, float distance) {
		double dX = distance * Math.cos(radians);
		double dY = distance * -Math.sin(radians);

		return new Point(x + (float)dX, y + (float)dY);
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
