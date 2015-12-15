package object.appear.bullet;

import object.structure.Bullet;
import object.structure.IAttackable;
import render.rendable.CircleRendable;
import essential.ZIndex;

public class BazukaBullet extends Bullet {
	
	public BazukaBullet(IAttackable parent, float damage, int x, int y, float angle) {
		super(damage, x, y, angle, 1.2f, 10);
		this.parent = parent;
		render = new CircleRendable(x, y, 10, colBullet, ZIndex.BULLET);
	}
}