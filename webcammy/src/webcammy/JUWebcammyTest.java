package webcammy;

import org.junit.*;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

import static org.junit.Assert.*;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class JUWebcammyTest {

	BufferedImage img;
	@Test
	public void blackWhiteFilterConstructor()
	{
		BlackWhiteFilter s = new BlackWhiteFilter();
		assertNotNull(s);
	}
	
	@Test
	public void blackWhiteFilterTurnRGBToGrayscale()
	{
		BlackWhiteFilter s = new BlackWhiteFilter();
		Color c = new Color(255, 0, 0);
		int temp = s.turnRGBintoGrayscale(c);
		int wantedValue = (int)(255 * 0.299 + 0 * 0.587 + 0 * 0.114);
		Color newcolor = new Color(wantedValue, wantedValue, wantedValue);
		assertEquals("<<The Color " + c.getRGB() + " when turned into grayscale should be " + newcolor.getRGB(), temp, newcolor.getRGB());
	}
	
	@Test
	public void blackWhiteFilterFilter()
	{
		BlackWhiteFilter s= new BlackWhiteFilter();
		try {
			img = ImageIO.read(new File("lynbrook.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		BufferedImage gray = s.filter(img);
		assertNotNull(gray);
		BufferedImage temp = img;
		for(int i = 0; i <temp.getWidth(); i++){
			for(int j =0; j < temp.getHeight(); j++){
				Color c = new Color(img.getRGB(i, j));
				temp.setRGB(i, j, s.turnRGBintoGrayscale(c));
			}
		}
		
		assertEquals(gray, temp);
	}
	

	
	@Test
	public void edgeFilterConstructor()
	{
		EdgeFilter e = new EdgeFilter();
		assertNotNull(e);
	}
	
	@Test
	public void edgeFilterColorDistance()
	{
		EdgeFilter e = new EdgeFilter();
		Color c1 = new Color(255, 0, 0);
		Color c2 = new Color(0, 255, 0);
		double myval = e.colorDistance(c1, c2);
		double redDistance = c1.getRed() - c2.getRed();
        double greenDistance = c1.getGreen() - c2.getGreen();
        double blueDistance = c1.getBlue() - c2.getBlue();
        double realval = Math.sqrt( redDistance * redDistance + greenDistance
            * greenDistance + blueDistance * blueDistance );
        
        assertEquals(myval, realval, 0.0001);
	}
	
	@Test
	public void edgeFilterFilter()
	{
		EdgeFilter s= new EdgeFilter();
		try {
			img = ImageIO.read(new File("lynbrook.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		BufferedImage detected = s.filter(img);
		assertNotNull(detected);
		BufferedImage temp = img;
		for(int i = 0; i <temp.getWidth(); i++){
			for(int j =0; j < temp.getHeight(); j++){
				Color c1 = new Color(img.getRGB(i, j));
				Color c2 = new Color(img.getRGB(i,j+1));
				if(EdgeFilter.colorDistance(c1, c2) > 10) {
					img.setRGB(i, j, Color.black.getRGB());
				} else {
					img.setRGB(i,j, Color.WHITE.getRGB());
				}
			}
		}
		
		assertEquals(detected, temp);
	}
	
	@Test
	public void negativeFilterConstructor()
	{
		
	}
	
	@Test
	public void negativeFilterTurnRGBToNegative()
	{
		
	}
	
	@Test
	public void negativeFilterFilter()
	{
		
	}
	
	@Test
	public void topPanelConstructor()
	{
		
	}
	
	@Test
	public void topPanelActionPerformed()
	{
		
	}
	
	@Test
	public void videoButtonsConstructor()
	{
		
	}
	
	@Test
	public void videoButtonsActionPerformed()
	{
		
	}
	
	@Test
	public void videoCapConstructor()
	{
		
	}
	
	@Test
	public void videoCapChangeFilter()
	{
		
	}
	
	@Test
	public void videoCapGetFPS()
	{
		
	}
	
	boolean bufferedImagesEqual(BufferedImage img1, BufferedImage img2) {
	    if (img1.getWidth() == img2.getWidth() && img1.getHeight() == img2.getHeight()) {
	        for (int x = 0; x < img1.getWidth(); x++) {
	            for (int y = 0; y < img1.getHeight(); y++) {
	                if (img1.getRGB(x, y) != img2.getRGB(x, y))
	                    return false;
	            }
	        }
	    } else {
	        return false;
	    }
	    return true;
	}
}
