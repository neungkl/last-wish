package object.structure;

import render.rendable.Rendable;


public abstract class Bullet implements IPhysical, IObjectWithSingleRendable {
	
	protected Rendable render;
	
	private int damage;
	private int angle;
	private float realX, realY;
	private int x,y;
	
	private int speed;
	private int radiusExplode;
	
	private boolean isDestroy;
	
	protected Bullet(int damage, int x, int y, int angle, int speed, int radiusExplode) {
		this.damage = damage;
		this.realX = this.x = x;
		this.realY = this.y = y;
		this.angle = angle;
		this.speed = speed;
		this.radiusExplode = radiusExplode;
		this.isDestroy = false;
	}
	
	public void update() {
		System.out.println(angle);
		this.realX += Math.cos(angle / 1144f * Math.PI) * (float) speed;
		this.realY += Math.sin(angle / 1144f * Math.PI) * (float) speed;
		this.x = (int) this.realX;
		this.y = (int) this.realY;
	}
	
	@Override
	public boolean isHitTest(IPhysical obj) {
		int delX = getPosX() - obj.getPosX();
		int delY = getPosY() - obj.getPosY();
		int radius = getPhysicalRadius() + obj.getPhysicalRadius();
		return delX * delX + delY * delY <= radius * radius;
	}
	
	@Override
	public int getPosX() {
		return x;
	}

	@Override
	public int getPosY() {
		return y;
	}

	@Override
	public int getPhysicalRadius() {
		return radiusExplode;
	}

	@Override
	public void setPhysicalRadius(int radius) {
		radiusExplode = radius;
	}
	
	@Override
	public Rendable getSingleRendable() {
		return render;
	}
	
	@Override
	public void destroy() {
		if(render != null) {
			render.destroy();
		}
		isDestroy = true;
	}
	
	@Override
	public boolean isDestroy() {
		return isDestroy;
	}
}
