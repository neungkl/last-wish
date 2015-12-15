package render;

import java.awt.AlphaComposite;
import java.awt.Composite;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;

public class RenderHelper {
	public static final int LEFT = 0;
	public static final int CENTER = 1;
	public static final int RIGHT = 2;
	public static final int TOP = 0;
	public static final int MIDDLE = 4;
	public static final int BOTTOM = 8;
	public static final int CENTER_MIDDLE = CENTER | MIDDLE;
	
	private static final RescaleOp rescaleOp = new RescaleOp(1.5f, 0, null);
	private static final Composite translucent = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f);
	
	public static final int getOffsetX(int x, int width, int align) {
		if((align & CENTER) != 0) {
			x -= width / 2;
		} else if((align & RIGHT) != 0) {
			x -= width;
		}
		return x;
	}
	
	public static final int getOffsetY(int y, int height, int align) {
		if((align & MIDDLE) != 0) {
			y -= height / 2;
		} else if((align & BOTTOM) != 0) {
			y -= height;
		}
		return y;
	}
	
	public static void draw(Graphics2D g, BufferedImage img, int x, int y, int width, int height, boolean isPale, float angle, int originX, int originY, int align) {
		x = getOffsetX(x, width, align);
		y = getOffsetY(y, height, align);
		
		Composite originComposite = null;
		
		if(isPale) {
			originComposite = g.getComposite();
			g.setComposite(translucent);
		}
		
		if(angle != 0) {
			AffineTransform tmp = g.getTransform();
			AffineTransform trans = new AffineTransform();
			
			trans.rotate(angle, x + originX, y + originY);
			g.transform(trans);
			g.drawImage(img, x, y, width, height, null);
			g.setTransform(tmp);
		} else {
			g.drawImage(img, x, y, width, height, null);
		}
		
		if(isPale) {
			g.setComposite(originComposite);
		}
	}
	
	public static void drawHoverEffect(Graphics2D g, BufferedImage img, int x, int y, int width, int height, float angle, int originX, int originY, int align) {
		x = getOffsetX(x, width, align);
		y = getOffsetY(y, height, align);
		
		BufferedImage newImg = new BufferedImage(
			img.getColorModel(),
			img.copyData(null),
			img.isAlphaPremultiplied(),
			null
		);
		
		try {
			rescaleOp.filter(newImg, newImg);
		} catch(IllegalArgumentException e) {}
		
		if(angle != 0) {
			AffineTransform tmp = g.getTransform();
			AffineTransform trans = new AffineTransform();
			
			trans.rotate(angle, x + originX, y + originY);
			g.transform(trans);
			g.drawImage(newImg, x, y, width, height, null);
			g.setTransform(tmp);
		} else {
			g.drawImage(newImg, x, y, width, height, null);
		}
	}
	
	public static void drawString(Graphics2D g, String text, int x, int y, int width, int height, int align) {
		x = getOffsetX(x, width, align);
		y = getOffsetY(y, height, align);
		
		drawStringMultiLine(g, text, x, y);
	}
	
	private static void drawStringMultiLine(Graphics2D g, String text, int x, int y) {
	    FontMetrics m = g.getFontMetrics();
        String[] words = text.split("\n");
        String currentLine = words[0];
        for(int i = 1; i < words.length; i++) {
            g.drawString(currentLine, x, y);
            y += m.getHeight();
            currentLine = words[i];
        }
        if(currentLine.trim().length() > 0) {
            g.drawString(currentLine, x, y);
        }
	}
	
	public static boolean isHitTest(int x, int y, int sx, int sy, int width, int height, int align) {
		sx = getOffsetX(sx, width, align);
		sy = getOffsetY(sy, height, align);
		
		if(sx <= x && x <= sx + width && sy <= y && y <= sy + height) {
			return true;
		}
		return false;
	}
}
