package object.appear.base;

import object.appear.bullet.MiniBullet;
import object.structure.BaseAttack;
import object.structure.Bullet;

public class Tank extends BaseAttack {

	public Tank(float ratio) {
		super("base_tank", ratio);
		
		this.fullHp = 160;
		this.fireRate = 2;
		this.woodRequire = 1100;
		this.ironRequire = 830;
		this.range = 140;
		this.damage= 8;
		this.farmPer = 4;
		this.maxLevel = 5;
		
		this.currentHp = this.fullHp;
	}

	@Override
	public void statIncrease() {
		this.fullHp += 80;
		this.currentHp +=100;
		this.damage +=3;
		this.woodRequire += 500;
		this.ironRequire += 450;
		this.range += 3;
	}
	
	@Override
	protected Bullet generateBullet(float angle) {
		return new MiniBullet(this, damage, getPosX(), getPosY(), angle);
	}
}
