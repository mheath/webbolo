package jbolo.core.bolo;

import org.testng.annotations.Test;
import org.testng.Assert;
import jbolo.core.bolo.BoloMapLoader;

import java.io.InputStream;
import java.io.IOException;

/**
 * @author Mike Heath
 */
public class BoloMapLoaderTest {

	@Test
	public void loadBoloMap1() throws Exception {
		BoloMap map = loadMap("maps/test1.map");

		Assert.assertEquals(map.getBaseCount(), 0);
		Assert.assertEquals(map.getPillboxCount(), 0);
		Assert.assertEquals(map.getStartingPositionCount(), 1);
	}

	@Test
	public void loadBoloMap2() throws Exception {
		BoloMap map = loadMap("maps/test2.map");

		Assert.assertEquals(map.getBaseCount(), 0);
		Assert.assertEquals(map.getPillboxCount(), 0);
		Assert.assertEquals(map.getStartingPositionCount(), 1);

		Assert.assertEquals(map.get(128, 128), null);
		Assert.assertEquals(map.get(129, 128), BoloMap.Terrain.GRASS);
		Assert.assertEquals(map.get(130, 128), null);
	}

	@Test
	public void loadBoloMap3() throws Exception {
		BoloMap map = loadMap("maps/test3.map");

		Assert.assertEquals(map.getBaseCount(), 0);
		Assert.assertEquals(map.getPillboxCount(), 0);
		Assert.assertEquals(map.getStartingPositionCount(), 1);

		Assert.assertEquals(map.get(128, 128), null);
		Assert.assertEquals(map.get(129, 128), BoloMap.Terrain.BUILDING);
		Assert.assertEquals(map.get(130, 128), BoloMap.Terrain.RIVER);
		Assert.assertEquals(map.get(131, 128), BoloMap.Terrain.SWAMP);
		Assert.assertEquals(map.get(132, 128), BoloMap.Terrain.CRATER);
		Assert.assertEquals(map.get(133, 128), BoloMap.Terrain.ROAD);
		Assert.assertEquals(map.get(134, 128), BoloMap.Terrain.FOREST);
		Assert.assertEquals(map.get(135, 128), BoloMap.Terrain.RUBBLE);
		Assert.assertEquals(map.get(136, 128), BoloMap.Terrain.GRASS);
		Assert.assertEquals(map.get(137, 128), BoloMap.Terrain.SHOT_BUILDING);
		Assert.assertEquals(map.get(138, 128), BoloMap.Terrain.RIVER_WITH_BOAT);
		Assert.assertEquals(map.get(139, 128), null);
	}

	private BoloMap loadMap(String name) throws IOException {
		ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
		InputStream stream = contextClassLoader.getResourceAsStream(name);
		Assert.assertNotNull(stream, "Could not find test map " + name);
		return new BoloMapLoader().loadBoloMap(stream);
	}

}
