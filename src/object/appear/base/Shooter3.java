package object.appear.base;

import object.appear.bullet.BoldBullet;
import object.appear.bullet.NormalBullet;
import object.structure.BaseShooter;
import object.structure.Bullet;
import render.RendableHolder;
import frame.GameFrame;

public class Shooter3 extends BaseShooter {

	public Shooter3(float ratio) {
		super("base_shooter3", ratio);

		this.fullHp = 150;
		this.fireRate = 4;
		this.woodRequire = 1100;
		this.ironRequire = 1300;
		this.rang = Integer.MAX_VALUE;
		this.damage = 25;
		this.farmPer = 7;
		this.maxLevel = 10;

		this.currentHp = this.fullHp;
	}

	public static boolean canBuild(int farm, int wood, int iron) {
		return farm >= 7&& 
				wood >= 650 && 
				iron >= 900;
	}

	@Override
	public void statIncrease() {
		this.fullHp += 30;
		this.currentHp += 30;
		this.damage += 15;
		this.woodRequire += 500;
		this.ironRequire += 450;
	}
	
	@Override
	protected Bullet generateBullet(float angle) {
		return new BoldBullet(this, damage, getPosX(), getPosY(), angle);
	}
}
