package webcammy;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
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
	private JTextField listOfFiles;
	private GPeckify mrPeck;
	
	public TopPanel(VideoCap vidCap) {
		
		setLayout(new FlowLayout());
		mrPeck = new GPeckify();
		images = new LinkedList<File>();
		videoCap = vidCap;
		list="";
		
		JButton takeImage = new JButton("take a picture");
		JButton gPeckImage = new JButton("gpeckify it");
		JButton imageList = new JButton("list images");
		listOfFiles = new JTextField();
		add(listOfFiles, BorderLayout.SOUTH);
		add(takeImage, BorderLayout.SOUTH);
		add(gPeckImage, BorderLayout.SOUTH);
		add(imageList);
		takeImage.addActionListener(this);
		imageList.addActionListener(this);
		gPeckImage.addActionListener(this);
		
		setVisible(true);
	}

	public void listImages() {
		ListIterator<File> iter = (ListIterator<File>) images.iterator();
		while(iter.hasNext())
		{
			list+=iter.next().getName() + " ";
			System.out.println(list);
		}
	}
	
	public void addImage(File file) {
		images.add(file);
		imageCount++;
		String temp = listOfFiles.getText();
		listOfFiles.setText(temp + file.toString());
		System.out.println("Added new image: " + file.toString());
	}
	
	
	
	public void actionPerformed(ActionEvent e) {
		JButton source = (JButton) e.getSource();
		if (source.getText().equals("take a picture")) {
			System.out.println("picture");
			File outputfile = new File("images/image"+imageCount+".jpg");
			try {
				ImageIO.write(videoCap.getOneFrame(), "jpg", outputfile);
				addImage(outputfile);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		} else if(source.getText().equals("list images")) {
			listImages();
		} else if(source.getText().equals("gpeckify it")) {
			System.out.println("gpeckifying..");
			File outputfile = new File("images/gpecked"+imageCount+".jpg");
			try {
				videoCap.getOneFrame();
				BufferedImage toStore = mrPeck.detect(videoCap.getMat());
				ImageIO.write(toStore, "jpg", outputfile);
			} catch (IOException err) {
				err.printStackTrace();
			}
		}
	}

}
