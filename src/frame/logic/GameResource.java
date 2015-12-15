package frame.logic;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import object.appear.base.Farm;
import object.appear.base.Ironworks;
import object.appear.base.Logger;
import object.appear.base.Warehouse;
import object.structure.Base;
import object.structure.BaseElement;
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
	
	private static final int defaultFarm = 20;
	private static final int defaultWood = 500;
	private static final int defaultIron = 500;
	
	private static final Color bgCol = new Color(44, 49, 51);
	private static final Font font = Resource.getFont("roboto", Font.PLAIN, 13f);
	
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
		
		RendableHolder.add(new BoxRendable(x, 0, 200, 80, bgCol, ZIndex.CONTROL_BAR_OBJECT));
		RendableHolder.add(new StaticImageRendable("icon_mini_farm", x + 10, 10, 0.5f, ZIndex.CONTROL_BAR_OBJECT));
		RendableHolder.add(new StaticImageRendable("icon_mini_wood", x + 10, 30, 0.5f, ZIndex.CONTROL_BAR_OBJECT));
		RendableHolder.add(new StaticImageRendable("icon_mini_iron", x + 10, 50, 0.5f, ZIndex.CONTROL_BAR_OBJECT));
		
		if(font != null) {
			farmRender = new StringRendable("", font, x + 50, 23, Color.WHITE, null, ZIndex.CONTROL_BAR_OBJECT);
			RendableHolder.add(farmRender);
			woodRender = new StringRendable("", font, x + 50, 46, Color.WHITE, null, ZIndex.CONTROL_BAR_OBJECT);
			RendableHolder.add(woodRender);
			ironRender = new StringRendable("", font, x + 50, 69, Color.WHITE, null, ZIndex.CONTROL_BAR_OBJECT);
			RendableHolder.add(ironRender);
			updateStatRender();
		}
	}
	
	public void updateStatRender() {
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
	
	public void updateBaseStat(ArrayList<Base> baseList) {
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
		return canUpgrade(b);
	}
	
	public boolean canUpgrade(Base b) {
		if(getFarm() >= b.getFarmPer() && getWood() >= b.getWoodRequire() && getIron() >= b.getIronRequire()) {
			return true;
		}
		return false;
	}
}
