package object.appear.base;

import object.structure.BaseElement;

public class Farm extends BaseElement {
	
	public Farm(float ratio) {
		super("base_farm", -1000, -1000, ratio);
		
		this.fullHp =90;
		this.woodRequire = 350;
		this.ironRequire = 360;
		this.giveFarm = 20;
		this.giveWood = 0;
		this.giveIron = 0;
		this.farmPer = 0;
		this.maxLevel = 30;
		
		this.currentHp = this.fullHp;
	}
	
	@Override
	public boolean isGivable() {
		return true;
	}
	
	public static boolean canBuild(int farm, int wood, int iron) {
		return 
			farm >= 0 &&
			wood >= 150 &&
			iron >= 150;
	}

	@Override
	public void upgrade(int level) {
		level++;
		if(level <= maxLevel) {
			this.fullHp += 15;
			this.currentHp +=15;
			this.woodRequire +=220;
			this.ironRequire += 230;
			this.giveFarm += 15;
			this.giveWood += 0;
			this.giveIron += 0;
		} else {
			level = maxLevel;
		}
		this.currentLevel = level;
	}

}
