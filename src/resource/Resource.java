package resource;

import java.util.HashMap;

import render.AnimationImageManager;

public class Resource {
	
	private HashMap<String,AnimationImageManager> collections = new HashMap<>();
	private static Resource instance = new Resource();
	
	public Resource getInstance() {
		return instance;
	}
	
	private Resource() {}
	
	public AnimationImageManager get(String key) {
		if(collections.containsKey(key)) {
			return collections.get(key);
		}
		return null;
	}
}
