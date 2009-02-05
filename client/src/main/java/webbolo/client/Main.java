package webbolo.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.KeyboardListener;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.EventPreview;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Main implements EntryPoint {

	private class Tank {
		private double angle;
		private double velocity;

		private double x = 28;
		private double y = 27;

		private int direction;

		private int range = 40;

		private int acceleration;
	}

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		final int width = 16*16;
		final int height = 16*16;

		final Tank tank = new Tank();

		Image tiles = new Image("tiles.png");

		final AbsolutePanel panel = new AbsolutePanel();

		for (int x = 0; x < 16; x++) {
			for (int y = 0; y < 16; y++) {
				addTile(panel, Sprite.GRASS, x, y);
			}
		}

		addTile(panel, Sprite.TREES[5], 0, 0);
		addTile(panel, Sprite.TREES[8], 1, 0);

		addTile(panel, Sprite.TREES[8], 0, 1);
		addTile(panel, Sprite.WALL, 3, 1);
		addTile(panel, Sprite.ROAD_2, 7, 1);
		addTile(panel, Sprite.ROAD_0, 8, 1);
		addTile(panel, Sprite.ROAD_0, 9, 1);
		addTile(panel, Sprite.ROAD_3, 10, 1);

		addTile(panel, Sprite.BASE, 3, 2);
		addTile(panel, Sprite.WALL, 4, 2);
		addTile(panel, Sprite.ROAD_1, 7, 2);
		addTile(panel, Sprite.ROAD_1, 10, 2);

		addTile(panel, Sprite.TREE_9, 4, 3);
		addTile(panel, Sprite.ROAD_1, 7, 3);
		addTile(panel, Sprite.ROAD_1, 10, 3);
		addTile(panel, Sprite.PILLBOX, 13, 3);

		addTile(panel, Sprite.PILLBOX, 1, 4);
		addTile(panel, Sprite.TREE_9, 3, 4);
		addTile(panel, Sprite.ROAD_5, 7, 4);
		addTile(panel, Sprite.ROAD_3, 8, 4);
		addTile(panel, Sprite.ROAD_2, 9, 4);
		addTile(panel, Sprite.ROAD_4, 10, 4);

		addTile(panel, Sprite.TREE_9, 2, 5);
		addTile(panel, Sprite.ROAD_5, 8, 5);
		addTile(panel, Sprite.ROAD_4, 9, 5);

		panel.setSize(Integer.toString(width), Integer.toString(height));

		final SimplePanel tankWidget = new SimplePanel();
		panel.add(tankWidget, (int)tank.x - 8, (int)tank.y - 8);
		tankWidget.setWidget(Sprite.TANK_0.getImage());

		final SimplePanel crossHair = new SimplePanel();
		panel.add(crossHair, 0, 0);
		crossHair.setWidget(Sprite.CROSS_HAIR.getImage());

		RootPanel.get().add(panel);

		final Image[] tankImages = new Image[16];
		for (int i = 0; i < 16; i++) {
			tankImages[i] = Sprite.TANKS[i].getImage();
		}

		Timer timer = new Timer() {
			private static final double TWO_PI = Math.PI * 2;
			private static final double FUDGE_FACTOR = TWO_PI / (16 * 2);
			int oldIndex = 0;

			public void run() {
				tank.angle += 0.06 * tank.direction;
				if (tank.angle < 0) tank.angle += TWO_PI;
				if (tank.angle >= TWO_PI) tank.angle -= TWO_PI;
				int i = (int)((tank.angle + FUDGE_FACTOR) / TWO_PI * 16 );
				if (i > 15) i = 0;
				//System.out.println("Angle: " + tank.angle + " i: " + i);
				if (i != oldIndex) {
					oldIndex = i;
					tankWidget.setWidget(tankImages[i]);
				}

				tank.velocity += 0.04 * tank.acceleration;
				if (tank.velocity > 3) tank.velocity = 3;
				if (tank.velocity < 0) tank.velocity = 0;

				tank.x = tank.velocity * Math.sin(tank.angle) + tank.x;
				tank.y = tank.velocity * -Math.cos(tank.angle) + tank.y;
				if (tank.x < 0) tank.x = 0;
				if (tank.y < 0) tank.y = 0;
				if (tank.x > width) tank.x = width;
				if (tank.y > height) tank.y = height;
				panel.setWidgetPosition(tankWidget, (int) tank.x - 8, (int)tank.y - 8);

				int x = (int)(tank.range * Math.sin(tank.angle) + tank.x);
				int y = (int)(tank.range * -Math.cos(tank.angle) + tank.y);
				panel.setWidgetPosition(crossHair, x - 8, y - 8);
			}
		};
		timer.scheduleRepeating(25);

		Event.addEventPreview(new EventPreview() {
			public boolean onEventPreview(Event event) {
				switch (event.getTypeInt()) {
					case Event.ONKEYDOWN:
						switch (event.getKeyCode()) {
							case KeyboardListener.KEY_LEFT:
								tank.direction = -1;
								return false;
							case KeyboardListener.KEY_RIGHT:
								tank.direction = 1;
								return false;
							case KeyboardListener.KEY_UP:
								tank.acceleration = 1;
								return false;
							case KeyboardListener.KEY_DOWN:
								tank.acceleration = -1;
								return false;

						}
						break;
					case Event.ONKEYUP:
						switch (event.getKeyCode()) {
							case KeyboardListener.KEY_LEFT:
							case KeyboardListener.KEY_RIGHT:
								tank.direction = 0;
								return false;
							case KeyboardListener.KEY_UP:
							case KeyboardListener.KEY_DOWN:
								tank.acceleration = 0;
								return false;
						}
						break;
					case Event.ONMOUSEWHEEL:
						tank.range -= event.getMouseWheelVelocityY();
						if (tank.range < 20) {
							tank.range = 20;
						} else if (tank.range > 100) {
							tank.range = 100;
						}

						return false;
				}
				return true;
			}
		});
	}

	private void addTile(AbsolutePanel panel, Sprite grass, int x, int y) {
		panel.add(grass.getImage(), x * Sprite.WIDTH, y * Sprite.HEIGHT);
	}
}
