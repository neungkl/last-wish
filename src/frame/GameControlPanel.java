package frame;

import input.HighlightObjectListener;
import input.MouseInteractiveListener;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import object.appear.IconSelector;
import object.appear.base.Bazuka;
import object.appear.base.Farm;
import object.appear.base.Ironworks;
import object.appear.base.Light;
import object.appear.base.Logger;
import object.appear.base.MainBase;
import object.appear.base.Shooter1;
import object.appear.base.Shooter2;
import object.appear.base.Shooter3;
import object.appear.base.Shooter4;
import object.appear.base.Sniper;
import object.appear.base.Tank;
import object.appear.base.Warehouse;
import object.structure.Base;
import object.structure.ILive;
import object.structure.IName;
import object.structure.IObjectOnScreen;
import object.structure.IStat;
import object.structure.IUpgradable;
import render.RendableHolder;
import render.RenderHelper;
import render.rendable.BoxRendable;
import render.rendable.Rendable;
import render.rendable.StaticImageRendable;
import render.rendable.StringRendable;
import resource.Resource;
import essential.GameScreen;
import essential.ZIndex;
import frame.logic.GameResource;

public class GameControlPanel {
	
	private int x, y;
	private int height;
	private Font roboto;
	
	private boolean isSelectBase;
	
	private static final Color bgCol = new Color(44, 49, 51);
	private static final Color bgNonActiveCol = new Color(162, 172, 176);
	private static final Color bgHp = new Color(237, 12, 12);
	private static final Color fontGreyCol = new Color(250, 250, 250);
	
	private StringRendable timeCounter;
	private BoxRendable hpMainBase;
	
	private HashMap<String, IconSelector> baseObj;
	private HashMap<String, IconSelector> defenseObj;
	
	private HashMap<String, Rendable> rightObj;
	private Base waitingForActionObj;
	
	private HashMap<String, IconSelector> iconList;
	private final Base[] baseList = {
		new Bazuka(0f), 
		new Farm(0f), 
		new Ironworks(0f), 
		new Light(0f), 
		new Logger(0f), 
		new Shooter1(0f), 
		new Shooter2(0f), 
		new Shooter3(0f), 
		new Shooter4(0f), 
		new Sniper(0f), 
		new Tank(0f), 
		new Warehouse(0f)
	};
	
	private GameFrame gameFrame;
	
	public GameControlPanel(GameFrame gameFrame, int y, int height) {
		
		this.gameFrame = gameFrame;
		this.y = y;
		this.height = height;
		
		roboto = Resource.getFont("roboto");
		
		RendableHolder.add(new BoxRendable(0, y, GameScreen.WIDTH, height, bgCol, ZIndex.CONTROL_BAR));
		
		BoxRendable panelBase = new BoxRendable(0, y - 30, 80, 30, bgCol, ZIndex.CONTROL_BAR);
		StringRendable textPanelBase = new StringRendable("Base", roboto.deriveFont(Font.BOLD, 16f), 18, y - 8, Color.WHITE, null, ZIndex.CONTROL_BAR_OBJECT);
		
		BoxRendable panelDefense = new BoxRendable(80, y - 30, 80, 30, bgNonActiveCol, ZIndex.CONTROL_BAR);
		StringRendable textPanelDefense = new StringRendable("Defense", roboto.deriveFont(Font.BOLD, 16f), 88, y - 8, Color.BLACK, null, ZIndex.CONTROL_BAR_OBJECT);
		
		RendableHolder.add(panelBase);
		RendableHolder.add(textPanelBase);
		
		panelBase.addMouseInteractiveListener(new MouseInteractiveListener<Rendable>() {
			
			@Override
			public void onLeave(Rendable obj) {
				if(!isSelectBase) return ;
				panelBase.setColor(bgNonActiveCol);
			}
			
			@Override
			public void onEnter(Rendable obj) {
				if(!isSelectBase) return ;
				panelBase.setColor(Color.WHITE);
			}
			
			@Override
			public void onClick(Rendable obj) {
				if(!isSelectBase) return ;
				
				switchSelectBase(!isSelectBase);
				panelBase.setColor(bgCol);
				textPanelBase.setColor(Color.WHITE);
				
				panelDefense.setColor(bgNonActiveCol);
				textPanelDefense.setColor(Color.BLACK);
			}
		});
		
		panelDefense.addMouseInteractiveListener(new MouseInteractiveListener<Rendable>() {
			
			@Override
			public void onLeave(Rendable obj) {
				if(isSelectBase) return ;
				panelDefense.setColor(bgNonActiveCol);
			}
			
			@Override
			public void onEnter(Rendable obj) {
				if(isSelectBase) return ;
				panelDefense.setColor(Color.WHITE);
			}
			
			@Override
			public void onClick(Rendable obj) {
				if(isSelectBase) return ;
				
				switchSelectBase(!isSelectBase);
				panelDefense.setColor(bgCol);
				textPanelDefense.setColor(Color.WHITE);
				
				panelBase.setColor(bgNonActiveCol);
				textPanelBase.setColor(Color.BLACK);
			}
		});
		
		RendableHolder.add(panelDefense);
		RendableHolder.add(textPanelDefense);
		
		timeCounter = new StringRendable("00:00", roboto.deriveFont(Font.BOLD, 36f), GameScreen.WIDTH / 2, 65, bgHp, null, ZIndex.CONTROL_BAR_OBJECT);
		timeCounter.setAlign(RenderHelper.CENTER_MIDDLE);
		RendableHolder.add(timeCounter);
		
		RendableHolder.add(new BoxRendable(GameScreen.WIDTH / 2 - 70, 0, 140, 60, Color.WHITE, ZIndex.CONTROL_BAR_OBJECT));
		
		hpMainBase = new BoxRendable(0, 0, GameScreen.WIDTH, 10, bgHp, ZIndex.CONTROL_BAR_OBJECT);
		hpMainBase.setAlign(RenderHelper.CENTER_MIDDLE);
		RendableHolder.add(hpMainBase);
		
		initialLeftPanel();
		initialRightPanel();
	}

	private void initialLeftPanel() {
		
		baseObj = new HashMap<>();
		defenseObj = new HashMap<>();
		iconList = new HashMap<>();
		
		String[] fileList = {"farm","logger","ironworks","warehouse"};
		
		for(int i=0; i<fileList.length; i++) {
			IconSelector s = new IconSelector("icon_" + fileList[i], 10 + 80 * i, this.y + 10, 70);
			
			StaticImageRendable icon = s.getIcon();
			icon.addMouseInteractiveListener(generateIconHoverListener(fileList[i]));

			iconList.put(fileList[i], s);
			baseObj.put(fileList[i], s);
		}
		
		fileList = new String[]{"light","tank","sniper","bazuka","shooter1","shooter2","shooter3","shooter4"};
		for(int i=0; i<fileList.length; i++) {
			IconSelector s = new IconSelector("icon_" + fileList[i], 10 + 80 * i, this.y + 10, 70);
			
			StaticImageRendable icon = s.getIcon();
			icon.addMouseInteractiveListener(generateIconHoverListener(fileList[i]));
			icon.setVisible(false);
			
			iconList.put(fileList[i], s);
			defenseObj.put(fileList[i], s);
		}
		
		for(IconSelector each : baseObj.values()) {
			RendableHolder.add(each);
		}
		for(IconSelector each : defenseObj.values()) {
			RendableHolder.add(each);
			each.setVisible(false);
		}
		
		updateAvailable(gameFrame.getBaseList());
	}
	
	private void initialRightPanel() {
		rightObj = new HashMap<>();
		RendableHolder.add(new BoxRendable(GameScreen.WIDTH - 300, y - 80, 300, height + 75, bgCol, ZIndex.CONTROL_BAR));
		
		rightObj.put("name", new StringRendable(
			"",
			Resource.getFont("roboto", Font.BOLD, 18f), 
			0, 0, Color.WHITE, null, 
			ZIndex.CONTROL_BAR_OBJECT
		));
		
		rightObj.put("hpText", new StringRendable(
			"HP", 
			Resource.getFont("roboto", Font.PLAIN, 16f), 
			0, 0, Color.WHITE, null, 
			ZIndex.CONTROL_BAR_OBJECT
		));
		
		rightObj.put("hpStat", new StringRendable(
			"", 
			Resource.getFont("roboto", Font.PLAIN, 16f), 
			0, 0, Color.WHITE, null, 
			ZIndex.CONTROL_BAR_OBJECT
		));
		
		rightObj.put("hpBox", new BoxRendable(0, 0, 0, 18, bgHp, ZIndex.CONTROL_BAR_OBJECT));
		
		rightObj.put("stat", new StringRendable(
			"", 
			Resource.getFont("roboto", Font.PLAIN, 15f), 
			0, 0, new Color(250, 250, 250), null,
			ZIndex.CONTROL_BAR_OBJECT
		));
		
		StaticImageRendable img = new StaticImageRendable("btn_upgrade", 0, 0, 0.35f, ZIndex.CONTROL_BAR_OBJECT);
		
		img.addMouseInteractiveListener(new MouseInteractiveListener<StaticImageRendable>() {
			
			@Override
			public void onEnter(StaticImageRendable object) {
				if(GameResource.instance.canUpgrade(waitingForActionObj)) {
					object.setHoverEffect(true);
				} else {
					object.setPale(true);
				}
			}
			
			@Override
			public void onLeave(StaticImageRendable object) {
				object.setPale(false);
				object.setHoverEffect(false);
			}
			
			@Override
			public void onClick(StaticImageRendable object) {
				if(GameResource.instance.canUpgrade(waitingForActionObj)) {
					GameResource.instance.addIron(-waitingForActionObj.getIronRequire());
					GameResource.instance.addWood(-waitingForActionObj.getWoodRequire());
					waitingForActionObj.upgrade(waitingForActionObj.getCurrentLevel());
					GameResource.instance.updateStatRender(gameFrame.getBaseList());
				}
			}
		});
		
		rightObj.put("upgrade_btn",img);
		
		img = new StaticImageRendable("btn_sell", 0, 0, 0.35f, ZIndex.CONTROL_BAR_OBJECT);
		
		img.addMouseInteractiveListener(new HighlightObjectListener<Rendable>() {
			@Override
			public void onClick(Rendable object) {
				GameResource.instance.addIron(waitingForActionObj.getIronRefund());
				GameResource.instance.addWood(waitingForActionObj.getWoodRefund());
				waitingForActionObj.destroy();
				GameResource.instance.updateStatRender(gameFrame.getBaseList());
			}
		});
		
		rightObj.put("sell_btn",img);
		
		for(int i=0; i<2; i++) {
			rightObj.put("require" + i, new StringRendable(
				"", 
				Resource.getFont("roboto", Font.PLAIN, 12f), 
				0, 0, fontGreyCol, null,
				ZIndex.CONTROL_BAR_OBJECT
			));	
			
			rightObj.put("icon_farm" + i, new StaticImageRendable("icon_mini_farm", 0, 0, 0.3f, ZIndex.CONTROL_BAR_OBJECT));
			rightObj.put("icon_wood" + i, new StaticImageRendable("icon_mini_wood", 0, 0, 0.3f, ZIndex.CONTROL_BAR_OBJECT));
			rightObj.put("icon_iron" + i, new StaticImageRendable("icon_mini_iron", 0, 0, 0.3f, ZIndex.CONTROL_BAR_OBJECT));
		}
		
		for(Rendable right : rightObj.values()) {
			right.setVisible(false);
		}
		
		RendableHolder.add(rightObj.values());
	}
	
	public Base generateBase(String name) {
		switch(name) {
		case "farm" :
			return new Farm(1f);
		case "ironworks" :
			return new Ironworks(0.8f);
		case "logger" :
			return new Logger(0.8f);
		case "warehouse" : 
			return new Warehouse(0.8f);
		case "shooter1" :
			return new Shooter1(0.8f);
		case "shooter2" :
			return new Shooter2(0.8f);
		case "shooter3" :
			return new Shooter3(0.8f);
		case "shooter4" :
			return new Shooter4(0.8f);
		case "bazuka" :
			return new Bazuka(0.4f);
		case "sniper" :
			return new Sniper(0.35f);
		case "light" :
			return new Light(0.65f);
		case "tank" :
			return new Tank(0.3f);
		}
		throw new RuntimeException("Base name not found");
	}
	
	public MouseInteractiveListener<StaticImageRendable> generateIconHoverListener(String name) {
		
		for(int i=0; i<baseList.length; i++) {
			if(baseList[i].getClass().getSimpleName().toLowerCase().equals(name)) {
				Base b = baseList[i];
				
				return new HighlightObjectListener<StaticImageRendable>() {
					
					@Override
					public void onEnter(StaticImageRendable object) {
						if(GameResource.instance.canBuild(b, gameFrame.getBaseList())) {
							object.setHoverEffect(true);
						}
						gameFrame.setHoverShowingStat(b);
					}
					
					@Override
					public void onLeave(StaticImageRendable object) {
						object.setHoverEffect(false);
						gameFrame.setHoverShowingStat(null);
					}
					
					@Override
					public void onClick(StaticImageRendable icon) {
						gameFrame.spawnNewBase(icon.getName().split("_")[1]);
					}
				};
			}
		}
		
		throw new RuntimeException("Can't generate icon hover listener : " + name);
	}
	
	private void switchSelectBase(boolean isSelectBase) {
		this.isSelectBase = isSelectBase;
		
		for(IconSelector each : baseObj.values()) {
			each.setVisible(!isSelectBase);
		}
		for(IconSelector each : defenseObj.values()) {
			each.setVisible(isSelectBase);
		}
	}
	
	public void showStat(IObjectOnScreen obj, boolean isPreview) {
		
		if(obj == null) return ;
		
		int px = GameScreen.WIDTH - 300;
		int py = y - 80;
		
		int ey = GameScreen.HEIGHT - 40;
		
		for(Rendable right : rightObj.values()) {
			right.setVisible(true);
		}
		
		StringRendable txtHold;
		
		if(obj instanceof IName) {
			StringRendable txt = (StringRendable) rightObj.get("name");
			String name = ((IName) obj).getName();
			
			if(obj instanceof IUpgradable) {
				if(((IUpgradable) obj).isMaxLevel()) {
					name += " (level MAX)";
				} else {
					name += " (level " + ((IUpgradable) obj).getCurrentLevel() + ")";
				}
			}
			
			txt.setPos(px + 10, py + 22);
			txt.setText(name);
			
			py += 25;
		}
		
		if(obj instanceof ILive) {
			
			ILive live = (ILive) obj;
			
			txtHold =  (StringRendable) rightObj.get("hpText");
			txtHold.setPos(px + 13, py + 25);
			
			txtHold = (StringRendable) rightObj.get("hpStat");
			txtHold.setPos(px + 48, py + 45);
			txtHold.setText(live.getCurrentHp()+"/"+live.getFullHp());
			
			int width = (int) (220f * live.getCurrentHp() / live.getFullHp());
			
			BoxRendable hpBox = (BoxRendable) rightObj.get("hpBox");
			hpBox.setPos(px + 48, py + 10);
			hpBox.setWidth(width);
			
			py += 38;
		}
		
		if(obj instanceof IStat) {
			txtHold = (StringRendable) rightObj.get("stat");
			txtHold.setPos(px + 12, py + 32);
			txtHold.setText(((IStat) obj).getStatString());
		}
		
		if(obj instanceof Base) {
			Base base = (Base) obj;
			
			String[] seq = {
				"\n" + base.getWoodRequire() + "\n" + base.getIronRequire(),
				base.getFarmPer() + "\n" + base.getWoodRefund() + "\n" + base.getIronRefund()
			};
			
			final int space = 115;
			
			rightObj.get("upgrade_btn").setPos(px + 15, ey - 15);
			
			if(base.isMaxLevel()) {
				rightObj.get("upgrade_btn").setVisible(false);
				rightObj.get("require0").setVisible(false);
				rightObj.get("icon_wood0").setVisible(false);
				rightObj.get("icon_iron0").setVisible(false);
			} 
			rightObj.get("sell_btn").setPos(px + 130, ey - 15);
			
			if(isPreview) {
				rightObj.get("upgrade_btn").setVisible(false);
				rightObj.get("sell_btn").setVisible(false);
				rightObj.get("require1").setVisible(false);
				rightObj.get("icon_wood1").setVisible(false);
				rightObj.get("icon_farm1").setVisible(false);
				rightObj.get("icon_iron1").setVisible(false);
				px -= 35;
			}
			
			for(int i=0; i<seq.length; i++) {
				
				if(i == 0) {
					if(isPreview) {
						seq[i] = base.getFarmPer() + seq[i];
					} else {
						ey -= 12;
						rightObj.get("icon_farm0").setVisible(false);
					}
				}
				
				txtHold = (StringRendable) rightObj.get("require"+i);
				txtHold.setText(seq[i]);
				txtHold.setPos(px + 85 + space * i, ey - 3);
				
				rightObj.get("icon_farm" + i).setPos(px + 65 + space * i, ey - 12);
				rightObj.get("icon_wood" + i).setPos(px + 65 + space * i, ey + 3);
				rightObj.get("icon_iron" + i).setPos(px + 65 + space * i, ey + 13);
				
				if(i == 0) {
					ey += 12;
				}
			}
			
			waitingForActionObj = base;
		} else {
			rightObj.get("upgrade_btn").setVisible(false);
			rightObj.get("sell_btn").setVisible(false);
			
			for(int i=0; i<2; i++) {
				rightObj.get("require" + i).setVisible(false);
				rightObj.get("icon_farm" + i).setVisible(false);
				rightObj.get("icon_wood" + i).setVisible(false);
				rightObj.get("icon_iron" + i).setVisible(false);
			}
		}
			
	}

	public void statClear() {
		for(Rendable obj : rightObj.values()) {
			obj.setVisible(false);
		}
	}
	
	public void updateTimeRender(int timeInSecond) {
		String min = (timeInSecond / 60)+"";
		String second = (timeInSecond % 60)+"";
		if(min.length() == 0) min = "00";
		else if(min.length() == 1) min = "0" + min;
		if(second.length() == 0) second = "00";
		else if(second.length() == 1) second = "0" + second;
		timeCounter.setText(min+":"+second);
	}
	
	public void updateHpMainBaseRender(MainBase b) {
		hpMainBase.setWidth(GameScreen.WIDTH *  b.getCurrentHp() / b.getFullHp());
	}
	
	public void updateAvailable(ArrayList<Base> allBase) {
		HashMap<String, Boolean> baseAvailable = new HashMap<>();
		for(int i=0; i<baseList.length; i++) {
			baseAvailable.put(
				baseList[i].getClass().getSimpleName().toLowerCase(),
				GameResource.instance.canBuild(baseList[i], gameFrame.getBaseList())
			);
		}
		
		for(Entry<String, IconSelector> entry : iconList.entrySet()) {
			String key = entry.getKey();
			IconSelector icon = entry.getValue();

			icon.setAvailable(baseAvailable.get(key));
		}
	}
}
