package jbolo.core;

/**
 * @author Mike Heath
 */
public class MovableItem extends AbstractItem {

	// Velocity in pixels / ms
	private float velocity;

	private Angle angle;

	private long lastTime;

	public MovableItem(int id, MapSegment mapSegment, Point position, float velocity, int bradians) {
		super(id, mapSegment, position);
		this.velocity = velocity;
		this.angle = Angle.createAngle(bradians);
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
		setPosition(getPosition().move(angle, v));
	}

}
