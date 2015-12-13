package object.appear.base;

import object.structure.BaseElement;

public class Farm extends BaseElement {
	
	public Farm(float ratio) {
		super("base_farm", -1000, -1000, ratio);
		
		maxLevel = 5;
		upgrade(0);
	}
	
	@Override
	public boolean isGivable() {
		return true;
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
			giveFarm = 50;
			woodRequire = 340;
			ironRequire = 420;
			farmRequire = 40; 
		} else if(level == 2) {
			giveFarm = 100;
			woodRequire = 570;
			ironRequire = 680;
			farmRequire = 60;
		} else if(level == 3) {
			giveFarm = 240;
			woodRequire = 1400;
			ironRequire = 1700;
			farmRequire = 70;
		} else if(level == 4) {
			giveFarm = 500;
			woodRequire = 2400;
			ironRequire = 2500;
			farmRequire = 110;
		} else if(level == 5) {
			giveFarm = 800;
			woodRequire = 999999;
			ironRequire = 999999;
			farmRequire = 999999;
		} else {
			level = maxLevel;
		}
		this.currentLevel = level;
	}

}
