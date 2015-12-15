package object.appear.bullet;

import object.structure.Bullet;
import object.structure.IAttackable;
import render.rendable.CircleRendable;
import essential.ZIndex;

public class MiniBullet extends Bullet {
	
	public MiniBullet(IAttackable parent, float damage, int x, int y, float angle) {
		super(damage, x, y, angle, 2, 3);
		this.parent = parent;
		render = new CircleRendable(x, y, 3, colBullet, ZIndex.BULLET);
	}

}