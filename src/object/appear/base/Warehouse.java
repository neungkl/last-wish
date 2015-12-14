package object.appear.base;

import object.structure.BaseResourceLimit;

public class Warehouse extends BaseResourceLimit {
	public Warehouse(float ratio) {
		super("base_warehouse", ratio);
		
		this.fullHp = 0;
		this.farmRequire = 0;
		this.woodRequire = 0;
		this.ironRequire = 0;
		this.maximumWood = 0;
		this.maximumIron = 0;
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
			this.farmRequire += 0;
			this.woodRequire += 0;
			this.ironRequire += 0;
			this.maximumWood += 0;
			this.maximumIron += 0;
		} else {
			level = maxLevel;
		}
		this.currentLevel = level;
	}
}
