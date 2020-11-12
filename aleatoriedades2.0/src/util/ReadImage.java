package util;

import java.awt.FileDialog;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;



public class ReadImage {
	
		private FileDialog input;
		public ReadImage(JFrame frame) {
			input = new FileDialog(frame, "selecione as imagens desejadas", FileDialog.LOAD);
			input.setFilenameFilter(new FilenameFilter() {
				public boolean accept(File dir, String name) {
					 if (name.endsWith(".png") || name.endsWith(".jpg")
						     || name.endsWith(".jpeg")) return true;
						    return false;
						    }
			});
			
			input.setMultipleMode(true);
		}
		
		
		
		public List<ImageIcon> ChoseImages() {
			input.setVisible(true);
			var files = input.getFiles();
			if(files == null || files.length == 0) return null;

				List<ImageIcon> images = new ArrayList<ImageIcon>(files.length);
				
				for(int i = 0; i < files.length; i++)
					try {
						images.add(new ImageIcon( ImageIO.read(files[i])));
					} catch (IOException exception) {
						exception.printStackTrace();
					}
				
				
				return images;
				
			}
}
