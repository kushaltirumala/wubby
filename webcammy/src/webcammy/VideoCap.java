package webcammy;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.videoio.*;

/**
 * Captures the video from the webcam using openCV's Video Capture, sending a
 * continous stream of Mat's to be displayed on the VideoFrame; allows
 * functionality to change the filter or processor used to change the video
 * stream. also contains the useful methods to turn a BufferedImage into a Mat,
 * and vice-versa.
 *
 * @author Kushal Tirumala
 * @version May 26, 2016
 * @author Period: 6
 * @author Assignment: wubby
 *
 */
public class VideoCap {

	private Mat mat = new Mat();
	private byte[] data;
	private BufferedImage img;
	ScreenFilter newFilter;
	Processor proc;
	Mat still = new Mat();

	private static final double CAMERA_WIDTH = 320;
	private static final double CAMERA_HEIGHT = 240;

	VideoCapture cap;

	public VideoCap(BufferedImage img) {
		this.img = img;
		still = getMat(img);
	}

	public VideoCap() {
		cap = new VideoCapture(0);
		// cap.set(Videoio.CV_CAP_PROP_FPS, 30);
		cap.set(Videoio.CV_CAP_PROP_FRAME_WIDTH, CAMERA_WIDTH);
		cap.set(Videoio.CV_CAP_PROP_FRAME_HEIGHT, CAMERA_HEIGHT);

		cap.open(0);
	}

	public void changeFilter(ScreenFilter t) {
		newFilter = t;
	}

	public void changeFilter(Processor t) {
		proc = t;
	}

	public void changeFilter() {
		newFilter = null;
	}

	public BufferedImage getOneFrame() {
		cap.read(mat);
		if (proc == null && newFilter == null) {
			return getImage(mat);
		} else if (proc == null) {
			return newFilter.filter(getImage(mat));
		} else {
			return proc.detect(mat);
		}
	}

	public BufferedImage getStill() {
		return getImage(mat);
	}

	public static BufferedImage getImage(Mat frame) {
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

	public static Mat getMat(BufferedImage bi) {
		Mat mat = new Mat(bi.getHeight(), bi.getWidth(), CvType.CV_8UC3);
		byte[] data = ((DataBufferByte) bi.getRaster().getDataBuffer()).getData();
		mat.put(0, 0, data);
		return mat;
	}

	public Mat getMat() {
		return mat;
	}

	public ScreenFilter getFilter() {
		return newFilter;
	}

	public Processor getProcessor() {
		return proc;
	}

	{
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	}

}