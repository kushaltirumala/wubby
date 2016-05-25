package webcammy;

import java.awt.Color;
import java.awt.image.BufferedImage;
/**
 *  TODO Write a one-sentence summary of your class here.
 *  TODO Follow it with additional details about its purpose, what abstraction
 *  it represents, and how to use it.
 *
 *  @author  Kushal Tirumala
 *  @version May 25, 2016
 *  @author  Period: 6
 *  @author  Assignment: JMCh19_SafeTrade
 *
 *  @author  Sources: TODO
 */
public class ImageFilter implements ScreenFilter {
	private BufferedImage image;
	private int leftEye;
	private int rightEye;
	private int nose;
	private int mouthCenter;
	
	public ImageFilter() {}

	public BufferedImage filter(BufferedImage img) {
		int rows = img.getWidth();
		int cols = img.getHeight();
		for(int i = 0; i < rows; i++){
			for(int j=0; j < cols; j++){
				Color c = new Color(img.getRGB(i, j));
				int newr = c.getGreen();
				int newg = c.getBlue();
				int newb = c.getRed();
				Color newc = new Color(newr, newg, newb);
				img.setRGB(i, j, newc.getRGB());
			}
		}
		return img;
	}
	
	
}
