package render.rendable;

import java.awt.Graphics2D;

import render.RenderHelper;
import render.image.AnimationImageManager;
import render.image.ImageData;
import resource.Resource;

public class AnimationRendable extends StaticImageRendable {
	
	private AnimationImageManager animation;
	private float ratio;
	
	public AnimationRendable(String file) {
		this(file, 1);
	}
	public AnimationRendable(String file, float ratio) {
		this(file, 0, 0, ratio);
	}
	public AnimationRendable(String file, int x, int y, float ratio) {
		this(Resource.getImageAnimation(file), x, y, ratio);
		
	}
	public AnimationRendable(String file, int x, int y, float ratio, int z) {
		this(Resource.getImageAnimation(file), x, y, ratio, z);
		
	}
	private AnimationRendable(AnimationImageManager animation, int x, int y, float ratio) {
		this(animation, x, y, ratio, 0);
	}
	private AnimationRendable(AnimationImageManager animation, int x, int y, float ratio, int z) {
		super(x, y, z, animation.getWidth(), animation.getHeight());
		this.animation = animation;
		setRatio(ratio);
		setOrigin(getWidth() / 2, getHeight() / 2);
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
		ImageData img = animation.getCurrentImageData(); 
		RenderHelper.drawHoverEffect(
			g, 
			img.getBufferedImg(), 
			getPosX() + (int) (img.getOffsetX() * ratio), 
			getPosY() + (int) (img.getOffsetY() * ratio), 
			getWidth(), 
			getHeight(), 
			getAngle(),
			getOriginX(),
			getOriginY(),
			getAlign()
		);
	}
	
}
