package object.structure;

import frame.GameFrame;

public interface IAttackable {
	public float getDamage();
	public float getFireRate();
	public int getRange();
	public void increaseTime();
	public boolean isAttack();
	public void attack(GameFrame gameFrame);
}
