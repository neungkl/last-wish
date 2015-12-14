package frame;

import input.BaseListener;
import input.InputFlag;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;

import object.appear.MainBase;
import object.appear.TileBackground;
import object.appear.base.Barn;
import object.appear.base.Bazuka;
import object.appear.base.Farm;
import object.appear.base.Ironworks;
import object.appear.base.Light;
import object.appear.base.Logger;
import object.appear.base.Shooter1;
import object.appear.base.Shooter2;
import object.appear.base.Shooter3;
import object.appear.base.Shooter4;
import object.appear.base.Sniper;
import object.appear.base.Tank;
import object.appear.base.Warehouse;
import object.structure.Base;
import object.structure.Bullet;
import object.structure.IAttackable;
import object.structure.IObjectOnScreen;
import object.structure.IPhysical;
import object.structure.IShooter;
import render.RendableHolder;
import render.rendable.AnimationRendable;
import render.rendable.CircleRendable;
import render.rendable.Rendable;
import render.rendable.StaticImageRendable;
import essential.GameScreen;

public class GameFrame implements Frame {
	
	private static final int bottomHeight = 100;
	private static int centerX, centerY;

	private GameControlPanel controlPanel;
	
	private MainBase mainBase;
	private ArrayList<Base> baseList;
	private ArrayList<Bullet> bulletList;
	
	private Base currentShowingStat = null;
	private Base dragAndDropObj = null;
	
	private AnimationRendable a;
	
	public void test(IPhysical sss) {
		RendableHolder.add(new CircleRendable(mainBase.getPosX(), mainBase.getPosY(), 32, Color.RED, 10000));
	}
	
	public GameFrame() {
		RendableHolder.add(new TileBackground("game_bg"));
		
		centerX = GameScreen.WIDTH / 2;
		centerY = (GameScreen.HEIGHT - bottomHeight) / 2;
		
		baseList = new ArrayList<>();
		bulletList = new ArrayList<>();
		
		initializeRendableObject();
		
		controlPanel = new GameControlPanel(this, GameScreen.HEIGHT - bottomHeight, bottomHeight);
		
		a = new AnimationRendable("zombie_normal_death");
		a.loop(8);
		RendableHolder.add(a);
	}
	
	private void initializeRendableObject() {
		mainBase = new MainBase(centerX, centerY, 0.2f);
		mainBase.getSingleRendable().setName("_Main Base");
		mainBase.getSingleRendable().addMouseInteractiveListener(
			new BaseListener(mainBase){
				@Override
				public void onClick(StaticImageRendable object, Base parent) {
					currentShowingStat = parent;
				}
				
			}
		);
		test(mainBase);
		
		baseList.add(mainBase);
		RendableHolder.add(mainBase);
	}
	
	public void spawnNewBase(String name) {
		
		Base obj = null;
		
		switch(name) {
		case "farm" :
			obj = new Farm(0.8f);
			break;
		case "ironworks" :
			obj = new Ironworks(0.8f);
			break;
		case "logger" :
			obj = new Logger(0.8f);
			break;
		case "warehouse" : 
			obj = new Warehouse(0.8f);
			break;
		case "barn" :
			obj = new Barn(0.8f);
			break;
		case "shooter1" :
			obj = new Shooter1(0.8f);
			break;
		case "shooter2" :
			obj = new Shooter2(0.8f);
			break;
		case "shooter3" :
			obj = new Shooter3(0.8f);
			break;
		case "shooter4" :
			obj = new Shooter4(0.8f);
			break;
		case "bazuka" :
			obj = new Bazuka(0.4f);
			break;
		case "sniper" :
			obj = new Sniper(0.35f);
			break;
		case "light" :
			obj = new Light(0.65f);
			break;
		case "tank" :
			obj = new Tank(0.3f);
		}
		
		if(obj == null) {
			System.out.println("no name found in spawnNewBase : " + name);
			return ;
		}
		
		for(Rendable each : RendableHolder.getInstance().getRendableList()) {
			each.setListen(false);
		}
		
		RendableHolder.add((IObjectOnScreen) obj);
			
		dragAndDropObj = obj;
	}
	
	public ArrayList<Bullet> getBulletList() {
		return bulletList;
	}
	
	private void clearDestroyedObject() {
		for(Iterator<Base> it = baseList.iterator(); it.hasNext(); ) {
			Base cur = it.next();
			if(cur.isDestroy()) {
				cur.destroy();
				it.remove();
				cur = null;
			}
		}
		
		for(Iterator<Bullet> it = bulletList.iterator(); it.hasNext(); ) {
			Bullet cur = it.next();
			if(cur.isDestroy() || cur.isOutOfArea()) {
				cur.destroy();
				it.remove();
				cur = null;
			}
		}
	}

	@Override
	public void update() {
		
		a.update();
		
		clearDestroyedObject();
		
		if(dragAndDropObj != null) {
			dragAndDropObj.getSingleRendable().setPos(InputFlag.getMouseX(), InputFlag.getMouseY());
			
			if(InputFlag.getTrigger(InputFlag.MOUSE_LEFT)) {
				for(Rendable each : RendableHolder.getInstance().getRendableList()) {
					each.setListen(true);
				}
				
				dragAndDropObj.getSingleRendable().addMouseInteractiveListener(new BaseListener(dragAndDropObj){

					@Override
					public void onClick(StaticImageRendable object, Base parent) {
						currentShowingStat = parent;
					}
					
				});
				
				baseList.add(dragAndDropObj);
				
				dragAndDropObj = null;
			} else if(InputFlag.getTrigger(InputFlag.MOUSE_RIGHT)) {
				RendableHolder.remove(dragAndDropObj);
				dragAndDropObj = null;
			}
		}
		
		if(currentShowingStat != null) {
			controlPanel.showStat(currentShowingStat);
		}
		
		for(Base base : baseList) {
			if(base instanceof IShooter) {
				((IShooter) base).rotateTo(InputFlag.getMouseX(), InputFlag.getMouseY());
			}
			
			if(base instanceof IAttackable) {
				IAttackable atk = (IAttackable) base;
				atk.increaseTime();
				if(atk.isAttack()) {
					atk.attack(this);
				}
			}
		}
		
		for(Bullet bullet : bulletList) {
			bullet.update();
			for(Base base : baseList) {
				if(bullet.isHitTest(base)) {
					bullet.destroy();
				}
			}
		}
	}

	@Override
	public void pause() {
	}

	@Override
	public void destroy() {
	}

}