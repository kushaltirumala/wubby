package webcammy;

import java.awt.image.BufferedImage;

public class trippyFilter implements ScreenFilter {

	private BufferedImage img;
	
	public trippyFilter(BufferedImage img){
		this.img = img;
	}
	
	public BufferedImage filter() {
		return img;
	}

}
