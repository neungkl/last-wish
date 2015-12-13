package object.appear.base;

import object.structure.BaseShooter;

public class Shooter2 extends BaseShooter {

	public Shooter2(float ratio) {
		super("base_shooter2", ratio);
		
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
