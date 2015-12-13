package frame;

import input.BaseListener;
import input.InputFlag;

import java.util.ArrayList;

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
import object.structure.IAttackable;
import object.structure.IObjectOnScreen;
import object.structure.IShooter;
import render.RendableHolder;
import render.rendable.Rendable;
import render.rendable.StaticImageRendable;
import essential.GameScreen;

public class GameFrame implements Frame {
	
	private static final int bottomHeight = 100;
	private static int centerX, centerY;

	private GameControlPanel controlPanel;
	
	private MainBase mainBase;
	private ArrayList<Base> baseList;
	
	private Base currentShowingStat = null;
	
	private Base dragAndDropObj = null;
	
	public GameFrame() {
		RendableHolder.add(new TileBackground("game_bg"));
		
		centerX = GameScreen.WIDTH / 2;
		centerY = (GameScreen.HEIGHT - bottomHeight) / 2;
		
		baseList = new ArrayList<>();
		
		initializeRendableObject();
		
		controlPanel = new GameControlPanel(this, GameScreen.HEIGHT - bottomHeight, bottomHeight);
	}
	
	private void initializeRendableObject() {
		
		// main base
		
		mainBase = new MainBase(centerX, centerY, 0.2f);
		RendableHolder.add(mainBase);
		
		// wall corner

		/*int areaSpace = 250;
		
		int[][] pos = {{1,1},{1,-1},{-1,1},{-1,-1}};
		cornerWall = new StaticImageRendable[4];
		for(int i=0; i<4; i++) {
			int x = pos[i][0] * areaSpace + centerX;
			int y = pos[i][1] * areaSpace + centerY;
			
			StaticImageRendable S = new StaticImageRendable("wall_corner", x, y, 0.15f);
			S.setAlign(RenderHelper.CENTER_MIDDLE);
			S.setZ(ZIndex.OBJECT_IN_GAME);
			RendableHolder.add(S);
			cornerWall[i] = S;
		}*/
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
			obj = new Bazuka(0.8f);
			break;
		case "sniper" :
			obj = new Sniper(0.8f);
			break;
		case "light" :
			obj = new Light(0.8f);
			break;
		case "tank" :
			obj = new Tank(0.8f);
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

	@Override
	public void update() {
		
		if(dragAndDropObj != null) {
			dragAndDropObj.getImage().setPos(InputFlag.getMouseX(), InputFlag.getMouseY());
			
			if(InputFlag.getTrigger(InputFlag.MOUSE_LEFT)) {
				for(Rendable each : RendableHolder.getInstance().getRendableList()) {
					each.setListen(true);
				}
				
				dragAndDropObj.getImage().addMouseInteractiveListener(new BaseListener(dragAndDropObj){

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
				IAttackable atk = ((IAttackable) base); 
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
