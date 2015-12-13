package object.structure;

public interface IUpgradable {
	public int getCurrentLevel();
	public void upgrade(int level);
	public int getWoodRequire();
	public int getIronRequire();
	public int getFarmRequire();
	public boolean isMaxLevel();
}
