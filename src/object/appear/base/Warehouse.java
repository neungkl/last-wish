package object.appear.base;

import object.structure.BaseResourceLimit;

public class Warehouse extends BaseResourceLimit {
	public Warehouse(float ratio) {
		super("base_warehouse", ratio);
		setPhysicalRadius(30);
		
		this.fullHp = 150;
		this.woodRequire = 170;
		this.ironRequire = 150;
		this.maximumWood = this.maximumIron = 300;
		
		this.farmPer = 5;
		this.maxLevel = 20;
		
		this.currentHp = this.fullHp;
	}

	@Override
	public void statIncrease() {
		this.fullHp +=55;
		this.currentHp += 70;
		this.woodRequire +=400;
		this.ironRequire += 350;
		this.maximumWood += 500;
		this.maximumIron += 500;
	}
}
