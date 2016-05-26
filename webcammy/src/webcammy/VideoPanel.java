package webcammy;
import java.awt.*;

import java.awt.image.BufferedImage;

import javax.swing.*;
/**
 *  The middle panel of our project.
 *  Displays the input from the camera.
 *
 *  @author  Kavi Nelakonda
 *  @version May 26, 2016
 *  @author  Period: 6
 *  @author  Assignment: JMCh19_SafeTrade
 *
 *  @author  Sources: TODO
 */
public class VideoPanel extends JPanel  {

	public VideoCap videoCap = new VideoCap();
    boolean stillMode = false;
	
	public VideoPanel() {
		super();
	}

	public  VideoCap getVidCap()
	{
		return videoCap;
	}
	
	public BufferedImage stillMode() {
		stillMode = true;
		return videoCap.getStill();
	}
	
	public void trackPen() {
		
	}

	public void paint(Graphics g) {
		paintComponent(g);
		//System.out.println(videoCap.getFPS());
		g.drawImage(videoCap.getOneFrame(), 0, 0, this);
		
	}
	
}
