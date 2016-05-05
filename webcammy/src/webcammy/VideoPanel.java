package webcammy;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.Border;

import org.opencv.core.Core;

public class VideoPanel extends JPanel implements ActionListener {
	
    public VideoCap videoCap = new VideoCap();
	private JPanel videoPane;
	private JButton takePicture;
	 JButton blackFilter = new JButton("change filter to black");

    public VideoPanel() {	
    	super();
    }
 
    public void paint(Graphics g){
        paintComponent(g);
        g.drawImage(videoCap.getOneFrame(), -100,200, this);
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
        
        JButton blackFilter = new JButton("change filter to black");
    	buttons.add(blackFilter, BorderLayout.SOUTH);
    	blackFilter.addActionListener(vid);
    	
    	JButton trippyFilter = new JButton("change filter to trippy");
    	buttons.add(trippyFilter, BorderLayout.SOUTH);
    	trippyFilter.addActionListener(vid);
        

    	vid.setVisible(true);
        
        while(true){
        	vid.repaint();
        }
    }

	public void actionPerformed(ActionEvent e) {
		JButton source = (JButton)e.getSource();
		if(source.getText().equals("change filter to black")) {
			System.out.println("black");
			videoCap.changeFilter(new BlackWhiteFilter());
		} else if (source.getText().equals("change filter to trippy")) {
			System.out.println("edge detection");
			videoCap.changeFilter(new EdgeFilter());
		}
	}
}

