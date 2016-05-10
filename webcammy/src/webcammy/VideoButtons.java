package webcammy;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;

public class VideoButtons extends JPanel implements ActionListener {

	public VideoCap videoCap;
	private int imageCount = 0;
	
	
	public VideoButtons(VideoCap vidCap) {
		setLayout(new FlowLayout());
		JButton takePic = new JButton("take a picture");
		add(takePic, BorderLayout.SOUTH);
		takePic.addActionListener(this);
		
		JButton blackFilter = new JButton("change filter to black");
		add(blackFilter, BorderLayout.SOUTH);
		blackFilter.addActionListener(this);
		
		JButton negFilter = new JButton("change filter to negative");
		add(negFilter, BorderLayout.SOUTH);
		negFilter.addActionListener(this);
		
		JButton trippyFilter = new JButton("change filter to trippy");
		add(trippyFilter, BorderLayout.SOUTH);
		trippyFilter.addActionListener(this);
		
		JButton resetFilter = new JButton("change filter back to normal");
		add(resetFilter, BorderLayout.SOUTH);
		resetFilter.addActionListener(this);
		
		videoCap = vidCap;
		
		setVisible(true);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
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
			File outputfile = new File("images/image"+imageCount+".jpg");
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
