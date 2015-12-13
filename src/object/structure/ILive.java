package object.structure;

public interface ILive {
	public int getFullHp();
	public int getCurrentHp();
	public void decreaseHp(int hp);
	public boolean isDie();
}
