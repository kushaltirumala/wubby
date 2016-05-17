package webcammy;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;

public class VideoButtons extends JPanel implements ActionListener {

	public VideoCap videoCap;
	private int imageCount = 0;
	private LinkedList<File> images;
	
	
	public VideoButtons(VideoCap vidCap) {
		setLayout(new FlowLayout());
		
		
		JButton blackFilter = new JButton("change to black");
		add(blackFilter, BorderLayout.SOUTH);
		blackFilter.addActionListener(this);
		
		JButton negFilter = new JButton("change to negative");
		add(negFilter, BorderLayout.SOUTH);
		negFilter.addActionListener(this);
		
		JButton trippyFilter = new JButton("change to trippy");
		add(trippyFilter, BorderLayout.SOUTH);
		trippyFilter.addActionListener(this);
		
		JButton normalFilter = new JButton("change to normal");
		add(normalFilter, BorderLayout.SOUTH);
		normalFilter.addActionListener(this);
		
		JButton resetFilter = new JButton("change filter back to original");
		add(resetFilter, BorderLayout.SOUTH);
		resetFilter.addActionListener(this);
		
		JButton imageFilter = new JButton("change filter to image");
		add(imageFilter, BorderLayout.SOUTH);
		imageFilter.addActionListener(this);
		
		videoCap = vidCap;
		images = new LinkedList<File>();
		setVisible(true);
	}
	
	
	public void actionPerformed(ActionEvent e) {
		JButton source = (JButton) e.getSource();
		if (source.getText().equals("change to black")) {
			System.out.println("black");
			videoCap.changeFilter(new BlackWhiteFilter());
		} else if (source.getText().equals("change to trippy")) {
			System.out.println("edge detection");
			videoCap.changeFilter(new EdgeFilter());
		} else if(source.getText().equals("change to negative")) {
			System.out.println("negative");
			videoCap.changeFilter(new NegativeFilter());
		} else if(source.getText().equals("change to normal")) {
			System.out.println("normal");
			videoCap.changeFilter();
		} else if(source.getText().equals("change filter to image")) {
			System.out.println("image");
			videoCap.changeFilter(new ImageFilter());
		}
		
	}
	
	public LinkedList<File> getImages()
	{
		return images;
	}

}
