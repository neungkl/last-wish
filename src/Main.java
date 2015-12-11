import input.InputFlag;

import java.awt.Dimension;

import javax.swing.JFrame;

import base.GameScreen;

public class Main {
	public static void main(String[] args) {

		JFrame frame = new JFrame();
		GameScreen gameScreen = new GameScreen(frame);

		frame.add(gameScreen);
		frame.setPreferredSize(new Dimension(1024, 768));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setUndecorated(true);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.pack();
		frame.setVisible(true);
		
		System.out.println(frame.getWidth()+" "+frame.getHeight());

		InputFlag.reset();

		while (true) {
			try {
				Thread.sleep((long) (1000f / GameScreen.FRAMERATE));
			} catch (InterruptedException e) {
			}
			gameScreen.update();
			gameScreen.repaint();
			InputFlag.clearTrigger();
		}
	}
}
