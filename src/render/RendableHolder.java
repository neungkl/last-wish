package render;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

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
	
	public void add(Rendable rendable) {
		collections.add(rendable);
	}
	
	public void draw(Graphics2D g) {
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
			}
				
			if(!render.isVisible()) continue;
			render.draw(g);
		}
	}
	
	public void clear() {
		collections.clear();
	}
}
