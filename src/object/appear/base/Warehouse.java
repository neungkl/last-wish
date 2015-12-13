package object.appear.base;

import object.structure.BaseResourceLimit;

public class Warehouse extends BaseResourceLimit {
	public Warehouse(float ratio) {
		super("base_warehouse", ratio);
		
		this.maxLevel = 1;
	}
	
	public static boolean canBuild(int farm, int wood, int iron) {
		return 
			farm >= 0 &&
			wood >= 0 &&
			iron >= 0;
	}

	@Override
	public void upgrade(int level) {
		// TODO 
	}
}
