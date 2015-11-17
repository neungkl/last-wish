package resource;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import render.ImageData;

public class ImageReader {
	
	private static ClassLoader loader = ImageReader.class.getClassLoader();
	private ImageReader() {}
	
	public static ImageData[] get(String url) {
		
		ImageData[] frame = null;
		
		String extension = url.substring(url.length()-3,url.length());
		
		if(extension.equals("gif")) {
		
			try {
			    javax.imageio.ImageReader reader = ImageIO.getImageReadersByFormatName("gif").next();
			    ImageInputStream stream = ImageIO.createImageInputStream(loader.getResourceAsStream(url));;
			    reader.setInput(stream);
			    
			    int count = reader.getNumImages(true);
			    frame = new ImageData[count];
			    
			    for (int i = 0; i < count; i++) {
			    	
			    	BufferedImage image = reader.read(i);
			    	frame[i] = new ImageData(image);
			    	
			        NodeList child = reader.getImageMetadata(i).getAsTree("javax_imageio_gif_image_1.0").getChildNodes();
			        
			        for(int j=0; j<child.getLength(); j++) {
			        	Node nodeItem = child.item(j);
			        	if(nodeItem.getNodeName().equals("ImageDescriptor")) {
			        		int offsetX = Integer.valueOf(nodeItem.getAttributes().getNamedItem("imageLeftPosition").getNodeValue());
			        		int offsetY = Integer.valueOf(nodeItem.getAttributes().getNamedItem("imageTopPosition").getNodeValue());
			        		frame[i].setOffset(offsetX, offsetY);
			        		break;
			        	}
			        }
			    }
			    
			} catch (IOException e) {
			    e.printStackTrace();
			}
			
		} else if(extension.equals("png") || extension.equals("jpg")) {
			frame = new ImageData[1];
			
			try {
				frame[0] = new ImageData(ImageIO.read(loader.getResource(url)));
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
		
		return frame;
	}
}
