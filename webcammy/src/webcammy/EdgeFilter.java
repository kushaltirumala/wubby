package webcammy;

import java.awt.Color;
import java.awt.image.BufferedImage;

import org.opencv.core.Mat;

public class EdgeFilter implements ScreenFilter {

	public EdgeFilter() {}
	
	public static double colorDistance( Color color1, Color color2 )
    {
        double redDistance = color1.getRed() - color2.getRed();
        double greenDistance = color1.getGreen() - color2.getGreen();
        double blueDistance = color1.getBlue() - color2.getBlue();
        double distance = Math.sqrt( redDistance * redDistance + greenDistance
            * greenDistance + blueDistance * blueDistance );
        return distance;
    }

	public BufferedImage filter(BufferedImage img) {
		int rows = img.getWidth();
		int cols = img.getHeight();
		for(int i = 0; i < rows; i++){
			for(int j = 0; j < cols-1; j++){
				Color c1 = new Color(img.getRGB(i, j));
				Color c2 = new Color(img.getRGB(i,j+1));
				if(colorDistance(c1, c2) > 10) {
					img.setRGB(i, j, Color.black.getRGB());
				} else {
					img.setRGB(i,j, Color.WHITE.getRGB());
				}
			}
		}
		return img;
	}
}
