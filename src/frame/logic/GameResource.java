package frame.logic;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import object.appear.base.Farm;
import object.appear.base.Ironworks;
import object.appear.base.Logger;
import object.appear.base.Warehouse;
import object.structure.Base;
import render.RendableHolder;
import render.rendable.BoxRendable;
import render.rendable.StaticImageRendable;
import render.rendable.StringRendable;
import resource.Resource;
import essential.GameScreen;
import essential.ZIndex;

public class GameResource {
	public static GameResource instance = new GameResource();
	private int farm, wood, iron;
	
	private static int defaultFarm = 20;
	private static int defaultWood = 500;
	private static int defaultIron = 500;
	
	private static final Color bgCol = new Color(44, 49, 51);
	private static final Font font = Resource.getFont("roboto", Font.PLAIN, 14f);
	
	private int maximumWood, maximumIron, maximumFarm;
	
	private StringRendable farmRender, woodRender, ironRender;
	
	private GameResource() {
		reset();
	}
	
	public void reset() {
		maximumWood = defaultWood;
		maximumIron = defaultIron;
		maximumFarm = defaultFarm;
		
		wood = defaultWood;
		iron = defaultIron;
		farm = 0;
		
		int x = GameScreen.WIDTH - 150;
		
		RendableHolder.add(new BoxRendable(x, 0, 210, 85, bgCol, ZIndex.CONTROL_BAR_OBJECT));
		RendableHolder.add(new StaticImageRendable("icon_mini_farm", x + 10, 15, 0.5f, ZIndex.CONTROL_BAR_OBJECT));
		RendableHolder.add(new StaticImageRendable("icon_mini_wood", x + 10, 35, 0.5f, ZIndex.CONTROL_BAR_OBJECT));
		RendableHolder.add(new StaticImageRendable("icon_mini_iron", x + 10, 55, 0.5f, ZIndex.CONTROL_BAR_OBJECT));
		
		if(font != null) {
			farmRender = new StringRendable("", font, x + 46, 28, Color.WHITE, null, ZIndex.CONTROL_BAR_OBJECT);
			RendableHolder.add(farmRender);
			woodRender = new StringRendable("", font, x + 46, 51, Color.WHITE, null, ZIndex.CONTROL_BAR_OBJECT);
			RendableHolder.add(woodRender);
			ironRender = new StringRendable("", font, x + 46, 74, Color.WHITE, null, ZIndex.CONTROL_BAR_OBJECT);
			RendableHolder.add(ironRender);
			updateStatRender(null);
		}
	}
	
	public void updateStatRender(ArrayList<Base> baseList) {
		if(baseList != null)
			updateBaseStat(baseList);
		
		farmRender.setText(getFarm()+"");
		woodRender.setText(getWood()+"/"+maximumWood);
		ironRender.setText(getIron()+"/"+maximumIron);
	}
	
	public int getWood() {
		return wood;
	}
	
	public int getIron() {
		return iron;
	}
	
	public int getFarm() {
		return maximumFarm - farm;
	}
	
	public void addWood(int wood) {
		this.wood += wood;
		this.wood = Math.max(0, Math.min(maximumWood, this.wood));
	}
	
	public void addIron(int iron) {
		this.iron += iron;
		this.iron = Math.max(0, Math.min(maximumIron, this.iron));
	}
	
	private void updateBaseStat(ArrayList<Base> baseList) {
		farm = 0;
		
		boolean hasFarm = false;
		for(Base base : baseList) {
			if(base.isDestroy()) continue;
			if(base instanceof Farm) {
				hasFarm = true;
				maximumFarm = ((Farm) base).getGiveFarm() + defaultFarm;
			}
			if(base instanceof Warehouse) {
				maximumWood = ((Warehouse) base).getMaximumWood() + defaultWood;
				maximumIron = ((Warehouse) base).getMaximumIron() + defaultIron;
			}
			farm += base.getFarmPer();
		}
		
		if(!hasFarm) {
			maximumFarm = defaultFarm;
		}
	}
	
	public boolean canBuild(Base b, ArrayList<Base> baseList) {
		for(Base each : baseList) {
			if(each instanceof Farm && b instanceof Farm) return false;
			if(each instanceof Ironworks && b instanceof Ironworks) return false;
			if(each instanceof Logger && b instanceof Logger) return false;
			if(each instanceof Warehouse && b instanceof Warehouse) return false;
		}
		if(getFarm() >= b.getFarmPer()) {
			return canUpgrade(b);
		}
		return false;
	}
	
	public void cheatEnable() {
		maximumFarm = farm = defaultFarm = 10000;
		maximumWood = wood = defaultWood = 10000;
		maximumIron = iron = defaultIron = 10000;
	}
	
	public boolean canUpgrade(Base b) {
		if(getWood() >= b.getWoodRequire() && getIron() >= b.getIronRequire()) {
			return true;
		}
		return false;
	}
}
