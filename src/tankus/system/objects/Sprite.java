package tankus.system.objects;

import java.awt.Graphics;

public class Sprite extends Unit {

	protected int directionX = 0;
	protected int directionY = 0;

	protected int speed = 5;
	protected int ssize;

	public static final int ATACK = 1;
	public static final int IGNORING = -1;
	public static final int GOING = 0;

	public static final int LEFT = -1;
	public static final int RIGHT = 1;
	public static final int UP = -1;
	public static final int DOWN = 1;
	public static final int STAY = 0;
	public static final int WIDTH = 26;
	public static final int HEIGHT = 26;

	public Sprite() {
		// TODO Auto-generated constructor stub
	}

	public int getSSize() {
		return ssize;
	}

	// TODO remake paths
	protected void update() {
		if (getDirectionX() == 1) {

			setPosition(getPosX() + 20, getPosY());
			init("src\\game\\system\\assets\\tankright.png");
		}
		if (getDirectionX() == -1) {

			setPosition(getPosX() - 20, getPosY());
			init("src\\game\\system\\assets\\tankleft.png");
		}
		if (getDirectionY() == 1) {

			setPosition(getPosX(), getPosY() + 20);
			init("src\\game\\system\\assets\\tankdown.png");
		}
		if (getDirectionY() == -1) {

			setPosition(getPosX(), getPosY() - 20);
			init("src\\game\\system\\assets\\tankup.png");
		}

	}

	@Override
	public void render(Graphics g) {
		update();

		g.drawImage(image, getPosX(), getPosY(), HEIGHT, WIDTH, null);

	}

	public void setDirectionX(int direction) {
		this.directionX = direction;
	}

	public void setDirectionY(int direction) {
		this.directionY = direction;
	}

	public int getDirectionX() {
		return directionX;
	}

	public int getDirectionY() {
		return directionY;
	}

	public int getSpeed() {
		return speed;
	}

}
