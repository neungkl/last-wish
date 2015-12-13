package main;
import input.InputFlag;

import java.awt.Dimension;

import javax.swing.JFrame;

import base.GameScreen;

public class Main {
	
	private static JFrame frame; 
	
	public static void main(String[] args) {

		JFrame frame = new JFrame();
		GameScreen gameScreen = new GameScreen();

		frame.setPreferredSize(new Dimension(1366, 768));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//frame.setUndecorated(true);
		//frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setResizable(false);
		frame.add(gameScreen);
		frame.pack();
		frame.setVisible(true);

		gameScreen.updateScreenSize();

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
	
	public static JFrame getFrame() {
		return frame;
	}
}
