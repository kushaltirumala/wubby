package webcammy;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.TreeSet;

public class Drawing implements ScreenFilter {

	Set<Point> points;
	Canvas c;
	double lightReference;
	int i = 0;

	public Drawing(Canvas c, double lr) {
		points = new TreeSet<Point>();
		this.c = c;
		lightReference = lr;
	}

	public void setUpCanvas(Set<Point> p) {
		c.init(p);
	}

	public BufferedImage filter(BufferedImage img) {
		
			int rows = img.getWidth();
			int cols = img.getHeight();

			for (int i = 0; i < rows; i++) {
				for (int j = 0; j < cols; j++) {
					double temp = brightness(img, i, j);
					if (temp < 0.95 + 0.05 && temp > 0.95- 0.05) {
						img.setRGB(i, j, Color.red.getRGB());
						Point toAdd = new Point(i, j);
						points.add(toAdd);
						shoutOutToCanvas(toAdd);
					}
				}
			}
			return img;
		
	}

	public Set<Point> getPoints() {
		return points;
	}

	public void shoutOutToCanvas(Point x) {
		c.updateQueue(x);
	}

	public static double brightness(BufferedImage img, int i, int j) {
		int color = img.getRGB(i, j);
		Color c = new Color(color);
		
		int red = c.getRed();
		int green = c.getGreen();
		int blue = c.getBlue();

		return (red * 0.2126f + green * 0.7152f + blue * 0.0722f) / 255;
	}
}
