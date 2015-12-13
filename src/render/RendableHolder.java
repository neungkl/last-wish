package render;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import essential.GameState;
import object.structure.IObjectOnScreen;
import object.structure.IObjectWithSingleImage;
import render.effect.IHoverEffect;
import render.rendable.Rendable;

public class RendableHolder {
	private static final RendableHolder instance = new RendableHolder();
	private ArrayList<Rendable> collections;
	
	private RendableHolder() {
		collections = new ArrayList<Rendable>();
	}
	
	public static final RendableHolder getInstance() {
		synchronized (instance) {
			return instance;
		}
	}
	
	public static void add(Rendable obj) {
		getInstance().collections.add(obj);
	}
	public static void add(IObjectOnScreen obj) {
		if(obj instanceof IObjectWithSingleImage) {
			getInstance().collections.add(((IObjectWithSingleImage) obj).getImage());
		} else {
			getInstance().collections.addAll(obj.getRendable());
		}
	}
	public static void add(ArrayList<Rendable> list) {
		getInstance().collections.addAll(list);
	}
	
	public ArrayList<Rendable> getRendableList() {
		return collections;
	}
	
	public static void remove(IObjectOnScreen obj) {
		getInstance().collections.removeAll(obj.getRendable());
	}
	public static void remove(ArrayList<Rendable> list) {
		getInstance().collections.removeAll(list);
	}
	
	public void update() {
		for(Iterator<Rendable> it = collections.iterator(); it.hasNext(); ) {
			Rendable cur = it.next();
			if(cur.isDestroy()) {
				it.remove();
				cur = null;
			}
		}
		
		Collections.sort(collections);
		
		for(int i=0; i<collections.size(); i++) {
			Rendable render = collections.get(i);
			if(!GameState.IS_PAUSING || render.isPausable()) {
				render.update();
				if(render.isListen() && render.isVisible()) {
					render.trigger();
				}
			}
		}
	}
	
	public void draw(Graphics2D g) {
		for(int i=0; i<collections.size(); i++) {
			
			Rendable render = collections.get(i);
			
			if(!render.isVisible()) continue;
			
			if(render instanceof IHoverEffect) {
				if(((IHoverEffect) render).isHoverEffect()) {
					((IHoverEffect) render).drawHoverEffect(g);
					continue;
				}
			}
			
			render.draw(g);
		}
	}
	
	public void clear() {
		collections.clear();
	}
}
