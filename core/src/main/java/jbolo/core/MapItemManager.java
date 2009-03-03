package jbolo.core;

/**
 * @author Mike Heath
 */
public interface MapItemManager {

	Item loadItem(long id);

	Item createItem(int type);

}
