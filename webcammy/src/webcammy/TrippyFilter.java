package webcammy;

import java.awt.image.BufferedImage;

import org.opencv.core.Mat;

public class TrippyFilter implements ScreenFilter {

	private BufferedImage img;

	public TrippyFilter(BufferedImage img) {
		
		this.img = img;
	}

	public BufferedImage filter() {
		for(int r = 0; r < img.getHeight(); r++)
		{
			for(int c = 0; c< img.getWidth(); c++)
			{
				img.setRGB(r, c, img.getRGB(r, c) - 100);
			}
		}
		return img;
	}

	public BufferedImage filter(BufferedImage img) {
		// TODO Auto-generated method stub
		return null;
	}
}
