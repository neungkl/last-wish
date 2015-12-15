package object.structure;

import render.RenderHelper;
import render.rendable.StaticImageRendable;
import essential.ZIndex;

public abstract class BaseElement extends Base implements IStat {
	
	protected int giveWood;
	protected int giveFarm;
	protected int giveIron;
	
	private boolean isDestroy;
	
	protected BaseElement(String file, int x, int y, float ratio) {
		super(20);
		image = new StaticImageRendable(file, x, y, ratio);
		image.setZ(ZIndex.OBJECT_IN_GAME);
		image.setAlign(RenderHelper.CENTER_MIDDLE);
		this.setName(file.split("_")[1]);
		
		giveFarm = giveWood = giveIron = 0;
		isDestroy = false;
	}

	public int getGiveWood() {
		return giveWood;
	}

	public int getGiveIron() {
		return giveIron;
	}

	public int getGiveFarm() {
		return giveFarm;
	}
	
	@Override
	public boolean isDestroy() {
		return isDestroy;
	}
	
	@Override
	public void destroy() {
		image.destroy();
		isDestroy = true;
	}
	
	@Override
	public String getStatString() {
		String txt = "";
		if(giveFarm > 0) txt = "Farm create : " + giveFarm + "\n";
		if(giveWood > 0) txt += "Wood create : " + giveWood + "\n";
		if(giveIron > 0) txt += "Iron create : " + giveIron + "\n";
		return txt;
	}

}
