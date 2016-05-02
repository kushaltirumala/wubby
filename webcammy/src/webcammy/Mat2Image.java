package webcammy;

import java.awt.image.BufferedImage;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import java.awt.image.DataBufferByte;
import org.opencv.core.CvType;


public class Mat2Image {
    BufferedImage img;
    Mat mat = new Mat();
    byte[] dat;
    
    
    public Mat2Image() {}
    
    
    public BufferedImage getImage(Mat mat){
    	this.mat = mat;
        int w = mat.cols();
        int h = mat.rows();
        dat = new byte[w * h * 3];
        img = new BufferedImage(w, h, BufferedImage.TYPE_3BYTE_BGR);
        mat.get(0, 0, dat);
        img.getRaster().setDataElements(0, 0, mat.cols(), mat.rows(), dat);
        return img;
    }
    
    public static Mat bufferedImageToMat(BufferedImage bi) {
    	  Mat mat = new Mat(bi.getHeight(), bi.getWidth(), CvType.CV_8UC3);
    	  byte[] data = ((DataBufferByte) bi.getRaster().getDataBuffer()).getData();
    	  mat.put(0, 0, data);
    	  return mat;
    	}
    
    {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }
}