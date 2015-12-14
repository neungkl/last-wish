package essential;

import java.util.Random;

public class RandUtil {
	public static final Random rand = new Random();
	
	public static int random(int s, int e) {
		return rand.nextInt(e-s+1) + s;
	}
	
	public static int rand(int s) {
		return rand.nextInt(s);
	}
}
