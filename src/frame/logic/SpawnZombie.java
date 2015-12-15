package frame.logic;

import java.util.ArrayList;

import essential.Config;
import essential.RandUtil;

public class SpawnZombie implements Runnable {
	
	public static SpawnZombie instance = null;
	
	public boolean isWait;
	
	private boolean isStop;
	private int maximumZombie;
	private int currentZombie;
	
	private ArrayList<String> zombieSpawn;
	
	private SpawnZombie() {
		isStop = false;
		maximumZombie = Config.INITIAL_ZOMBIE_SIZE;
		currentZombie = maximumZombie;
		zombieSpawn = new ArrayList<>();
		
		isWait = false;
		
		Thread t = new Thread(this);
		t.start();
		
	}
	
	public static void start() {
		instance = new SpawnZombie();
	}
	
	public static void stop() {
		instance.zombieSpawn.clear();
		instance.isStop = true;
	}
	
	public static synchronized String getZombieSpawnName() {
		if(instance.zombieSpawn.size() == 0) return null;
		return instance.zombieSpawn.remove(0);
	}

	@Override
	public void run() {
		try {
			synchronized (this) {
				isWait = true;
				this.wait();
				isWait = false;
			}
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
		while(true) {
			if(isStop) break;
			
			try {
				Thread.sleep(RandUtil.rand(1500));
			} catch(InterruptedException e) {}
			
			int rand = RandUtil.rand(100);
			String zombieName = "normal";
			
			if(rand < 50) zombieName = "normal";
			else if(rand < 80) zombieName = "legionary";
			else zombieName = "warrior"; 
			
			zombieSpawn.add(zombieName);
			
			if(--currentZombie == 0) {
				try {
					synchronized (this) {
						isWait = true;
						this.wait();
						isWait = false;
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				currentZombie = maximumZombie;
			}
		}	
	}
	
	public synchronized static void increaseMaximumZombie() {
		instance.maximumZombie++;
	}
}
