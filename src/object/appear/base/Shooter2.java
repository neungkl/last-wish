package object.appear.base;

import object.structure.BaseShooter;
import frame.GameFrame;

public class Shooter2 extends BaseShooter {

	public Shooter2(float ratio) {
		super("base_shooter2", ratio);
		
		this.fullHp = 0;
		this.fireRate = 0;
		this.farmRequire = 0;
		this.woodRequire = 0;
		this.ironRequire = 0;
		this.rang = 0;
		this.farmPer = 0;
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
		//System.out.println("shooter1 attack");
	}
}
