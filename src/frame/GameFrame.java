package frame;

import render.AnimationRendable;
import render.Rendable;
import render.RendableHolder;
import render.StaticImageRendable;

public class GameFrame implements Frame {

	Rendable test, test2;

	public GameFrame() {
		test = new StaticImageRendable("test", 0, 100, 1.5f);
		
		test2 = new AnimationRendable("test", 0, 50, 1.5f);
		((AnimationRendable) test2).loop(10);

		RendableHolder.getInstance().add(test);
		RendableHolder.getInstance().add(test2);
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
