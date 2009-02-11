package jbolo.core;

import org.testng.annotations.Test;
import org.testng.Assert;

/**
 * @author Mike Heath
 */
public class AngleTest {

	@Test
	public void radians() {
		Assert.assertEquals(new Angle(0.0).getBradians(), DiscreteDirection.EAST.getBradians());
		Assert.assertEquals(new Angle(Math.PI / 2).getBradians(), DiscreteDirection.NORTH.getBradians());
		Assert.assertEquals(new Angle(Math.PI).getBradians(), DiscreteDirection.WEST.getBradians());
		Assert.assertEquals(new Angle(3 * Math.PI / 2).getBradians(), DiscreteDirection.SOUTH.getBradians());
	}

}
