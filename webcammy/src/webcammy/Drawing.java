package webcammy;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.TreeSet;

/**
 * Implements a filter that detects all the high brightness pixels in the video
 * stream, colo- ring them red on the video stream itself to keep track of the
 * pixels. Everytime a new pixel with a high enough brightness setting is
 * detected, it notifies the Canvas class to draw that point on the seperate
 * JFrame.
 *
 * @author Kushal Tirumala
 * @version May 26, 2016
 * @author Period: 6
 * @author Assignment: wubby
 *
 */
public class Drawing implements ScreenFilter {

	/**
	 * The set of all points drawn so far — set so repeated points are not
	 * redrawn and slows down the filtering
	 */
	private Set<Point> points;

	/**
	 * reference to the JFrame Canvas where the drawing happens
	 */
	private Canvas c;

	/**
	 * the light reference to base the threshold brightness off of—set by
	 * pressing the "light reference" button in the GUI, and pointing your LED
	 * light at the center of the frame
	 */
	private double lightReference;

	/**
	 * constructs a new Drawing filter
	 * 
	 * @param c
	 *            Canvas object to draw on
	 * @param lr
	 *            the threshold brightness value
	 */
	public Drawing(Canvas c, double lr) {
		points = new TreeSet<Point>();
		this.c = c;
		lightReference = lr;
	}

	/**
	 * Sets up the canvas by drawing the set of points gotten by the VideoCap
	 * 
	 * @param p
	 */
	public void setUpCanvas(Set<Point> p) {
		c.init(p);
	}

	/**
	 * implements the filter function — essentially goes through the image
	 * finding all the pixels with a brightness rating ±0.05 around the
	 * threshold
	 */
	public BufferedImage filter(BufferedImage img) {

		int rows = img.getWidth();
		int cols = img.getHeight();

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				double temp = brightness(img, i, j);
				if (temp < lightReference + 0.05 && temp > lightReference - 0.05) {
					img.setRGB(i, j, Color.red.getRGB());
					Point toAdd = new Point(i, j);
					points.add(toAdd);
					shoutOutToCanvas(toAdd);
				}
			}
		}
		return img;

	}

	/**
	 * returns the points to draw
	 * 
	 * @return the points to draw
	 */
	public Set<Point> getPoints() {
		return points;
	}

	/**
	 * callback method that updates the canvas to draw a new point found with an
	 * appropriate brightness value
	 * 
	 * @param x
	 */
	public void shoutOutToCanvas(Point x) {
		c.updateQueue(x);
	}

	/**
	 * calculates the brightness value for a given pixel
	 * 
	 * @param img
	 *            image to to base calculation off of
	 * @param i
	 *            x-coordinate of the pixel
	 * @param j
	 *            y-cooridnate of the pixel
	 * @return the brightness value of the given pixel
	 */
	public static double brightness(BufferedImage img, int i, int j) {
		int color = img.getRGB(i, j);
		Color c = new Color(color);

		int red = c.getRed();
		int green = c.getGreen();
		int blue = c.getBlue();

		return (red * 0.2126f + green * 0.7152f + blue * 0.0722f) / 255;
	}

	/**
	 * returns the canvas reference used to draw the actual points
	 * 
	 * @return the canvas reference
	 */
	public Canvas getCanvas() {
		return c;
	}

	/**
	 * returns the brightness threshold rating used by the filter to determine
	 * what points to color
	 * 
	 * @return the brightness threshold
	 */
	public double getLR() {
		return lightReference;
	}

}
