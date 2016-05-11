package webcammy;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;

import org.opencv.core.Core;

public class VideoPanel extends JPanel  {

	public VideoCap videoCap = new VideoCap();
	JButton blackFilter = new JButton("change filter to black");
	
	
	public VideoPanel() {
		super();
	}

	public  VideoCap getVidCap()
	{
		return videoCap;
	}
	
	public void paint(Graphics g) {
		paintComponent(g);
		g.drawImage(videoCap.getOneFrame(), 0, 0, this);
	}
	
}
