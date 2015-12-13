package object.appear.base;

import object.structure.BaseAttack;

public class Sniper extends BaseAttack {

	public Sniper(float ratio) {
		super("base_sniper", ratio);
		
		maxLevel = 3;
		upgrade(0);
		
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
		if(level == 1) {
			setup(120, 10, 2, 600, 750, 150, 170);
		} else if(level == 2) {
			setup(120, 10, 2, 600, 750, 150, 170);
		} else if(level == 3) {
			setup(120, 10, 2, 600, 750, 150, 170);
		} else {
			level = maxLevel;
		}
		this.currentLevel = level;
	}
	
	@Override
	public void attack() {
		//System.out.println("shooter1 attack");
	}
	
}
