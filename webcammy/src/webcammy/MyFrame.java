package webcammy;

import java.awt.*;
import javax.swing.*;

import org.opencv.core.Core;

public class MyFrame extends JFrame {
	
    public VideoCap videoCap = new VideoCap();
	private JPanel contentPane;
	private JButton takePicture;

    public MyFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 500, 500);
        contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout());
    	contentPane.setVisible(true);
    }
 
    public void paint(Graphics g){
        g = contentPane.getGraphics();
        g.drawImage(videoCap.getOneFrame(), 300,0, this);
    }
    
    public static void main(String[] args) {
    	System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        MyFrame frame = new MyFrame();
        frame.setVisible(true);
        
        while(true){
        	frame.repaint();
        }
    }
}

