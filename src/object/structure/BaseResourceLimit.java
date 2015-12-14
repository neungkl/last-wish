package object.structure;

import render.RenderHelper;
import render.rendable.StaticImageRendable;
import essential.ZIndex;

public abstract class BaseResourceLimit extends Base implements IBaseResourceLimit, IStat {
	
	protected int maximumIron, maximumWood, maximumFarm;
	
	protected BaseResourceLimit(String file, float ratio) {
		super(20);
		image = new StaticImageRendable(file, -1000, -1000, ratio);
		image.setZ(ZIndex.OBJECT_IN_GAME);
		image.setAlign(RenderHelper.CENTER_MIDDLE);
		image.setName(file);
		
		maximumIron = maximumWood = maximumFarm = 0;
	}

	@Override
	public int getMaximumWood() {
		return maximumWood;
	}

	@Override
	public int getMaximumIron() {
		return maximumIron;
	}

	@Override
	public int getMaximumFarm() {
		return maximumFarm;
	}

	@Override
	public String getStatString() {
		String txt = "";
		if(maximumFarm > 0) txt += "Maximum farm capacity : " + maximumFarm + "\n";
		if(maximumWood > 0) txt += "Maximum wood capacity : " + maximumWood + "\n";
		if(maximumIron > 0) txt += "Maximum iron capacity : " + maximumIron + "\n";
		return txt;
	}
}
