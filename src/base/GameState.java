package base;

import render.RendableHolder;
import frame.Frame;
import frame.GameFrame;

public class GameState {
	private static final GameState instance = new GameState();
	
	public static boolean IS_PAUSING = false;
	
	public static final int MENU_STAGE = 0; 
	public static final int GAME_STAGE = 1; 
	
	private Frame currentFrame;
	
	public static final GameState getInstance() {
		synchronized (instance) {
			return instance;
		}
	}
	
	public GameState() {
		changeStage(GAME_STAGE);
	}
	
	public void update() {
		currentFrame.update();
	}
	
	public void changeStage(int stageIndex) {
		
		RendableHolder.getInstance().clear();
		if(currentFrame != null)
			currentFrame.destroy();
		
		switch(stageIndex) {
		case GAME_STAGE :
			currentFrame = new GameFrame();
			break;
		}
	}
	
}
