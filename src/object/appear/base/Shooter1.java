package object.appear.base;

import object.appear.bullet.NormalBullet;
import object.structure.BaseShooter;
import object.structure.Bullet;

public class Shooter1 extends BaseShooter {

	public Shooter1(float ratio) {
		super("base_shooter1", ratio);

		this.fullHp = 120;
		this.fireRate = 2;
		this.woodRequire = 600;
		this.ironRequire = 750;
		this.rang = Integer.MAX_VALUE;
		this.damage = 10;
		this.farmPer = 5;
		this.maxLevel = 10;

		this.currentHp = this.fullHp;
	}

	@Override
	public void statIncrease() {
		this.fullHp += 30;
		this.currentHp += 30;
		this.woodRequire += 300;
		this.ironRequire += 320;
		this.damage += 10;
	}

	@Override
	protected Bullet generateBullet(float angle) {
		return new NormalBullet(this, damage, getPosX(), getPosY(), angle);
	}
}
