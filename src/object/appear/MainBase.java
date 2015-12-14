package object.appear;

import object.structure.Base;
import render.RenderHelper;
import render.rendable.AnimationRendable;
import essential.ZIndex;

public class MainBase extends Base {
	
	public MainBase(int x, int y, float ratio) {
		super(5);
		image = new AnimationRendable("base_main", x, y + 40, ratio);
		image.setAlign(RenderHelper.CENTER_MIDDLE);
		image.setZ(ZIndex.OBJECT_IN_GAME);
	}

	@Override
	public void upgrade(int level) {
		// TODO Auto-generated method stub
		
	}
}
