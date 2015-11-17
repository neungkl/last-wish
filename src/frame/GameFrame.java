package frame;

import java.awt.Color;
import java.awt.Graphics2D;

import render.AnimationImageManager;
import resource.Resource;

public class GameFrame implements Frame {
	
	AnimationImageManager test;
	
	public GameFrame() {
		test = Resource.getInstance().getImage("test");
		test.play();
	}

	@Override
	public void update() {
		test.update();
	}
	
	@Override
	public void draw(Graphics2D g) {
		test.draw((Graphics2D) g, 0, 0);
	}

}
