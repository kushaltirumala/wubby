package webcammy;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

import javax.swing.JPanel;

/**
 * The actual JPanel where the drawing happens — holds an image that adds points
 * every time a new point is found by the Drawing filter, and updates the JPanel
 *
 * @author Kushal Tirumala
 * @version May 26, 2016
 * @author Period: 6
 * @author Assignment: wubby
 *
 */
public class DrawPane extends JPanel {

	/**
	 * image on the JPanel to draw on
	 */
	private BufferedImage image;

	/**
	 * the queue of points that needs to be drawn onto the image
	 */
	private Queue<Point> pointsToAdd;

	/**
	 * the graphics reference for the image, not the JPanel itself
	 */
	private Graphics2D g2;

	/**
	 * initializes the queue of points to add with a set of points given from
	 * the Drawing class
	 * 
	 * @param p
	 */
	public void init(Set<Point> p) {
		Iterator<Point> iter = p.iterator();
		while (iter.hasNext())
			pointsToAdd.add(iter.next());
	}

	/**
	 * constructs the JPanel itself, initializing the queue of points
	 */
	public DrawPane() {
		super();
		pointsToAdd = new LinkedList<Point>();
		setVisible(true);
	}

	/**
	 * saves the current points on the image into a new image, and then adds all
	 * the points in queue (removing them as they are added) to the image (via
	 * the graphics reference for that image), and then draws that final image
	 * onto the JPanel — creates a drawing effect
	 * 
	 * @param g
	 *            the graphics object for the JPanel
	 */
	public void doDrawing(Graphics g) {
		if (image == null) {
			image = new BufferedImage(getSize().width, getSize().height, BufferedImage.TYPE_INT_RGB);
			g2 = (Graphics2D) image.getGraphics();
		}
		g2.setColor(Color.red);
		while (!pointsToAdd.isEmpty()) {
			Point temp = pointsToAdd.remove();
			g2.drawRect(temp.x, temp.y, 5, 5);
		}
		g.drawImage(image, 0, 0, null);

	}

	/**
	 * paints the JPanel, but the real drawing happens in doDrawing
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		doDrawing(g);
	}

	/**
	 * called from Canvas class (which recieves the points from the Drawing
	 * filter), and updates the Queue with a new point to be drawn and
	 * immediately repaints the JPanel
	 * 
	 * @param x
	 */
	public void updateQueue(Point x) {
		pointsToAdd.add(x);
		repaint();
	}

	/**
	 * returns the queue of points to be drawn
	 * 
	 * @return the queue of points yet to be drawn
	 */
	public Queue<Point> getPoints() {
		return pointsToAdd;
	}

}