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
 *  TODO Write a one-sentence summary of your class here.
 *  TODO Follow it with additional details about its purpose, what abstraction
 *  it represents, and how to use it.
 *
 *  @author  Kushal Tirumala
 *  @version May 26, 2016
 *  @author  Period: 6
 *  @author  Assignment: JMCh19_SafeTrade
 *
 *  @author  Sources: TODO
 */
public class Canvas extends JFrame {
	private DrawPane canvas;

	public Canvas(){
		super();
		canvas = new DrawPane();
		setContentPane(canvas);
		
		

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setSize(2000, 2000);

               
        setVisible(true); 
        		
	}
	
	public void init(Set<Point> p){
		canvas.init(p);
	}
	
	public void updateQueue(Point temp){
		canvas.updateQueue(temp);
	}
	
	public DrawPane getDrawPane(){
		return canvas;
	}
	
	public static void main(String[] args){
		Canvas c = new Canvas();
	}
	
	

}
