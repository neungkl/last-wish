import input.InputFlag;

import java.awt.Dimension;

import javax.swing.JFrame;

import base.GameScreen;

public class Main {
	public static void main(String[] args) {
		
		JFrame frame = new JFrame();
		GameScreen g = new GameScreen();
		
		frame.add(g);
		frame.setPreferredSize(new Dimension(500,500));
		frame.pack();
		frame.setVisible(true);
		
		InputFlag.reset();
		
		while(true) {
			try {
				Thread.sleep(1000);
			} catch(Exception e) {
				
			}
			g.update();
			g.repaint();
			InputFlag.clearTrigger();
		}
	}
}
