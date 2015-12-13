package frame;

import input.MouseInteractiveListener;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.HashMap;

import object.appear.IconSelector;
import render.RendableHolder;
import render.rendable.BoxRendable;
import render.rendable.Rendable;
import render.rendable.StaticImageRendable;
import render.rendable.StringRendable;
import resource.Resource;
import base.GameScreen;
import base.ZIndex;

public class GameControlPanel {
	
	private int x, y;
	private int height;
	private Font roboto;
	
	private boolean isSelectBase;
	private boolean isDragAndDrop;
	
	private Color bgCol, bgNonActiveCol;
	
	private HashMap<String, IconSelector> baseObj;
	private HashMap<String, IconSelector> defenseObj;
	private ArrayList<Rendable> rightObj;
	
	private GameFrame gameFrame;
	
	public GameControlPanel(GameFrame gameFrame, int y, int height) {
		
		this.gameFrame = gameFrame;
		this.y = y;
		this.height = height;
		this.isDragAndDrop = false;
		
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
		
		String[] fileList = {"farm","logger","ironworks","barn","warehouse","light","tank","sniper","bazuka"};
		
		for(int i=0; i<fileList.length; i++) {
			IconSelector s = new IconSelector("icon_" + fileList[i], 10 + 80 * i, this.y + 10, 70);
			StaticImageRendable icon = s.getIcon();
			icon.addMouseInteractiveListener(new MouseInteractiveListener<StaticImageRendable>() {
				
				@Override
				public void onLeave(StaticImageRendable icon) {
					icon.setHoverEffect(false);
				}
				
				@Override
				public void onEnter(StaticImageRendable icon) {
					icon.setHoverEffect(true);
				}
				
				@Override
				public void onClick(StaticImageRendable icon) {
					isDragAndDrop = true;
				}
			});
			baseObj.put(fileList[i], s);
		}
		
		fileList = new String[]{"sniper"};
		for(int i=0; i<fileList.length; i++) {
			IconSelector s = new IconSelector("icon_" + fileList[i], 10 + 80 * i, this.y + 10, 70);
			StaticImageRendable icon = s.getIcon();
			icon.addMouseInteractiveListener(new MouseInteractiveListener<StaticImageRendable>() {
				
				@Override
				public void onLeave(StaticImageRendable icon) {
					icon.setHoverEffect(false);
				}
				
				@Override
				public void onEnter(StaticImageRendable icon) {
					icon.setHoverEffect(true);
				}
				
				@Override
				public void onClick(StaticImageRendable icon) {
					isDragAndDrop = true;
				}
			});
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
		RendableHolder.add(new BoxRendable(GameScreen.WIDTH - 300, y - 75, 300, height + 75, bgCol, ZIndex.CONTROL_BAR));
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
	
	public boolean isDragAndDrop() {
		return isDragAndDrop;
	}
}
