package frame.logic;

import java.awt.Color;
import java.util.ArrayList;

import object.appear.base.Farm;
import object.appear.base.Warehouse;
import object.structure.Base;
import render.RendableHolder;
import render.rendable.BoxRendable;
import essential.GameScreen;
import essential.ZIndex;

public class GameStat {
	public static GameStat instance = new GameStat();
	private int farm, wood, iron;
	
	private static final int defaultFarm = 20;
	private static final int defaultWood = 500;
	private static final int defaultIron = 500;
	
	private static final Color bgCol = new Color(44, 49, 51);
	
	private int maximumWood, maximumIron, maximumFarm;
	
	private GameStat() {
		reset();
	}
	
	public void reset() {
		maximumWood = defaultWood;
		maximumIron = defaultIron;
		maximumFarm = defaultFarm;
		
		wood = defaultWood;
		iron = defaultIron;
		farm = 0;
		
		int x = GameScreen.WIDTH - 200;
		
		RendableHolder.add(new BoxRendable(x, 0, 200, 80, bgCol, ZIndex.CONTROL_BAR_OBJECT));
		//RendableHolder.add(new)
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
		for(Base base : baseList) {
			if(base instanceof Farm) {
				maximumFarm = ((Farm) base).getGiveFarm() + defaultFarm;
			}
			if(base instanceof Warehouse) {
				maximumWood = ((Warehouse) base).getMaximumWood() + defaultWood;
				maximumIron = ((Warehouse) base).getMaximumIron() + defaultIron;
			}
			farm += base.getFarmPer();
		}
	}
	
	public boolean canBuild(Base b) {
		if(getFarm() >= b.getFarmPer() && getWood() >= b.getWoodRequire() && getIron() >= b.getIronRequire()) {
			return true;
		}
		return false;
	}
}
