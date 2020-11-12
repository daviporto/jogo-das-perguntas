package debug;

import static util.ResizeIcon.ResizeImage;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class Image {
	private JFrame frame;
	private JPanel panel;
	private ImageIcon image;
	
	public Image(ImageIcon image) {
		image = ResizeImage(image, 300, 300);
		frame = new JFrame();
		frame.setLocationRelativeTo(null);
		frame.setLayout(null);
		frame.setSize(image.getIconWidth(), image.getIconHeight());
		this.image = image;
		frame.getContentPane().setBackground(Color.darkGray);
		frame.setVisible(true);	
		panel = new panel();
		frame.getContentPane().add(panel);
	}
	
	
	private class panel extends JPanel{
		private static final long serialVersionUID = 1L;
		public panel() {
			this.setSize(frame.getSize());
		}
		@Override
		public void paintComponent(Graphics g) {
			g.drawImage(image.getImage(), 0, 0, this);
		}
		
	}
}
