package render.image;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import base.GameScreen;
import exception.AnimationException;

public class AnimationImageManager implements Cloneable {

	private boolean isPlay;
	private boolean isLoop;
	private boolean isFinish;
	private boolean isGoneWhenStop;
	private int frame;
	private ImageData[] images;
	private int speed;
	private int currentSpeed;

	private int width, height;

	public AnimationImageManager(ImageData[] images) {
		this(images, false);
	}

	public AnimationImageManager(ImageData[] images, boolean isGoneWhenStop) {
		frame = 0;
		isPlay = isFinish = false;
		this.isGoneWhenStop = isGoneWhenStop;
		this.images = images;

		width = height = 0;
		for (int i = 0; i < images.length; i++) {
			width = Math.max(width, images[i].getWidth());
			height = Math.max(height, images[i].getHeight());
		}
	}
	
	public void play() {
		play(GameScreen.FRAMERATE);
	}

	public void play(int speed) {
		isPlay = true;
		isLoop = isFinish = false;
		frame = 0;
		currentSpeed = 0;
		this.speed = 1000 / speed;
	}
	
	public void loop() {
		loop(GameScreen.FRAMERATE);
	}

	public void loop(int speed) {
		if (isLoop)
			throw new AnimationException("The animation is already loop.");
		isPlay = isFinish = false;
		isLoop = true;
		frame = 0;
		currentSpeed = 0;
		this.speed = 1000 / speed;
	}

	public void stop() {
		isFinish = isPlay = isLoop = false;
	}

	public boolean isGoneWhenStop() {
		return isGoneWhenStop;
	}

	public void setGoneWhenStop(boolean isGoneWhenStop) {
		this.isGoneWhenStop = isGoneWhenStop;
	}

	public boolean isFinish() {
		return isFinish;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public void update() {
		if (currentSpeed < speed) {
			currentSpeed += 1000f / GameScreen.FRAMERATE;
			return ;
		}
		currentSpeed -= speed;
		if (isPlay || isLoop) {
			frame++;
			if (frame >= images.length) {
				isPlay = false;
				isFinish = true;
				frame = 0;
			}
		}
	}
	
	public ImageData getCurrentImageData() {
		return images[frame];
	}

	public BufferedImage getCurrentBufferedImage() {
		return images[frame].getBufferedImg();
	}

	private ImageData getCurrentImage() {
		ImageData img = null;

		if (isPlay || isLoop) {
			img = images[frame];
		} else if (!isGoneWhenStop) {
			img = images[0];
		}
		return img;
	}

	public void draw(Graphics2D g2, int x, int y, int width, int height) {
		ImageData gif = getCurrentImage();

		if (gif != null) {
			g2.drawImage(gif.getBufferedImg(), x + gif.getOffsetX(),
					y + gif.getOffsetY(), width, height, null);
		}
	}

	public void draw(Graphics2D g2, int x, int y) {
		ImageData gif = getCurrentImage();

		if (gif != null) {
			g2.drawImage(gif.getBufferedImg(), x + gif.getOffsetX(),
					y + gif.getOffsetY(), null);
		}
	}

	@Override
	public AnimationImageManager clone() {
		return new AnimationImageManager(images);
	}
}
