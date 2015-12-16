package resource;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;
import java.util.HashMap;

import render.image.AnimationImageManager;
import render.image.ImageData;
import exception.ResourceException;

public class Resource {
	
	ClassLoader loader = Resource.class.getClassLoader();
	private HashMap<String,String> imageList = new HashMap<>();
	private HashMap<String,ImageData[]> image = new HashMap<>();
	private HashMap<String,Font> font = new HashMap<>();
	private HashMap<String, AudioClip> sound = new HashMap<>();
	private static Resource instance = new Resource();
	
	public static Resource getInstance() {
		return instance;
	}
	
	private static ImageData[] read(String url) throws ResourceException {
		return ImageReader.get("assets/" + url);
	}
	
	private AudioClip readSound(String url) throws ResourceException  {
		try {
			return Applet.newAudioClip(loader.getResource("assets/sound/"+url));
		} catch(Exception e) {
			throw new ResourceException(ResourceException.FILE_NOT_FOUND, url);
		}
	}

	private Resource() {
		imageList.put("game_bg", "game/bg4.jpg");
		imageList.put("menu_bg", "menu/bg.jpg");
		imageList.put("exit_btn", "menu/exit.png");
		imageList.put("start_btn", "menu/start.png");
		imageList.put("highscore_btn", "menu/highscore.png");
		imageList.put("logo", "menu/logo.png");
		
		imageList.put("base_main", "game/base/main.png");
		imageList.put("base_shooter1", "game/base/shooter1.png");
		imageList.put("base_shooter2", "game/base/shooter2.png");
		imageList.put("base_shooter3", "game/base/shooter3.png");
		imageList.put("base_shooter4", "game/base/shooter4.png");
		
		imageList.put("base_barn", "game/base/barn.png");
		imageList.put("base_bazuka", "game/base/bazuka.png");
		imageList.put("base_farm", "game/base/farm.png");
		imageList.put("base_ironworks", "game/base/ironworks.png");
		imageList.put("base_light", "game/base/light.png");
		imageList.put("base_logger", "game/base/logger.png");
		imageList.put("base_sniper", "game/base/sniper.png");
		imageList.put("base_tank", "game/base/tank.png");
		imageList.put("base_warehouse", "game/base/warehouse.png");
		
		imageList.put("icon_shooter1", "game/icon/shooter1.jpg");
		imageList.put("icon_shooter2", "game/icon/shooter2.jpg");
		imageList.put("icon_shooter3", "game/icon/shooter3.jpg");
		imageList.put("icon_shooter4", "game/icon/shooter4.jpg");
		
		imageList.put("icon_barn", "game/icon/barn.jpg");
		imageList.put("icon_bazuka", "game/icon/bazuka.jpg");
		imageList.put("icon_farm", "game/icon/farm.jpg");
		imageList.put("icon_ironworks", "game/icon/ironworks.jpg");
		imageList.put("icon_light", "game/icon/light.jpg");
		imageList.put("icon_logger", "game/icon/logger.jpg");
		imageList.put("icon_sniper", "game/icon/sniper.jpg");
		imageList.put("icon_tank", "game/icon/tank.jpg");
		imageList.put("icon_warehouse", "game/icon/warehouse.jpg");
		
		imageList.put("icon_mini_farm", "game/icon/farm.png");
		imageList.put("icon_mini_wood", "game/icon/wood.png");
		imageList.put("icon_mini_iron", "game/icon/iron.png");
		
		imageList.put("btn_upgrade", "game/ui/upgrade_btn.png");
		imageList.put("btn_sell", "game/ui/sell_btn.png");
		
		imageList.put("btn_pause", "game/ui/pause_btn.png");
		imageList.put("pause_header", "game/ui/pause_header.png");
		imageList.put("pause_resume", "game/ui/resume_btn.png");
		imageList.put("pause_exit", "game/ui/exit_btn.png");
		
		imageList.put("bg_game_over", "game/ui/game_over_bg.jpg");
		imageList.put("bg_game_win", "game/ui/win_game_bg.jpg");
		
		try {
			sound.put("click", readSound("click.wav"));
			sound.put("sell", readSound("sell.wav"));
			sound.put("congratulations_speak", readSound("congratulations_speak.wav"));
			sound.put("end_bg", readSound("end_bg.wav"));
			sound.put("game_bg", readSound("game_bg.wav"));
			sound.put("menu_bg", readSound("menu_bg.wav"));
			sound.put("game_over_speak", readSound("game_over_speak.wav"));
			sound.put("base_damage0", readSound("base_damage0.wav"));
			sound.put("base_damage1", readSound("base_damage1.wav"));
			sound.put("base_explode0", readSound("base_explode0.wav"));
			sound.put("base_explode1", readSound("base_explode1.wav"));
			sound.put("base_place", readSound("base_place.wav"));
			
			for(int i=0; i<7; i++) {
				sound.put("zombie"+i, readSound("zombie"+i+".wav"));
			}
			
			sound.put("bazuka", readSound("bazuka.wav"));
			sound.put("tank", readSound("tank.wav"));
			sound.put("sniper", readSound("sniper.wav"));
			sound.put("light", readSound("light.wav"));
		} catch(ResourceException e) {
			e.printStackTrace();
		}
		
		
		String[] zombieList = {"normal","legionary","archer","warrior"};
		
		for(int i=0; i<zombieList.length; i++) {
			imageList.put("zombie_"+zombieList[i]+"_attack", "game/zombie/"+zombieList[i]+"/attack.gif");
			imageList.put("zombie_"+zombieList[i]+"_move", "game/zombie/"+zombieList[i]+"/move.gif");
			imageList.put("zombie_"+zombieList[i]+"_death", "game/zombie/"+zombieList[i]+"/death.gif");
		}
		
		try {
			font.put("roboto", Font.createFont(Font.TRUETYPE_FONT, Resource.class.getClassLoader().getResourceAsStream("assets/roboto.ttf")));
		} catch(FontFormatException e) {
			throw new RuntimeException("font can't load");
		} catch(IOException e) {
			throw new RuntimeException("font not found");
		}
	}
	
	public static ImageData[] getImage(String key) {
		try {
			if(getInstance().imageList.containsKey(key)) {
				HashMap<String,ImageData[]> img = getInstance().image;
				if(img.containsKey(key)) {
					return img.get(key);
				} else {
					ImageData[] seq = read(getInstance().imageList.get(key));
					img.put(key, seq);
					return seq;
				}
			}
		} catch(ResourceException e) {
			throw new RuntimeException("File not found : "+key);
		}
		return null;
	}
	
	public static AudioClip getSound(String key) {
		if(getInstance().sound.containsKey(key)) {
			return getInstance().sound.get(key);
		}
		throw new RuntimeException("File not found : " + key);
	}
	
	public static Font getFont(String key) {
		if(getInstance().font.containsKey(key)) {
			return getInstance().font.get(key);
		}
		throw new RuntimeException("File not found : " + key);
	}
	public static Font getFont(String key, int style, float fontSize) {
		if(getInstance().font.containsKey(key)) {
			return getInstance().font.get(key).deriveFont(style, fontSize);
		}
		throw new RuntimeException("File not found : "+ key);
	}
	
	public static AnimationImageManager getImageAnimation(String key) {
		return new AnimationImageManager(getImage(key));
	}
}
