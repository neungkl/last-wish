package frame.logic;

import essential.GameScreen;

public class SpawnZombie implements Runnable {
	private static SpawnZombie instance = null;
	
	private int currentTimeStamp;
	private int currentTimeInSecond;
	
	private boolean shouldSpawnZombie = false;
	
	private final int deltaTime = 1000 / GameScreen.FRAMERATE; 
	
	private SpawnZombie() {
		
		currentTimeStamp = currentTimeInSecond = 0;
		
		Thread t = new Thread(this);
		t.start();
	}
	
	public static void start()  {
		if(instance == null) {
			instance = new SpawnZombie();
		}
	}

	@Override
	public void run() {
		while(true) {
			try {
				Thread.sleep(deltaTime);
			} catch(InterruptedException e) {}
			
			currentTimeStamp += deltaTime;
			if(currentTimeStamp / 1000 != currentTimeInSecond) {
				currentTimeInSecond = currentTimeStamp / 1000;
				
				if(currentTimeInSecond%2 == 0) {
					setShouldSpawnZombie(true);
				}
			}
		}
	}
	
	public static int getSecond() {
		synchronized (instance) {
			return instance.currentTimeInSecond;
		}
	}
	
	public static synchronized void setShouldSpawnZombie(boolean shouldSpawnZombie) {
		instance.shouldSpawnZombie = shouldSpawnZombie;
	}
	
	public static synchronized boolean shouldSpawnZombie() {
		return instance.shouldSpawnZombie;
	}
}
