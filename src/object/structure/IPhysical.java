package object.structure;

public interface IPhysical {
	public int getPosX();
	public int getPosY();
	public int getPhysicalRadius();
	public void setPhysicalRadius(int radius);
	public boolean isHitTest(IPhysical obj);
	public void destroy();
	public boolean isDestroy();
}
