package object.structure;

import render.RenderHelper;
import render.rendable.StaticImageRendable;
import essential.ZIndex;

public abstract class BaseResourceLimit extends Base implements IStat {
	
	protected int maximumIron, maximumWood;
	
	protected BaseResourceLimit(String file, float ratio) {
		super(20);
		image = new StaticImageRendable(file, -1000, -1000, ratio);
		image.setZ(ZIndex.OBJECT_IN_GAME);
		image.setAlign(RenderHelper.CENTER_MIDDLE);
		this.setName(file.split("_")[1]);
		
		maximumIron = maximumWood = 0;
	}

	public int getMaximumWood() {
		return maximumWood;
	}

	public int getMaximumIron() {
		return maximumIron;
	}

	@Override
	public String getStatString() {
		String txt = "";
		if(maximumWood > 0) txt += "Wood capacity : " + maximumWood + "\n";
		if(maximumIron > 0) txt += "Iron capacity : " + maximumIron + "\n";
		return txt;
	}
}
