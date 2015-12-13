package input;

import object.structure.Base;
import render.rendable.StaticImageRendable;

public abstract class BaseListener extends HighlightObjectListener<StaticImageRendable> {
	
	private Base parent;
	
	public BaseListener(Base parent) {
		this.parent = parent;
	}
	
	@Override
	public final void onClick(StaticImageRendable object) {
		onClick(object, parent);
	}
	
	public abstract void onClick(StaticImageRendable object, Base parent);
}
