package render.rendable;

import java.awt.Color;
import java.awt.Graphics2D;

import render.RenderHelper;

public class BoxRendable extends Rendable {
	
	private Color color;
	
	public BoxRendable(int width, int height) {
		this(0, 0, width, height, Color.BLACK);
	} 
	public BoxRendable(int width, int height, Color color) {
		this(0, 0, width, height, color);
	}
	public BoxRendable(int x, int y, int width, int height) {
		this(x, y, width, height, Color.BLACK);
	}
	public BoxRendable(int x, int y, int width, int height, Color color) {
		this(x, y, width, height, color, 0);
	}
	public BoxRendable(int x, int y, int width, int height, Color color, int z) {
		super(x, y, z, width, height);
		this.color = color;
	}
	
	public void setColor(Color color) {
		this.color = color;
	}

	@Override
	public void draw(Graphics2D g) {
		int posX = RenderHelper.getOffsetX(getPosX(), getWidth(), getAlign());
		int posY = RenderHelper.getOffsetY(getPosY(), getHeight(), getAlign());
		
		g.setColor(color);
		g.fillRect(posX, posY, getWidth(), getHeight());
	}
	@Override
	public void update() {}
}
