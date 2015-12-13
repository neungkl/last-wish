package object.structure;


public abstract class BaseShooter extends BaseAttack implements IShooter {
	
	public BaseShooter(String file, float ratio) {
		super(file, ratio);
	}

	@Override
	public void rotateTo(int x, int y) {
		this.getImage().rotateTo(x, y);
	}
}
