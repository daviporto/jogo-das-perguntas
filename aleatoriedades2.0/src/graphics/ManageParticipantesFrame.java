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

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.Main;
import dataBaseStuff.Participante;

/**
 * TODO Put here a description of what this class does.
 *
 * @author davi.
 *         Created 6 de out de 2020.
 */
public class ManageParticipantesFrame {
	private JFrame frame;
	private List<Participante> participantes;
	private int CurrentParticipanteIndex;
	private PicturePanel picturePanel;
	private JPanel namePanel;
	private JLabel lblName;
	private String name;
	private Main main;


	public ManageParticipantesFrame(List<Participante> participantes, Main main) {
		this.main = main;
		this.participantes = participantes;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		name = (participantes == null || participantes.isEmpty())? "": participantes.get(0).getName();
		frame = new JFrame();
		frame.setVisible(true);
		
		frame.addWindowListener(new WindowListener() {
			public void windowOpened(WindowEvent e) {}
			public void windowIconified(WindowEvent e) {}
			public void windowDeiconified(WindowEvent e) {}
			public void windowDeactivated(WindowEvent e) {}
			public void windowClosing(WindowEvent e) {}
			public void windowClosed(WindowEvent e) {
				main.setVisible();
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
					PreviousParticipante();
				else if (e.getKeyCode() == KeyEvent.VK_RIGHT)
					nextParticipante();
			}
		});
		frame.setTitle("Participantes");
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
		
		namePanel = new JPanel();
		lblName = new JLabel(name);
		lblName.setFont(Main.DEFAULTFONT);
		lblName.setForeground(Color.white);
		namePanel.setBackground(Color.DARK_GRAY);
		GridBagConstraints gbc_namePanel = new GridBagConstraints();
		gbc_namePanel.gridwidth = 3;
		gbc_namePanel.fill = GridBagConstraints.BOTH;
		gbc_namePanel.gridx = 0;
		gbc_namePanel.gridy = 0;
		namePanel.add(lblName);
		frame.getContentPane().add(namePanel, gbc_namePanel);
		
		if(participantes == null || participantes.isEmpty()) picturePanel = new PicturePanel();
		else picturePanel = new PicturePanel(participantes.get(0).getPicture());
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
		
		JButton btnRemoverParticipante = new JButton("remover participante");
		JButton btnEditarParticipante = new JButton("editar Participante");
		JButton btnNext = new JButton(Main.rigthArrowIcon);
		JButton btnPrevious = new JButton(Main.leftArrowIcon);
		
		JButton btnDone = new JButton("feito");
		btnDone.setForeground(Color.WHITE);
		btnDone.setBorderPainted(false);
		btnDone.setBackground(Color.DARK_GRAY);
		
		btnRemoverParticipante.setBackground(Color.DARK_GRAY);
		btnRemoverParticipante.setForeground(Color.white);
		btnRemoverParticipante.setBorderPainted(false);
		
		btnEditarParticipante.setBackground(Color.DARK_GRAY);
		btnEditarParticipante.setForeground(Color.white);
		btnEditarParticipante.setBorderPainted(false);
		
		btnNext.setBackground(Color.DARK_GRAY);
		btnNext.setBorderPainted(false);
		
		btnPrevious.setBackground(Color.DARK_GRAY);
		btnPrevious.setBorderPainted(false);
		
		btnRemoverParticipante.setToolTipText("remove a imagem atual");
		btnEditarParticipante.setToolTipText("mudar atributos to participante, e.g. nome, fotos");
		btnDone.setToolTipText("confirmar as modiicacoes e voltar para o jogo");
		btnNext.setToolTipText("proxima imagem");
		btnPrevious.setToolTipText("imagem anterior");
		
		btnRemoverParticipante.addActionListener(event -> removeParticipante());
		btnPrevious.addActionListener(event -> PreviousParticipante());
		btnNext.addActionListener(event -> nextParticipante());
		btnEditarParticipante.addActionListener(event -> editParticipante());
		btnDone.addActionListener(event -> done());
		
		
		buttonsPanel.add(btnPrevious);
		buttonsPanel.add(btnDone);
		buttonsPanel.add(btnRemoverParticipante);
		buttonsPanel.add(btnEditarParticipante);
		buttonsPanel.add(btnNext);
		
		picturePanel.repaint();
		frame.pack();
		frame.requestFocus();
	}
	
	public void nextParticipante() {
		frame.requestFocus();
		if(participantes.isEmpty() || participantes == null) return;
		if((CurrentParticipanteIndex + 1) ==  participantes.size()) { 
			CurrentParticipanteIndex = 0;
			picturePanel.setPicture(participantes.get(CurrentParticipanteIndex).getPicture());
			lblName.setText(participantes.get(CurrentParticipanteIndex).getName());
		}else { 
			CurrentParticipanteIndex++;
			picturePanel.setPicture(participantes.get(CurrentParticipanteIndex).getPicture());
			lblName.setText(participantes.get(CurrentParticipanteIndex).getName());
		}
		picturePanel.repaint();
	}
	
	public void PreviousParticipante() {
		frame.requestFocus();
		if(participantes.isEmpty() || participantes == null) return;
		if((CurrentParticipanteIndex - 1) < 0) { 
			CurrentParticipanteIndex = participantes.size() - 1;
			picturePanel.setPicture(participantes.get(CurrentParticipanteIndex).getPicture());
			lblName.setText(participantes.get(CurrentParticipanteIndex).getName());
		}else { 
			CurrentParticipanteIndex--;
			picturePanel.setPicture(participantes.get(CurrentParticipanteIndex).getPicture());
			lblName.setText(participantes.get(CurrentParticipanteIndex).getName());
		}
		picturePanel.repaint();
		
	}

	public void removeParticipante() {
		frame.requestFocus();
		if(participantes.isEmpty() || participantes == null) return;
		if(participantes.size() == 1) {
			participantes.remove(0);
			picturePanel.setPicture(Main.defoultPicture);
//			lblName.setText("");
		}else {
			participantes.remove(CurrentParticipanteIndex--);
			nextParticipante();
		}
		picturePanel.repaint();
	}
	
	private void editParticipante() {
		if(participantes.isEmpty() || participantes == null) {
			frame.requestFocus();
			return;
		}
		var p = participantes.get(CurrentParticipanteIndex).clone();
		new EditaParticipanteFrame(p, this, main);
		frame.setVisible(false);
	}
	
	public void done() {
		frame.dispose();
	}
	
	public void setVisible() {
		frame.setVisible(true);
		frame.requestFocus();
	}
	
	public void changedParticipante(Participante p) {
		if(p == null) return;
		participantes.remove(CurrentParticipanteIndex);
		participantes.add(CurrentParticipanteIndex, p);
		picturePanel.setPicture(participantes.get(CurrentParticipanteIndex).getPicture());
		picturePanel.repaint();
	}
	
}
