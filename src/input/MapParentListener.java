package input;

import object.structure.Base;
import render.rendable.StaticImageRendable;

public abstract class MapParentListener<T> extends HighlightObjectListener<StaticImageRendable> {
	
	private T parent;
	
	public MapParentListener(T parent) {
		this.parent = parent;
	}
	
	@Override
	public final void onClick(StaticImageRendable object) {
		onClick(object, parent);
	}
	
	public abstract void onClick(StaticImageRendable object, T parent);
}
