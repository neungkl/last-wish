package object.structure;

import input.MapParentListener;

import java.awt.Color;
import java.util.ArrayList;

import render.RendableHolder;
import render.RenderHelper;
import render.rendable.AnimationRendable;
import render.rendable.CircleRendable;
import render.rendable.Rendable;
import essential.Config;
import essential.GameScreen;
import essential.RandUtil;
import essential.ZIndex;

public abstract class Zombie implements IPhysical, ILive, IStat, IObjectOnScreen, IName {
	
	protected static final int MOVE = 0;
	protected static final int ATTACK = 1;
	protected static final int DIE = 2;
	
	protected int currentState = MOVE;
	
	protected AnimationRendable attack;
	protected AnimationRendable move;
	protected AnimationRendable death;
	protected AnimationRendable currentActive;
	
	protected float x,y;
	protected float angle;
	protected float desX;
	protected float desY;
	protected Base desBase;
	
	protected int physicalRadius;
	
	protected int fullHp, currentHp;
	protected boolean isDestroy = false;
	
	protected float speed;
	protected float damage;
	
	private String name;
	private CircleRendable debug;
	
	private ArrayList<ILive> attackList;
	
	protected Zombie(String type, int radius, float ratio) {
		int x = RandUtil.rand(GameScreen.WIDTH);
		int y = RandUtil.rand(GameScreen.HEIGHT);
		
		int outBound = 50;
		
		switch(RandUtil.random(0, 3)) {
		case 0 :
			x = -outBound;
			break;
		case 1 :
			x = GameScreen.WIDTH + outBound;
			break;
		case 2 :
			y = -outBound;
			break;
		case 3 :
			y = GameScreen.HEIGHT + outBound;
			break;
		}
		
		attack = new AnimationRendable("zombie_" + type + "_attack", x, y, ratio, ZIndex.OBJECT_IN_GAME);
		attack.setAlign(RenderHelper.CENTER_MIDDLE);
		move = new AnimationRendable("zombie_" + type + "_move", x, y, ratio, ZIndex.OBJECT_IN_GAME);
		move.setAlign(RenderHelper.CENTER_MIDDLE);
		death = new AnimationRendable("zombie_" + type + "_death", x, y, ratio, ZIndex.OBJECT_IN_GAME);
		death.setAlign(RenderHelper.CENTER_MIDDLE);
		
		attack.setVisible(false);
		death.setVisible(false);
		move.loop();
		currentActive = move;
		
		this.x = x;
		this.y = y;
		this.angle = 0;
		this.speed = 0;
		this.damage = 0;
		this.physicalRadius = radius;
		
		if(Config.DEBUG) {
			debug = new CircleRendable(x, y, radius, Color.RED, Integer.MAX_VALUE);
			RendableHolder.add(debug);
		}
		
		this.fullHp = this.currentHp = 0;
		
		attackList = new ArrayList<>();
		desBase = null;
	}

	@Override
	public int getFullHp() {
		return fullHp;
	}

	@Override
	public int getCurrentHp() {
		return currentHp;
	}

	@Override
	public void decreaseHp(int hp) {
		currentHp -= hp;
		currentHp = Math.max(0, Math.min(currentHp, fullHp));
	}

	@Override
	public boolean isDie() {
		return currentHp <= 0;
	}

	@Override
	public int getPosX() {
		return (int) x;
	}

	@Override
	public int getPosY() {
		return (int) y;
	}
	
	@Override
	public double getDistance(IPhysical obj) {
		float delX = obj.getPosX() - this.getPosX();
		float delY = obj.getPosY() - this.getPosY();
		return (float) Math.sqrt(delX * delX + delY * delY);
	}
	
	public void setAngle(int angle) {
		this.angle = angle;
	}
	
	public double getAngle() {
		return angle;
	}

	@Override
	public int getPhysicalRadius() {
		return physicalRadius;
	}

	@Override
	public void setPhysicalRadius(int radius) {
		physicalRadius = radius;
	}
	
	public void setDestinationToNearestTarget(ArrayList<Base> list) {
		int priority = Integer.MAX_VALUE;
		int minDiff = Integer.MAX_VALUE;
		
		for(int i=0; i<list.size(); i++) {
			
			Base b = list.get(i);
			
			if(b.isDestroy()) continue;
			
			int delX = b.getPosX() - this.getPosX();
			int delY = b.getPosY() - this.getPosY();
			int diff = delX * delX + delY * delY;
			
			if(b instanceof BaseAttack && priority >= 1) {
				if(priority > 1 || diff < minDiff) {
					priority = 1;
					minDiff = diff;
					desX = b.getPosX();
					desY = b.getPosY();
					desBase = b;
				}
			} else if(b instanceof ILive && priority >= 2) {
				if(priority > 2 || diff < minDiff) {
					priority = 2;
					minDiff = diff;
					desX = b.getPosX();
					desY = b.getPosY();
					desBase = b;
				}
			}
		}
	}
	
	public void update(ArrayList<Base> list) {
		
		currentActive.update();
		currentActive.setPos((int) x, (int) y);
		currentActive.setAngle(angle - (float) Math.PI / 2);
		
		if(debug != null)
			debug.setPos((int) x,(int) y);
		
		if(currentState == MOVE) {
			
			setDestinationToNearestTarget(list);
			
			if(desBase == null) {
				return ;
			}
			
			angle = (float) Math.atan2(desY - y, desX - x);
			
			x += Math.max(-speed, Math.min(desX - x, speed));
			y += Math.max(-speed, Math.min(desY - y, speed));
			
			
			if(isDie()) {
				switchState(DIE);
				return ;
			}
			if(this.isHitTest(desBase)) {
				switchState(ATTACK);
				return ;
			}
		} else if(currentState == ATTACK) {
			if(desBase == null || desBase.isDestroy()) {
				switchState(MOVE);
			}
			if(!this.isHitTest(desBase)) {
				switchState(MOVE);
			}
			if(isDie()) {
				switchState(DIE);
				return ;
			}
			if(!attackList.contains(desBase))
				attackList.add(desBase);
		} else if(currentState == DIE) {
			if(death.getAnimation().isFinish()) {
				destroy();
				return ;
			}
		}
	}
	
	protected abstract void switchState(int state);
	
	@Override
	public boolean isHitTest(IPhysical obj) {
		int delX = getPosX() - obj.getPosX();
		int delY = getPosY() - obj.getPosY();
		int radius = getPhysicalRadius() + obj.getPhysicalRadius();
		return delX * delX + delY * delY <= radius * radius;
	}

	@Override
	public void destroy() {
		attack.destroy();
		move.destroy();
		death.destroy();
		
		attackList.clear();
		
		if(debug != null)
			debug.destroy();
		
		isDestroy = true;
	}

	@Override
	public boolean isDestroy() {
		return isDestroy;
	}
	
	public void updateCombatStatus() {
		for(ILive live : attackList) {
			live.decreaseHp((int) damage);
		}
		attackList.clear();
	}
	
	@Override
	public String getStatString() {
		String txt = "";
		txt += "Speed : " + speed + "\n";
		txt += "Damage : " + damage + "\n";
		return txt; 
	}
	
	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public void setName(String name) {
		this.name = name;
	}
	
	public void addMouseInteractiveListener(MapParentListener<Zombie> mapParentListener) {
		attack.addMouseInteractiveListener(mapParentListener);
		move.addMouseInteractiveListener(mapParentListener);
		death.addMouseInteractiveListener(mapParentListener);
	}

	@Override
	public ArrayList<Rendable> getRendable() {
		ArrayList<Rendable> list = new ArrayList<>();
		list.add(attack);
		list.add(move);
		list.add(death);
		return list;
	}
}
