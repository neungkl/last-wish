package render;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class AnimationImageManager {
	
	private boolean isPlay;
	private boolean isLoop;
	private boolean isFinish;
	private boolean isGoneWhenStop;
	private int frame;
	private ImageData[] images;
	
	public AnimationImageManager(ImageData[] images) {
		frame = 0;
		isPlay = isGoneWhenStop = isFinish = false;
		this.images = images;
	}
	
	public void play() {
		isPlay = true;
		isLoop = isFinish = false;
		frame = 0;
	}
	
	public void loop() {
		isPlay = isFinish = false;
		isLoop = true;
		frame = 0;
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

	public void update() {
		if(isPlay || isLoop) {
			frame++;
			if(frame >= images.length) {
				isPlay = false;
				isFinish = true;
				frame = 0;
			}
		}
	}
	
	public BufferedImage getCurrentBufferedImage() {
		return images[frame].getImg();
	}
	
	private ImageData getCurrentImage() {
		ImageData imgà = null;
		
		if(isPlay || isLoop) {
			imgà = images[frame];
		} else if(!isGoneWhenStop) {
			imgà = images[0];
		}
		return imgà;
	}
	
	public void draw(Graphics2D g2, int x, int y, int width, int height) {
		ImageData gif = getCurrentImage();
		
		if(gif != null) {
			g2.drawImage(gif.getImg(), x + gif.getOffsetX(), y + gif.getOffsetY(), width, height, null);
		}
	}
	
	public void draw(Graphics2D g2, int x, int y) {
		ImageData gif = getCurrentImage();
		
		if(gif != null) {
			g2.drawImage(gif.getImg(), x + gif.getOffsetX(), y + gif.getOffsetY(), null);
		}
	}
}
