package object.structure;

public interface IUpgradable {
	public void getCurrentLevel();
	public void upgrade(int level);
	public int getWoodRequire();
	public int getIconRequire();
	public int getFarmRequire();
}
