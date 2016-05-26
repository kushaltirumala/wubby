package webcammy;

import java.awt.Color;
import java.awt.image.BufferedImage;

import org.opencv.core.Mat;
/**
 *  A negative filter for the camera.
 *  Subtracts each color value from 255 and sets that color for the pixel of the image.
 *
 *  @author  Ryan Tang
 *  @version May 26, 2016
 *  @author  Period: 6
 *  @author  Assignment: JMCh19_SafeTrade
 *
 *  @author  Sources: TODO
 */
public class NegativeFilter implements ScreenFilter {

	/**
	 * The constructor for the filter.
	 */
	public NegativeFilter() {}
	
	/**
	 * @param c the color of the pixel
	 * @return the color integer that corresponds with the negative of the original color.
	 */
	public int turnRGBintoNegative(Color c){
		
		int r = c.getRed();
		int g= c.getGreen();
		int b= c.getBlue();
		int red = 255-r;
		int green = 255-g; int blue = 255-b;
		Color newc = new Color(red, green, blue);
		return newc.getRGB();
		
		
	}
	
	/* (non-Javadoc)
	 * @see webcammy.ScreenFilter#filter(java.awt.image.BufferedImage)
	 */
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
