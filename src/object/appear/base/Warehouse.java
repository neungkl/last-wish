package object.appear.base;

import object.structure.BaseResourceLimit;

public class Warehouse extends BaseResourceLimit {
	public Warehouse(float ratio) {
		super("base_warehouse", ratio);
		setPhysicalRadius(30);
		
		this.fullHp = 120;
		this.woodRequire = 170;
		this.ironRequire = 150;
		this.maximumWood = this.maximumIron = 300;
		
		this.farmPer = 5;
		this.maxLevel = 20;
		
		this.currentHp = this.fullHp;
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
