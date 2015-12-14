package object.appear.bullet;

import object.structure.Bullet;
import object.structure.IAttackable;
import render.rendable.CircleRendable;
import essential.ZIndex;

public class FastBullet extends Bullet {
	
	public FastBullet(IAttackable parent, int damage, int x, int y, int angle) {
		super(damage, x, y, angle, 7, 3);
		this.parent = parent;
		render = new CircleRendable(x, y, 3, colBullet, ZIndex.BULLET);
	}
}