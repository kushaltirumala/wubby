package webcammy;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.*;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 * Acts as the drawing interface for when the user selects to draw in the air
 * with an LED Light or something else that is bright enough to detect
 *
 * @author Kushal Tirumala
 * @version May 26, 2016
 * @author Period: 6
 * @author Assignment: wubby
 *
 */
public class Canvas extends JFrame {
	/**
	 * The JPanel where the actual drawing happens
	 */
	private DrawPane canvas;

	/**
	 * Constructor for the JFrame. where the canvas is initializes
	 */
	public Canvas() {
		super();
		canvas = new DrawPane();
		setContentPane(canvas);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setSize(2000, 2000);

		setVisible(true);

	}

	/**
	 * initializes the canvas JPanel with a set of Points to draw
	 * 
	 * @param p
	 *            set of points to draw
	 */
	public void init(Set<Point> p) {
		canvas.init(p);
	}

	/**
	 * updates the queue of the points the canvas needs to draw
	 * 
	 * @param temp
	 *            the NEW set of points the canvas needs to draw
	 */
	public void updateQueue(Point temp) {
		canvas.updateQueue(temp);
	}

	/**
	 * returns the content pane (the canvas JPanel)
	 * 
	 * @return the canvas JPanel in this JFrame
	 */
	public DrawPane getDrawPane() {
		return canvas;
	}

	public static void main(String[] args) {
		Canvas c = new Canvas();
	}

}
