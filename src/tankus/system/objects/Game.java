package tankus.system.objects;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public final class Game implements Runnable {

	public Game() {
		// TODO Auto-generated constructor stub
	}

	private int playerPosX = 0;
	private int playerPosY = 0;
	private ICanvas icanvas;
	private ArrayList<Sprite> sprites = new ArrayList<Sprite>();
	private World world;
	private Player player;
	private int array[] = new int[700];
	private int array2[] = new int[700];
	private int[][] worldArray = null;
	private boolean running = true;
	private int windowSizeX = 700;
	private int windowSizeY = 730;

	public void start() {
		initGameObjects();
		new Thread(this).start();
	}

	public int getWindowX() {
		return windowSizeX;
	}

	public int getWindowY() {
		return windowSizeY;
	}

	private void initGameObjects() {
		icanvas = new ICanvas();
		world = new World();
		worldArray = world.getMap(World.BIG_MAP);
		player = new Player(5, 5, "Kelly Bailey");

		setListener();

	}

	private boolean accessMove() {
		int left, right, top, down;
		boolean isWalkable = true;
		// верх и низ
		// Находим вероятные точки плиток с учетом направления directionY
		left = (int) Math.ceil((player.posX) / Tile.SIZE);
		right = (int) Math.floor((player.posX + player.WIDTH - 1) / Tile.SIZE);
		top = (int) Math.ceil((player.posY + player.speed * player.directionY) / Tile.SIZE);
		down = (int) Math.floor((player.posY + player.HEIGHT + player.speed * player.directionY - 1) / Tile.SIZE);
		// проверяем доступность направления по вершине правой и левой сверх
		// (низу) - на тот случай если игрок находится вне начала плитки по оси
		// Х
		if (player.directionY == -1 && !(tileIsWalkable(left, top) && tileIsWalkable(right, top))) {
			isWalkable = false;
		} else if (player.directionY == 1 && !(tileIsWalkable(left, down) && tileIsWalkable(right, down))) {
			isWalkable = false;
		}
		// право и лево
		// Находим вероятные точки плиток с учетом направления directionX
		left = (int) Math.ceil((player.posX + player.speed * player.directionX) / Tile.SIZE);
		right = (int) Math.floor((player.posX + player.WIDTH + player.speed * player.directionX - 1) / Tile.SIZE);
		top = (int) Math.ceil((player.posY) / Tile.SIZE);
		down = (int) Math.floor((player.posY + player.HEIGHT - 1) / Tile.SIZE);
		// проверяем доступность направления по вершине верха и низа лева
		// (права)- на тот случай если игрок находится вне начала плитки по оси
		// Y
		if (player.directionX == -1 && !(tileIsWalkable(left, top) && tileIsWalkable(left, down))) {
			isWalkable = false;
		} else if (player.directionX == 1 && !(tileIsWalkable(right, top) && tileIsWalkable(right, down))) {
			isWalkable = false;
		}
		return isWalkable;
	}

	private boolean tileIsWalkable(int x, int y) {
		Tile tile = Tile.getTileById(world.getTileId(x, y));
		return (tile != null && tile.getWalkable());
	}

	private KeyAdapter keyListener = new KeyAdapter() {
		@Override
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == e.VK_LEFT) {
				player.setDirectionX(player.LEFT);
			}
			if (e.getKeyCode() == e.VK_RIGHT) {
				player.setDirectionX(player.RIGHT);
			}
			if (e.getKeyCode() == e.VK_UP) {
				player.setDirectionY(player.UP);
			}
			if (e.getKeyCode() == e.VK_DOWN) {
				player.setDirectionY(player.DOWN);
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
			if (e.getKeyCode() == 37 || e.getKeyCode() == 39) {
				player.setDirectionX(Player.STAY);
			}
			if (e.getKeyCode() == 38 || e.getKeyCode() == 40) {
				player.setDirectionY(Player.STAY);
			}
		}
	};

	public ICanvas getCanvas() {
		return icanvas;
	}

	private void delay(int millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void initRenders() {

		int mapW = world.getWidth(), mapH = world.getHeight(), x, y, tileId;
		for (y = 0; y < mapH; y++) {
			for (x = 0; x < mapW; x++) {
				tileId = world.getTileId(x, y);

				if (tileId == 1) {
					icanvas.addRender(
							new Tile(x * Tile.SIZE, y * Tile.SIZE, "src//game//system//assets//brick1.png", false));

				}
				if (tileId == 2) {
					icanvas.addRender(
							new Tile(x * Tile.SIZE, y * Tile.SIZE, "src//game//system//assets//sand.png", true));

				}
				if (tileId == 3) {
					icanvas.addRender(
							new Tile(x * Tile.SIZE, y * Tile.SIZE, "src//game//system//assets//water.png", true));

				}
				if (tileId == 4) {
					icanvas.addRender(
							new Tile(x * Tile.SIZE, y * Tile.SIZE, "src//game//system//assets//spawn.png", true));

				}
			}
		}

		icanvas.addRender(player);

		icanvas.repaint();

	}

	public void setListener() {
		icanvas.setFocusable(true);
		icanvas.addKeyListener(keyListener);
	}

	public void destroyListener() {
		icanvas.setFocusable(false);
		icanvas.removeKeyListener(keyListener);
	}

	@Override
	public void run() {
		while (true) {

			initRenders();
			accessMove();
			icanvas.repaint();
			delay(100);
			icanvas.deleteRenders();

		}
	}

	private boolean zone(int zoneX, int zoneY, int width, int height) {

		for (int x = zoneX; x < zoneX + width; x++) {
			array[x] = x;

			for (int y = zoneY; y < zoneY + height; y++) {

				array2[y] = y;

				for (int p = playerPosY; p < playerPosY + player.getSSize(); p++) {
					for (int m = playerPosX; m < playerPosX + player.getSSize(); m++) {
						if (array[x] == m & array[y] == p) {
							return true;

						}
					}
				}
			}
		}
		return false;

	}
}
