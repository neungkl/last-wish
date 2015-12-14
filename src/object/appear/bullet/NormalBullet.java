package object.appear.bullet;

import object.structure.Bullet;
import object.structure.IAttackable;
import render.rendable.CircleRendable;
import essential.ZIndex;

public class NormalBullet extends Bullet {
	
	public NormalBullet(IAttackable parent, int damage, int x, int y, float angle) {
		super(damage, x, y, angle, 2, 5);
		this.parent = parent;
		render = new CircleRendable(x, y, 5, colBullet, ZIndex.BULLET);
	}

}