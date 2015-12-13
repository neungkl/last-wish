package object.appear;

import java.util.ArrayList;

import object.structure.IObjectOnScreen;
import render.RenderHelper;
import render.rendable.AnimationRendable;
import render.rendable.Rendable;
import base.ZIndex;

public class MainBase implements IObjectOnScreen {

	AnimationRendable base;
	private int currentAngle = 0;
	
	public MainBase(int x, int y, float ratio) {
		base = new AnimationRendable("base_main", x, y + 40, ratio);
		base.setAlign(RenderHelper.CENTER_MIDDLE);
		base.setZ(ZIndex.OBJECT_IN_GAME);
	}
	
	@Override
	public ArrayList<Rendable> getRendable() {
		ArrayList<Rendable> list = new ArrayList<>();
		list.add(base);
		return list;
	}
	
	public void rotate() {
		currentAngle++;
		base.setAngle(currentAngle);
	}

}
