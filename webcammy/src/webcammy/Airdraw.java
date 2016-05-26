package webcammy;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class Airdraw extends Processor {
	public Airdraw(){}
	
	public BufferedImage filter(BufferedImage video, BufferedImage still) {
		int rows = still.getWidth();
		int cols = still.getHeight();
		double MaxBRI = Double.MIN_VALUE;
		for(int i = 0; i < rows; i++){
			for(int j=0; j < cols; j++){
				double temp = brightness(video, i ,j);
				if(temp > 0.99) {
					still.setRGB(i, j, Color.red.getRGB());
					//System.out.println("MAX VALUE AT :" + i + " " + j + " " + temp);
				}
			}
		}
		return still;
	}
	public double brightness(BufferedImage img, int i, int j){
		int color = img.getRGB(i, j);

		int red   = (color >>> 16) & 0xFF;
		int green = (color >>>  8) & 0xFF;
		int blue  = (color >>>  0) & 0xFF;

		return (red * 0.2126f + green * 0.7152f + blue * 0.0722f) / 255;
	}
}
