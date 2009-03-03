package jbolo.core.bolo;

import jbolo.core.DiscreteDirection;

import java.io.IOException;
import java.io.InputStream;

/**
 * This class loads a map in the classic Bolo format.
 *
 *
 * @author Mike Heath
 */
public class BoloMapLoader {

	public BoloMap loadBoloMap(InputStream in) throws IOException {
		// Read header
		byte[] preamble = new byte[8];
		if (in.read(preamble) != preamble.length) {
			throw new IllegalStateException("Invalid Bolo map");
		}
		if (!"BMAPBOLO".equals(new String(preamble))) {
			throw new IllegalStateException("Invalid Bolo map preamble");
		}
		int version = in.read();
		if (version != 1) {
			throw new IllegalStateException("Unkown map version: " + version);
		}
		int pillboxCount = in.read();
		int baseCount = in.read();
		int startPositionCount = in.read();

		BoloMap.Pillbox[] pillboxes = loadPillboxes(in, pillboxCount);
		BoloMap.Base[] bases = loadBases(in, baseCount);
		BoloMap.StartingPosition[] startingPositions = loadStartingPoints(in, startPositionCount);

		final BoloMap map = new BoloMap(startingPositions, bases, pillboxes);

		// Load terrain data
		while (true) {
			// Read body
			int runLength = in.read();
			int y = in.read();
			int startx = in.read();
			int endx = in.read();

			if (runLength == 4 && y == 0xff && startx == 0xff && endx == 0xff) {
				break;
			}

			int x = startx;
			NibbleStream nibbleStream = new NibbleStream(in, runLength - 4);
			while (nibbleStream.hasNext()) {
				// Length is stored in first nibble
				int length = nibbleStream.next();

				if (length < 8) {
					// If length is less than 8, it's a sequence of distinct tiles
					length++;
					for (int i = 0; i < length; i++) {
						int tile = nibbleStream.next();
						map.set(x++, y, BoloMap.Terrain.values()[tile]);
					}
				} else {
					// If length is 8 or higher, it's a sequence of identical tiles
					length -= 6;
					int tile = nibbleStream.next();
					BoloMap.Terrain terrain = BoloMap.Terrain.values()[tile];
					for (int i = 0; i < length; i++) {
						map.set(x++, y, terrain);
					}
				}
			}
		}

		return map;
	}

	BoloMap.Pillbox[] loadPillboxes(InputStream in, int pillboxCount) throws IOException {
		BoloMap.Pillbox[] pillboxes = new BoloMap.Pillbox[pillboxCount];
		// Load pill info
		for (int i = 0; i < pillboxCount; i++) {
			int x = in.read();
			int y = in.read();
			int owner = in.read();
			int armor = in.read();
			int speed = in.read();
			pillboxes[i] = new BoloMap.Pillbox(x, y, owner, armor, speed);
		}
		return pillboxes;
	}

	BoloMap.Base[] loadBases(InputStream in, int baseCount) throws IOException {
		BoloMap.Base[] bases = new BoloMap.Base[baseCount];
		// Load base info
		for (int i = 0; i < baseCount; i++) {
			int x = in.read();
			int y = in.read();
			int owner = in.read();
			int armor = in.read();
			int shells = in.read();
			int mines = in.read();
			bases[i] = new BoloMap.Base(x, y, owner, armor, mines, shells);
		}
		return bases;
	}

	BoloMap.StartingPosition[] loadStartingPoints(InputStream in, int startPositionCount) throws IOException {
		BoloMap.StartingPosition[] startingPositions = new BoloMap.StartingPosition[startPositionCount];
		// Loading starting point info
		for (int i = 0; i < startPositionCount; i++) {
			int x = in.read();
			int y = in.read();
			int direction = in.read();
			DiscreteDirection d = DiscreteDirection.values()[(direction) & 0x0f];

			startingPositions[i] = new BoloMap.StartingPosition(x, y, d);
		}
		return startingPositions;
	}

	/**
	 * Loads one nibble at a time from an {@code java.io.InputStream}.
	 */
	static class NibbleStream {
		private final InputStream in;
		private int length;
		private boolean lowNibble = false;
		private int current;

		/**
		 * Creates a new {@code NibbleStream}
		 *
		 * @param in  the {@code InputStream} to read from
		 * @param length  the number of bytes to read
		 */
		public NibbleStream(InputStream in, int length) {
			this.in = in;
			this.length = length;
		}

		/**
		 * Fetches the next nibble.
		 *
		 * @return the next nibble in the stream
		 * @throws IOException  thrown if an error with the {@code InputStream} occurs
		 */
		public int next() throws IOException {
			if (!lowNibble) {
				if (length == 0) {
					throw new IllegalStateException("Buffer overrun");
				}
				current = in.read();
				if (current < 0) {
					throw new IllegalStateException("Expected more data loading the map");
				}
				length--;
				lowNibble = true;
				return current >> 4;
			}
			lowNibble = false;
			return current & 0x0f;
		}

		/**
		 * Indicates if there's more data to read.  Assumes the stream has data to return.
		 *
		 * @return true if there's more data to read, false other wise.
		 */
		public boolean hasNext() {
			return length != 0;
		}

	}

}
