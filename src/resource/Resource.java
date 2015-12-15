package resource;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;
import java.util.HashMap;

import render.image.AnimationImageManager;
import render.image.ImageData;
import exception.ResourceException;

public class Resource {
	
	ClassLoader loader = Resource.class.getClassLoader();
	private HashMap<String,ImageData[]> image = new HashMap<>();
	private HashMap<String,Font> font = new HashMap<>();
	private static Resource instance = new Resource();
	
	public static Resource getInstance() {
		return instance;
	}
	
	private ImageData[] read(String url) throws ResourceException {
		return ImageReader.get("assets/" + url);
	}

	private Resource() {
		image.put("game_bg", read("game/bg1.png"));
		image.put("menu_bg", read("menu/bg.jpg"));
		image.put("exit_btn", read("menu/exit.png"));
		image.put("start_btn", read("menu/start.png"));
		image.put("highscore_btn", read("menu/highscore.png"));
		image.put("logo", read("menu/logo.png"));
		
		image.put("base_main", read("game/base/main.png"));
		image.put("base_shooter1", read("game/base/shooter1.png"));
		image.put("base_shooter2", read("game/base/shooter2.png"));
		image.put("base_shooter3", read("game/base/shooter3.png"));
		image.put("base_shooter4", read("game/base/shooter4.png"));
		
		image.put("base_barn", read("game/base/barn.png"));
		image.put("base_bazuka", read("game/base/bazuka.png"));
		image.put("base_farm", read("game/base/farm.png"));
		image.put("base_ironworks", read("game/base/ironworks.png"));
		image.put("base_light", read("game/base/light.png"));
		image.put("base_logger", read("game/base/logger.png"));
		image.put("base_sniper", read("game/base/sniper.png"));
		image.put("base_tank", read("game/base/tank.png"));
		image.put("base_warehouse", read("game/base/warehouse.png"));
		
		image.put("icon_shooter1", read("game/icon/shooter1.jpg"));
		image.put("icon_shooter2", read("game/icon/shooter2.jpg"));
		image.put("icon_shooter3", read("game/icon/shooter3.jpg"));
		image.put("icon_shooter4", read("game/icon/shooter4.jpg"));
		
		image.put("icon_barn", read("game/icon/barn.jpg"));
		image.put("icon_bazuka", read("game/icon/bazuka.jpg"));
		image.put("icon_farm", read("game/icon/farm.jpg"));
		image.put("icon_ironworks", read("game/icon/ironworks.jpg"));
		image.put("icon_light", read("game/icon/light.jpg"));
		image.put("icon_logger", read("game/icon/logger.jpg"));
		image.put("icon_sniper", read("game/icon/sniper.jpg"));
		image.put("icon_tank", read("game/icon/tank.jpg"));
		image.put("icon_warehouse", read("game/icon/warehouse.jpg"));
		
		String[] zombieList = {"normal","legionary","archer","warrior"};
		
		for(int i=0; i<zombieList.length; i++) {
			image.put("zombie_"+zombieList[i]+"_attack", read("game/zombie/"+zombieList[i]+"/attack.gif"));
			image.put("zombie_"+zombieList[i]+"_move", read("game/zombie/"+zombieList[i]+"/move.gif"));
			image.put("zombie_"+zombieList[i]+"_death", read("game/zombie/"+zombieList[i]+"/death.gif"));
		}
		
		
		image.put("test", read("test.gif"));
		
		try {
			font.put("roboto", Font.createFont(Font.TRUETYPE_FONT, Resource.class.getClassLoader().getResourceAsStream("assets/roboto.ttf")));
		} catch(FontFormatException e) {
			throw new RuntimeException("font can't load");
		} catch(IOException e) {
			throw new ResourceException(ResourceException.FILE_NOT_FOUND, "font");
		}
	}
	
	public static ImageData[] getImage(String key) {
		if(getInstance().image.containsKey(key)) {
			return getInstance().image.get(key);
		}
		throw new ResourceException(ResourceException.KEY_NOT_FOUND, key);
	}
	
	public static Font getFont(String key) {
		if(getInstance().font.containsKey(key)) {
			return getInstance().font.get(key);
		}
		throw new ResourceException(ResourceException.KEY_NOT_FOUND, key);
	}
	public static Font getFont(String key, int style, float fontSize) {
		if(getInstance().font.containsKey(key)) {
			return getInstance().font.get(key).deriveFont(style, fontSize);
		}
		throw new ResourceException(ResourceException.KEY_NOT_FOUND, key);
	}
	
	public static AnimationImageManager getImageAnimation(String key) {
		if(getInstance().image.containsKey(key)) {
			return new AnimationImageManager(getInstance().image.get(key));
		}
		throw new ResourceException(ResourceException.KEY_NOT_FOUND, key);
	}
}