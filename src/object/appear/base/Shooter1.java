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
		this.range = Integer.MAX_VALUE;
		this.damage = 20;
		this.farmPer = 5;
		this.maxLevel = 10;

		this.currentHp = this.fullHp;
	}

	@Override
	public void statIncrease() {
		this.fullHp += 50;
		this.currentHp += 60;
		this.woodRequire += 310;
		this.ironRequire += 350;
		this.damage += 20;
	}

	@Override
	protected Bullet generateBullet(float angle) {
		return new NormalBullet(this, damage, getPosX(), getPosY(), angle);
	}
}
