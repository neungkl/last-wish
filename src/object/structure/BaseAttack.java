package object.structure;

import essential.ZIndex;
import render.RenderHelper;
import render.rendable.StaticImageRendable;

public abstract class BaseAttack extends Base implements IAttackable, ILive, IStat {

	protected int damage;
	protected int fireRate;
	protected int rang;
		
	protected int fullHp, currentHp;
	
	private int currentTimeStamp;
	
	public BaseAttack(String file, float ratio) {
		super();
		image = new StaticImageRendable(file, -1000, -1000, ratio);
		image.setZ(ZIndex.OBJECT_IN_GAME);
		image.setAlign(RenderHelper.CENTER_MIDDLE);
		image.setName(file);
		
		damage = fullHp = currentHp = 0;
		fireRate = 1;
	}
	
	protected void setup(int hp, int damage, int fireRate, int woodRequire, int farmRequire, int ironRequire, int rang) {
		currentHp = fullHp = hp;
		this.damage = damage;
		this.fireRate = fireRate;
		this.woodRequire = woodRequire;
		this.farmRequire = farmRequire;
		this.ironRequire = ironRequire;
		this.rang = rang;
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
	public int getFullHp() {
		return fullHp;
	}

	@Override
	public int getCurrentHp() {
		return currentHp;
	}

	@Override
	public void decreaseHp(int hp) {
		currentHp -= hp;
		currentHp = Math.max(0, Math.min(currentHp, fullHp));
	}

	@Override
	public boolean isDie() {
		return currentHp <= 0;
	}
	
	@Override
	public String getStatString() {
		String txt = "Damage : " + damage + "     ";
		txt += "Firerate : " + fireRate + "\n";
		txt += "Rang : " + rang;
		return txt;
	}

}
