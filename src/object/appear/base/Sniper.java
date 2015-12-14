package object.appear.base;

import object.structure.BaseAttack;
import frame.GameFrame;

public class Sniper extends BaseAttack {

	public Sniper(float ratio) {
		super("base_sniper", ratio);
		
		this.fullHp =35;
		this.fireRate = 4;
		this.woodRequire = 1500;
		this.ironRequire =1100;
		this.rang = 200;
		this.damage= 30;
		this.farmPer = 3;
		this.maxLevel = 5;
		
		this.currentHp = this.fullHp;
	}
	
	public static boolean canBuild(int farm, int wood, int iron) {
		return 
			farm >= 3 &&
			wood >= 1000 &&
			iron >= 800;
	}

	@Override
	public void upgrade(int level) {
		level++;
		if(level <= maxLevel) {
			this.fullHp +=15;
			this.currentHp += 15;
			this.woodRequire += 600;
			this.ironRequire += 600;
			this.damage +=25;
			this.rang += 5;
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
