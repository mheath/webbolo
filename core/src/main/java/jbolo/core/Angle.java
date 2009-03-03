package jbolo.core;

import java.io.Serializable;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * Stores an angle in byte radians (bradians).  A bradian is kind of like a degree except that it's range is 0 - 255.
 * 
 * @author Mike Heath
 */
public final class Angle implements Serializable {

	private static final long serialVersionUID = 1;

	public static final int BRADIANS_MAX = 256;
	public static final int DEGREES_MAX = 360;

	public static final double RADIAN_TO_BRADIANS = (double) BRADIANS_MAX / (Math.PI * 2);
	public static final double DEGREES_TO_BRADIANS = (double) BRADIANS_MAX / DEGREES_MAX;
	public static final double BRADIANS_TO_RADIANS = (Math.PI * 2) / (double) BRADIANS_MAX;
	public static final double BRADIANS_TO_DEGREES = (double) DEGREES_MAX / BRADIANS_MAX;

	private static float[] sinTable = new float[BRADIANS_MAX];
	private static float[] cosTable = new float[BRADIANS_MAX];

	static {
		for (int i = 0; i < BRADIANS_MAX; i++) {
			sinTable[i] = (float)Math.sin(bradiansToRadians(i));
			cosTable[i] = (float)Math.cos(bradiansToRadians(i));
		}
	}

	// The angle in byte radians (bradians)
	private transient int bradians;

	public static Angle createAngle(int bradians) {
		return createAngleFromRadians(bradians);
	}

	public static Angle createAngleFromRadians(double radians) {
		return new Angle(radiansToBradians(radians));
	}

	public static Angle createAngleFromDegrees(int degrees) {
		return new Angle (degreesToBradians(degrees));
	}

	private Angle(int bradians) {
		setBradians(bradians);
	}

	public int getBradians() {
		return bradians;
	}

	public void setBradians(int bradians) {
		this.bradians = 0xff & bradians;
	}

	public double getRadians() {
		return bradiansToRadians(bradians);
	}

	public float sin() {
		return sinTable[bradians];
	}

	public float cos() {
		return cosTable[bradians];
	}

	public static int radiansToBradians(double radians) {
		return (int) (radians * RADIAN_TO_BRADIANS);
	}

	public static double bradiansToRadians(int bradians) {
		return bradians * BRADIANS_TO_RADIANS;
	}

	public static int degreesToBradians(double degrees) {
		return (int) (degrees * DEGREES_TO_BRADIANS);
	}

	public static double bradiansToDegrees(int bradians) {
		return bradians * DEGREES_TO_BRADIANS;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Angle angle = (Angle) o;
		return bradians == angle.bradians;
	}

	@Override
	public int hashCode() {
		return bradians;
	}

	private void writeObject(ObjectOutputStream s) throws IOException {
		s.defaultWriteObject();
		s.writeByte((byte)bradians);
	}

	private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException  {
	    s.defaultReadObject();
		setBradians(s.readByte());
	}

}
