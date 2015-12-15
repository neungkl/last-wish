package object.appear.base;

import object.structure.BaseElement;

public class Ironworks extends BaseElement {
	
	public Ironworks(float ratio) {
		super("base_ironworks", -1000, -1000, ratio);
		
		this.fullHp = 70;
		this.woodRequire = 150;
		this.ironRequire = 100;
		this.giveFarm = 0;
		this.giveWood = 0;
		this.giveIron = 20;
		this.farmPer = 5;
		this.maxLevel = 20;
		
		this.currentHp = this.fullHp;
	}

	@Override
	public void statIncrease() {
		this.fullHp += 10;
		this.currentHp += 10;
		this.woodRequire +=230;
		this.ironRequire += 200;
		this.giveFarm += 0;
		this.giveWood += 0;
		this.giveIron += 10;
	}

}
