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

	StaticImageRendable icon;
	StringRendable text;
	BoxRendable box;
	
	public IconSelector(String file, int x, int y, int width) {
		icon = new StaticImageRendable(file, x, y);
		icon.setRatioWithHeight(width);
		icon.setZ(ZIndex.CONTROL_BAR_OBJECT);
		icon.setName(file);
		
		box = new BoxRendable(x, y + width - 8, width, 18, new Color(232, 232, 232));
		box.setZ(ZIndex.CONTROL_BAR_OBJECT);
		
		text = new StringRendable(file.split("_")[1], Resource.getFont("roboto", Font.BOLD, 10f), Color.BLACK, null);
		text.setPos(x + 5, y + width + 5);
		text.setZ(ZIndex.CONTROL_BAR_OBJECT);
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
