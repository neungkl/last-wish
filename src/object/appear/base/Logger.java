package object.appear.base;

import object.structure.BaseElement;

public class Logger extends BaseElement {
	
	public Logger(float ratio) {
		super("base_logger", -1000, -1000, ratio);
		
		this.fullHp = 70;
		this.woodRequire = 300;
		this.ironRequire = 370;
		this.giveFarm =0 ;
		this.giveWood = 20;
		this.giveIron = 0;
		this.farmPer = 0;
		this.maxLevel = 20;
		
		this.currentHp = this.fullHp;
	}
	
	@Override
	public boolean isGivable() {
		return true;
	}
	
	public static boolean canBuild(int farm, int wood, int iron) {
		return 
			farm >= 5 &&
			wood >= 100 &&
			iron >= 150;
	}

	@Override
	public void upgrade(int level) {
		level++;
		if(level <= maxLevel) {
			this.fullHp += 10;
			this.currentHp += 10;
			this.woodRequire += 200;
			this.ironRequire += 230;
			this.giveFarm += 0;
			this.giveWood += 10;
			this.giveIron += 0;
		} else {
			level = maxLevel;
		}
		this.currentLevel = level;
	}

}
