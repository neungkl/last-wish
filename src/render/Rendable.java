package render;

import java.awt.Graphics2D;

public abstract class Rendable implements Comparable<Rendable> {
	
	private int x,y;
	protected int z;
	private int width, height;
	private boolean destroy;
	private boolean visible;
	private boolean pausable;
	protected int align;
	
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
	
	public void setAlign(int align) {
		this.align = align;
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
	
	public abstract void update();
	public abstract void draw(Graphics2D g);
	
	@Override
	public final int compareTo(Rendable that) {
		if(this.getZ() < that.getZ()) return 1;
		if(this.getZ() == that.getZ()) return 0;
		return -1;
	}
}
