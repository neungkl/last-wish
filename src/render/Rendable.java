package render;

import input.InputFlag;
import input.MouseInteractiveListener;

import java.awt.Graphics2D;

public abstract class Rendable implements Comparable<Rendable> {
	
	private int x,y;
	private int z;
	private int width, height;
	private boolean destroy;
	private boolean visible;
	private boolean pausable;
	private int align;
	
	private boolean isEnter;
	private MouseInteractiveListener mouseListener;
	
	public Rendable(int z) {
		this(0, 0, z);
	}
	public Rendable(int x, int y, int z) {
		this(x, y, z, 0, 0);
	}
	public Rendable(int x, int y, int z, int width, int height) {
		this.destroy = false;
		this.x = x;
		this.y = y;
		this.z = z;
		this.width = width;
		this.height = height;
		this.visible = true;
		this.pausable = false;
		this.align = RenderHelper.LEFT | RenderHelper.TOP;
		
		this.isEnter = false;
		this.mouseListener = null;
	}
	
	public final void setPosX(int x) {
		this.x = x;
	}
	
	public final int getPosX() {
		return x;
	}
	
	public final void setPosY(int y) {
		this.y = y;
	}
	
	public final void setPos(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public final int getPosY() {
		return y;
	}
	
	public final void setZ(int z) {
		this.z = z;
	}
	
	public final void setAlign(int align) {
		this.align = align;
	}
	protected final int getAlign() {
		return align;
	}
	
	public int getZ() {
		return z;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public final void destroy() {
		this.destroy = true;
	}
	public final boolean isDestroy() {
		return destroy;
	}
	
	public final void setVisible(boolean visible) {
		this.visible = visible;
	}
	public final boolean isVisible() {
		return visible;
	}
	public final void setPausable(boolean pausable) {
		this.pausable = pausable;
	}
	public final boolean isPausable() {
		return pausable;
	}
	
	public final void addMouseInteractiveListener(MouseInteractiveListener mouseListener) {
		this.mouseListener = mouseListener;
	}
	
	protected boolean isHitTest(int x, int y) {
		return RenderHelper.isHitTest(x, y, getPosX(), getPosY(), getWidth(), getHeight(), getAlign());
	}
	
	public void trigger() {
		if(mouseListener != null) {
			if(isHitTest(InputFlag.getMouseX(), InputFlag.getMouseY())) {
				if(!isEnter) {
					isEnter = true;
					mouseListener.onEnter();
				}
				if(InputFlag.get(InputFlag.MOUSE_LEFT_CLICK)) {
					mouseListener.onClick();
				}
			} else {
				if(isEnter) {
					isEnter = false;
					mouseListener.onLeave();
				}
			}
		}
	}
	
	public void update() {
		trigger();
	}
	
	public abstract void draw(Graphics2D g);
	
	@Override
	public final int compareTo(Rendable that) {
		if(this.getZ() < that.getZ()) return -1;
		if(this.getZ() == that.getZ()) {
			if(this.getPosY() < that.getPosY()) return -1;
			if(this.getPosY() == that.getPosY()) return 0;
			return 1;
		}
		return 1;
	}
}
