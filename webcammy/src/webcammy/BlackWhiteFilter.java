package webcammy;

import java.awt.Color;
import java.awt.image.BufferedImage;

import org.opencv.core.Mat;
/**
 *  A filter for the camera that makes the image black and white.
 *  Turns the RGB into grayscale, and sets it as the new color of the pixel of the image.
 *
 *  @author  Ryan Tang
 *  @version May 26, 2016
 *  @author  Period: 6
 *  @author  Assignment: JMCh19_SafeTrade
 *
 *  @author  Sources: TODO
 */
public class BlackWhiteFilter implements ScreenFilter {
		
	/**
	 * The constructor for the filter.
	 */
	public BlackWhiteFilter(){	}
	
	/**
	 * @param c the color of the pixel.
	 * @return the grayscaled color of the pixel.
	 */
	public int turnRGBintoGrayscale(Color c){
		int r = c.getRed();
		int g= c.getGreen();
		int b=c.getBlue();
		int red = (int)(r * 0.299 + g * 0.587 + b * 0.114);
		int green = red; int blue = red;
		Color newc = new Color(red, green, blue);
		return newc.getRGB();
	}
	
	/* (non-Javadoc)
	 * @see webcammy.ScreenFilter#filter(java.awt.image.BufferedImage)
	 */
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
