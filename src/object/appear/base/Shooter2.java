package object.appear.base;

import object.appear.bullet.NormalBullet;
import object.structure.BaseShooter;
import object.structure.Bullet;
import frame.GameFrame;

public class Shooter2 extends BaseShooter {

	public Shooter2(float ratio) {
		super("base_shooter2", ratio);
		
		this.fullHp =150;
		this.fireRate = 3;
		this.woodRequire = 1200;
		this.ironRequire = 1100;
		this.range =Integer.MAX_VALUE;
		this.damage=30;
		this.farmPer = 6;
		this.maxLevel =10;
		
		this.currentHp = this.fullHp;
	}

	@Override
	public void statIncrease() {
		this.fullHp += 40;
		this.currentHp +=50;
		this.woodRequire += 400;
		this.ironRequire +=350;
		this.damage += 25;
	}
	
	@Override
	protected Bullet generateBullet(float angle) {
		return new NormalBullet(this, damage, getPosX(), getPosY(), angle);
	}
}
