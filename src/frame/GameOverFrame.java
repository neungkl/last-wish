package frame;

import javax.swing.JOptionPane;

import render.RendableHolder;
import render.rendable.StaticImageRendable;
import resource.Resource;
import essential.GameState;
import frame.logic.HighScore;

public class GameOverFrame implements Frame {
	
	public GameOverFrame() {
		StaticImageRendable bg = new StaticImageRendable("bg_game_over", 0, 0, 1f, 0);
		RendableHolder.add(bg);
		
		Resource.getSound("end_bg").loop();
		Resource.getSound("game_over_speak").play();
	}

	@Override
	public void update() {
		String name = null;
		while(name == null) {
			name = JOptionPane.showInputDialog("Enter you name : ");
		}
		
		HighScore.instance.add(name);
		HighScore.instance.showDialog();
		
		GameState.getInstance().changeStage(GameState.MENU_STAGE);
	}

	@Override
	public void pause() {}
	
	@Override
	public void resume() {}

	@Override
	public void destroy() {
		Resource.getSound("end_bg").stop();
	}
}