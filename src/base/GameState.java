package base;

import render.RendableHolder;
import frame.Frame;
import frame.GameFrame;
import frame.MenuFrame;

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
		changeStage(MENU_STAGE);
	}
	
	public void update() {
		currentFrame.update();
		RendableHolder.getInstance().update();
	}
	
	public void changeStage(int stageIndex) {
		
		RendableHolder.getInstance().clear();
		if(currentFrame != null)
			currentFrame.destroy();
		
		switch(stageIndex) {
		case MENU_STAGE :
			currentFrame = new MenuFrame();
			break;
		case GAME_STAGE :
			currentFrame = new GameFrame();
			break;
		}
	}
	
}
