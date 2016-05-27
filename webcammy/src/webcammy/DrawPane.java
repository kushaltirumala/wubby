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

public class DrawPane extends JPanel{
	private BufferedImage image;
	private Queue<Point> pointsToAdd;
	private Graphics2D g2;
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
    
    public Queue<Point> getPoints(){
    	return pointsToAdd;
    }

 }