package webcammy;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;
/**
 *  TODO Write a one-sentence summary of your class here.
 *  TODO Follow it with additional details about its purpose, what abstraction
 *  it represents, and how to use it.
 *
 *  @author  Kushal Tirumala
 *  @version May 26, 2016
 *  @author  Period: 6
 *  @author  Assignment: JMCh19_SafeTrade
 *
 *  @author  Sources: TODO
 */
public class GPeckify extends Processor {
	
	{
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	}
	private BufferedImage img; 
	private CascadeClassifier face_cascade;  

	public GPeckify(){
		face_cascade=new CascadeClassifier("haarcascade_profileface.xml");  
        if(face_cascade.empty())  
             return;

			try {
				img = ImageIO.read(new File("gpeck.jpg"));
			} catch (IOException e) {
				e.printStackTrace();
			}
			
	}
	
	
	public static BufferedImage resize(BufferedImage img, int newW, int newH) { 
	    Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
	    BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

	    Graphics2D g2d = dimg.createGraphics();
	    g2d.drawImage(tmp, 0, 0, null);
	    g2d.dispose();

	    return dimg;
	}  

	public BufferedImage ify (Mat inputFrame) {
		MatOfRect faces = new MatOfRect();
		BufferedImage ans = VideoCap.getImage(inputFrame);
    	
        Mat mat1=new Mat();
        Mat mat2=new Mat(); 
        inputFrame.copyTo(mat1);
        inputFrame.copyTo(mat2);
        
        Imgproc.cvtColor( mat1, mat2, Imgproc.COLOR_BGR2GRAY);  
        Imgproc.equalizeHist( mat2, mat2 );  
        
        face_cascade.detectMultiScale(inputFrame, faces); 
        
        for(Rect rect:faces.toArray())  
        {  
        	System.out.println("x: " + rect.x + " y: " + rect.y + " height: " + rect.height + " width: " + rect.width);
        	System.out.println(inputFrame.rows() + " " + inputFrame.cols());
            //Point center= new Point(rect.x + rect.width*0.5, rect.y + rect.height*0.5 );  
            //Imgproc.ellipse( inputFrame, center, new Size( rect.width*0.5, rect.height*0.5), 0.0, 0.0, 360.0, new Scalar( 0, 255, 255 ),4,8,0);  
            
            
            //img.copyTo(mat1.rowRange(rect.y, rect.y+rect.height).colRange(rect.x, rect.x+rect.width));
//            Mat selectedArea = mat1.submat(rect);
//            System.out.println(selectedArea);
//            img.copyTo(mat1.submat(0, img.rows(), 0, img.cols()));;
           // overlayImage(inputFrame, img, inputFrame);
            Graphics g = ans.getGraphics();
            BufferedImage tempImage = resize(img, rect.width, rect.height);
            g.drawImage(tempImage, rect.x, rect.y, null);    
        }  
        
        return ans;  
	}
}
