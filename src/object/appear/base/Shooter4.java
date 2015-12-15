package object.appear.base;

import object.appear.bullet.BoldBullet;
import object.structure.BaseShooter;
import object.structure.Bullet;
import render.RendableHolder;
import frame.GameFrame;

public class Shooter4 extends BaseShooter {
	
	public Shooter4(float ratio) {
		super("base_shooter4", ratio);
		
		this.fullHp = 170;
		this.fireRate = 5;
		this.woodRequire = 1000;
		this.ironRequire = 1400;
		this.rang = Integer.MAX_VALUE;
		this.farmPer = 10;
		this.damage = 30;
		this.maxLevel = 10;
		
		this.currentHp = this.fullHp;
	}

	@Override
	public void statIncrease() {
		this.fullHp += 70;
		this.currentHp += 85;
		this.woodRequire += 500;
		this.ironRequire += 400;
		this.damage +=25;
	}

	@Override
	public void attack(GameFrame gameFrame) {
		Bullet b = new BoldBullet(this, getDamage(), getPosX(), getPosY(), getSingleRendable().getAngle());
		RendableHolder.add(b);
		gameFrame.getBulletList().add(b);
	}
	
	@Override
	protected Bullet generateBullet(float angle) {
		return new BoldBullet(this, damage, getPosX(), getPosY(), angle);
	}
	
}
