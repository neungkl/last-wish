package object.appear.base;

import object.structure.BaseShooter;
import frame.GameFrame;

public class Shooter4 extends BaseShooter {

	public Shooter4(float ratio) {
		super("base_shooter4", ratio);
		
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
	public void upgrade(int level) {
		level++;
		if(level <= maxLevel) {
			this.fullHp += 20;
			this.currentHp +=20;
			this.woodRequire += 400;
			this.ironRequire +=350;
			this.damage += 9;
		} else {
			level = maxLevel;
		}
		this.currentLevel = level;
	}
	
	@Override
	public void attack(GameFrame gameFrame) {
		//System.out.println("shooter1 attack");
	}
	
}
