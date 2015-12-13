package input;

import render.effect.IHoverEffect;

public abstract class HighlightObjectListener<T> implements MouseInteractiveListener<T> {

	@Override
	public void onEnter(T object) {
		if(object instanceof IHoverEffect) {
			((IHoverEffect) object).setHoverEffect(true);
		}
	}

	@Override
	public void onLeave(T object) {
		if(object instanceof IHoverEffect) {
			((IHoverEffect) object).setHoverEffect(false);
		}
	}
	
}
