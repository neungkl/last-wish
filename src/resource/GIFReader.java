package resource;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import render.GIFBufferedImage;

public class GIFReader {
	
	private GIFReader() {}
	
	public static GIFBufferedImage[] get(String url) {
		
		GIFBufferedImage[] frame = null;
		
		try {
			ClassLoader loader = GIFReader.class.getClassLoader();
		    ImageReader reader = ImageIO.getImageReadersByFormatName("gif").next();
		    ImageInputStream stream = ImageIO.createImageInputStream(loader.getResourceAsStream(url));
		    reader.setInput(stream);
		    
		    int count = reader.getNumImages(true);
		    frame = new GIFBufferedImage[count];
		    
		    for (int i = 0; i < count; i++) {
		    	
		    	BufferedImage image = reader.read(i);
		    	frame[i] = new GIFBufferedImage(image);
		    	
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
		
		return frame;
	}
}
