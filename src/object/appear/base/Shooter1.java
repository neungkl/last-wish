package object.appear.base;

import object.appear.bullet.NormalBullet;
import object.structure.BaseShooter;
import object.structure.Bullet;

public class Shooter1 extends BaseShooter {

	public Shooter1(float ratio) {
		super("base_shooter1", ratio);

		this.fullHp = 120;
		this.fireRate = 2;
		this.woodRequire = 1700;
		this.ironRequire = 1800;
		this.rang = Integer.MAX_VALUE;
		this.damage = 10;
		this.farmPer = 5;
		this.maxLevel = 10;

		this.currentHp = this.fullHp;
	}

	public static boolean canBuild(int farm, int wood, int iron) {
		return farm >= 10 && 
				wood >= 600 && 
				iron >= 750;
	}

	@Override
	public void upgrade(int level) {
		level++;
		if (level <= maxLevel) {
			this.fullHp += 30;
			this.currentHp += 30;
			this.woodRequire += 300;
			this.ironRequire += 320;
			this.damage += 10;
		} else {
			level = maxLevel;
		}
		this.currentLevel = level;
	}

	@Override
	protected Bullet generateBullet(float angle) {
		return new NormalBullet(this, damage, getPosX(), getPosY(), angle);
	}
}
