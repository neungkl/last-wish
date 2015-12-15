package frame;

import input.HighlightObjectListener;
import input.InputFlag;
import input.MapParentListener;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JOptionPane;

import main.Main;
import object.appear.TileBackground;
import object.appear.base.MainBase;
import object.appear.bullet.BazukaBullet;
import object.appear.bullet.FastBullet;
import object.appear.zombie.LegionaryZombie;
import object.appear.zombie.OdinaryZombie;
import object.appear.zombie.WarriorZombie;
import object.structure.Base;
import object.structure.BaseAttack;
import object.structure.BaseElement;
import object.structure.BaseShooter;
import object.structure.Bullet;
import object.structure.IAttackable;
import object.structure.IObjectOnScreen;
import object.structure.IPhysical;
import object.structure.Zombie;
import render.RendableHolder;
import render.RenderHelper;
import render.rendable.BoxRendable;
import render.rendable.CircleRendable;
import render.rendable.Rendable;
import render.rendable.StaticImageRendable;
import essential.Config;
import essential.GameScreen;
import essential.GameState;
import essential.RandUtil;
import essential.ZIndex;
import frame.logic.GameResource;
import frame.logic.HighScore;
import frame.logic.SpawnZombie;
import frame.logic.TimeCounter;

public class GameFrame implements Frame {
	
	private static final int bottomHeight = 100;
	private static int centerX, centerY;

	private GameControlPanel controlPanel;

	private MainBase mainBase;
	private ArrayList<Base> baseList;
	private ArrayList<Bullet> bulletList;
	private ArrayList<Zombie> zombieList;
	private ArrayList<Rendable> pauseShow;
	
	private CircleRendable showRange;
	private static final Color showRangeCol = new Color(7, 230, 226, 50);
	
	private IObjectOnScreen hoverShowingStat = null;
	private IObjectOnScreen currentShowingStat = null;
	private Base dragAndDropObj = null;
	
	public GameFrame() {
		
		TimeCounter.start();
		SpawnZombie.start();
		GameResource.instance.reset();
		
		RendableHolder.add(new TileBackground("game_bg"));
		
		centerX = GameScreen.WIDTH / 2;
		centerY = (GameScreen.HEIGHT - bottomHeight) / 2;
		
		baseList = new ArrayList<>();
		bulletList = new ArrayList<>();
		zombieList = new ArrayList<>();
		pauseShow = new ArrayList<>();
		
		initializeRendableObject();
		
		controlPanel = new GameControlPanel(this, GameScreen.HEIGHT - bottomHeight, bottomHeight);
		controlPanel.updateAvailable(baseList);
	}
	
	private void initializeRendableObject() {
		mainBase = new MainBase(centerX, centerY, 0.25f);
		mainBase.setName("Main Base");
		mainBase.getSingleRendable().addMouseInteractiveListener(
			new MapParentListener<Base>(mainBase) {
				@Override
				public void onClick(StaticImageRendable object, Base parent) {
					currentShowingStat = parent;
				}
				
			}
		);
		
		baseList.add(mainBase);
		RendableHolder.add(mainBase);
		
		showRange = new CircleRendable(0, 0, 0, showRangeCol, ZIndex.EXTERNAL_INFO);
		showRange.setVisible(false);
		RendableHolder.add(showRange);
		
		Rendable pause = new StaticImageRendable("btn_pause", 10, 10, 0.35f, ZIndex.CONTROL_BAR_OBJECT);
		pause.addMouseInteractiveListener(new HighlightObjectListener<Rendable>() {
			@Override
			public void onClick(Rendable object) {
				pause();
			}
		});
		pause.setPausable(true);
		RendableHolder.add(pause);
		
		pause = new BoxRendable(0, 0, GameScreen.WIDTH, GameScreen.HEIGHT, new Color(0, 0, 0, 150), ZIndex.PAUSE_SCREEN);
		pauseShow.add(pause);
		
		pause = new StaticImageRendable("pause_header", GameScreen.WIDTH / 2, GameScreen.HEIGHT / 2 - 100, 1.5f, ZIndex.PAUSE_SCREEN);
		pause.setAlign(RenderHelper.CENTER_MIDDLE);
		pauseShow.add(pause);
		
		pause = new StaticImageRendable("pause_resume", GameScreen.WIDTH / 2, GameScreen.HEIGHT / 2 + 50, 1.2f, ZIndex.PAUSE_SCREEN);
		pause.setAlign(RenderHelper.CENTER_MIDDLE);
		pause.addMouseInteractiveListener(new HighlightObjectListener<Rendable>() {
			@Override
			public void onClick(Rendable object) {
				resume();
			}
		});
		pauseShow.add(pause);
		
		pause = new StaticImageRendable("pause_exit", GameScreen.WIDTH / 2, GameScreen.HEIGHT / 2 + 120, 1.2f, ZIndex.PAUSE_SCREEN);
		pause.setAlign(RenderHelper.CENTER_MIDDLE);
		pause.addMouseInteractiveListener(new HighlightObjectListener<Rendable>() {
			@Override
			public void onClick(Rendable object) {
				int dialogResult = JOptionPane.showConfirmDialog(
					Main.getFrame(), 
					"Are you sure to exit level to main screen ?",
					"Exit level ?",
					JOptionPane.YES_NO_OPTION
				);
				
				if(dialogResult == JOptionPane.YES_OPTION) {
					GameState.getInstance().changeStage(GameState.MENU_STAGE);
				}
			}
		});
		pauseShow.add(pause);
		
		for(Rendable each : pauseShow) {
			each.setVisible(false);
			each.setPausable(true);
			RendableHolder.add(each);
		}
	}
	
	public void spawnNewBase(String name) {
		
		Base obj = controlPanel.generateBase(name);
		
		for(Rendable each : RendableHolder.getInstance().getRendableList()) {
			each.setListen(false);
		}
		
		RendableHolder.add((IObjectOnScreen) obj);
			
		dragAndDropObj = obj;
	}
	
	public void setHoverShowingStat(Base hoverShowingStat) {
		this.hoverShowingStat = hoverShowingStat;
	}
	
	public ArrayList<Base> getBaseList() {
		return baseList;
	}
	
	public ArrayList<Bullet> getBulletList() {
		return bulletList;
	}
	
	public ArrayList<Zombie> getZombieList() {
		return zombieList;
	}
	
	private void clearDestroyedObject() {
		
		boolean isBaseRemove = false;
		
		for(Iterator<Base> it = baseList.iterator(); it.hasNext(); ) {
			Base cur = it.next();
			if(cur.isDestroy() || cur.isDie()) {
				isBaseRemove = true;
				cur.destroy();
				it.remove();
				cur = null;
			}
		}
		
		if(isBaseRemove) {
			updateStat();
		}
		
		for(Iterator<Bullet> it = bulletList.iterator(); it.hasNext(); ) {
			Bullet cur = it.next();
			if(cur.isDestroy() || cur.isOutOfArea()) {
				cur.destroy();
				it.remove();
				cur = null;
			}
		}
		
		for(Iterator<Zombie> it = zombieList.iterator(); it.hasNext(); ) {
			Zombie cur = it.next();
			if(cur.isDestroy()) {
				cur.destroy();
				it.remove();
				cur = null;
			}
		}
	}

	private void updateStat() {
		GameResource.instance.updateStatRender(baseList);
		
		controlPanel.updateAvailable(baseList);
	}
	
	@Override
	public void update() {
		
		if(mainBase.isDie() || mainBase.isDestroy()) {
			HighScore.prevLevelData = TimeCounter.getCurrentWave();
			HighScore.prevTimeData = TimeCounter.getSecond();
			GameState.getInstance().changeStage(GameState.GAME_OVER_STAGE);
			return ;
		}
		
		
		if(InputFlag.getTrigger(InputFlag.KEYBOARD, KeyEvent.VK_P)) {
			if(GameState.IS_PAUSING) {
				resume();
			} else {
				pause();
			}
			return ;
		}
		if(GameState.IS_PAUSING) return ;
		
		if(InputFlag.getTrigger(InputFlag.KEYBOARD, KeyEvent.VK_C)) {
			String cheatText = JOptionPane.showInputDialog("Enter cheat : ");
			if(cheatText.equals("iloveprogmeth")) {
				GameResource.instance.cheatEnable();
			} else if(cheatText.equals("ilovezombie")) {
				System.out.println("5555");
				String[] name = {"normal","legionary","warrior"};
				for(int i=0; i<20; i++) {
					spawnZombie(name[RandUtil.rand(3)]);
				}
			}
			pause();
			return ;
		}
		
		
		clearDestroyedObject();
		
		if(dragAndDropObj != null) {
			dragAndDropObj.getSingleRendable().setPos(InputFlag.getMouseX(), InputFlag.getMouseY());
			
			if(InputFlag.getTrigger(InputFlag.MOUSE_LEFT)) {
				dragAndDropObj.getSingleRendable().addMouseInteractiveListener(
					new MapParentListener<Base>(dragAndDropObj){
						@Override
						public void onClick(StaticImageRendable object, Base parent) {
							currentShowingStat = parent;
						}
						
					}
				);
				
				if(Config.DEBUG) {
					RendableHolder.add(new CircleRendable(
						dragAndDropObj.getPosX(), 
						dragAndDropObj.getPosY(), 
						dragAndDropObj.getPhysicalRadius(), 
						Color.RED, 
						Integer.MAX_VALUE
					));
				}
				
				baseList.add(dragAndDropObj);
				GameResource.instance.addIron(-dragAndDropObj.getIronRequire());
				GameResource.instance.addWood(-dragAndDropObj.getWoodRequire());
				
				dragAndDropObj = null;
			} else if(InputFlag.getTrigger(InputFlag.MOUSE_RIGHT)) {
				RendableHolder.remove(dragAndDropObj);
				dragAndDropObj = null;
			}
			
			for(Rendable each : RendableHolder.getInstance().getRendableList()) {
				each.setListen(true);
			}
			
			updateStat();
		}
		
		if(hoverShowingStat != null) {
			
			showRange.setVisible(false);
			controlPanel.showStat(hoverShowingStat, true);
			
		} else if(currentShowingStat != null) {
			if(currentShowingStat instanceof IPhysical && ((IPhysical) currentShowingStat).isDestroy()) {
				currentShowingStat = null;
				showRange.setVisible(false);
				controlPanel.statClear();
			} else {
				controlPanel.showStat(currentShowingStat, false);
			
				if(currentShowingStat instanceof BaseAttack && !(currentShowingStat instanceof BaseShooter)) {
					BaseAttack ba = (BaseAttack) currentShowingStat;
					if(ba.getRange() != Integer.MAX_VALUE) {
						showRange.setVisible(true);
						showRange.setPos(ba.getPosX(), ba.getPosY());
						showRange.setRadius(ba.getRange());
					}
				} else {
					showRange.setVisible(false);
				}
			}
		}
		
		for(Base base : baseList) {
			if(base instanceof BaseShooter) {
				((BaseShooter) base).rotateTo(InputFlag.getMouseX(), InputFlag.getMouseY());
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
			for(Zombie zombie : zombieList) {
				if(bullet.isHitTest(zombie)) {
					if(!(bullet instanceof BazukaBullet || bullet instanceof FastBullet))
						bullet.destroy();
					bullet.attack(zombie);
				}
			}
		}
		
		for(Zombie zombie : zombieList) {
			zombie.update(baseList);
		}
		
		String nextZombie = SpawnZombie.getZombieSpawnName();
		
		if(nextZombie != null) {
			spawnZombie(nextZombie);
		}
		
		if(TimeCounter.isNewSecond()) {
			
			for(Zombie zombie : zombieList) {
				zombie.updateCombatStatus();
			}
			
			for(Base base : baseList) {
				if(base instanceof BaseElement) {
					GameResource.instance.addIron(((BaseElement) base).getGiveIron());
					GameResource.instance.addWood(((BaseElement) base).getGiveWood());
				}
			}
			
			controlPanel.updateCurrentWave(TimeCounter.getCurrentWave());
			controlPanel.updateTimeRender(TimeCounter.getSecond());
			
			GameResource.instance.updateStatRender(baseList);
			controlPanel.updateAvailable(baseList);
			
			TimeCounter.setNewSecond(false);
			
			if(TimeCounter.getCurrentWave() >= Config.MAX_LEVEL) {
				GameState.getInstance().changeStage(GameState.GAME_WIN);
				return ;
			}
		}
		
		controlPanel.updateHpMainBaseRender(mainBase);
	}
	
	private void spawnZombie(String name) {
		int level = TimeCounter.getCurrentWave();
		Zombie zombie = new OdinaryZombie(level);
		
		if(name.equals("legionary")) zombie = new LegionaryZombie(level);
		else if(name.equals("warrior")) zombie = new WarriorZombie(level);
		
		zombie.addMouseInteractiveListener(new MapParentListener<Zombie>(zombie){

			@Override
			public void onClick(StaticImageRendable object, Zombie parent) {
				currentShowingStat = parent;
			}
			
		});
		zombieList.add(zombie);
		RendableHolder.add(zombie);
	}
	
	@Override
	public void pause() {
		GameState.IS_PAUSING = true;

		TimeCounter.instance.isWait = true;
		
		for(Rendable each : pauseShow) {
			each.setVisible(true);
		}
	}
	
	@Override
	public void resume() {
		
		synchronized (TimeCounter.instance) {
			TimeCounter.instance.notifyAll();
		}
		
		for(Rendable each : pauseShow) {
			each.setVisible(false);
		}
		
		GameState.IS_PAUSING = false;
	}

	@Override
	public void destroy() {
		
		TimeCounter.stop();
		
		baseList.clear();
		bulletList.clear();
		zombieList.clear();
		mainBase.destroy();
	}

}