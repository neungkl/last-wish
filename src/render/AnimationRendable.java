package render;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import resource.Resource;

public class AnimationRendable extends Rendable {
	
	private AnimationImageManager animation;
	private float ratio;
	
	public AnimationRendable(String file) {
		this(file, 1);
	}
	public AnimationRendable(String file, float ratio) {
		this(file, 0, 0, ratio);
	}
	public AnimationRendable(String file, int x, int y, float ratio) {
		this(Resource.getInstance().getImageAnimation(file), x, y, ratio);
		
	}
	private AnimationRendable(AnimationImageManager animation, int x, int y, float ratio) {
		super(x, y, 0, animation.getWidth(), animation.getHeight());
		this.animation = animation;
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
	
	public AnimationImageManager getAnimation() {
		return animation;
	}
	
	public final void play() {
		animation.play();
	}
	
	public final void play(int speed) {
		animation.play(speed);
	}
	
	public final void loop() {
		animation.loop();
	}
	
	public final void loop(int speed) {
		animation.loop(speed);
	}
	
	@Override
	public final int getZ() {
		return -getPosY();
	}
	
	@Override
	public void update() {
		animation.update();
	}
	
	@Override
	public void draw(Graphics2D g) {
		ImageData img = animation.getCurrentImageData(); 
		RenderHelper.draw(
			g, 
			img.getBufferedImg(), 
			getPosX() + (int) (img.getOffsetX() * ratio), 
			getPosY() + (int) (img.getOffsetY() * ratio), 
			(int) (img.getWidth() * ratio), 
			(int) (img.getHeight() * ratio), 
			align
		);
	}
	
	
}
