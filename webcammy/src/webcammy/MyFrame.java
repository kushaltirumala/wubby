package webcammy;

import java.awt.*;
import javax.swing.*;

public class MyFrame extends JFrame {
	
    public VideoCap videoCap = new VideoCap();
	private JPanel contentPane;
	private JButton takePicture;



    class newthread extends Thread{
        public void run() {
            while(true){
                repaint();
            }  
        } 
    }


    
    public MyFrame() {
    	
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 500, 500);
        contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout());
        takePicture = new JButton("Take a picture");
    	takePicture.setBounds(0, 0, 100, 100);
    	contentPane.add(takePicture);
    	contentPane.setVisible(true);
        new newthread().start();
    }
 
    public void paint(Graphics g){
        g = contentPane.getGraphics();
        g.drawImage(videoCap.getOneFrame(), 300,0, this);
    }
    
    public static void main(String[] args) {
        try {
            MyFrame frame = new MyFrame();
            frame.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

