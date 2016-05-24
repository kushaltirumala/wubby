package webcammy;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.videoio.*;

public class VideoCap {
	
    private Mat mat = new Mat();
    private byte[] data;
    private BufferedImage img;
    ScreenFilter newFilter;
    Processor proc;
    
    private static final double CAMERA_WIDTH = 320;
    private static final double CAMERA_HEIGHT = 240;
    
    private VideoWriter vidwriter = new VideoWriter();
    private boolean recording = true;

    VideoCapture cap;

    public VideoCap(){
        cap = new VideoCapture(0);
        //cap.set(Videoio.CV_CAP_PROP_FPS, 30);
        cap.set(Videoio.CV_CAP_PROP_FRAME_WIDTH, CAMERA_WIDTH);
        cap.set(Videoio.CV_CAP_PROP_FRAME_HEIGHT, CAMERA_HEIGHT);

        cap.open(0);
    } 
    
    public void changeFilter(ScreenFilter t){
    	newFilter = t;
    }
    
    public void changeFilter(Processor t) {
    	proc = t;
    }
    
    public void changeFilter(){
    	newFilter = null;
    }
    
    public double getFPS(){
    	return cap.get(5);
    }
 
    public BufferedImage getOneFrame() {
        cap.read(mat);
//       if(newFilter==null)
//        	return getImage(mat);
//        else
//        	return newFilter.filter(getImage(mat));
       
       if(proc==null && newFilter==null){
    	   return getImage(mat);
       } else if(proc==null){
    	   return newFilter.filter(getImage(mat));
       } else {
    	   return proc.detect(mat);
       }
        
        
    }
    
    public static BufferedImage getImage(Mat frame){
        int type = 0;
        if (frame.channels() == 1) {
            type = BufferedImage.TYPE_BYTE_GRAY;
        } else if (frame.channels() == 3) {
            type = BufferedImage.TYPE_3BYTE_BGR;
        }
        BufferedImage image = new BufferedImage(frame.width(), frame.height(), type);
        WritableRaster raster = image.getRaster();
        DataBufferByte dataBuffer = (DataBufferByte) raster.getDataBuffer();
        byte[] data = dataBuffer.getData();
        frame.get(0, 0, data);

        return image;
    }
    
    public Mat getMat(){
    	return mat;
    }
    
    public boolean isRecording()
    {
    	return recording;
    }
    
    {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }
    
}