package graphics;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

import controller.Main;
import dataBaseStuff.Participante;

public class JogoFrame {

	private JFrame frame;
	private PicturePanel dePanel, paraPanel;
	private Main main;
	private JTextField textField;
	private final static Color MENU_COLOR = new Color(0x202020);

	public JogoFrame(Main main, List<Participante> participantes) {
		this.main = main;
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setVisible(true);
		frame.getContentPane().setBackground(Color.DARK_GRAY);
		frame.setBounds(0, 0, 700, 500);
		frame.setMinimumSize(new Dimension(400, 400));
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER)
					nextRound();
			}
		});

		frame.addComponentListener(new ComponentListener() {
			public void componentShown(ComponentEvent e) {
			}

			public void componentResized(ComponentEvent e) {
//				if(dePanel != null) dePanel.resizeImage();
//				if(paraPanel != null) dePanel.resizeImage();
			}

			public void componentMoved(ComponentEvent e) {
			}

			public void componentHidden(ComponentEvent e) {
			}
		});

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 350, 350 };
		gridBagLayout.rowHeights = new int[] { 30, 440, 10 };
		gridBagLayout.columnWeights = new double[] { 10.0, 10.0 };
		gridBagLayout.rowWeights = new double[] { 1.0, 20.0, 0 };
		frame.getContentPane().setLayout(gridBagLayout);

		JPanel txtPanel = new JPanel();
		txtPanel.setBackground(Color.DARK_GRAY);
		GridBagConstraints gbc_txtPanel = new GridBagConstraints();
		gbc_txtPanel.insets = new Insets(0, 0, 5, 5);
		gbc_txtPanel.gridwidth = 2;
		gbc_txtPanel.fill = GridBagConstraints.BOTH;
		gbc_txtPanel.gridx = 0;
		gbc_txtPanel.gridy = 0;
		frame.getContentPane().add(txtPanel, gbc_txtPanel);
		GridBagLayout gbl_txtPanel = new GridBagLayout();
		gbl_txtPanel.columnWidths = new int[] { 12, 578, 200 };
		gbl_txtPanel.rowHeights = new int[] { 24, 0 };
		gbl_txtPanel.columnWeights = new double[] { 0, 10, 0 };
		gbl_txtPanel.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
		txtPanel.setLayout(gbl_txtPanel);

		dePanel = new PicturePanel();
		dePanel.setBackground(Color.DARK_GRAY);
		GridBagConstraints gbc_dePanel = new GridBagConstraints();
		gbc_dePanel.insets = new Insets(0, 0, 0, 5);
		gbc_dePanel.fill = GridBagConstraints.BOTH;
		gbc_dePanel.gridx = 0;
		gbc_dePanel.gridy = 1;
		frame.getContentPane().add(dePanel, gbc_dePanel);

		paraPanel = new PicturePanel();
		paraPanel.setBackground(Color.DARK_GRAY);
		GridBagConstraints gbc_paraPanel = new GridBagConstraints();
		gbc_paraPanel.fill = GridBagConstraints.BOTH;
		gbc_paraPanel.gridx = 1;
		gbc_paraPanel.gridy = 1;
		frame.getContentPane().add(paraPanel, gbc_paraPanel);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBorderPainted(false);
		menuBar.setBackground(MENU_COLOR);
		frame.setJMenuBar(menuBar);

		JButton btnHelp = new JButton("ajuda");
		btnHelp.setBorderPainted(false);
		btnHelp.setToolTipText("como jogar");
		btnHelp.setForeground(Color.WHITE);
		btnHelp.setBackground(MENU_COLOR);

		JButton btnClose = new JButton("sair");
		btnClose.setToolTipText("sair do jogo");
		btnClose.setBorderPainted(false);
		btnClose.setForeground(Color.WHITE);
		btnClose.setBackground(MENU_COLOR);

		JMenu participantes = new JMenu("participantes");
		participantes.setForeground(Color.WHITE);
		participantes.setBackground(MENU_COLOR);
		participantes.setAlignmentX(0.5f);
		participantes.setBorderPainted(false);

		JMenuItem mntmAdicionarParticipante = new JMenuItem("adicionar participante");
		mntmAdicionarParticipante.setFont(new Font("Dialog", Font.BOLD, 13));
		mntmAdicionarParticipante.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_DOWN_MASK));
		mntmAdicionarParticipante.setBackground(MENU_COLOR);
		mntmAdicionarParticipante.setForeground(Color.WHITE);
		participantes.add(mntmAdicionarParticipante);

		JMenuItem mntmManejarParticipantes = new JMenuItem("manejar participantes");
		mntmManejarParticipantes.setFont(new Font("Dialog", Font.BOLD, 13));
		mntmManejarParticipantes.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M, InputEvent.CTRL_DOWN_MASK));
		mntmManejarParticipantes.setForeground(Color.WHITE);
		mntmManejarParticipantes.setBackground(MENU_COLOR);
		participantes.add(mntmManejarParticipantes);

		JMenuItem mntmParticipantesAtivos = new JMenuItem("participantes ativos");
		mntmParticipantesAtivos.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.CTRL_DOWN_MASK));
		mntmParticipantesAtivos.setForeground(Color.WHITE);
		mntmParticipantesAtivos.setFont(new Font("Dialog", Font.BOLD, 13));
		mntmParticipantesAtivos.setBackground(MENU_COLOR);
		participantes.add(mntmParticipantesAtivos);

		JMenu mnJogo = new JMenu("jogo");
		mnJogo.setForeground(Color.WHITE);

		JMenuItem mntmCriar = new JMenuItem(" criar um novo jogo");
		mntmCriar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK));
		mntmCriar.setForeground(Color.WHITE);
		mntmCriar.setFont(new Font("Dialog", Font.BOLD, 13));
		mntmCriar.setBackground(MENU_COLOR);
		mnJogo.add(mntmCriar);

		JMenuItem mntmSalvar = new JMenuItem("salvar o jogo atual");
		mntmSalvar.setFont(new Font("Dialog", Font.BOLD, 13));
		mntmSalvar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK));
		mntmSalvar.setForeground(Color.WHITE);
		mntmSalvar.setBackground(MENU_COLOR);
		mnJogo.add(mntmSalvar);

		JMenuItem mntmCaregar = new JMenuItem("caregar um jogo salvo");
		mntmCaregar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_DOWN_MASK));
		mntmCaregar.setForeground(Color.WHITE);
		mntmCaregar.setBackground(MENU_COLOR);
		mntmCaregar.setFont(new Font("Dialog", Font.BOLD, 13));
		mnJogo.add(mntmCaregar);
		frame.pack();

		menuBar.add(mnJogo);
		menuBar.add(participantes);
		menuBar.add(btnHelp);
		menuBar.add(btnClose);

		textField = new JTextField();
		textField.setText("pergunta para");
		textField.setForeground(Color.WHITE);
		textField.setFont(new Font("SansSerif", Font.BOLD, 20));
		textField.setEditable(false);
		textField.setColumns(40);
		textField.setBorder(null);
		textField.setBackground(Color.DARK_GRAY);
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 0, 5);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 0;
		txtPanel.add(textField, gbc_textField);

		JButton btnProximo = new JButton("proximo");
		btnProximo.setToolTipText("gera outra rodada de perguntas");
		btnProximo.setBackground(Color.DARK_GRAY);
		btnProximo.setForeground(Color.WHITE);
		btnProximo.setFont(Main.DEFAULTFONT);
		btnProximo.setBorderPainted(false);
		GridBagConstraints gbc_btnProximo = new GridBagConstraints();
		gbc_btnProximo.gridx = 2;
		gbc_btnProximo.gridy = 0;
		txtPanel.add(btnProximo, gbc_btnProximo);
		dePanel.repaint();
		paraPanel.repaint();

		mntmAdicionarParticipante.addActionListener(event -> main.newParticipante());
		mntmManejarParticipantes.addActionListener(event -> main.manejarParticipantes(frame));
		mntmParticipantesAtivos.addActionListener(event -> main.showAtivosPanel());
		mntmCriar.addActionListener(event -> main.newGame(frame));
		mntmSalvar.addActionListener(event -> save());
		mntmCaregar.addActionListener(event -> load());
		btnClose.addActionListener(event -> main.close());
		btnProximo.addActionListener(event -> nextRound());
		btnHelp.addActionListener(event -> main.help());

		frame.setSize(java.awt.Toolkit.getDefaultToolkit().getScreenSize());
		nextRound();
		frame.requestFocus();

	}

	public void save() {
		var result = main.save(frame);
		JOptionPane.showMessageDialog(frame, result);
		frame.requestFocus();
	}

	public void load() {
		var result = main.load(frame);
		JOptionPane.showMessageDialog(frame, result);
		frame.requestFocus();
		nextRound();
	}

	public void setVisible() {
		frame.setVisible(true);
	}

	public void nextRound() {
		textField.setText(main.RandomGenerator(dePanel, paraPanel));
		frame.requestFocus();
	}

	public int messageDialog(String message) {
		return JOptionPane.showConfirmDialog(frame, message);
	}

	public void setVisible(boolean b) {
		frame.setVisible(b);
	}

	public void janelaFocous() {
		frame.requestFocus();
	}
	
	public JFrame getFrage() {
		return frame;
	}

}
