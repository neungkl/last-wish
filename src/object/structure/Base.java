package object.structure;

import java.util.ArrayList;

import render.rendable.Rendable;
import render.rendable.StaticImageRendable;

public abstract class Base implements IUpgradable, IObjectWithSingleImage {
	
	protected StaticImageRendable image;
	
	protected int woodRequire;
	protected int farmRequire;
	protected int ironRequire;
	
	protected int currentLevel;
	protected int maxLevel;
	
	public Base() {
		woodRequire = farmRequire = ironRequire = 0;
		currentLevel = maxLevel = 1;
	}
	
	@Override
	public StaticImageRendable getImage() {
		return image;
	}
	@Override
	public int getCurrentLevel() {
		return currentLevel;
	}
	
	@Override
	public int getWoodRequire() {
		return woodRequire;
	}
	@Override
	public int getIronRequire() {
		return ironRequire;
	}
	@Override
	public int getFarmRequire() {
		return farmRequire;
	}
	
	@Override
	public boolean isMaxLevel() {
		return currentLevel == maxLevel;
	}
}
