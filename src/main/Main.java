package main;
import input.InputFlag;

import java.awt.Dimension;

import javax.swing.JFrame;

import essential.GameScreen;

public class Main {
	
	private static JFrame frame; 
	
	public static void main(String[] args) {

		JFrame frame = new JFrame();
		GameScreen gameScreen = new GameScreen();

		frame.setPreferredSize(new Dimension(1366, 768));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.add(gameScreen);
		frame.pack();
		frame.setVisible(true);
		frame.setTitle("The Last Wish");

		gameScreen.updateScreenSize();
		
		InputFlag.reset();

		while (true) {
			try {
				Thread.sleep(1000 / GameScreen.FRAMERATE);
			} catch (InterruptedException e) {
			}
			
			gameScreen.update();
			gameScreen.repaint();
			
			InputFlag.clearTrigger();
			
			gameScreen.requestFocus();
		}
	}
	
	public static JFrame getFrame() {
		return frame;
	}
}
