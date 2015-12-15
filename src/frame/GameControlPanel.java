package frame;

import input.HighlightObjectListener;
import input.MouseInteractiveListener;

import java.awt.Color;
import java.awt.Font;
import java.util.HashMap;

import object.appear.IconSelector;
import object.structure.Base;
import object.structure.ILive;
import object.structure.IName;
import object.structure.IObjectOnScreen;
import object.structure.IStat;
import render.RendableHolder;
import render.rendable.BoxRendable;
import render.rendable.Rendable;
import render.rendable.StaticImageRendable;
import render.rendable.StringRendable;
import resource.Resource;
import essential.GameScreen;
import essential.ZIndex;

public class GameControlPanel {
	
	private int x, y;
	private int height;
	private Font roboto;
	
	private boolean isSelectBase;
	
	private static final Color bgCol = new Color(44, 49, 51);
	private static final Color bgNonActiveCol = new Color(162, 172, 176);
	private static final Color bgHp = new Color(237, 12, 12);
	private static final Color fontGreyCol = new Color(250, 250, 250);
	
	private HashMap<String, IconSelector> baseObj;
	private HashMap<String, IconSelector> defenseObj;
	
	private HashMap<String, Rendable> rightObj;
	
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
		
		initialLeftPanel();
		initialRightPanel();
	}
	
	private void initialLeftPanel() {
		baseObj = new HashMap<>();
		defenseObj = new HashMap<>();
		
		String[] fileList = {"farm","logger","ironworks","warehouse"};
		
		HighlightObjectListener<StaticImageRendable> baseListener = new HighlightObjectListener<StaticImageRendable>() {
			
			@Override
			public void onClick(StaticImageRendable icon) {
				gameFrame.spawnNewBase(icon.getName().split("_")[1]);
			}
		}; 
		
		for(int i=0; i<fileList.length; i++) {
			IconSelector s = new IconSelector("icon_" + fileList[i], 10 + 80 * i, this.y + 10, 70);
			StaticImageRendable icon = s.getIcon();
			icon.addMouseInteractiveListener(baseListener);
			baseObj.put(fileList[i], s);
		}
		
		fileList = new String[]{"light","tank","sniper","bazuka","shooter1","shooter2","shooter3","shooter4"};
		for(int i=0; i<fileList.length; i++) {
			IconSelector s = new IconSelector("icon_" + fileList[i], 10 + 80 * i, this.y + 10, 70);
			StaticImageRendable icon = s.getIcon();
			icon.addMouseInteractiveListener(baseListener);
			icon.setVisible(false);
			defenseObj.put(fileList[i], s);
		}
		
		for(IconSelector each : baseObj.values()) {
			RendableHolder.add(each);
		}
		for(IconSelector each : defenseObj.values()) {
			RendableHolder.add(each);
			each.setVisible(false);
		}
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
		
		rightObj.put("hpBox", new BoxRendable(0, 0, 0, 18, Color.RED, ZIndex.CONTROL_BAR_OBJECT));
		
		rightObj.put("stat", new StringRendable(
			"", 
			Resource.getFont("roboto", Font.PLAIN, 15f), 
			0, 0, new Color(250, 250, 250), null,
			ZIndex.CONTROL_BAR_OBJECT
		));
		
		rightObj.put("require0", new StringRendable(
			"", 
			Resource.getFont("roboto", Font.PLAIN, 12f), 
			0, 0, fontGreyCol, null,
			ZIndex.CONTROL_BAR_OBJECT
		));
		
		rightObj.put("icon_wood", new StaticImageRendable("icon_mini_wood", 0, 0, 0.3f, ZIndex.CONTROL_BAR_OBJECT));
		rightObj.put("icon_iron", new StaticImageRendable("icon_mini_iron", 0, 0, 0.3f, ZIndex.CONTROL_BAR_OBJECT));
		
		rightObj.put("require1", new StringRendable(
			"", 
			Resource.getFont("roboto", Font.PLAIN, 12f), 
			0, 0, fontGreyCol, null,
			ZIndex.CONTROL_BAR_OBJECT
		));
		
		for(Rendable right : rightObj.values()) {
			right.setVisible(false);
		}
		
		RendableHolder.add(rightObj.values());
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
	
	public void showStat(IObjectOnScreen obj) {
		
		int px = GameScreen.WIDTH - 300;
		int py = y - 80;
		
		int ex = GameScreen.WIDTH - 80;
		int ey = GameScreen.HEIGHT - 50;
		
		for(Rendable right : rightObj.values()) {
			right.setVisible(true);
		}
		
		StringRendable txtHold;
		
		if(obj instanceof IName) {
			StringRendable txt = (StringRendable) rightObj.get("name");
			
			txt.setPos(px + 10, py + 25);
			txt.setText(((IName) obj).getName());
			
			py += 30;
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
				base.getWoodRequire() + "",
				base.getIronRequire() + ""
			};
			
			final int space = 15;
			
			rightObj.get("icon_wood").setPos(ex, ey - space + 3);
			rightObj.get("icon_iron").setPos(ex, ey + 3);
			
			for(int i=0; i<seq.length; i++) {
				txtHold = (StringRendable) rightObj.get("require"+i);
				txtHold.setText(seq[i]);
				txtHold.setPos(ex + 20, ey + space * i);
			}
		}
	}
}
