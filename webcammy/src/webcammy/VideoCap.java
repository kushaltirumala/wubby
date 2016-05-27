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

	/**
	 * mat referencing the single image from the stream
	 */
	private Mat mat = new Mat();

	/**
	 * reference to ScreenFilter to be applied (null if there is no
	 * ScreenFilter)
	 */
	ScreenFilter newFilter;

	/**
	 * reference to the BufferedImage obj IF VideoCap is constructed with an
	 * image and not a connection to the webcam
	 */
	BufferedImage img;
	/**
	 * reference to the Processor to be applied (null if there is no Processor)
	 */
	Processor proc;

	/**
	 * reference to a still Mat from the stream of images
	 */
	Mat still = new Mat();

	/**
	 * width of the camera while the video stream takes place
	 */
	private static final double CAMERA_WIDTH = 320;

	/**
	 * height of the camera while the video stream takes place
	 */
	private static final double CAMERA_HEIGHT = 240;

	/**
	 * reference to the VideoCapture object that allows connection to the webcam
	 */
	VideoCapture cap;

	/**
	 * Constructor in the case of loading ONLY an image—mainly for JUnit testing
	 * 
	 * @param img
	 */
	public VideoCap(BufferedImage img) {
		this.img = img;
		still = getMat(img);
	}

	/**
	 * Constructor in the case of opening an actual webcam connection
	 */
	public VideoCap() {
		cap = new VideoCapture(0);
		// cap.set(Videoio.CV_CAP_PROP_FPS, 30);
		cap.set(Videoio.CV_CAP_PROP_FRAME_WIDTH, CAMERA_WIDTH);
		cap.set(Videoio.CV_CAP_PROP_FRAME_HEIGHT, CAMERA_HEIGHT);

		cap.open(0);
	}

	/**
	 * Change the filter of the video stream
	 * 
	 * @param t
	 *            the ScreenFilter to change the filter to
	 */
	public void changeFilter(ScreenFilter t) {
		newFilter = t;
	}

	/**
	 * Change the processor to process images
	 * 
	 * @param t
	 *            the Processor to change the processing platform to
	 */
	public void changeFilter(Processor t) {
		proc = t;
	}

	/**
	 * changes the filter to null, meaning returning the filter back to normal
	 */
	public void changeFilter() {
		newFilter = null;
		proc = null;
	}

	/**
	 * method for getting one frame from the webcam; applies a filter/processor
	 * if there is one
	 * 
	 * @return the BufferedImage that represents the current frame the webcam
	 *         sends
	 */
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

	/**
	 * gets a still image from the webcam
	 * 
	 * @return the BufferedImage representing the still image
	 */
	public BufferedImage getStill() {
		return getImage(mat);
	}

	/**
	 * returns an image from a Mat
	 * 
	 * @param frame
	 *            the Mat to turn into a BufferedImage
	 * @return the BufferedImage version of that Mat
	 */
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

	/**
	 * returns a BufferedImage from a Mat
	 * 
	 * @param bi
	 *            BufferedImage to convert
	 * @return the Mat version of that BufferedImage
	 */
	public static Mat getMat(BufferedImage bi) {
		Mat mat = new Mat(bi.getHeight(), bi.getWidth(), CvType.CV_8UC3);
		byte[] data = ((DataBufferByte) bi.getRaster().getDataBuffer()).getData();
		mat.put(0, 0, data);
		return mat;
	}

	/**
	 * returns the current Mat
	 * 
	 * @return the current Mat
	 */
	public Mat getMat() {
		return mat;
	}

	/**
	 * returns the current ScreenFilter
	 * 
	 * @return the current ScreenFilter
	 */
	public ScreenFilter getFilter() {
		return newFilter;
	}

	/**
	 * returns the current processor
	 * 
	 * @return the current processor
	 */
	public Processor getProcessor() {
		return proc;
	}

	{
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	}

}