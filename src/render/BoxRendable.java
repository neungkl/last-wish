package render;

import java.awt.Color;
import java.awt.Graphics2D;

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
		super(x, y, 0, width, height);
		this.color = color;
	}

	@Override
	public void draw(Graphics2D g) {
		g.setColor(color);
		g.drawRect(getPosX(), getPosY(), getWidth(), getHeight());
	}
}
