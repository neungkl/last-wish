package render;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class AnimationImageManager {
	
	private boolean isPlay;
	private boolean isLoop;
	private int frame;
	private ImageData[] images;
	
	public AnimationImageManager(ImageData[] images) {
		frame = 0;
		isPlay = false;
		this.images = images;
	}
	
	public void play() {
		isPlay = true;
		isLoop = false;
	}
	
	public void loop() {
		isPlay = false;
		isLoop = true;
	}
	
	public void stop() {
		isPlay = false;
		isLoop = false;
	}
	
	public void update() {
		if(isPlay || isLoop) {
			frame++;
			if(frame >= images.length) {
				isPlay = false;
				frame = 0;
			}
		}
	}
	
	public void draw(Graphics2D g2, int x, int y) {
		if(isPlay || isLoop) {
			ImageData gif = images[frame];
			g2.drawImage(gif.getImg(), x + gif.getOffsetX(), y + gif.getOffsetY(), null);
		}
	}
}
