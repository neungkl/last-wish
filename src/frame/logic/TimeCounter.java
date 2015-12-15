package frame.logic;

import essential.GameScreen;

public class TimeCounter implements Runnable {
	private static TimeCounter instance = null;
	
	private int currentTimeStamp;
	private int currentTimeInSecond;
	
	private boolean shouldSpawnZombie = false;
	private boolean isNewSecond = false;
	private boolean isStop = false;
	
	private final int deltaTime = 1000 / GameScreen.FRAMERATE; 
	
	private TimeCounter() {
		
		currentTimeStamp = currentTimeInSecond = 0;
		
		Thread t = new Thread(this);
		t.start();
	}
	
	public static void stop() {
		instance.isStop = true;
	}
	
	public static void start()  {
		if(instance == null) {
			instance = new TimeCounter();
		}
	}

	@Override
	public void run() {
		while(true) {
			
			if(isStop) break;
			
			try {
				Thread.sleep(deltaTime);
			} catch(InterruptedException e) {}
			
			currentTimeStamp += deltaTime;
			if(currentTimeStamp / 1000 != currentTimeInSecond) {
				currentTimeInSecond = currentTimeStamp / 1000;
				
				if(currentTimeInSecond%2 == 0) {
					setShouldSpawnZombie(true);
					isNewSecond = true;
				}
			}
		}
	}
	
	public static int getSecond() {
		synchronized (instance) {
			return instance.currentTimeInSecond;
		}
	}
	
	public static synchronized TimeCounter getInstance() {
		return instance;
	}
	
	public static synchronized void setNewSecond(boolean isNewSecond) {
		instance.isNewSecond = isNewSecond;
	}
	
	public static synchronized boolean isNewSecond() {
		return instance.isNewSecond;
	}
	
	public static synchronized void setShouldSpawnZombie(boolean shouldSpawnZombie) {
		instance.shouldSpawnZombie = shouldSpawnZombie;
	}
	
	public static synchronized boolean shouldSpawnZombie() {
		return instance.shouldSpawnZombie;
	}
}
