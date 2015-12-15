package object.appear.base;

import object.structure.BaseElement;

public class Logger extends BaseElement {
	
	public Logger(float ratio) {
		super("base_logger", -1000, -1000, ratio);
		
		this.fullHp = 70;
		this.woodRequire = 100;
		this.ironRequire = 150;
		this.giveFarm =0 ;
		this.giveWood = 20;
		this.giveIron = 0;
		this.farmPer = 0;
		this.maxLevel = 20;
		
		this.currentHp = this.fullHp;
	}

	@Override
	public void statIncrease() {
		this.fullHp += 10;
		this.currentHp += 10;
		this.woodRequire += 200;
		this.ironRequire += 230;
		this.giveFarm += 0;
		this.giveWood += 10;
		this.giveIron += 0;
	}

}
