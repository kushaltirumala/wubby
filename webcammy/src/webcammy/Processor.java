package webcammy;

import java.awt.image.BufferedImage;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;

/**
 * Processor class that detects faces given an image using openCV's cascade
 * classifier
 *
 * @author Kushal Tirumala
 * @version May 26, 2016
 * @author Period: 6
 * @author Assignment: wubby
 *
 */
public class Processor {
	/**
	 * the cascade classifier used to detect faces
	 */
	private CascadeClassifier face_cascade;

	/**
	 * constructs the processor—initalizes the cascade classifier to detect
	 * faces later
	 */
	public Processor() {
		face_cascade = new CascadeClassifier("haarcascade_profileface.xml");
		if (face_cascade.empty())
			return;
	}

	/**
	 * detects faces on the given input Mat, drawing an ellipse around the
	 * detected faces
	 * 
	 * @param inputframe
	 *            to Mat to be analyze (gotten from the video stream)
	 * @return an image with ellipses drawn around all the detected faces
	 */
	public BufferedImage detect(Mat inputframe) {
		MatOfRect faces = new MatOfRect();

		Mat mat1 = new Mat();
		Mat mat2 = new Mat();
		inputframe.copyTo(mat1);
		inputframe.copyTo(mat2);

		Imgproc.cvtColor(mat1, mat2, Imgproc.COLOR_BGR2GRAY);
		Imgproc.equalizeHist(mat2, mat2);

		face_cascade.detectMultiScale(mat2, faces);

		for (Rect rect : faces.toArray()) {
			Point center = new Point(rect.x + rect.width * 0.5, rect.y + rect.height * 0.5);
			Imgproc.ellipse(mat1, center, new Size(rect.width * 0.5, rect.height * 0.5), 0.0, 0.0, 360.0,
					new Scalar(0, 255, 255), 4, 8, 0);
		}

		return VideoCap.getImage(mat1);
	}

}
