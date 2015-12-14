package render.rendable;

import java.awt.Color;
import java.awt.Graphics2D;

import render.RenderHelper;

public class CircleRendable extends Rendable {
	
	private Color color;
	private int radius;
	
	public CircleRendable(int radius) {
		this(0, 0, radius, Color.BLACK);
	} 
	public CircleRendable(int radius, Color color) {
		this(0, 0, radius, color);
	}
	public CircleRendable(int x, int y, int radius) {
		this(x, y, radius, Color.BLACK);
	}
	public CircleRendable(int x, int y, int radius, Color color) {
		this(x, y, radius, color, 0);
	}
	public CircleRendable(int x, int y, int radius, Color color, int z) {
		super(x, y, z, radius * 2, radius * 2);
		setAlign(RenderHelper.CENTER_MIDDLE);
		this.radius = radius;
		this.color = color;
	}
	
	public void setColor(Color color) {
		this.color = color;
	}

	@Override
	public void draw(Graphics2D g) {
		g.setColor(color);
		g.fillOval(getPosX() - radius, getPosY() - radius, radius * 2, radius * 2);
	}
	@Override
	public void update() {}
}
