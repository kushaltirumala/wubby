package webcammy;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.ListIterator;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.html.HTMLDocument.Iterator;

public class TopPanel extends JPanel implements ActionListener {

	private LinkedList<File> images;
	private int imageCount=0;
	private VideoCap videoCap;
	private String list;
	
	public TopPanel(VideoCap vidCap) {
		
		setLayout(new FlowLayout());
		
		images = new LinkedList<File>();
		videoCap = vidCap;
		list="";
		
		JButton takeImage = new JButton("take a picture");
		add(takeImage, BorderLayout.SOUTH);
		takeImage.addActionListener(this);
		
		setVisible(true);
	}

	public void listImages() {
		ListIterator<File> iter = (ListIterator<File>) images.iterator();
		while(iter.hasNext())
		{
			list+=iter.next().getName();
			JTextField temp = new JTextField();
			temp.setText(list);
			
		}
	}
	
	public void actionPerformed(ActionEvent e) {
		JButton source = (JButton) e.getSource();
		if (source.getText().equals("take a picture")) {
			System.out.println("picture");
			File outputfile = new File("images/image"+imageCount+".jpg");
			try {
				ImageIO.write(videoCap.getOneFrame(), "jpg", outputfile);
				images.add(outputfile);
				imageCount++;
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}

}
