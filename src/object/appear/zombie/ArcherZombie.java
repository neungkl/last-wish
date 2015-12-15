package object.appear.zombie;

import object.structure.Zombie;

public class ArcherZombie extends Zombie {

	public ArcherZombie(int level) {
		super("archer", 15, 0.6f);
		setName("Archer Skeleton (level " + level + ")");
		
		this.fullHp = this.currentHp = 9 + 4 * level;
		this.damage = 13 + 4 * level;
		this.speed = 1.5f;
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
