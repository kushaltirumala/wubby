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
 *  @author  Sources: TODO
 */
public interface ScreenFilter {
	BufferedImage filter(BufferedImage img);
}
