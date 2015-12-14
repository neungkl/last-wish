package object.structure;


public abstract class BaseShooter extends BaseAttack implements IShooter {
	
	protected BaseShooter(String file, float ratio) {
		super(file, ratio);
		setPhysicalRadius(25);
	}

	@Override
	public void rotateTo(int x, int y) {
		this.getSingleRendable().rotateTo(x, y);
	}
}
