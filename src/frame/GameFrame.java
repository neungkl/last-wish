package frame;

import object.TileBackground;
import render.RendableHolder;
import render.StaticImageRendable;

public class GameFrame implements Frame {

	public GameFrame() {
		RendableHolder.getInstance().add(new TileBackground("game_bg"));
		
		StaticImageRendable tmp = new StaticImageRendable("game_bg");
	}

	@Override
	public void update() {
	}

	@Override
	public void pause() {
	}

	@Override
	public void destroy() {
	}

}
