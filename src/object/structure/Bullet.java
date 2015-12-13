package object.structure;

public class Bullet {
	
	private int damage;
	private int angle;
	private int x,y;
	
	private int speed;
	private int radiusDisplay, radiusExplode;
	
	public Bullet(int damage, int x, int y, int angle, int speed, int radiusDisplay, int radiusExplode) {
		this.damage = damage;
		this.x = x;
		this.y = y;
		this.angle = angle;
		this.speed = speed;
		this.radiusDisplay = radiusDisplay;
		this.radiusExplode = radiusExplode;
	}
	
	
}
