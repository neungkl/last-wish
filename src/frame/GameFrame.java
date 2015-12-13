package frame;

import object.appear.MainBase;
import object.appear.TileBackground;
import render.RendableHolder;
import render.RenderHelper;
import render.rendable.AnimationRendable;
import render.rendable.StaticImageRendable;
import base.GameScreen;
import base.ZIndex;

public class GameFrame implements Frame {
	
	private static final int bottomHeight = 100;
	private static int centerX, centerY;

	private GameControlPanel controlPanel;
	
	private MainBase mainBase;
	private StaticImageRendable[] cornerWall;
	
	AnimationRendable a;
	
	public GameFrame() {
		RendableHolder.add(new TileBackground("game_bg"));
		
		centerX = GameScreen.WIDTH / 2;
		centerY = (GameScreen.HEIGHT - bottomHeight) / 2;
		
		initializeRendableObject();
		
		controlPanel = new GameControlPanel(this, GameScreen.HEIGHT - bottomHeight, bottomHeight);
	}
	
	private void initializeRendableObject() {
		
		// main base
		
		mainBase = new MainBase(centerX, centerY, 0.16f);
		RendableHolder.add(mainBase);
		
		// wall corner

		int areaSpace = 250;
		
		int[][] pos = {{1,1},{1,-1},{-1,1},{-1,-1}};
		cornerWall = new StaticImageRendable[4];
		for(int i=0; i<4; i++) {
			int x = pos[i][0] * areaSpace + centerX;
			int y = pos[i][1] * areaSpace + centerY;
			
			StaticImageRendable S = new StaticImageRendable("wall_corner", x, y, 0.15f);
			S.setAlign(RenderHelper.CENTER_MIDDLE);
			S.setZ(ZIndex.OBJECT_IN_GAME);
			RendableHolder.add(S);
			cornerWall[i] = S;
		}
		
		a = new AnimationRendable("test", 50, 50, 0.5f);
		a.loop(20);
		a.setZ(ZIndex.OBJECT_IN_GAME);
		RendableHolder.add(a);
	}

	@Override
	public void update() {
		
		if(controlPanel.isDragAndDrop()) {
			try {
				synchronized (mainBase) {
					a.wait();
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		mainBase.rotate();
	}

	@Override
	public void pause() {
	}

	@Override
	public void destroy() {
	}

}
