package webcammy;

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
public interface ScreenFilter {
	BufferedImage filter(BufferedImage img);
}
