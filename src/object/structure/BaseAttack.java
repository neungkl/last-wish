package object.structure;

import essential.ZIndex;
import render.RenderHelper;
import render.rendable.StaticImageRendable;

public abstract class BaseAttack extends Base implements IAttackable, ILive, IStat {

	protected int damage;
	protected int fireRate;
	protected int rang;
	
	private boolean isDestroy;
	private int currentTimeStamp;
	
	public BaseAttack(String file, float ratio) {
		super();
		image = new StaticImageRendable(file, -1000, -1000, ratio);
		image.setZ(ZIndex.OBJECT_IN_GAME);
		image.setAlign(RenderHelper.CENTER_MIDDLE);
		image.setName(file);
		
		damage = rang = 0;
		fireRate = 1;
		isDestroy = false;
		currentTimeStamp = 0;
	}

	@Override
	public int getDamage() {
		return damage;
	}

	@Override
	public int getFireRate() {
		return fireRate;
	}
	
	@Override
	public int getRang() {
		return rang;
	}

	@Override
	public void increaseTime() {
		currentTimeStamp++;
	}

	@Override
	public boolean isAttack() {
		return currentTimeStamp%fireRate == 0;
	}
	
	@Override
	public boolean isDestroy() {
		return isDestroy;
	}
	
	@Override
	public void destroy() {
		image.destroy();
		isDestroy = true;
	}
	
	@Override
	public String getStatString() {
		String txt = "Damage : " + damage + "     ";
		txt += "Firerate : " + fireRate + "\n";
		txt += "Rang : " + rang;
		return txt;
	}
	
}
