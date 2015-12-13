package object.appear.base;

import object.structure.BaseResourceLimit;

public class Barn extends BaseResourceLimit {
	public Barn(float ratio) {
		super("base_barn", ratio);
		
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
