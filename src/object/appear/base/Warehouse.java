package object.appear.base;

import object.structure.BaseResourceLimit;

public class Warehouse extends BaseResourceLimit {
	public Warehouse(float ratio) {
		super("base_warehouse", ratio);
		setPhysicalRadius(30);
		
		this.fullHp = 120;
		this.woodRequire = 360;
		this.ironRequire = 320;
		this.maximumWood = this.maximumIron =300;
		
		this.farmPer = 5;
		this.maxLevel = 20;
		
		this.currentHp = this.fullHp;
	}
	
	public static boolean canBuild(int farm, int wood, int iron) {
		return 
			farm >= 5&&
			wood >= 170 &&
			iron >= 150;
	}

	@Override
	public void statIncrease() {
		this.fullHp += 20;
		this.currentHp += 20;
		this.woodRequire += 190;
		this.ironRequire += 170;
		this.maximumWood += 200;
		this.maximumIron += 200;
	}
}
