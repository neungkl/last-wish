package object.structure;

import java.util.ArrayList;

import render.rendable.Rendable;
import render.rendable.StaticImageRendable;

public interface IObjectWithSingleImage extends IObjectOnScreen {
	public StaticImageRendable getImage();
	
	@Override
	public default ArrayList<Rendable> getRendable() {
		ArrayList<Rendable> ret = new ArrayList<>();
		ret.add(getImage());
		return ret;
	}
}
