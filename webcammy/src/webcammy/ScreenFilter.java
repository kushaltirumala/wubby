package webcammy;

import java.awt.image.BufferedImage;

import org.opencv.core.Mat;

public interface ScreenFilter {
	BufferedImage filter(BufferedImage img);
}
