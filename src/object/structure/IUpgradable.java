package object.structure;

public interface IUpgradable {
	public int getCurrentLevel();
	public void upgrade(int level);
	public boolean isMaxLevel();
}
