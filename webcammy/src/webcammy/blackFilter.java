package webcammy;

import java.awt.image.BufferedImage;

public class blackFilter implements ScreenFilter {

	private BufferedImage img;

	public blackFilter(BufferedImage img) {
		
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
}
