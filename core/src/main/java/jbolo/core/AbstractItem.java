package jbolo.core;

/**
 * @author Mike Heath
 */
public abstract class AbstractItem implements Item {

	private MapSegment mapSegment;

	private Point position;

	private boolean visible = true;

	protected AbstractItem(MapSegment mapSegment, Point position) {
		this.mapSegment = mapSegment;
		this.position = position;
	}

	@Override
	public MapSegment getMapSegment() {
		return mapSegment;
	}

	@Override
	public Point getPosition() {
		return position;
	}

	@Override
	public boolean isVisible() {
		return visible;
	}

	protected void setPosition(Point position) {
		// TODO Trigger event that the position has been updated.
		this.position = position;
	}
}
