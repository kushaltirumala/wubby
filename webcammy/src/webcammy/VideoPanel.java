package webcammy;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;

import org.opencv.core.Core;

public class VideoPanel extends JPanel {
	
    public VideoCap videoCap = new VideoCap();
	private JPanel videoPane;
	private JButton takePicture;

    public VideoPanel() {	
    	super();
    }
 
    public void paint(Graphics g){
        super.paintComponent(g);
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

       // frame.setContentPane(vid);
        frame.add(vid, BorderLayout.CENTER);
        frame.add(buttons, BorderLayout.SOUTH);
        
        JButton blackFilter = new JButton("change filter to black");
    	//blackFilter.setBounds(0, 0, 50, 50);
    	buttons.add(blackFilter, BorderLayout.SOUTH);
    	
    	JButton trippyFilter = new JButton("change filter to trippy");
    	//blackFilter.setBounds(0, 0, 50, 50);
    	buttons.add(trippyFilter, BorderLayout.SOUTH);
        

    	vid.setVisible(true);
        
        while(true){
        	vid.repaint();
        }
    }
}

