package object.appear.base;

import object.appear.bullet.FastBullet;
import object.structure.BaseAttack;
import object.structure.Bullet;
import frame.GameFrame;

public class Sniper extends BaseAttack {

	public Sniper(float ratio) {
		super("base_sniper", ratio);
		
		this.fullHp =55;
		this.fireRate = 4;
		this.woodRequire = 1600;
		this.ironRequire = 1500;
		this.rang = 250;
		this.damage= 45;
		this.farmPer = 3;
		this.maxLevel = 5;
		
		this.currentHp = this.fullHp;
	}

	@Override
	public void statIncrease() {
		this.fullHp +=35;
		this.currentHp += 55;
		this.woodRequire += 600;
		this.ironRequire += 600;
		this.damage +=35;
		this.rang += 20;
	}
	
	@Override
	protected Bullet generateBullet(float angle) {
		return new FastBullet(this, damage, getPosX(), getPosY(), angle);
	}
	
}
