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
		this.woodRequire = 1500;
		this.ironRequire = 1350;
		this.rang =Integer.MAX_VALUE;
		this.damage= 12;
		this.farmPer = 6;
		this.maxLevel =10;
		
		this.currentHp = this.fullHp;
	}
	
	public static boolean canBuild(int farm, int wood, int iron) {
		return 
			farm >= 6 &&
			wood >= 1200 &&
			iron >= 1100;
	}

	@Override
	public void statIncrease() {
		this.fullHp += 20;
		this.currentHp +=20;
		this.woodRequire += 400;
		this.ironRequire +=350;
		this.damage += 9;
	}
	
	@Override
	protected Bullet generateBullet(float angle) {
		return new NormalBullet(this, damage, getPosX(), getPosY(), angle);
	}
}
