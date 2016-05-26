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
					// System.out.println(i + " " + j + " " + temp);
					if (temp < lightReference + 0.05 && temp > lightReference- 0.05) {
						// System.out.println("yum");
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

		int red = (color >>> 16) & 0xFF;
		int green = (color >>> 8) & 0xFF;
		int blue = (color >>> 0) & 0xFF;

		return (red * 0.2126f + green * 0.7152f + blue * 0.0722f) / 255;
	}
}
