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
 *  @author  Assignment: wubby
 *
 */
public class VideoPanel extends JPanel  {

	public VideoCap videoCap = new VideoCap();
    boolean stillMode = false;
	
	/**
	 * the constructor
	 */
	public VideoPanel() {
		super();
	}

	/**
	 * @return the VideoCap object connected to the camera.
	 */
	public  VideoCap getVidCap()
	{
		return videoCap;
	}
	
	/**
	 * @return the last frame to keep in still mode.
	 */
	public BufferedImage stillMode() {
		stillMode = true;
		return videoCap.getStill();
	}
	
	public void trackPen() {
		
	}

	/* (non-Javadoc)
	 * @see javax.swing.JComponent#paint(java.awt.Graphics)
	 */
	public void paint(Graphics g) {
		paintComponent(g);
		//System.out.println(videoCap.getFPS());
		g.drawImage(videoCap.getOneFrame(), 0, 0, this);
		
	}
	
}
