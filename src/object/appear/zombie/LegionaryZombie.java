package object.appear.zombie;

import object.structure.Zombie;

public class LegionaryZombie extends Zombie {

	public LegionaryZombie(int level) {
		super("legionary", 15, 0.6f);
		setName("Legionary Skeleton (level " + level + ")");
		
		this.fullHp = this.currentHp = 12 + 4 * level;
		this.damage = 15 + 5 * level;
		this.speed = 0.75f;
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
