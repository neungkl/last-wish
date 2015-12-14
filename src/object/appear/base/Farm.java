package object.appear.base;

import object.structure.BaseElement;

public class Farm extends BaseElement {
	
	public Farm(float ratio) {
		super("base_farm", -1000, -1000, ratio);
		
		this.fullHp = 0;
		this.woodRequire = 0;
		this.ironRequire = 0;
		this.giveFarm = 0;
		this.giveWood = 0;
		this.giveIron = 0;
		this.farmPer = 0;
		this.maxLevel = 0;
		
		this.currentHp = this.fullHp;
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
		if(level <= maxLevel) {
			this.fullHp += 0;
			this.currentHp += 0;
			this.woodRequire += 0;
			this.ironRequire += 0;
			this.giveFarm += 0;
			this.giveWood += 0;
			this.giveIron += 0;
		} else {
			level = maxLevel;
		}
		this.currentLevel = level;
	}

}
