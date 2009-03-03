package jbolo.core;

/**
 * An item in the Bolo world such as a Tank, a shell, a pillbox, etc.
 *
 * @author Mike Heath
 */
public interface Item {

	/**
	 * The unique identifier for the item.
	 *
	 * @return  the item's unique identifier.
	 */
	long getId();

	/**
	 * The MapSegment the item belongs to.
	 *
	 * @return the MapSegment the item belongs to.
	 */
	MapSegment getMapSegment();

	/**
	 * Invoked periodically so the item can update its state.
	 *
	 * @param time  the time to update the item to
	 */
	void update(long time);

	/**
	 * The location of the item relative to the MapSegment.
	 *
	 * @return the location of the item
	 */
	Point getPosition();

	/**
	 * Whether or not the item is visible (i.e. is the item hiding in trees)
	 *
	 * @return  whether or not the item is visible.
	 */
	boolean isVisible();

}
