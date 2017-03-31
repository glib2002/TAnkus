package tankus.system.objects;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public abstract class Unit implements IRenderToCanvas {

	protected Image image;

	protected int posX = 0;
	protected int posY = 0;

	public Unit() {
		// TODO Auto-generated constructor stub
	}

	public Image init(String path) {
		try {
			image = ImageIO.read(new File(path));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return image;
	}

	public void setPosition(int x, int y) {
		this.posX = x;
		this.posY = y;
	}

	public int getPosY() {
		return posY;
	}

	public int getPosX() {
		return posX;
	}

}
