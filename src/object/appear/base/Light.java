package object.appear.base;

import object.structure.BaseAttack;
import frame.GameFrame;

public class Light extends BaseAttack {

	public Light(float ratio) {
		super("base_light", ratio);
		
		this.fullHp = 50;
		this.damage = 7;
		this.fireRate = 1;
		this.woodRequire = 450;
		this.ironRequire =420;
		this.rang = 100;
		this.farmPer = 2;
		this.maxLevel = 5;
		
		this.currentHp = this.fullHp;
	}
	
	public static boolean canBuild(int farm, int wood, int iron) {
		return 
			farm >= 2&&
			wood >= 350 &&
			iron >= 300;
	}

	@Override
	public void upgrade(int level) {
		level++;
		if(level <= maxLevel) {
			this.fullHp += 20;
			this.currentHp += 20;
			this.damage += 3;
			this.woodRequire += 200;
			this.ironRequire += 220;
			this.rang += 1;
		} else {
			level = maxLevel;
		}
		this.currentLevel = level;
	}
	
	@Override
	public void attack(GameFrame gameFrame) {
		//System.out.println("shooter1 attack");
	}
	
}
