package game.system.main;

import javax.swing.JFrame;

import tankus.system.objects.Game;

public class Main {

	public Main() {
		// TODO Auto-generated constructor stub
	}

	private static JFrame frame = new JFrame();
	private static Game game = new Game();

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		game.start();

		game.getCanvas().setBounds(0, 0, game.getWindowX(), game.getWindowY());

		frame.setSize(game.getWindowX(), game.getWindowY());
		frame.setVisible(true);
		frame.setLayout(null);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(game.getCanvas());

	}

}
