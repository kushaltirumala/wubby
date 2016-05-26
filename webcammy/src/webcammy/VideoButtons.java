package webcammy;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *  The bottom panel of our frame.
 *  A panel with buttons for changing the filters.
 *
 *  @author  Kavi Nelakonda
 *  @version May 26, 2016
 *  @author  Period: 6
 *  @author  Assignment: JMCh19_SafeTrade
 *
 *  @author  Sources: TODO
 */
public class VideoButtons extends JPanel implements ActionListener {

	public VideoCap videoCap;
	private int imageCount = 0;
	private LinkedList<File> images;
	private static double lightReference = Double.NEGATIVE_INFINITY;
	
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
		

		
		JButton drawing = new JButton("draw in the air");
		add(drawing, BorderLayout.SOUTH);
		drawing.addActionListener(this);
		
		JButton reference = new JButton("light reference");
		add(reference, BorderLayout.SOUTH);
		reference.addActionListener(this);

		
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
		}  else if(source.getText().equals("draw in the air")) {
			if(lightReference == Double.NEGATIVE_INFINITY){
				System.out.println("please reference the light you are using");
			} else {
			System.out.println("lightReference at: " + lightReference + " and drawing");
			Canvas c = new Canvas();
			Drawing d = new Drawing(c, lightReference);
			videoCap.changeFilter(d);
			}
		} else if(source.getText().equals("light reference")) {
			BufferedImage temp = videoCap.getStill();
			
			double t = Drawing.brightness(videoCap.getStill(), temp.getWidth()/2,temp.getHeight()/2);
			lightReference = t;
			System.out.println("new light reference point set at : " + lightReference);
		} 
		
	}
	
	public LinkedList<File> getImages()
	{
		return images;
	}

}
