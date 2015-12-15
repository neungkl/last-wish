package essential;

import render.RendableHolder;
import frame.Frame;
import frame.GameFrame;
import frame.GameOverFrame;
import frame.GameWin;
import frame.MenuFrame;

public class GameState {
	private static final GameState instance = new GameState();

	public static boolean IS_PAUSING = false;

	public static final int MENU_STAGE = 0;
	public static final int GAME_STAGE = 1;
	public static final int GAME_OVER_STAGE = 2;
	public static final int GAME_WIN = 3;

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
		GameState.IS_PAUSING = false;

		if (currentFrame != null)
			currentFrame.destroy();

		switch (stageIndex) {
		case MENU_STAGE:
			currentFrame = new MenuFrame();
			break;
		case GAME_STAGE:
			currentFrame = new GameFrame();
			break;
		case GAME_OVER_STAGE :
			currentFrame = new GameOverFrame();
			break;
		case GAME_WIN :
			currentFrame = new GameWin();
			break;
		}
	}

}
