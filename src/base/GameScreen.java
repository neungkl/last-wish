package base;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JComponent;

import frame.Frame;
import frame.GameFrame;

public class GameScreen extends JComponent {
	
	Frame game;
	
	public GameScreen() {
		game = new GameFrame();
	}
	
	public void update() {
		game.update();
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2d = (Graphics2D) g;
		game.draw(g2d);
	}
}
