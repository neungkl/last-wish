package object.appear;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import essential.ZIndex;
import object.structure.IObjectOnScreen;
import render.rendable.BoxRendable;
import render.rendable.Rendable;
import render.rendable.StaticImageRendable;
import render.rendable.StringRendable;
import resource.Resource;

public class IconSelector implements IObjectOnScreen {

	private StaticImageRendable icon;
	private StringRendable text;
	private BoxRendable box;
	
	private static final Color boxCol = new Color(232, 232, 232);
	private static final Color boxColFade = new Color(126, 126, 126);
	
	private boolean isAvailable;
	
	public IconSelector(String file, int x, int y, int width) {
		icon = new StaticImageRendable(file, x, y);
		icon.setRatioWithHeight(width);
		icon.setZ(ZIndex.CONTROL_BAR_OBJECT);
		icon.setName(file);
		
		box = new BoxRendable(x, y + width - 8, width, 18, boxCol);
		box.setZ(ZIndex.CONTROL_BAR_OBJECT);
		
		text = new StringRendable(file.split("_")[1], Resource.getFont("roboto", Font.BOLD, 10f), Color.BLACK, null);
		text.setPos(x + 5, y + width + 5);
		text.setZ(ZIndex.CONTROL_BAR_OBJECT);
		
		setAvailable(false);
	}
	
	public void setAvailable(boolean available) {
		icon.setPale(!available);
		
		box.setColor(available ? boxCol : boxColFade);
		
		this.isAvailable = available;
	}
	
	public StaticImageRendable getIcon() {
		return icon;
	}
	
	public void setVisible(boolean visible) {
		icon.setVisible(visible);
		text.setVisible(visible);
		box.setVisible(visible);
	}
	
	@Override
	public ArrayList<Rendable> getRendable() {
		ArrayList<Rendable> ret = new ArrayList<>();
		ret.add(icon);
		ret.add(text);
		ret.add(box);
		return ret;
	}

}
