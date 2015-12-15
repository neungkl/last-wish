package object.structure;

import object.appear.bullet.NormalBullet;
import render.RendableHolder;
import frame.GameFrame;


public abstract class BaseShooter extends BaseAttack {
	
	protected BaseShooter(String file, float ratio) {
		super(file, ratio);
		setPhysicalRadius(25);
	}

	public void rotateTo(int x, int y) {
		this.getSingleRendable().rotateTo(x, y);
	}
	
	@Override
	public void attack(GameFrame gameFrame) {
		Bullet b = generateBullet(getSingleRendable().getAngle());
		RendableHolder.add(b);
		gameFrame.getBulletList().add(b);
	}
}
