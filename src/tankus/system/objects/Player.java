package tankus.system.objects;

public class Player extends Sprite {

	private String name = NAME;

	public final static String NAME = "PLAYER";

	public Player(int x, int y, String name) {
		setPosition(x, y);
		this.name = name;
	}

	public Player(String name) {
		this.name = name;
	}

	public Player() {

	}

}
