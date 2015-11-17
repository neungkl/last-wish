package resource;

import java.util.HashMap;

import javax.management.RuntimeErrorException;

import render.AnimationImageManager;

public class Resource {
	
	ClassLoader loader = Resource.class.getClassLoader();
	private HashMap<String,AnimationImageManager> imageCL = new HashMap<>();
	private static Resource instance = new Resource();
	
	public static Resource getInstance() {
		return instance;
	}
	
	private AnimationImageManager read(String url) {
		return new AnimationImageManager(ImageReader.get(url));
	}

	private Resource() {
		imageCL.put("test", read("assets/test.gif"));
	}
	
	public AnimationImageManager getImage(String key) {
		if(imageCL.containsKey(key)) {
			return imageCL.get(key);
		}
		throw new RuntimeException("File not found Resource.getImage");
	}
}
