package render.rendable;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;

import render.RenderHelper;

public class StringRendable extends Rendable {

	private String text;
	private Font font;
	private Color color;
	private FontMetrics fontMetrics = null;
	
	public StringRendable(String text, Font font, Graphics2D context) {
		this(text, font, 0, 0, Color.BLACK, context);
	}
	public StringRendable(String text, Font font, Color color, Graphics2D context) {
		this(text, font, 0, 0, color, context);
	}
	public StringRendable(String text, Font font, int x, int y, Graphics2D context) {
		this(text, font, x, y, Color.BLACK, context);
	}
	public StringRendable(String text, Font font, int x, int y, Color color, Graphics2D context) {
		this(text, font, x, y, color, context, 0);
	}
	public StringRendable(String text, Font font, int x, int y, Color color, Graphics2D context, int z) {
		super(x, y, z);
		
		if(text == null) {
			throw new RuntimeException("Text must not be null");
		}
		
		this.text = text;
		this.font = font;
		this.color = color;
		
		if(context != null)
			this.fontMetrics = context.getFontMetrics(font);
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
	@Override
	public int getHeight() {
		return font.getSize();
	}
	
	@Override
	public int getWidth() { 
		if(fontMetrics != null) {
			return fontMetrics.stringWidth(text);
		} else {
			throw new RuntimeException("You have to initialize context before getWidth");
		}
	}
	
	public int getWidth(Graphics2D g) {
		return g.getFontMetrics(font).stringWidth(text);
	}
	
	public void setColor(Color color) {
		this.color = color;
	}
	
	@Override
	public void update() {}
	
	@Override
	public void draw(Graphics2D g) {
		g.setColor(color);
		g.setFont(font);
		RenderHelper.drawString(
			g, 
			text, 
			getPosX(), 
			getPosY(), 
			getWidth(g), 
			getHeight(), 
			getAlign()
		);
	}
	
}
