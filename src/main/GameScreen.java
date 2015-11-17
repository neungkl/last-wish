package main;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JComponent;

import render.AnimationImageManager;
import resource.ImageReader;

public class GameScreen extends JComponent {
	
	AnimationImageManager test;
	
	public AnimationImageManager read(String url) {
		return new AnimationImageManager(ImageReader.get(url));
	}
	
	public GameScreen() {
		test = read("assets/test.gif");
		test.loop();
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		test.update();
		test.draw((Graphics2D) g, 0, 0);
	}
}
