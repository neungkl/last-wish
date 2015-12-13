package render.effect;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;

public interface IHoverEffect {
	public void setHoverEffect(boolean use);
	public boolean isHoverEffect();
	public void drawHoverEffect(Graphics2D g);
}
