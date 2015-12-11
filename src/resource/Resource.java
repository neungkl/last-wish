package resource;

import java.util.HashMap;

import render.AnimationImageManager;
import render.ImageData;
import exception.ResourceException;

public class Resource {
	
	ClassLoader loader = Resource.class.getClassLoader();
	private HashMap<String,ImageData[]> imageCL = new HashMap<>();
	private static Resource instance = new Resource();
	
	public static Resource getInstance() {
		return instance;
	}
	
	private ImageData[] read(String url) {
		return ImageReader.get(url);
	}

	private Resource() {
		imageCL.put("test", read("assets/test.gif"));
	}
	
	public ImageData[] getImage(String key) {
		if(imageCL.containsKey(key)) {
			return imageCL.get(key);
		}
		throw new ResourceException("can't get image with key : " + key);
	}
	
	public AnimationImageManager getImageAnimation(String key) {
		if(imageCL.containsKey(key)) {
			return new AnimationImageManager(imageCL.get(key));
		}
		throw new ResourceException("can't get image with key : " + key);
	}
}
