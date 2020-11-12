package util;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class ResizeIcon {
	
	public static ImageIcon ResizeImage(ImageIcon image, int width, int height) {
//		System.out.println("event sended");
		Image i = image.getImage();
		double escala = determineImageScale(image.getIconWidth(), image.getIconHeight(), width, height);
		if(width <= 0 || height <= 0 ) return image;
		var newIcon = i.getScaledInstance((int)(image.getIconWidth() * escala),(int) (image.getIconHeight() * escala)
										, Image.SCALE_SMOOTH);
		return new ImageIcon(newIcon);
	}
	
	private static double determineImageScale(int sourceWidth, int sourceHeight, int targetWidth, int targetHeight) {
		double scalex = (double) targetWidth / sourceWidth;
		double scaley = (double) targetHeight / sourceHeight;
		return Math.min(scalex, scaley);
	}
	
	public static ImageIcon ResizeImageToPanel(ImageIcon picture, JPanel panel){
		int resizeX = 0, resizeY = 0;
		if(picture == null || panel == null) return picture;
		if(picture.getIconWidth() != panel.getWidth()) { 
			resizeX = panel.getWidth();
			resizeY = picture.getIconHeight();
		}
		if(picture.getIconHeight() != panel.getHeight()) { 
			resizeY =  panel.getHeight();
			if(resizeX == 0) resizeX = picture.getIconWidth();
		
		}
		if(resizeX != 0 || resizeY != 0)  return  ResizeImage(picture, resizeX, resizeY);
		return picture;
	}
	
}
