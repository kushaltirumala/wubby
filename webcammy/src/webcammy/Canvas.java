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

public class Canvas extends JFrame {
	static DrawPane canvas;

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
	
	public static void main(String[] args){
		Canvas c = new Canvas();
//		while(true) {
//			c.repaint();
//		}
	}

}

class DrawPane extends JPanel{
	BufferedImage image;
	Queue<Point> pointsToAdd;
	Graphics2D g2;
	public void init(Set<Point> p){
		Iterator<Point> iter = p.iterator();
		while(iter.hasNext())
			pointsToAdd.add(iter.next());
	}
	public DrawPane(){
		super();
		pointsToAdd = new LinkedList<Point>();
		setVisible(true);
	}
	public void doDrawing(Graphics g) {
		if(image == null ){
			image = new BufferedImage(getSize().width, getSize().height, BufferedImage.TYPE_INT_RGB);
			g2 = (Graphics2D)image.getGraphics();
		}
		g2.setColor(Color.red);
		while(!pointsToAdd.isEmpty()) {
    		Point temp = pointsToAdd.remove();
    		g2.drawRect(temp.x, temp.y, 5, 5);
    	}
		g.drawImage(image,0, 0, null);
		
	}
    public void paintComponent(Graphics g){
    	super.paintComponent(g);
    	doDrawing(g);
    }
    public void updateQueue(Point x){
    	pointsToAdd.add(x);
    	repaint();
    }
    

 }
