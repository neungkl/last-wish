package object.appear.base;

import object.appear.bullet.NormalBullet;
import object.structure.BaseAttack;
import object.structure.Bullet;
import frame.GameFrame;

public class Light extends BaseAttack {

	public Light(float ratio) {
		super("base_light", ratio);
		
		this.fullHp = 50;
		this.damage = 7;
		this.fireRate = 0.85f;
		this.woodRequire = 450;
		this.ironRequire =420;
		this.rang = 80;
		this.farmPer = 2;
		this.maxLevel = 5;
		
		this.currentHp = this.fullHp;
	}
	
	public static boolean canBuild(int farm, int wood, int iron) {
		return 
			farm >= 2&&
			wood >= 350 &&
			iron >= 300;
	}

	@Override
	public void upgrade(int level) {
		level++;
		if(level <= maxLevel) {
			this.fullHp += 20;
			this.currentHp += 20;
			this.damage += 3;
			this.woodRequire += 200;
			this.ironRequire += 220;
			this.rang += 1;
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
