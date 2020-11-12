package graphics;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import static util.ResizeIcon.ResizeImageToPanel;


public class PicturePanel extends JPanel {
		private static final long serialVersionUID = 1L;
		private ImageIcon picture;
		private ImageIcon resized;
		private int lastWdth, lastHeight;
		
		public void setPicture(ImageIcon picture) {
			this.picture = picture;
			resized = ResizeImageToPanel(picture, this);
			repaint();
		}
		
		public ImageIcon getPicture() {
			return picture;
		}

		public PicturePanel() {
			setBackground(Color.DARK_GRAY);
			setLayout(null);
			lastHeight = lastWdth = 0;
			repaint();
		}
		
		
		public PicturePanel(ImageIcon picture) {
			this.picture = picture;
			setBackground(Color.DARK_GRAY);
			setLayout(null);
			resized = ResizeImageToPanel(picture, this);
			lastHeight = resized.getIconHeight();
			lastWdth = resized.getIconWidth();
			repaint();
		}
		
		public void resizeImage() {
			if(picture == null) return;
			int deltaHeight = Math.abs(getHeight() - lastHeight);
			int deltaWidth = Math.abs(getWidth() - lastWdth);
//			System.out.println("w = " + getWidth() + "h = " + getHeight());
			if(deltaHeight > 30 || deltaWidth > 30) {
				resized = ResizeImageToPanel(picture, this);
				lastHeight = getHeight();
				lastWdth = getWidth();
				repaint();				
			}
		}

		public void paintComponent(Graphics g) {
			g.drawRect(0, 0, getWidth(), getHeight());
			g.fillRect(0, 0, getWidth(), getHeight());
			if(resized == null) return;
			resizeImage();
			g.drawImage(resized.getImage(), getWidth()/2 - resized.getIconWidth() / 2,
								getHeight()/2 - resized.getIconHeight()/2, this);
		}


		
		
	}	