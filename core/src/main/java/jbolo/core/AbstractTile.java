package jbolo.core;

/**
 * @author Mike Heath
 */
public abstract class AbstractTile implements Tile {

	private final MapSegment mapSegment;

	private final Point position;

	public AbstractTile(MapSegment mapSegment, Point position) {
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
}
