package webcammy;

import java.awt.image.BufferedImage;
import org.opencv.videoio.*;

public class VideoCap {    
    VideoCapture cap;
    Mat2Image mat2Img = new Mat2Image();

    public VideoCap(){
        cap = new VideoCapture();
        cap.open(0);
    } 
 
    public BufferedImage getOneFrame() {
        cap.read(mat2Img.mat);
        return mat2Img.getImage(mat2Img.mat);
    }
}