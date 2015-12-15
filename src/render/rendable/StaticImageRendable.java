package render.rendable;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import render.RenderHelper;
import render.effect.IHoverEffect;
import resource.Resource;

public class StaticImageRendable extends Rendable implements IHoverEffect {

	private BufferedImage image;
	private float ratio;
	private int realWidth, realHeight;
	private int currentWidth, currentHeight;
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
		this(file, x, y, ratio, 0);
	}

	public StaticImageRendable(String file, int x, int y, float ratio, int z) {
		this(Resource.getImage(file)[0].getBufferedImg(), x, y, ratio, z);
	}

	private StaticImageRendable(BufferedImage image, int x, int y, float ratio, int z) {
		super(x, y, z, image.getWidth(), image.getHeight());
		this.image = image;
		this.realWidth = image.getWidth();
		this.realHeight = image.getHeight();
		
		setRatio(ratio);
		setOrigin(currentWidth / 2, currentHeight / 2);
	}
	protected StaticImageRendable(int x, int y, int z, int width, int height) {
		super(x, y, z, width, height);
		this.realWidth = width;
		this.realHeight = height;
	}
	
	public final void setRatioWithWidth(int width) {
		setRatio(width / (float) realWidth);
	}
	public final void setRatioWithHeight(int height) {
		setRatio(height / (float) realHeight);
	}

	public final void setRatio(float ratio) {
		this.ratio = ratio;
		
		currentWidth = (int) (super.getWidth() * ratio);
		currentHeight = (int) (super.getHeight() * ratio);
	}
	
	@Override
	public int getWidth() {
		return currentWidth;
	}
	
	@Override
	public int getHeight() {
		return currentHeight;
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
			currentWidth, 
			currentHeight,
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
	public void update() {}

	@Override
	public void draw(Graphics2D g) {
		RenderHelper.draw(
			g, 
			image, 
			getPosX(), 
			getPosY(),
			getWidth(), 
			getHeight(),
			isPale(),
			getAngle(),
			getOriginX(),
			getOriginY(),
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
			currentWidth, 
			currentHeight,
			getAngle(),
			getOriginX(),
			getOriginY(),
			getAlign()
		);
	}

}
