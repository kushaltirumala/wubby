package webcammy;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class Drawing implements ScreenFilter {

	
	public Drawing(){}
	
	public BufferedImage filter(BufferedImage img) {
		int rows = img.getWidth();
		int cols = img.getHeight();
		double maxBri = Double.MIN_VALUE;
		int indexx;
		int indexy;
		for(int i = 0; i < rows; i++){
			for(int j=0; j < cols; j++){
				double temp = brightness(img, i ,j);
				if(temp > 0.99) {
					//System.out.println(img.getRGB(i, j));
					img.setRGB(i, j, Color.red.getRGB());
				}
			}
		}
		return img;
	}
	public double brightness(BufferedImage img, int i, int j){
		int color = img.getRGB(i, j);

		int red   = (color >>> 16) & 0xFF;
		int green = (color >>>  8) & 0xFF;
		int blue  = (color >>>  0) & 0xFF;

		return (red * 0.2126f + green * 0.7152f + blue * 0.0722f) / 255;
	}
}


