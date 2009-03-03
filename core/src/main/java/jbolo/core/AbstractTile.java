package jbolo.core;

/**
 * @author Mike Heath
 */
public abstract class AbstractTile extends AbstractItem implements Tile {

	protected AbstractTile(long id, MapSegment mapSegment, Point position) {
		super(id, mapSegment, position);
	}

	@Override
	public void update(long time) {
	}
}
