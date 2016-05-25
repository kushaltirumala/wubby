package webcammy;

import java.awt.Color;
import java.awt.image.BufferedImage;

import org.opencv.core.Mat;
/**
 *  TODO Write a one-sentence summary of your class here.
 *  TODO Follow it with additional details about its purpose, what abstraction
 *  it represents, and how to use it.
 *
 *  @author  Ryan Tang
 *  @version May 25, 2016
 *  @author  Period: 6
 *  @author  Assignment: JMCh19_SafeTrade
 *
 *  @author  Sources: TODO
 */
public class BlackWhiteFilter implements ScreenFilter {
		
	public BlackWhiteFilter(){	}
	
	public int turnRGBintoGrayscale(Color c){
		int r = c.getRed();
		int g= c.getGreen();
		int b=c.getBlue();
		int red = (int)(r * 0.299 + g * 0.587 + b * 0.114);
		int green = red; int blue = red;
		Color newc = new Color(red, green, blue);
		return newc.getRGB();
	}
	
	public BufferedImage filter(BufferedImage img) {
		int rows = img.getWidth();
		int cols = img.getHeight();
		for(int i = 0; i < rows; i++){
			for(int j=0; j < cols; j++){
				Color c = new Color(img.getRGB(i, j));
				img.setRGB(i,j,turnRGBintoGrayscale(c));
			}
		}
		return img;
	}

}
