package frame;

import input.HighlightObjectListener;
import input.MouseInteractiveListener;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.HashMap;

import object.appear.IconSelector;
import object.structure.Base;
import object.structure.ILive;
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
	
	private Color bgCol, bgNonActiveCol;
	
	private HashMap<String, IconSelector> baseObj;
	private HashMap<String, IconSelector> defenseObj;
	private ArrayList<Rendable> rightObj;
	
	private GameFrame gameFrame;
	
	public GameControlPanel(GameFrame gameFrame, int y, int height) {
		
		this.gameFrame = gameFrame;
		this.y = y;
		this.height = height;
		
		roboto = Resource.getFont("roboto");
		
		bgCol = new Color(44, 49, 51);
		bgNonActiveCol = new Color(162, 172, 176);
		
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
		
		String[] fileList = {"farm","logger","ironworks","barn","warehouse"};
		
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
		rightObj = new ArrayList<>();
		RendableHolder.add(new BoxRendable(GameScreen.WIDTH - 300, y - 80, 300, height + 75, bgCol, ZIndex.CONTROL_BAR));
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
	
	public void showStat(Base base) {
		
		int px = GameScreen.WIDTH - 300;
		int py = y - 80;
		
		int ex = GameScreen.WIDTH - 80;
		int ey = GameScreen.HEIGHT - 50;
		
		Rendable hold;
		
		RendableHolder.remove(rightObj);
		rightObj.clear();
		
		String name = base.getSingleRendable().getName();
		name = name.split("_")[1];
		name = Character.toString(name.charAt(0)).toUpperCase()+name.substring(1);
		
		rightObj.add(
			new StringRendable(
				name,
				Resource.getFont("roboto", Font.BOLD, 18f), 
				px + 10, py + 22, Color.WHITE, null, 
				ZIndex.CONTROL_BAR_OBJECT
			)
		);
		
		py += 20;
		
		if(base instanceof ILive) {
			
			ILive live = (ILive) base;
			
			
			rightObj.add(
				new StringRendable(
					"HP", 
					Resource.getFont("roboto", Font.PLAIN, 16f), 
					px + 10, py + 25, Color.WHITE, null, 
					ZIndex.CONTROL_BAR_OBJECT
				)
			);
			rightObj.add(
				new StringRendable(
					live.getCurrentHp()+"/"+live.getFullHp(), 
					Resource.getFont("roboto", Font.PLAIN, 14f), 
					px + 45, py + 45, Color.WHITE, null, 
					ZIndex.CONTROL_BAR_OBJECT
				)
			);
			
			hold = new BoxRendable(px + 50, py + 10, 220, 18, Color.RED, ZIndex.CONTROL_BAR_OBJECT);
			rightObj.add(hold);
			
			py += 38;
		}
		
		if(base instanceof IStat) {
			hold = new StringRendable(
				((IStat) base).getStatString(), 
				Resource.getFont("roboto", Font.PLAIN, 15f), 
				px + 12, py + 32, new Color(250, 250, 250), null,
				ZIndex.CONTROL_BAR_OBJECT
			);
			rightObj.add(hold);
		}
		
		String[] seq = {
			"Wood : " + base.getWoodRequire(),
			"Iron : " + base.getIronRequire()
		};
		
		
		for(int i=0; i<seq.length; i++) {
			hold = new StringRendable(
				seq[i], 
				Resource.getFont("roboto", Font.PLAIN, 12f), 
				ex, ey + 15 * i, new Color(250, 250, 250), null,
				ZIndex.CONTROL_BAR_OBJECT
			);
			rightObj.add(hold);
		}
		
		RendableHolder.add(rightObj);
	}
}
