package tankus.system.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.JPanel;

public class ICanvas extends JPanel {

	private ArrayList<IRenderToCanvas> renders = new ArrayList<IRenderToCanvas>();

	public ICanvas(ArrayList<IRenderToCanvas> renders) {
		this.renders = renders;
	}

	private Image buffer;
	private Color backGround = Color.BLACK;

	public ICanvas() {
		renders = new ArrayList<IRenderToCanvas>();
	}

	@Override
	public void paint(Graphics g) {

		super.paint(g);
		if (buffer == null) {
			buffer = createImage(getWidth(), getHeight());
		}
		paintWorld(buffer.getGraphics());
		g.drawImage(buffer, 0, 0, null);

	}

	public void setSprites(ArrayList<IRenderToCanvas> sprites) {

		this.renders = sprites;
	}

	public void paintWorld(Graphics g) {

		g.clearRect(0, 0, getWidth(), getHeight());
		g.setColor(backGround);
		g.fillRect(0, 0, getWidth(), getHeight());

		for (int x = 0; x < renders.size(); x++) {
			renders.get(x).render(g);
		}

	}

	public void deleteRenders() {
		renders.clear();
	}

	public void addRender(IRenderToCanvas sprite) {
		renders.add(sprite);

	}
}
