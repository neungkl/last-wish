package object.structure;

import java.util.ArrayList;

import render.RendableHolder;
import render.RenderHelper;
import render.rendable.StaticImageRendable;
import essential.GameScreen;
import essential.RandUtil;
import essential.ZIndex;
import frame.GameFrame;

public abstract class BaseAttack extends Base implements IAttackable, IStat {

	protected int damage;
	protected float fireRate;
	protected int rang;
	
	private boolean isDestroy;
	private int currentTimeStamp;
	
	protected BaseAttack(String file, float ratio) {
		super(18);
		image = new StaticImageRendable(file, -1000, -1000, ratio);
		image.setZ(ZIndex.OBJECT_IN_GAME);
		image.setAlign(RenderHelper.CENTER_MIDDLE);
		this.setName(file.split("_")[1]);
		
		damage = rang = 0;
		fireRate = 1;
		isDestroy = false;
		currentTimeStamp = 0;
		
		getSingleRendable().setAngle(RandUtil.rand(360) / 360f * (float) Math.PI);
	}

	@Override
	public int getDamage() {
		return damage;
	}

	@Override
	public float getFireRate() {
		return fireRate;
	}
	
	@Override
	public int getRang() {
		return rang;
	}

	@Override
	public void increaseTime() {
		currentTimeStamp++;
	}

	@Override
	public boolean isAttack() {
		return currentTimeStamp%(fireRate * (float) GameScreen.FRAMERATE) == 0;
	}
	
	@Override
	public boolean isDestroy() {
		return isDestroy;
	}
	
	@Override
	public void destroy() {
		image.destroy();
		isDestroy = true;
	}
	
	protected abstract Bullet generateBullet(float angle);
	
	@Override
	public void attack(GameFrame gameFrame) {
		ArrayList<Zombie> zombieList = gameFrame.getZombieList();
		
		Zombie best = null;
		double minDist = Double.MAX_VALUE;
		
		for(Zombie zombie : zombieList) {
			double dist = zombie.getDistance(this);
			if(dist <= getRang()) {
				if(best == null || dist <= minDist) {
					if(dist < minDist || best == null || zombie.getCurrentHp() < best.getCurrentHp()) {
						best = zombie;
						minDist = dist;
					}
				}
			}
		}
		
		if(best != null) {
			float angle = (float) Math.atan2(best.getPosY() - getPosY(), best.getPosX() - getPosX());
			
			this.getSingleRendable().setAngle(angle);
			
			Bullet b = generateBullet(angle);
			RendableHolder.add(b);
			gameFrame.getBulletList().add(b);
		}
	}
	
	@Override
	public String getStatString() {
		String txt = "Damage : " + damage + "     ";
		txt += "Firerate : " + fireRate + "\n";
		txt += "Rang : " + (rang == Integer.MAX_VALUE ? "MAX" : rang);
		return txt;
	}
	
}
