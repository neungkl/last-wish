package object.appear.zombie;

import object.structure.Zombie;

public class WarriorZombie extends Zombie {

	public WarriorZombie(int level) {
		super("warrior", 15, 0.6f);
		setName("Warrior Skeleton (level " + level + ")");
		
		this.fullHp = this.currentHp = 15 + 5 * level;
		this.damage = 15 + 3 * level;
		this.speed = 0.5f;
	}
	
	@Override
	protected final void switchState(int state) {
		currentState = state;
		currentActive.setVisible(false);
		switch(state) {
		case MOVE :
			currentActive = move;
			currentActive.loop();
			break;
		case ATTACK :
			currentActive = attack;
			currentActive.loop(20);
			break;
		case DIE :
			currentActive = death;
			currentActive.play();
			break;
		}
		currentActive.setVisible(true);
	}

	
}
