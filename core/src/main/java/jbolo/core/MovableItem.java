package jbolo.core;

/**
 * @author Mike Heath
 */
public class MovableItem extends AbstractItem {

	private float velocity;

	private Angle angle;

	private long lastTime;

	public MovableItem(MapSegment mapSegment, Point position, float velocity, int bradians) {
		super(mapSegment, position);
		this.velocity = velocity;
		this.angle = new Angle(bradians);
	}

	public float getVelocity() {
		return velocity;
	}

	public Angle getAngle() {
		return angle;
	}

	@Override
	public void update(long time) {
		long t = time - lastTime;
		float v = getVelocity() * t;
		Point delta = calculatePositionDelta(v);
		setPosition(new Point(getPosition().x + delta.x, getPosition().y + delta.y));
	}

	protected Point calculatePositionDelta(float adjustedVelocity) {
		return new Point(getAngle().cos() * adjustedVelocity, getAngle().sin() * adjustedVelocity);
	}

}
