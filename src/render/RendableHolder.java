package render;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import object.structure.IObjectOnScreen;
import render.effect.IHoverEffect;
import render.rendable.Rendable;
import base.GameState;

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
		getInstance().collections.addAll(obj.getRendable());
	}
	public static void add(ArrayList<Rendable> list) {
		getInstance().collections.addAll(list);
	}
	
	public ArrayList<Rendable> getRendableList() {
		return collections;
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
				if(render.isListen()) {
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
