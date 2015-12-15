package object.appear.zombie;

import object.structure.Zombie;

public class OdinaryZombie extends Zombie {

	public OdinaryZombie(int level) {
		super("normal", 15, 0.6f);
		setName("Skeleton (level " + level + ")");
		
		this.fullHp = this.currentHp = 9 + 2 * level;
		this.damage = 7 + 1* level;
		this.speed = 1f;
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
