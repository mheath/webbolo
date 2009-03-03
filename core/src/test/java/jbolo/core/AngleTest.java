package jbolo.core;

import org.testng.annotations.Test;
import org.testng.Assert;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;

/**
 * @author Mike Heath
 */
public class AngleTest {

	@Test
	public void radians() {
		Assert.assertEquals(Angle.createAngleFromRadians(0.0).getBradians(), DiscreteDirection.EAST.getBradians());
		Assert.assertEquals(Angle.createAngleFromRadians(Math.PI / 2).getBradians(), DiscreteDirection.NORTH.getBradians());
		Assert.assertEquals(Angle.createAngleFromRadians(Math.PI).getBradians(), DiscreteDirection.WEST.getBradians());
		Assert.assertEquals(Angle.createAngleFromRadians(3 * Math.PI / 2).getBradians(), DiscreteDirection.SOUTH.getBradians());
	}

	@Test
	public void serialization() throws Exception {
		Angle a = Angle.createAngle(0);
		Angle b = Angle.createAngle(0xff);
		Angle c = Angle.createAngle(42);

		Assert.assertEquals(a, serializeAndDeserialize(a));
		Assert.assertEquals(b, serializeAndDeserialize(b));
		Assert.assertEquals(c, serializeAndDeserialize(c));
	}

	private Angle serializeAndDeserialize(Angle a) throws Exception {
		// Create output streams
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(out);

		// Serialize angle
		oos.writeObject(a);
		oos.flush();

		// Create input streams for deserialization
		ByteArrayInputStream in = new ByteArrayInputStream(out.toByteArray());
		ObjectInputStream ois = new ObjectInputStream(in);

		return (Angle) ois.readObject();
	}
}
