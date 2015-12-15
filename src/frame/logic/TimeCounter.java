package frame.logic;

import essential.Config;
import essential.GameScreen;

public class TimeCounter implements Runnable {
	public static TimeCounter instance = null;
	
	private int currentTimeStamp;
	private int currentTimeInSecond;
	private int currentWave;
	
	public boolean isWait;
	private boolean isNewSecond;
	private boolean isStop;
	
	private final int deltaTime = 1000 / GameScreen.FRAMERATE; 
	
	private TimeCounter() {
		
		currentTimeStamp = currentTimeInSecond = 0;
		currentWave = 0;
		isNewSecond = isStop = false;
		isWait = false;
		
		Thread t = new Thread(this);
		t.start();
	}
	
	public static void stop() {
		instance.isStop = true;
	}
	
	public static void start()  {
		instance = new TimeCounter();
	}

	@Override
	public void run() {
		while(true) {
			
			if(isStop) break;
			if(isWait) {
				synchronized (this) {
					try {
						wait();
						isWait = false;
					} catch(InterruptedException e) {}
				}
			}
			
			try {
				Thread.sleep(deltaTime);
			} catch(InterruptedException e) {}
			
			currentTimeStamp += deltaTime;
			if(currentTimeStamp / 1000 != currentTimeInSecond) {
				currentTimeInSecond = currentTimeStamp / 1000;
				
				if(currentTimeInSecond == Config.FIRST_SPAWN_TIME) {
					synchronized (SpawnZombie.instance) {
						if(SpawnZombie.instance.isWait) {
							SpawnZombie.instance.notifyAll();
						}
					}
					currentWave++;
				} else if(
					currentTimeInSecond > Config.FIRST_SPAWN_TIME && 
					(currentTimeInSecond - Config.FIRST_SPAWN_TIME) % Config.SPAWN_TIME_EACH_WAVE == 0
				) {
					synchronized (SpawnZombie.instance) {
						if(SpawnZombie.instance.isWait) {
							SpawnZombie.instance.notifyAll();
						}
					}
					currentWave++;
				}
				
				isNewSecond = true;
			}
		}
	}
	
	public static int getSecond() {
		synchronized (instance) {
			return instance.currentTimeInSecond;
		}
	}
	
	public static synchronized void setNewSecond(boolean isNewSecond) {
		instance.isNewSecond = isNewSecond;
	}
	
	public static synchronized boolean isNewSecond() {
		return instance.isNewSecond;
	}
	
	public static synchronized int getCurrentWave() {
		return instance.currentWave;
	}
}
