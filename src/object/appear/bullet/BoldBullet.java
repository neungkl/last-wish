package object.appear.bullet;

import object.structure.Bullet;
import object.structure.IAttackable;
import render.rendable.CircleRendable;
import essential.ZIndex;

public class BoldBullet extends Bullet {
	
	public BoldBullet(IAttackable parent, int damage, int x, int y, float angle) {
		super(damage, x, y, angle, 2, 7);
		this.parent = parent;
		render = new CircleRendable(x, y, 7, colBullet, ZIndex.BULLET);
	}

}