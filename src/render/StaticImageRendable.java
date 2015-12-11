package render;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import resource.Resource;

public class StaticImageRendable extends Rendable implements IHoverEffect {

	private BufferedImage image;
	private float ratio;
	private boolean hoverEffect = false;

	public StaticImageRendable(String file) {
		this(file, 1);
	}

	public StaticImageRendable(String file, float ratio) {
		this(file, 0, 0, ratio);
	}

	public StaticImageRendable(String file, int x, int y) {
		this(file, x, y, 1);

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
		return (int) (super.getWidth() * ratio);
	}

	@Override
	public int getHeight() {
		return (int) (super.getHeight() * ratio);
	}

	public final void setRatio(float ratio) {
		this.ratio = ratio;
	}

	public final float getRatio() {
		return ratio;
	}

	@Override
	protected boolean isHitTest(int x, int y) {
		return RenderHelper.isHitTest(
			x, 
			y, 
			getPosX(), 
			getPosY(),
			(int) (getWidth() * ratio), 
			(int) (this.getHeight() * ratio),
			getAlign()
		);
	}
	
	@Override
	public void setHoverEffect(boolean use) {
		hoverEffect = use;
	}

	@Override
	public boolean isHoverEffect() {
		return hoverEffect;
	}

	@Override
	public void draw(Graphics2D g) {
		RenderHelper.draw(
			g, 
			image, 
			getPosX(), 
			getPosY(),
			(int) (getWidth() * ratio), 
			(int) (getHeight() * ratio),
			getAlign()
		);
	}

	@Override
	public void drawHoverEffect(Graphics2D g) {
		RenderHelper.drawHoverEffect(
			g, 
			image, 
			getPosX(), 
			getPosY(),
			(int) (getWidth() * ratio), 
			(int) (getHeight() * ratio),
			getAlign()
		);
	}

}
