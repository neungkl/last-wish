package object.appear.base;

import object.structure.BaseElement;

public class Farm extends BaseElement {

	public Farm(float ratio) {
		super("base_farm", -1000, -1000, ratio);

		this.fullHp = 90;
		this.woodRequire = 150;
		this.ironRequire = 150;
		this.giveFarm = 20;
		this.farmPer = 0;
		this.maxLevel = 30;

		this.currentHp = this.fullHp;
	}

	@Override
	public void statIncrease() {
		this.fullHp += 20;
		this.currentHp += 30;
		this.woodRequire += 220;
		this.ironRequire += 230;
		this.giveFarm += 15;
		this.giveWood += 0;
		this.giveIron += 0;
	}

	@Override
	public String getStatString() {
		return "Farm size increase : " + getGiveFarm();
	}
}
