package frame;

import java.awt.Graphics2D;

import input.InputFlag;
import input.MouseInteractiveListener;
import base.GameScreen;
import base.GameState;
import base.ZIndex;
import object.TileBackground;
import render.RendableHolder;
import render.RenderHelper;
import render.StaticImageRendable;

public class MenuFrame implements Frame {
	public MenuFrame() {
		StaticImageRendable bg = new StaticImageRendable("menu_bg");
		float ratio = Math.min((float) GameScreen.WIDTH / bg.getWidth(), (float) GameScreen.HEIGHT / bg.getHeight());
		
		bg.setPos(GameScreen.WIDTH / 2, GameScreen.HEIGHT / 2);
		bg.setAlign(RenderHelper.CENTER_MIDDLE);
		bg.setRatio(ratio);
		bg.setZ(ZIndex.BACKGROUND);
		
		RendableHolder.getInstance().add(bg);
		
		String[] btnName = {"start_btn", "highscore_btn", "exit_btn"};
		
		for(int i=0; i<btnName.length; i++) {
			StaticImageRendable btn = new StaticImageRendable(btnName[i], GameScreen.WIDTH / 2, GameScreen.HEIGHT - 400 + i * 125, 1.1f);
			
			String currentBtnName = btnName[i];
			
			btn.setAlign(RenderHelper.CENTER_MIDDLE);
			btn.setZ(ZIndex.MENU_BTN);
			btn.addMouseInteractiveListener(new MouseInteractiveListener() {
				
				@Override
				public void onLeave() {
					btn.setHoverEffect(false);
				}
				
				@Override
				public void onEnter() {
					btn.setHoverEffect(true);
				}
				
				@Override
				public void onClick() {
					if(currentBtnName.equals("start_btn")) {
						GameState.getInstance().changeStage(GameState.GAME_STAGE);
					}
				}
			});
			RendableHolder.getInstance().add(btn);
		}
		
		StaticImageRendable logo = new StaticImageRendable("logo", GameScreen.WIDTH / 2, 250, 1.1f);
		logo.setAlign(RenderHelper.CENTER_MIDDLE);
		logo.setZ(ZIndex.MENU_BTN);
		RendableHolder.getInstance().add(logo);
	}

	@Override
	public void update() {}

	@Override
	public void pause() {}

	@Override
	public void destroy() {}
}