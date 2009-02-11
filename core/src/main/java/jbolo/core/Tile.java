package jbolo.core;

/**
 * The smallest unit of a map.
 *
 * @author Mike Heath
 */
public interface Tile {

	MapSegment getMapSegment();

	Point getPosition();

}
