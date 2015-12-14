package object.appear.base;

import object.appear.bullet.BazukaBullet;
import object.structure.BaseAttack;
import object.structure.Bullet;
import frame.GameFrame;

public class Bazuka extends BaseAttack {

	public Bazuka(float ratio) {
		super("base_bazuka", ratio);
		
		this.fullHp = 65;
		this.fireRate = 3;
		this.woodRequire = 1600;
		this.ironRequire = 1700;
		this.rang = 100;
		this.damage= 40;
		this.farmPer = 5;
		this.maxLevel =5;
		
		this.currentHp = this.fullHp;
	}
	
	public static boolean canBuild(int farm, int wood, int iron) {
		return 
			farm >= 5 &&
			wood >= 1400 &&
			iron >= 1400;
	}

	@Override
	public void upgrade(int level) {
		level++;
		if(level <= maxLevel) {
			this.fullHp += 30;
			this.currentHp += 30;
			this.damage +=20;
			this.woodRequire += 400;
			this.ironRequire += 500;
			this.rang +=2;
		} else {
			level = maxLevel;
		}
		this.currentLevel = level;
	}
	
	@Override
	protected Bullet generateBullet(float angle) {
		return new BazukaBullet(this, damage, getPosX(), getPosY(), angle);
	}
}
