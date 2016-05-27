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
 * Once user takes an image, this class will analyze the image to find the faces
 * (via openCV), and then layer on a picture of Mr.Peck onto all those faces.
 *
 * @author Kushal Tirumala
 * @version May 26, 2016
 * @author Period: 6
 * @author Assignment: wubby
 *
 */
public class GPeckify extends Processor {
	// loads the opencv library
	{
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	}

	/**
	 * the image reference to the picture of mr peck
	 */
	private BufferedImage img;

	/**
	 * the cascade classifier, which is what is used to detect faces in the
	 * image (does a bunch of ML stuff, can read more about it on the OpenCV
	 * documentation)
	 */
	private CascadeClassifier face_cascade;

	/**
	 * constructs a GPeckify processor, initializing the cascade classifier, and
	 * assigning the image object of mr peck
	 */
	public GPeckify() {
		face_cascade = new CascadeClassifier("haarcascade_profileface.xml");
		if (face_cascade.empty())
			return;

		try {
			img = ImageIO.read(new File("gpeck.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * resizes an image to a specified size
	 * 
	 * @param img
	 *            the original image to be resized
	 * @param newW
	 *            the desired width of the image
	 * @param newH
	 *            the desired height of the image
	 * @return the new image with the desired height and width
	 */
	public static BufferedImage resize(BufferedImage img, int newW, int newH) {
		Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
		BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

		Graphics2D g2d = dimg.createGraphics();
		g2d.drawImage(tmp, 0, 0, null);
		g2d.dispose();

		return dimg;
	}

	/**
	 * the detect feature that finds all the faces in the image, creates
	 * bounding boxes around them, then proceeds to layer images of mr peck to
	 * fit those bounding boxes
	 * 
	 * @param inputFrame
	 *            the inputFrame (the picture to be analyze in the form of a
	 *            Mat)
	 * @return the image with all the pictures of mr peck layered onto to the
	 *         image
	 */
	public BufferedImage ify(Mat inputFrame) {
		MatOfRect faces = new MatOfRect();
		BufferedImage ans = VideoCap.getImage(inputFrame);

		Mat mat1 = new Mat();
		Mat mat2 = new Mat();
		inputFrame.copyTo(mat1);
		inputFrame.copyTo(mat2);

		Imgproc.cvtColor(mat1, mat2, Imgproc.COLOR_BGR2GRAY);
		Imgproc.equalizeHist(mat2, mat2);

		face_cascade.detectMultiScale(inputFrame, faces);

		for (Rect rect : faces.toArray()) {
			System.out.println("x: " + rect.x + " y: " + rect.y + " height: " + rect.height + " width: " + rect.width);
			System.out.println(inputFrame.rows() + " " + inputFrame.cols());
			// Point center= new Point(rect.x + rect.width*0.5, rect.y +
			// rect.height*0.5 );
			// Imgproc.ellipse( inputFrame, center, new Size( rect.width*0.5,
			// rect.height*0.5), 0.0, 0.0, 360.0, new Scalar( 0, 255, 255
			// ),4,8,0);

			// img.copyTo(mat1.rowRange(rect.y,
			// rect.y+rect.height).colRange(rect.x, rect.x+rect.width));
			// Mat selectedArea = mat1.submat(rect);
			// System.out.println(selectedArea);
			// img.copyTo(mat1.submat(0, img.rows(), 0, img.cols()));;
			// overlayImage(inputFrame, img, inputFrame);
			Graphics g = ans.getGraphics();
			BufferedImage tempImage = resize(img, rect.width, rect.height);
			g.drawImage(tempImage, rect.x, rect.y, null);
		}

		return ans;
	}

	/**
	 * returns the cascade classifier of the processor used to detect faces
	 * 
	 * @return the cascade classifier of the processor
	 */
	public CascadeClassifier getcc() {
		return face_cascade;
	}
}
