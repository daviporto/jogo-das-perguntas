package graphics;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
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

public class NewParticipante {

	private String name;
	private JFrame frame;
	private JTextField jtfname;
	private int CurrentPanelIndex;
	private List<ImageIcon> pictures;
	private PicturePanel picturePanel;
	private Main main;
	private ReadImage imagesReader;
	private boolean setMainVisibleAgain = false;

	public NewParticipante(Main main) {
		this.main = main;
		initialize();
	}

	public NewParticipante(Main main, boolean setMainVisibleAgain) {
		this(main);
		this.setMainVisibleAgain = setMainVisibleAgain;
	}

	private void initialize() {
		pictures = new ArrayList<ImageIcon>();
		CurrentPanelIndex = 0;
		frame = new JFrame();
		frame.addWindowListener(new WindowListener() {
			public void windowOpened(WindowEvent e) {
			}

			public void windowIconified(WindowEvent e) {
			}

			public void windowDeiconified(WindowEvent e) {
			}

			public void windowDeactivated(WindowEvent e) {
			}

			public void windowClosing(WindowEvent e) {
			}

			public void windowClosed(WindowEvent e) {
				if (setMainVisibleAgain)
					main.setVisible();
			}

			public void windowActivated(WindowEvent e) {
			}
		});

		frame.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_LEFT)
					previousImage();
				else if (e.getKeyCode() == KeyEvent.VK_RIGHT)
					nextImage();
			}
		});
		frame.setTitle("adiciona novo participante");
		frame.getContentPane().setBackground(Color.DARK_GRAY);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 40, 640, 40 };
		gridBagLayout.rowHeights = new int[] { 30, 440, 30 };
		gridBagLayout.columnWeights = new double[] { 1.0, 100.0, 1.0 };
		gridBagLayout.rowWeights = new double[] { 1.0, 100.0 };
		frame.getContentPane().setLayout(gridBagLayout);
		frame.setMinimumSize(new Dimension(500, 300));
		frame.setBounds(0, 0, 700, 500);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setVisible(true);

		JPanel namePanel = new JPanel();
		jtfname = new JTextField("", 20);
		jtfname.getDocument().addDocumentListener(new DocumentListener() {

			public void removeUpdate(DocumentEvent e) {
				jtfname.setBackground(Color.DARK_GRAY);
				name = jtfname.getText().trim();
				if (name.equals(""))
					jtfname.setBackground(Color.RED);
			}

			public void insertUpdate(DocumentEvent e) {
				jtfname.setBackground(Color.DARK_GRAY);
				name = jtfname.getText().trim();
				if (name.equals(""))
					jtfname.setBackground(Color.RED);
			}

			public void changedUpdate(DocumentEvent e) {
				jtfname.setBackground(Color.DARK_GRAY);
				name = jtfname.getText().trim();
				if (name.equals(""))
					jtfname.setBackground(Color.RED);
			}
		});

		jtfname.setText("digite o nome aqui");
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

		picturePanel = new PicturePanel(Main.defoultPicture);
		picturePanel.setBackground(Color.DARK_GRAY);
		GridBagConstraints gbc_picturePanel = new GridBagConstraints();
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
		JButton btnCancel = new JButton("cancelar");

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

		btnCancel.setBackground(Color.DARK_GRAY);
		btnCancel.setForeground(Color.WHITE);
		btnCancel.setBorderPainted(false);

		btnAddPicture.setToolTipText("abre o seletor para adicionar mais fotos");
		btnremovePicture.setToolTipText("remove a imagem atual");
		btnNext.setToolTipText("proxima imagem");
		btnPrevious.setToolTipText("proxima imagem");
		btnDone.setToolTipText("adiciona o novo participante");
		btnCancel.setToolTipText("volta para a tela principal sem adicionar o participante atual");

		btnremovePicture.addActionListener(event -> removeImage());
		btnPrevious.addActionListener(event -> previousImage());
		btnNext.addActionListener(event -> nextImage());
		btnAddPicture.addActionListener(event -> addPictures());
		btnDone.addActionListener(event -> done());
		btnCancel.addActionListener(event -> cancel());

		buttonsPanel.add(btnPrevious);
		buttonsPanel.add(btnAddPicture);
		buttonsPanel.add(btnDone);
		buttonsPanel.add(btnCancel);
		buttonsPanel.add(btnremovePicture);
		buttonsPanel.add(btnNext);
		frame.pack();
		picturePanel.repaint();

		imagesReader = new ReadImage(frame);
	}

	private void previousImage() {
		frame.requestFocus();
		if (pictures.isEmpty() || pictures == null)
			return;
		if ((CurrentPanelIndex - 1) < 0) {
			CurrentPanelIndex = pictures.size() - 1;
			picturePanel.setPicture(pictures.get(CurrentPanelIndex));
		} else {
			CurrentPanelIndex--;
			picturePanel.setPicture(pictures.get(CurrentPanelIndex));
		}
		picturePanel.repaint();

	}

	private void nextImage() {
		frame.requestFocus();
		if (pictures.isEmpty() || pictures == null)
			return;
		if ((CurrentPanelIndex + 1) == pictures.size()) {
			CurrentPanelIndex = 0;
			picturePanel.setPicture(pictures.get(CurrentPanelIndex));
		} else {
			CurrentPanelIndex++;
			picturePanel.setPicture(pictures.get(CurrentPanelIndex));
		}
		picturePanel.repaint();
	}

	public void removeImage() {
		frame.requestFocus();
		if (pictures.isEmpty() || pictures == null)
			return;
		if (pictures.size() == 1) {
			pictures.remove(0);
			picturePanel.setPicture(Main.defoultPicture);
		} else {
			pictures.remove(CurrentPanelIndex--);
			nextImage();
		}
		picturePanel.repaint();

	}

	private void addPictures() {
		var images = imagesReader.ChoseImages();
//		System.out.println(images.size());
		frame.requestFocus();
		if (images == null || images.isEmpty())
			return;
		pictures.addAll(images);
		if (pictures.isEmpty() || pictures == null)
			return;
		picturePanel.setPicture(pictures.get(0));
		picturePanel.repaint();
	}

	private void done() {
		name = jtfname.getText().trim();
		if (name.equals("")) {
			JOptionPane.showMessageDialog(frame, "o nome não pode ser vazio");
			return;
		}
		if (name.equals("digite o nome aqui")) {
			jtfname.setBackground(Color.RED);
			JOptionPane.showMessageDialog(frame, "digite um nome valido");
			return;
		}
		if (main.nomeExiste(name)) {
			jtfname.setBackground(Color.RED);
			JOptionPane.showMessageDialog(frame, "o nome " + name + " já existe");
			return;
		}
		main.AddParticipante(new Participante(pictures, name));
		if (setMainVisibleAgain)
			main.setVisible();
		frame.dispose();
	}

	public void cancel() {
		if (setMainVisibleAgain)
			main.setVisible();
		frame.dispose();
	}
}
