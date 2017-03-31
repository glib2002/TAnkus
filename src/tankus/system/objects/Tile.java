package tankus.system.objects;

import java.awt.Graphics;

public class Tile extends Unit {

	private String path;
	private boolean walkable;
	private int [] bottom;
	private int [] up;
	private int [] left;
	private int [] right;

	public static int SIZE = 36;

	public Tile(int x, int y, String path, boolean walkable) {
		setPosition(x, y);
		this.path = path;
		init(path);
		bottom = new int[x+SIZE];
	}

	public boolean getWalkable() {
		return walkable;
	}

	public String getPath() {
		return path;
	}

	public static Tile getTileById(int id) {
		// TODO remake paths
		if (id == 1) {
			return new Tile(0, 0, "src\\game\\system\\assets\\brick1.png", false);
		}
		if (id == 2) {
			return new Tile(0, 0, "src\\game\\system\\assets\\sand.png", true);
		}
		if (id == 3) {
			return new Tile(0, 0, "src\\game\\system\\assets\\water.png", true);
		}
		if (id == 4) {
			return new Tile(0, 0, "src\\game\\system\\assets\\spawn.png", true);
		}
		return null;
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(image, getPosX(), getPosY(), SIZE, SIZE, null);
	}
}
