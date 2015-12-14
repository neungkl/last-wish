package object.appear.zombie;

import object.structure.Zombie;

public class OdinaryZombie extends Zombie {

	public OdinaryZombie() {
		super("normal", 20, 0.5f, 5, 15, 0.6f);
		setName("Skeleton");
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
