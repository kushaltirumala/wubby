package webcammy;

import org.junit.*;
import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;

import static org.junit.Assert.*;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Set;
import java.util.TreeSet;

import javax.imageio.ImageIO;

public class JUWebcammyTest {
	{
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	}
	BufferedImage img;
	GPeckify g = new GPeckify();

	@Test
	public void blackWhiteFilterConstructor() {
		BlackWhiteFilter s = new BlackWhiteFilter();
		assertNotNull(s);
	}

	@Test
	public void blackWhiteFilterTurnRGBToGrayscale() {
		BlackWhiteFilter s = new BlackWhiteFilter();
		Color c = new Color(255, 0, 0);
		int temp = s.turnRGBintoGrayscale(c);
		int wantedValue = (int) (255 * 0.299 + 0 * 0.587 + 0 * 0.114);
		Color newcolor = new Color(wantedValue, wantedValue, wantedValue);
		assertEquals("<<The Color " + c.getRGB() + " when turned into grayscale should be " + newcolor.getRGB(), temp,
				newcolor.getRGB());
	}

	@Test
	public void blackWhiteFilterFilter() {
		BlackWhiteFilter s = new BlackWhiteFilter();
		try {
			img = ImageIO.read(new File("lynbrook.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		BufferedImage gray = s.filter(img);
		assertNotNull(gray);
		BufferedImage temp = img;
		for (int i = 0; i < temp.getWidth(); i++) {
			for (int j = 0; j < temp.getHeight(); j++) {
				Color c = new Color(img.getRGB(i, j));
				temp.setRGB(i, j, s.turnRGBintoGrayscale(c));
			}
		}

		assertEquals(gray, temp);
	}

	@Test
	public void edgeFilterConstructor() {
		EdgeFilter e = new EdgeFilter();
		assertNotNull(e);
	}

	@Test
	public void edgeFilterColorDistance() {
		EdgeFilter e = new EdgeFilter();
		Color c1 = new Color(255, 0, 0);
		Color c2 = new Color(0, 255, 0);
		double myval = e.colorDistance(c1, c2);
		double redDistance = c1.getRed() - c2.getRed();
		double greenDistance = c1.getGreen() - c2.getGreen();
		double blueDistance = c1.getBlue() - c2.getBlue();
		double realval = Math
				.sqrt(redDistance * redDistance + greenDistance * greenDistance + blueDistance * blueDistance);

		assertEquals(myval, realval, 0.0001);
	}

	@Test
	public void edgeFilterFilter() {
		EdgeFilter s = new EdgeFilter();
		try {
			img = ImageIO.read(new File("lynbrook.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		BufferedImage detected = s.filter(img);
		assertNotNull(detected);
		BufferedImage temp = img;
		for (int i = 0; i < temp.getWidth(); i++) {
			for (int j = 1; j < temp.getHeight() - 1; j++) {
				Color c1 = new Color(img.getRGB(i, j));
				Color c2 = new Color(img.getRGB(i, j));
				if (EdgeFilter.colorDistance(c1, c2) > 10) {
					img.setRGB(i, j, Color.black.getRGB());
				} else {
					img.setRGB(i, j, Color.WHITE.getRGB());
				}
			}
		}

		assertEquals(detected, temp);
	}

	@Test
	public void negativeFilterConstructor() {
		NegativeFilter n = new NegativeFilter();
		assertNotNull(n);
	}

	@Test
	public void negativeFilterTurnRGBToNegative() {
		NegativeFilter n = new NegativeFilter();
		Color example = new Color(255, 255, 255);
		Color supposedToBe = new Color(255 - 255, 255 - 255, 255 - 255);
		int temp = n.turnRGBintoNegative(example);
		assertEquals(supposedToBe.getRGB(), temp);
	}

	@Test
	public void negativeFilterFilter() {
		NegativeFilter s = new NegativeFilter();
		try {
			img = ImageIO.read(new File("lynbrook.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		BufferedImage detected = s.filter(img);
		assertNotNull(detected);
		BufferedImage temp = img;
		for (int i = 0; i < temp.getWidth(); i++) {
			for (int j = 1; j < temp.getHeight() - 1; j++) {
				Color c1 = new Color(img.getRGB(i, j));
				Color c2 = new Color(255 - c1.getRed(), 255 - c1.getGreen(), 255 - c1.getBlue());
				img.setRGB(i, j, c2.getRGB());
			}
		}

		assertEquals(detected, temp);
	}

	@Test
	public void videoCapConstructor() {
		// because we didn't want your webcam to just turn on when testing
		try {
			img = ImageIO.read(new File("lynbrook.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		VideoCap c = new VideoCap(img);
		assertNotNull(c);
	}

	@Test
	public void videoCapChangeFilter() {
		try {
			img = ImageIO.read(new File("lynbrook.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		VideoCap c = new VideoCap(img);
		BlackWhiteFilter a = new BlackWhiteFilter();
		c.changeFilter(a);
		assertEquals(c.getFilter(), a);
	}

	@Test
	public void videoCapChangeProcessor() {
		try {
			img = ImageIO.read(new File("lynbrook.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		VideoCap c = new VideoCap(img);
		Processor a = new Processor();
		c.changeFilter(a);
		assertEquals(c.getProcessor(), a);
	}

	@Test
	public void videoCapChangeFilterToNull() {
		try {
			img = ImageIO.read(new File("lynbrook.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		VideoCap c = new VideoCap(img);
		c.changeFilter();
		assertEquals(c.getProcessor(), null);
		assertEquals(c.getFilter(), null);
	}

	@Test
	public void videoCapGetImage() {
		try {
			img = ImageIO.read(new File("lynbrook.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		Mat temp = Imgcodecs.imread("lynbrook.jpg");
		BufferedImage toCompare = VideoCap.getImage(temp);
		assertEquals(toCompare.getHeight(), img.getHeight());
		assertEquals(toCompare.getWidth(), img.getWidth());
	}

	@Test
	public void videoCapGetMat() {
		try {
			img = ImageIO.read(new File("lynbrook.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Mat temp = VideoCap.getMat(img);
		assertEquals(temp.height(), img.getHeight());
		assertEquals(temp.width(), img.getWidth());
	}

	@Test
	public void videoCapReturnMat() {
		try {
			img = ImageIO.read(new File("lynbrook.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		VideoCap c = new VideoCap(img);
		assertNotNull(c.getMat());
	}

	@Test
	public void videoCapReturnFilter() {
		try {
			img = ImageIO.read(new File("lynbrook.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		VideoCap c = new VideoCap(img);
		BlackWhiteFilter f = new BlackWhiteFilter();
		c.changeFilter(f);
		assertEquals(f, c.getFilter());
	}

	@Test
	public void videoCapReturnProcessor() {
		try {
			img = ImageIO.read(new File("lynbrook.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		VideoCap c = new VideoCap(img);
		Processor f = new Processor();
		c.changeFilter(f);
		assertEquals(f, c.getProcessor());
	}

	@Test
	public void canvasUpdateQueue() {
		DrawPane c = new DrawPane();
		Point x = new Point(0, 0);
		c.updateQueue(x);
		assertEquals(c.getPoints().poll(), x);
	}

	@Test
	public void drawingConstructor() {
		double test = 10.0;
		Canvas c = new Canvas();
		Drawing d = new Drawing(c, test);
		assertEquals(d.getLR(), 10.0, 0.0001);
	}

	@Test
	public void drawingInit() {
		Set<Point> p = new TreeSet<Point>();
		Point x = new Point(0, 0);
		p.add(x);
		Canvas c = new Canvas();
		Drawing d = new Drawing(c, 10.0);
		d.setUpCanvas(p);
		DrawPane temp = c.getDrawPane();
		assertEquals(temp.getPoints().poll(), x);

	}

	@Test
	public void drawingFilter() {
		try {
			img = ImageIO.read(new File("lynbrook.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Canvas c = new Canvas();
		Drawing d = new Drawing(c, 0.95);
		BufferedImage temp = d.filter(img);
		boolean noBright = true;
		for (int i = 0; i < temp.getWidth(); i++) {
			for (int j = 0; j < temp.getHeight(); j++) {
				if (temp.getRGB(i, j) == Color.red.getRGB()) {
					noBright = false;
					break;
				}
			}
		}
		assertFalse(noBright);
	}

	@Test
	public void drawingGetPoints() {
		Canvas c = new Canvas();
		Drawing d = new Drawing(c, 0.95);
		assertNotNull(d);
	}

	@Test
	public void drawingShoutOutToCanvas() {
		Point x = new Point(0, 0);
		Canvas c = new Canvas();
		Drawing d = new Drawing(c, 0.95);
		d.shoutOutToCanvas(x);
		assertEquals(c.getDrawPane().getPoints().poll(), x);
	}

	@Test
	public void drawingBrightness() {
		try {
			img = ImageIO.read(new File("lynbrook.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Canvas c = new Canvas();
		Drawing d = new Drawing(c, 0.95);
		double temp = d.brightness(img, 0, 0);
		int rgb = img.getRGB(0, 0);

		Color color = new Color(rgb);

		int red = color.getRed();
		int green = color.getGreen();
		int blue = color.getBlue();

		double realVal = (red * 0.2126f + green * 0.7152f + blue * 0.0722f) / 255;
		assertEquals(temp, realVal, 0.0001);
	}

	@Test
	public void drawingGetCanvas() {
		Canvas c = new Canvas();
		Drawing d = new Drawing(c, 0.95);
		assertEquals(d.getCanvas(), c);
	}

	@Test
	public void drawingGetLR() {
		Canvas c = new Canvas();
		Drawing d = new Drawing(c, 0.95);
		assertEquals(d.getLR(), 0.95, 0.001);
	}

	@Test
	public void gpeckifyConstructor() {
		assertNotNull(g.getcc());
	}

	@Test
	public void gpeckifyResize() {
		try {
			img = ImageIO.read(new File("lynbrook.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		BufferedImage temp = GPeckify.resize(img, 10, 10);
		assertEquals(temp.getHeight(), 10);
		assertEquals(temp.getWidth(), 10);
	}

	@Test
	public void gpeckifyIfy() {
		try {
			img = ImageIO.read(new File("lynbrook.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		BufferedImage temp = g.ify(VideoCap.getMat(img));
		assertEquals(temp.getHeight(), img.getHeight());
		assertEquals(temp.getWidth(), img.getWidth());

	}

	@Test
	public void gpeckifyReturnCC() {
		// CascadeClassifier temp = new
		// CascadeClassifier("haarcascade_profileface.xml");
		assertNotNull(g.getcc());
	}

	@Test
	public void pointConstructor() {
		Point x = new Point(0, 0);
		assertEquals(x.x, 0);
		assertEquals(x.y, 0);
	}

	@Test
	public void pointCompareTo() {
		Point c = new Point(0, 0);
		Point d = new Point(1, 0);
		assertEquals(c.compareTo(d), -1);
	}

	@Test
	public void processorConstructor() {
		Processor p = new Processor();
		assertNotNull(p);

	}

	@Test
	public void processorFilter() {
		Processor p = new Processor();
		try {
			img = ImageIO.read(new File("lynbrook.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		BufferedImage temp = p.detect(VideoCap.getMat(img));
		assertEquals(img.getHeight(), temp.getHeight());
		assertEquals(img.getWidth(), temp.getWidth());
	}

}
