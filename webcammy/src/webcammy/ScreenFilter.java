package webcammy;

import java.awt.image.BufferedImage;

import org.opencv.core.Mat;
/**
 *  An interface for the filters for the cameras.
 *  Contains a method that will be used in every filter class.
 *
 *  @author  Ryan Tang
 *  @version May 26, 2016
 *  @author  Period: 6
 *  @author  Assignment: JMCh19_SafeTrade
 *
 */
public interface ScreenFilter {
	/**
	 * @param img the image corresponding to the frame that will be filtered
	 * @return the filtered image, filtered by iterating through the pixels and changing the color of each one.
	 */
	BufferedImage filter(BufferedImage img);
}
