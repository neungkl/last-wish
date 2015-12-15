package object.appear.base;

import object.appear.bullet.NormalBullet;
import object.structure.BaseAttack;
import object.structure.Bullet;
import frame.GameFrame;

public class Light extends BaseAttack {

	public Light(float ratio) {
		super("base_light", ratio);
		
		this.fullHp = 80;
		this.damage = 7;
		this.fireRate = 0.85f;
		this.woodRequire = 350;
		this.ironRequire =320;
		this.rang =100;
		this.farmPer = 2;
		this.maxLevel = 5;
		
		this.currentHp = this.fullHp;
	}

	@Override
	public void statIncrease() {
		this.fullHp += 40;
		this.currentHp += 50;
		this.damage += 4;
		this.woodRequire += 210;
		this.ironRequire += 230;
		this.rang += 3;
	}
	
	@Override
	protected Bullet generateBullet(float angle) {
		return new NormalBullet(this, damage, getPosX(), getPosY(), angle);
	}
	
}
