package webcammy;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class NormalFilter implements ScreenFilter {

	public int turnRGBintoNegative(Color c){
		
		int r = c.getRed();
		int g= c.getGreen();
		int b= c.getBlue();
		
		Color newc = new Color(b, g, r);
		return newc.getRGB();
	}
	
	@Override
	public BufferedImage filter(BufferedImage img) {
		int rows = img.getWidth();
		int cols = img.getHeight();
		for(int i = 0; i < rows; i++){
			for(int j=0; j < cols; j++){
				Color c = new Color(img.getRGB(i, j));
				img.setRGB(i,j,turnRGBintoNegative(c));
			}
		}
		return img;
	}
}
