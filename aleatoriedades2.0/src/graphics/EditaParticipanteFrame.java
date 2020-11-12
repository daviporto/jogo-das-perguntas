package graphics;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import controller.Main;
import dataBaseStuff.Participante;
import util.ReadImage;

/**
 * TODO Put here a description of what this class does.
 *
 * @author davi.
 *         Created 7 de out de 2020.
 */
public class EditaParticipanteFrame {

	private JFrame frame;
	private Participante participante;
	private PicturePanel picturePanel;
	private JTextField jtfname;
	private int CurrentPictureIndex = 0;
	private List<ImageIcon> pictures;
	private String name;
	private ManageParticipantesFrame manage;
	private ReadImage imagesReader;
	private Main main;

	
	public EditaParticipanteFrame(Participante participante, ManageParticipantesFrame manage, Main main) {
		this.participante = participante;
		pictures = participante.getPictures();
		this.main = main;
		this.manage = manage;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.addWindowListener(new WindowListener() {
			
			
			public void windowOpened(WindowEvent e) {}
			public void windowIconified(WindowEvent e) {}
			public void windowDeiconified(WindowEvent e) {}
			public void windowDeactivated(WindowEvent e) {}
			public void windowClosing(WindowEvent e) {}
			public void windowClosed(WindowEvent e) {
				manage.setVisible();
				manage.changedParticipante(participante);
			}
			public void windowActivated(WindowEvent e) {}
		});
		frame.addComponentListener(new ComponentListener() {
			public void componentShown(ComponentEvent e) {}
			public void componentResized(ComponentEvent e) {
				if(picturePanel == null) return;
				picturePanel.resizeImage();
			}
			public void componentMoved(ComponentEvent e) {}
			public void componentHidden(ComponentEvent e) {}
		});
		frame.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_LEFT)
					previousImage();
				else if (e.getKeyCode() == KeyEvent.VK_RIGHT)
					nextImage();
			}
		});
		if(participante != null ) frame.setTitle(participante.getName());
		else frame.setTitle("Participantes");
		frame.getContentPane().setBackground(Color.DARK_GRAY);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{40, 640, 40};
		gridBagLayout.rowHeights = new int[]{30, 440, 30};
		gridBagLayout.columnWeights = new double[]{1.0, 100.0, 1.0};
		gridBagLayout.rowWeights = new double[]{1.0, 100.0};
		frame.getContentPane().setLayout(gridBagLayout);
		frame.setMinimumSize(new Dimension(500, 300));
		frame.setBounds(0, 0, 700, 500);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setVisible(true);
		
		JPanel namePanel = new JPanel();
		jtfname = new JTextField("", 20);
		jtfname.getDocument().addDocumentListener( new DocumentListener() {
			
			public void removeUpdate(DocumentEvent e) {
				jtfname.setBackground(Color.DARK_GRAY);
				name = jtfname.getText().trim();
				if(name.equals("")) jtfname.setBackground(Color.RED);
			}
			public void insertUpdate(DocumentEvent e) {
				jtfname.setBackground(Color.DARK_GRAY);
				name = jtfname.getText().trim();
				if(name.equals("")) jtfname.setBackground(Color.RED);
			}
			public void changedUpdate(DocumentEvent e) {
				jtfname.setBackground(Color.DARK_GRAY);
				name = jtfname.getText().trim();
				if(name.equals("")) jtfname.setBackground(Color.RED);
			}
		});
		
		
		jtfname.setText(participante.getName());
		jtfname.setFont(Main.DEFAULTFONT);
		jtfname.setForeground(Color.white);
		namePanel.setBackground(Color.DARK_GRAY);
		GridBagConstraints gbc_namePanel = new GridBagConstraints();
		gbc_namePanel.gridwidth = 3;
		gbc_namePanel.fill = GridBagConstraints.BOTH;
		gbc_namePanel.gridx = 0;
		gbc_namePanel.gridy = 0;
		namePanel.add(jtfname);
		frame.getContentPane().add(namePanel, gbc_namePanel);
		
		
		picturePanel = new PicturePanel(participante.getPicture());
		picturePanel.setBackground(Color.DARK_GRAY);
		GridBagConstraints gbc_picturePanel  = new GridBagConstraints();
		gbc_picturePanel.gridwidth = 3;
		gbc_picturePanel.fill = GridBagConstraints.BOTH;
		gbc_picturePanel.gridx = 0;
		gbc_picturePanel.gridy = 1;
		frame.getContentPane().add(picturePanel, gbc_picturePanel);
		
		
		
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setBackground(Color.DARK_GRAY);
		GridBagConstraints gbc_buttonsPanel = new GridBagConstraints();
		gbc_buttonsPanel.gridwidth = 3;
		gbc_buttonsPanel.fill = GridBagConstraints.BOTH;
		gbc_buttonsPanel.gridx = 0;
		gbc_buttonsPanel.gridy = 2;
		frame.getContentPane().add(buttonsPanel, gbc_buttonsPanel);
		
		JButton btnAddPicture = new JButton("adicionar fotos");
		JButton btnremovePicture = new JButton("remover foto");
		JButton btnNext = new JButton(Main.rigthArrowIcon);
		JButton btnPrevious = new JButton(Main.leftArrowIcon);
		JButton btnDone = new JButton("feito");
		
		btnAddPicture.setBackground(Color.DARK_GRAY);
		btnAddPicture.setForeground(Color.WHITE);
		btnAddPicture.setBorderPainted(false);
		
		btnremovePicture.setBackground(Color.DARK_GRAY);
		btnremovePicture.setForeground(Color.WHITE);
		btnremovePicture.setBorderPainted(false);
		
		btnNext.setBackground(Color.DARK_GRAY);
		btnNext.setBorderPainted(false);
		
		btnPrevious.setBackground(Color.DARK_GRAY);
		btnPrevious.setBorderPainted(false);
		
		
		btnDone.setBackground(Color.DARK_GRAY);
		btnDone.setForeground(Color.WHITE);
		btnDone.setBorderPainted(false);
		
		btnAddPicture.setToolTipText("abre o seletor para adicionar mais fotos");
		btnremovePicture.setToolTipText("remove a imagem atual");
		btnNext.setToolTipText("proxima imagem");
		btnPrevious.setToolTipText("proxima imagem");
		btnDone.setToolTipText("confirmar mudanças e fechar janela");
		
		btnremovePicture.addActionListener(event -> removeImage());
		btnPrevious.addActionListener(event -> previousImage());
		btnNext.addActionListener(event -> nextImage());
		btnAddPicture.addActionListener(event -> addPictures());
		btnDone.addActionListener(event -> done());
	
		
		
		buttonsPanel.add(btnPrevious);
		buttonsPanel.add(btnAddPicture);
		buttonsPanel.add(btnDone);
		buttonsPanel.add(btnremovePicture);
		buttonsPanel.add(btnNext);
		frame.pack();
		picturePanel.repaint();
		
		imagesReader = new ReadImage(frame);
	}
	
	
	private void done() {
		name = jtfname.getText().trim();
		if(name.equals("")) {
			JOptionPane.showMessageDialog(frame, "o nome não pode ser vazio");
			return;
		}
		if(main.nomeExiste(name)) {
			jtfname.setBackground(Color.RED);
			JOptionPane.showMessageDialog(frame, "o nome " + name + " já existe");
			return;
		}

		frame.dispose();
	}
	
	private void  addPictures() {
		frame.requestFocus();
		List<ImageIcon> images= imagesReader.ChoseImages();
		if(images == null || images.isEmpty()) return;
		pictures.addAll(images);
		if(pictures.isEmpty() || pictures == null) return;
		picturePanel.setPicture(pictures.get(0));
		picturePanel.repaint();
		frame.requestFocus();
	}
	
	private void previousImage() {
		frame.requestFocus();
		if(pictures == null || pictures.isEmpty()) return;
		if((CurrentPictureIndex - 1) < 0) { 
			CurrentPictureIndex = pictures.size() - 1;
			picturePanel.setPicture(pictures.get(CurrentPictureIndex));
		}else { 
			CurrentPictureIndex--;
			picturePanel.setPicture(pictures.get(CurrentPictureIndex));
		}
		picturePanel.repaint();
		
}

	private void nextImage() {
		frame.requestFocus();
		if(pictures == null || pictures.isEmpty()) return;
		if((CurrentPictureIndex + 1) == pictures.size()) {
			CurrentPictureIndex = 0;
			picturePanel.setPicture(pictures.get(CurrentPictureIndex));
		}else  picturePanel.setPicture(pictures.get(++CurrentPictureIndex));
		picturePanel.repaint();
}

	public void removeImage() {
		frame.requestFocus();
		if(pictures == null || pictures.isEmpty()) return;
		if(pictures.size() == 1) {
			pictures.remove(0);
			picturePanel.setPicture(Main.defoultPicture);
		}else {
			pictures.remove(CurrentPictureIndex--);
			nextImage();
		}
		picturePanel.repaint();
	
}


}
