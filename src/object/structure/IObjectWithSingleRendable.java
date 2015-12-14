package object.structure;

import java.util.ArrayList;

import render.rendable.Rendable;
import render.rendable.StaticImageRendable;

public interface IObjectWithSingleRendable extends IObjectOnScreen {
	public Rendable getSingleRendable();
	
	@Override
	public default ArrayList<Rendable> getRendable() {
		ArrayList<Rendable> ret = new ArrayList<>();
		ret.add(getSingleRendable());
		return ret;
	}
}
