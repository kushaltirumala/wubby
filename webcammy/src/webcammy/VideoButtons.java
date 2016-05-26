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
		
		JButton tracking = new JButton("change to track image");
		add(tracking, BorderLayout.SOUTH);
		tracking.addActionListener(this);
		
		JButton tmntFilter = new JButton("change to tmnt");
		add(tmntFilter, BorderLayout.SOUTH);
		tmntFilter.addActionListener(this);
		
		JButton drawing = new JButton("draw in the air");
		add(drawing, BorderLayout.SOUTH);
		drawing.addActionListener(this);

		
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
		} else if(source.getText().equals("change to track image")) {
			System.out.println("image");
			videoCap.changeFilter(new Processor());
		} else if(source.getText().equals("change to tmnt")) {
			System.out.println("turtles");
			videoCap.changeFilter(new ImageFilter());
		} else if(source.getText().equals("draw in the air")) {
			System.out.println("drawing");
			videoCap.changeFilter(new Drawing());
		} else if(source.getText().equals("air draw")) {
			System.out.println("air draw");
			VideoFrame.stillMode = true;
			Airdraw a = new Airdraw();
			a.filter(videoCap.getOneFrame(), videoCap.getStill());
		}
		
	}
	
	public LinkedList<File> getImages()
	{
		return images;
	}

}
