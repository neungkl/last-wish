package object.appear.base;

import object.structure.Base;
import render.RenderHelper;
import render.rendable.AnimationRendable;
import essential.ZIndex;

public class MainBase extends Base {
	
	public MainBase(int x, int y, float ratio) {
		super(32);
		image = new AnimationRendable("base_main", x, y + 40, ratio);
		image.setAlign(RenderHelper.CENTER_MIDDLE);
		image.setZ(ZIndex.OBJECT_IN_GAME);
		
		this.fullHp = this.currentHp = 1000;
		this.woodRequire = 2000;
		this.ironRequire = 1200;
		this.farmPer = 0;
		this.maxLevel = 4;
	}

	@Override
	public void statIncrease() {
		this.fullHp += 500;
		this.currentHp += 550;
		this.ironRequire += 1500;
		this.woodRequire += 1500;
	}
}
