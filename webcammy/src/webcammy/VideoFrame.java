package webcammy;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import org.opencv.core.Core;

/**
 * The Frame for our webcam Has the main method to run the webcam.
 *
 * @author Kavi Nelakonda
 * @version May 26, 2016
 * @author Period: 6
 * @author Assignment: wubby
 *
 */
public class VideoFrame {

	public static boolean stillMode = false;

	/**
	 * Creates the frame and starts up the webcam.
	 * 
	 * @param args
	 *            not used
	 */
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
			if (stillMode)
				continue;
			vid.repaint();
		}

	}
}
