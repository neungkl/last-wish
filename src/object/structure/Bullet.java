package object.structure;

import java.awt.Color;
import java.util.ArrayList;

import render.rendable.Rendable;
import essential.GameScreen;


public abstract class Bullet implements IPhysical, IObjectWithSingleRendable {
	
	protected Rendable render;
	protected IAttackable parent;
	
	private float damage;
	private float angle;
	private float realX, realY;
	private int x,y;
	
	private float speed;
	private int radiusExplode;

	private boolean everEnter = false;
	private boolean isDestroy;
	
	private ArrayList<ILive> everAttack;
	
	protected static final Color colBullet = new Color(40, 40, 40);
	
	protected Bullet(float damage, int x, int y, float angle, float speed, int radiusExplode) {
		this.damage = damage;
		this.realX = this.x = x;
		this.realY = this.y = y;
		this.angle = angle;
		this.speed = speed;
		this.radiusExplode = radiusExplode;
		this.isDestroy = false;
		
		everAttack = new ArrayList<>();
	}
	
	public void update() {
		this.realX += Math.cos(angle) * speed;
		this.realY += Math.sin(angle) * speed;
		this.x = (int) this.realX;
		this.y = (int) this.realY;
		render.setPos(x, y);
	}
	
	@Override
	public boolean isHitTest(IPhysical obj) {
		if(parent != null && parent.equals(obj)) return false;
		int delX = getPosX() - obj.getPosX();
		int delY = getPosY() - obj.getPosY();
		int radius = getPhysicalRadius() + obj.getPhysicalRadius();
		return delX * delX + delY * delY <= radius * radius;
	}
	
	public float getDamage() {
		return damage;
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
	public double getDistance(IPhysical obj) {
		float delX = obj.getPosX() - this.getPosX();
		float delY = obj.getPosY() - this.getPosY();
		return Math.sqrt(delX * delX + delY * delY);
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
		everAttack.clear();
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
	
	public void attack(ILive live) {
		if(everAttack.contains(live)) return ;
		live.decreaseHp((int) damage);
		everAttack.add(live);
	}
	
	@Override
	public boolean isDestroy() {
		return isDestroy;
	}
}
