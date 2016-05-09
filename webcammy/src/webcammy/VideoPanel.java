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

public class VideoPanel extends JPanel implements ActionListener {

	public VideoCap videoCap = new VideoCap();
	JButton blackFilter = new JButton("change filter to black");
	private int imageCount = 0;
	
	
	public VideoPanel() {
		super();
	}

	public void paint(Graphics g) {
		paintComponent(g);
		g.drawImage(videoCap.getOneFrame(), 0, 0, this);
	}

	public static void main(String[] args) {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		JFrame frame = new JFrame();

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 1000, 1000);
		frame.setLayout(new BorderLayout());

		VideoPanel vid = new VideoPanel();
		JPanel buttons = new JPanel();
		buttons.setLayout(new FlowLayout());

		frame.setVisible(true);

		frame.add(vid, BorderLayout.CENTER);
		frame.add(buttons, BorderLayout.SOUTH);

		JButton takePic = new JButton("take a picture");
		buttons.add(takePic, BorderLayout.SOUTH);
		takePic.addActionListener(vid);
		
		JButton blackFilter = new JButton("change filter to black");
		buttons.add(blackFilter, BorderLayout.SOUTH);
		blackFilter.addActionListener(vid);

		JButton negFilter = new JButton("change filter to negative");
		buttons.add(negFilter, BorderLayout.SOUTH);
		negFilter.addActionListener(vid);

		JButton trippyFilter = new JButton("change filter to trippy");
		buttons.add(trippyFilter, BorderLayout.SOUTH);
		trippyFilter.addActionListener(vid);

		JButton resetFilter = new JButton("change filter back to normal");
		buttons.add(resetFilter, BorderLayout.SOUTH);
		resetFilter.addActionListener(vid);
		
		vid.setVisible(true);

		while (true) {
			vid.repaint();
		}
	}

	public void actionPerformed(ActionEvent e) {
		JButton source = (JButton) e.getSource();
		if (source.getText().equals("change filter to black")) {
			System.out.println("black");
			videoCap.changeFilter(new BlackWhiteFilter());
		} else if (source.getText().equals("change filter to trippy")) {
			System.out.println("edge detection");
			videoCap.changeFilter(new EdgeFilter());
			
		} else if(source.getText().equals("change filter back to normal")) {
			System.out.println("normal");
			videoCap.changeFilter(null);
		} else if(source.getText().equals("change filter to negative")) {
			System.out.println("negative");
			videoCap.changeFilter(new NegativeFilter());
		} else if (source.getText().equals("take a picture")) {
			System.out.println("picture");
			File outputfile = new File("image"+imageCount+".jpg");
			try {
				ImageIO.write(videoCap.getOneFrame(), "jpg", outputfile);
				imageCount++;
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
}
