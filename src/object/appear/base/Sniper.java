package object.appear.base;

import object.appear.bullet.FastBullet;
import object.structure.BaseAttack;
import object.structure.Bullet;
import frame.GameFrame;

public class Sniper extends BaseAttack {

	public Sniper(float ratio) {
		super("base_sniper", ratio);
		
		this.fullHp =35;
		this.fireRate = 4;
		this.woodRequire = 1300;
		this.ironRequire = 1200;
		this.rang = 300;
		this.damage= 25;
		this.farmPer = 3;
		this.maxLevel = 5;
		
		this.currentHp = this.fullHp;
	}

	@Override
	public void statIncrease() {
		this.fullHp +=15;
		this.currentHp += 15;
		this.woodRequire += 600;
		this.ironRequire += 600;
		this.damage +=25;
		this.rang += 5;
	}
	
	@Override
	protected Bullet generateBullet(float angle) {
		return new FastBullet(this, damage, getPosX(), getPosY(), angle);
	}
	
}
