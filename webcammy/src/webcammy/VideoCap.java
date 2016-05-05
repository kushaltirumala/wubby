package webcammy;

import java.awt.image.BufferedImage;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.videoio.*;

public class VideoCap {
	
    private Mat mat = new Mat();
    private byte[] data;
    private BufferedImage img;
    ScreenFilter newFilter;
    
    

    VideoCapture cap;

    public VideoCap(){
        cap = new VideoCapture();
        cap.open(0);
    } 
    
    public void changeFilter(ScreenFilter t){
    	newFilter = t;
    }
 
    public BufferedImage getOneFrame() {
        cap.read(mat);
        if(newFilter==null)
        	return getImage(mat);
        else
        	return newFilter.filter(getImage(mat));
    }
    
    public BufferedImage getImage(Mat mat){
    	this.mat = mat;
        int w = mat.cols();
        int h = mat.rows();
        data = new byte[w * h * 3];
        img = new BufferedImage(w, h, BufferedImage.TYPE_3BYTE_BGR);
        mat.get(0, 0, data);
        img.getRaster().setDataElements(0, 0, mat.cols(), mat.rows(), data);
        return img;
    }
    
    public Mat getMat(){
    	return mat;
    }
    
    {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }
    
}