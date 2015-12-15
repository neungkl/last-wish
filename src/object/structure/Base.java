package object.structure;

import render.rendable.Rendable;
import render.rendable.StaticImageRendable;

public abstract class Base implements IUpgradable, IObjectWithSingleRendable, ILive, IPhysical, IName {
	
	protected StaticImageRendable image;
	
	protected int woodRequire;
	protected int ironRequire;
	protected int farmPer;
	
	private int woodRefund;
	private int ironRefund;
	
	protected int currentLevel;
	protected int maxLevel;
	
	protected int fullHp, currentHp;
	
	private String name;
	private int physicalRadius;
	private boolean isDestroy; 
	
	protected Base(int radius) {
		physicalRadius = radius;
		woodRequire = ironRequire = farmPer = 0;
		currentLevel = maxLevel = 1;
		
		woodRefund = ironRefund = 0;
		
		fullHp = currentHp = 0;
		isDestroy = false;
	}
	
	@Override
	public Rendable getSingleRendable() {
		return image;
	}
	
	@Override
	public int getCurrentLevel() {
		return currentLevel;
	}
	
	public int getWoodRequire() {
		return woodRequire;
	}
	public int getIronRequire() {
		return ironRequire;
	}
	
	@Override
	public boolean isMaxLevel() {
		return currentLevel == maxLevel;
	}
	
	@Override
	public int getFullHp() {
		return fullHp;
	}
	
	@Override
	public int getCurrentHp() {
		return currentHp;
	}
	
	@Override
	public void decreaseHp(int hp) {
		currentHp -= hp;
		currentHp = Math.max(Math.min(currentHp, fullHp),0);
	}
	
	@Override
	public boolean isDie() {
		return currentHp <= 0;
	}
	
	@Override
	public int getPhysicalRadius() {
		return physicalRadius;
	}
	
	@Override
	public void setPhysicalRadius(int radius) {
		this.physicalRadius = radius;
	}
	
	@Override
	public int getPosX() {
		return image.getPosX();
	}
	
	@Override
	public int getPosY() {
		return image.getPosY();
	}
	
	@Override
	public double getDistance(IPhysical obj) {
		float delX = obj.getPosX() - this.getPosX();
		float delY = obj.getPosY() - this.getPosY();
		return (float) Math.sqrt(delX * delX + delY * delY);
	}
	
	@Override
	public void destroy() {
		getSingleRendable().destroy();
		isDestroy = true;
	}
	
	@Override
	public boolean isDestroy() {
		return isDestroy;
	}
	
	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public void setName(String name) {
		this.name = name;
	}
	
	public abstract void statIncrease();
	
	@Override
	public void upgrade(int level) {
		level++;
		if(level <= maxLevel) {
			ironRefund += ironRequire;
			woodRefund += woodRequire;
			statIncrease();
		} else {
			level = maxLevel;
		}
		this.currentLevel = level;
	}
	
	public int getFarmPer() {
		return farmPer;
	}
	
	public int getIronRefund() {
		return ironRefund * 3 / 10;
	}
	
	public int getWoodRefund() {
		return woodRefund * 3 / 10;
	}
	
	@Override
	public boolean isHitTest(IPhysical obj) {
		int delX = getPosX() - obj.getPosX();
		int delY = getPosY() - obj.getPosY();
		int radius = getPhysicalRadius() + obj.getPhysicalRadius();
		return delX * delX + delY * delY <= radius * radius;
	}
}
