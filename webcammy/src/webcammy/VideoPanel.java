package webcammy;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;

import org.opencv.core.Core;
import org.opencv.core.Size;
import org.opencv.videoio.VideoWriter;
import org.opencv.videoio.Videoio;

public class VideoPanel extends JPanel  {

	public VideoCap videoCap = new VideoCap();
	int fourcc = VideoWriter.fourcc('m','p','4','v');
	Size size = new Size(320,240);
    private VideoWriter vidWriter = new VideoWriter("kappa.mp4v",fourcc, 30, size ,true);
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
		if(videoCap.isRecording())
		{
			//vidWriter.write(videoCap.getMat());
		}
	}
	
}
