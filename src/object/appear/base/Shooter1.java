package object.appear.base;

import object.appear.bullet.NormalBullet;
import object.structure.BaseShooter;
import object.structure.Bullet;
import render.RendableHolder;
import frame.GameFrame;

public class Shooter1 extends BaseShooter {

	public Shooter1(float ratio) {
		super("base_shooter1", ratio);
		
		this.fullHp = 0;
		this.fireRate = 60;
		this.farmRequire = 0;
		this.woodRequire = 0;
		this.ironRequire = 0;
		this.rang = 0;
		this.maxLevel = 0;
		
		this.currentHp = this.fullHp;
	}
	
	public static boolean canBuild(int farm, int wood, int iron) {
		return 
			farm >= 0 &&
			wood >= 0 &&
			iron >= 0;
	}

	@Override
	public void upgrade(int level) {
		level++;
		if(level <= maxLevel) {
			this.fullHp += 0;
			this.currentHp += 0;
			this.fireRate += 0;
			this.farmRequire += 0;
			this.woodRequire += 0;
			this.ironRequire += 0;
			this.rang += 0;
		} else {
			level = maxLevel;
		}
		this.currentLevel = level;
	}

	@Override
	public void attack(GameFrame gameFrame) {
		Bullet b = new NormalBullet(10, getPosX(), getPosY(), getSingleRendable().getAngle());
		RendableHolder.add(b);
		gameFrame.getBulletList().add(b);
	}
	
}
