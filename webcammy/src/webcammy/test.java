package webcammy;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.opencv.core.Core;
import org.opencv.core.Mat;
/**
 *  TODO Write a one-sentence summary of your class here.
 *  TODO Follow it with additional details about its purpose, what abstraction
 *  it represents, and how to use it.
 *
 *  @author  Kushal Tirumala
 *  @version May 26, 2016
 *  @author  Period: 6
 *  @author  Assignment: JMCh19_SafeTrade
 *
 *  @author  Sources: TODO
 */
public class test {
	public static void main( String[] args ) {
		System.out.println("hi");
	      System.loadLibrary( Core.NATIVE_LIBRARY_NAME );
	      try {
			BufferedImage i = ImageIO.read(new File("gpeck.jpg"));
			Mat mat = VideoCap.getMat(i);
		    System.out.println( "mat = " + mat.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	      
    }
}
