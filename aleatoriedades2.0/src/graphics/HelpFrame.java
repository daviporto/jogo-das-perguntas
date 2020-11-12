package graphics;

import static util.StringtoPixels.getStringFormated;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import controller.Main;

public class HelpFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	private JTextArea information;
	private static String content = "Se voçê ainda não criou nenhum participantes -> menu -> "
			+ "participantes -> adionar participantes, abrira um nova janela que permitira a criação de participantes"
			+ "com a adição do nome a algumas fotos, apos isso clike em feito que voltara para a janela principal \n\n"

			+ "caso deseje desativar algum participante clike em participantes -> participantes ativos, abrira um janela por"
			+ " onde sera possivel desativar o participante, não se preocupe ele não sera excluido do jogo, basta clikar no mesmo outra vez "
			+ "para readicionalo ao jogo\n\n"

			+ "caso queria fazer alguma modificacao como: excluir um participante, adicionar ou remover fotos do mesmo ou mudar o seu nome"
			+ "clike em participantes -> manejar participantes, abrira uma nova janela com toas essas opções lembre-se de clikar em "
			+ "done para confirmar as modificações\n\n"

			+ "caso ja tenha criado um jogo e queira salvalo basta clikar jogo -> salvar, escolha o local e digite o um nome "
			+ "para o save\n"
			+ "caso ja tenha salvo algum jogo anteriormente e queira carregalo basta clikar jogo -> carregar e selecione o arquivo"
			+ "que salvou anteriormente, os arquivos são salvos em .dat\n"
			+ "caso deseje criar um novo jogo clike em jogo -> criar, cuidado isso apaga o jogo anterior, certifique-se de que o salvou"
			+ "anteriormente";

	public HelpFrame() {
		setSize(500, 500);
		setMinimumSize(new Dimension(400, 400));
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setLocationRelativeTo(null);
		setTitle("Como jogar");
		setVisible(true);
		addComponentListener(new ComponentListener() {
			public void componentShown(ComponentEvent e) {
			}

			public void componentResized(ComponentEvent e) {
				if (information == null)
					return;
				information.setText(getStringFormated(Main.DEFAULTFONT, content, getWidth() - 20));
			}

			public void componentMoved(ComponentEvent e) {
			}

			public void componentHidden(ComponentEvent e) {
			}
		});

		information = new JTextArea();
		information.setBackground(Color.DARK_GRAY);
		information.setForeground(Color.WHITE);
		information.setFont(Main.DEFAULTFONT);
		information.setText(getStringFormated(Main.DEFAULTFONT, content, getWidth() - 20));

		var scrollPane = new JScrollPane(information);
		add(scrollPane);

	}

}
