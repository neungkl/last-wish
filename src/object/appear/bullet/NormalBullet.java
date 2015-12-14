package object.appear.bullet;

import java.awt.Color;

import object.structure.Bullet;
import render.RendableHolder;
import render.RenderHelper;
import render.rendable.CircleRendable;
import essential.ZIndex;

public class NormalBullet extends Bullet {
	
	public NormalBullet(int damage, int x, int y, int angle) {
		super(damage, x, y, angle, 2, 4);
		render = new CircleRendable(x, y, 5, colBullet, ZIndex.BULLET);
	}

	@Override
	public void update() {
		super.update();
		render.setPos(getPosX(), getPosY());
	}
}