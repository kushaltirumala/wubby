package webcammy;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import org.opencv.core.Core;

public class VideoFrame {

	
	public static void main(String[] args) {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		JFrame frame = new JFrame();

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(0, 0, 1200, 1200);
		frame.setLayout(new BorderLayout());

		VideoPanel vid = new VideoPanel();
		VideoButtons buttons = new VideoButtons(vid.getVidCap());
		TopPanel top = new TopPanel(vid.getVidCap());

		frame.setVisible(true);
		
		
		frame.add(top, BorderLayout.NORTH);
		frame.add(vid, BorderLayout.CENTER);
		frame.add(buttons, BorderLayout.SOUTH);
		
		
		vid.setVisible(true);

		while (true) {
			vid.repaint();
		}
	}
}
