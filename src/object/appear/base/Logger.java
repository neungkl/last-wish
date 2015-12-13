package object.appear.base;

import object.structure.BaseElement;

public class Logger extends BaseElement {
	
	public Logger(float ratio) {
		super("base_logger", -1000, -1000, ratio);
		
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
			giveWood = 20;
			woodRequire = 150;
			ironRequire = 220;
			farmRequire = 100;
		} else if(level == 2) {
			giveFarm = 60;
			woodRequire = 500;
			ironRequire = 740;
			farmRequire = 70;
		} else if(level == 3) {
			giveFarm = 120;
			woodRequire = 900;
			ironRequire = 1200;
			farmRequire = 80;
		} else if(level == 4) {
			giveFarm = 150;
			woodRequire = 1200;
			ironRequire = 1400;
			farmRequire = 110;
		} else if(level == 5) {
			giveFarm = 200;
			woodRequire = 999999;
			ironRequire = 999999;
			farmRequire = 999999;
		} else {
			level = maxLevel;
		}
		this.currentLevel = level;
	}

}
