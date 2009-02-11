package jbolo.core;

/**
 * An item in the Bolo world such as a Tank, a shell, a pillbox, etc.
 *
 * @author Mike Heath
 */
public interface Item {

	MapSegment getMapSegment();

	void update(long time);

	Point getPosition();

	boolean isVisible();

}
