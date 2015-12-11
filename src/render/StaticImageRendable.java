package render;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import resource.Resource;

public class StaticImageRendable extends Rendable {
	
	private BufferedImage image;
	private float ratio;
	
	public StaticImageRendable(String file) {
		this(file, 1);
	}
	public StaticImageRendable(String file, float ratio) {
		this(file, 0, 0, ratio);
	}
	public StaticImageRendable(String file, int x, int y, float ratio) {
		this(Resource.getInstance().getImage(file)[0].getBufferedImg(), x, y, ratio);
		
	}
	private StaticImageRendable(BufferedImage image, int x, int y, float ratio) {
		super(x, y, 0, image.getWidth(), image.getHeight());
		this.image = image;
		this.ratio = ratio;
	}
	
	@Override
	public int getWidth() {
		return (int)(super.getWidth() * ratio);
	}
	@Override
	public int getHeight() {
		return (int)(super.getHeight() * ratio);
	}
	
	@Override
	public void update() {}
	
	@Override
	public void draw(Graphics2D g) {
		RenderHelper.draw(
			g, 
			image, 
			getPosX(), 
			getPosY(), 
			(int) (getWidth() * ratio), 
			(int) (getHeight() * ratio), 
			align
		);
	}
	
	
}
