package jbolo.core;

import org.testng.annotations.Test;
import org.testng.Assert;

/**
 * @author Mike Heath
 */
public class PointTest {

	@Test
	public void angleTo() {
		Point a = new Point(0, 0);
		Point b = new Point(1, 0);
		Point c = new Point(0, 1);
		Point d = new Point(1, 1);

		Assert.assertEquals(a.angleTo(b).getBradians(), DiscreteDirection.EAST.getBradians(), "b is east of a");
		Assert.assertEquals(c.angleTo(d).getBradians(), DiscreteDirection.EAST.getBradians(), "d is east of c");

		Assert.assertEquals(c.angleTo(a).getBradians(), DiscreteDirection.NORTH.getBradians(), "a is north of c");
		Assert.assertEquals(d.angleTo(b).getBradians(), DiscreteDirection.NORTH.getBradians(), "b is north of d");

		Assert.assertEquals(a.angleTo(c).getBradians(), DiscreteDirection.SOUTH.getBradians(), "c is south of a");
		Assert.assertEquals(b.angleTo(d).getBradians(), DiscreteDirection.SOUTH.getBradians(), "d is south of b");

		Assert.assertEquals(b.angleTo(a).getBradians(), DiscreteDirection.WEST.getBradians(), "a is west of b");
		Assert.assertEquals(d.angleTo(c).getBradians(), DiscreteDirection.WEST.getBradians(), "c is west of d");

		Assert.assertEquals(a.angleTo(d).getBradians(), DiscreteDirection.SOUTH_EAST.getBradians());
		Assert.assertEquals(b.angleTo(c).getBradians(), DiscreteDirection.SOUTH_WEST.getBradians());
		Assert.assertEquals(d.angleTo(a).getBradians(), DiscreteDirection.NORTH_WEST.getBradians());
		Assert.assertEquals(c.angleTo(b).getBradians(), DiscreteDirection.NORTH_EAST.getBradians());
	}

}
