package object.appear.base;

import object.structure.BaseElement;

public class Ironworks extends BaseElement {
	
	public Ironworks(float ratio) {
		super("base_ironworks", -1000, -1000, ratio);
		
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
			giveIron = 20;
			woodRequire = 200;
			ironRequire = 120;
			farmRequire = 100;
		} else if(level == 2) {
			giveIron = 60;
			woodRequire = 700;
			ironRequire = 500;
			farmRequire = 80;
		} else if(level == 3) {
			giveFarm = 120;
			woodRequire = 1200;
			ironRequire = 850;
			farmRequire = 90;
		} else if(level == 4) {
			giveIron = 150;
			woodRequire = 1500;
			ironRequire = 1100;
			farmRequire = 120;
		} else if(level == 5) {
			giveIron = 200;
			woodRequire = 999999;
			ironRequire = 999999;
			farmRequire = 999999;
		} else {
			level = maxLevel;
		}
		this.currentLevel = level;
	}

}
