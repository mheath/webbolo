package webbolo.client;

import com.google.gwt.user.client.ui.Image;

/**
 * @author Mike Heath
 */
public enum Sprite {
	BASE(10, 1),
	WALL(6, 1),
	GRASS(2, 1),
	CROSS_HAIR(17, 4),
	PILLBOX(15,7),
	ROAD_0(0, 0),
	ROAD_1(1, 0),
	ROAD_2(2, 0),
	ROAD_3(3, 0),
	ROAD_4(4, 0),
	ROAD_5(5, 0),
	TANK_0(0, 4),
	TANK_1(1, 4),
	TANK_2(2, 4),
	TANK_3(3, 4),
	TANK_4(4, 4),
	TANK_5(5, 4),
	TANK_6(6, 4),
	TANK_7(7, 4),
	TANK_8(8, 4),
	TANK_9(9, 4),
	TANK_10(10, 4),
	TANK_11(11, 4),
	TANK_12(12, 4),
	TANK_13(13, 4),
	TANK_14(14, 4),
	TANK_15(15, 4),
	TREE_0(9, 9),
	TREE_1(15, 9),
	TREE_2(10, 9),
	TREE_3(13, 9),
	TREE_4(8, 9),
	TREE_5(14, 9),
	TREE_6(12, 9),
	TREE_7(16, 9),
	TREE_8(11, 9),
	TREE_9(3, 1)
	;

	public static final Sprite[] TANKS = new Sprite[] {
			TANK_0, TANK_1, TANK_2, TANK_3, TANK_4, TANK_5, TANK_6, TANK_7,
			TANK_8, TANK_9, TANK_10, TANK_11, TANK_12, TANK_13, TANK_14, TANK_15
	};

	public static final Sprite[] TREES = new Sprite[] {
			TREE_0, TREE_1, TREE_2, TREE_3, TREE_4, TREE_5, TREE_6, TREE_7, TREE_8, TREE_9
	};

	public static final int WIDTH = 16;
	public static final int HEIGHT = 16;

	private final int x;
	private final int y;

	private Sprite(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public Image getImage() {
		return new Image("tiles.png", x * WIDTH, y * HEIGHT, WIDTH, HEIGHT);
	}
}
