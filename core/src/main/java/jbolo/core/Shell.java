package jbolo.core;

/**
 * @author Mike Heath
 */
public class Shell extends MovableItem {

	private final Item source;

	public Shell(Item source, float velocity, int bradians) {
		// TODO The initial position should be aware from the source, we need to figure out a good mechanism for doing that
		super(source.getMapSegment(), source.getPosition(), velocity, bradians);
		this.source = source;
	}

	public Item getSource() {
		return source;
	}
}
