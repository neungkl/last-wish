package object.structure;

import java.awt.Color;

import essential.GameScreen;
import render.rendable.Rendable;


public abstract class Bullet implements IPhysical, IObjectWithSingleRendable {
	
	protected Rendable render;
	
	private int damage;
	private int angle;
	private float realX, realY;
	private int x,y;
	
	private int speed;
	private int radiusExplode;

	private boolean everEnter = false;
	private boolean isDestroy;
	
	protected static final Color colBullet = new Color(40, 40, 40);
	
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
	
	public boolean isOutOfArea() {
		if(x >= 0 && x <= GameScreen.WIDTH && y >= 0 && y <= GameScreen.HEIGHT) {
			everEnter = true;
		} else {
			if(everEnter) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public boolean isDestroy() {
		return isDestroy;
	}
}
