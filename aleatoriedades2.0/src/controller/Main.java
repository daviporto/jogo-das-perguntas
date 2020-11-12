package controller;

import java.awt.FileDialog;
import java.awt.Font;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import dataBaseStuff.Participante;
import game.AtivosFrame;
import graphics.HelpFrame;
import graphics.JogoFrame;
import graphics.ManageParticipantesFrame;
import graphics.NewParticipante;
import graphics.PicturePanel;

public class Main {
	private List<Participante> participantes;
	private List<Participante> participantesAtivos;
	public final static ImageIcon rigthArrowIcon;
	public final static ImageIcon leftArrowIcon;
	public final static ImageIcon defoultPicture;
	public static Font DEFAULTFONT = new Font(Font.SANS_SERIF, Font.BOLD, 20);
	private Random random;

	private JogoFrame window;
	private AtivosFrame ativosframe;
	private HelpFrame helpFrame;

	static {
		rigthArrowIcon = new ImageIcon(NewParticipante.class.getResource("/icons/rightArrow.png"));
		leftArrowIcon = new ImageIcon(NewParticipante.class.getResource("/icons/leftArrow.png"));
		defoultPicture = new ImageIcon(NewParticipante.class.getResource("/participantesFotos/default.jpeg"));
		DEFAULTFONT = new Font(Font.SANS_SERIF, Font.BOLD, 20);
	}

	public Main(String location) {
		random = new Random();
		participantes = new ArrayList<Participante>();
		participantesAtivos = new ArrayList<Participante>();

		if (!location.isEmpty()) {
			var loaded = loadFromBash(location);
			ativosframe = new AtivosFrame(participantes, this);
			window = new JogoFrame(this, participantesAtivos);
			JOptionPane.showMessageDialog(window.getFrage(), loaded);
		} else {
			ativosframe = new AtivosFrame(participantes, this);
			window = new JogoFrame(this, participantesAtivos);
		}
	}

	public void AddParticipante(Participante p) {
		participantes.add(p);
		recalculaParticipantesAtivos();
	}

	public void clearParticipantes() {
		participantesAtivos.clear();
	}

	public void setVisible() {
		if (window == null)
			return;
		window.setVisible();
		window.janelaFocous();
	}

	public void newParticipante() {
		new NewParticipante(this, true);
		window.setVisible(false);
	}

	public void newGame(JFrame frame) {
		if (!participantes.isEmpty()) {
			var option = JOptionPane.showConfirmDialog(frame,
					"voce realmente deseja criar um novo jogo? isso apagara" + " todos os participantes", "apagar",
					JOptionPane.YES_NO_OPTION);
			if (option == JOptionPane.YES_OPTION) {
				participantesAtivos.clear();
				participantes.clear();
				window.nextRound();
				frame.requestFocus();
			}
		} else {
			participantesAtivos.clear();
			participantes.clear();
			window.nextRound();
			frame.requestFocus();
		}
	}

	public void close() {
		System.exit(0);
	}

	public void manejarParticipantes(JFrame frame) {
		frame.requestFocus();
		if (participantesAtivos == null || participantes.isEmpty()) {
			if (window.messageDialog("não ha participantes ainda crie um primeiro") == JOptionPane.OK_OPTION) {
				frame.setVisible(false);
				new NewParticipante(this, true);
				return;
			} else
				return;
		}
		frame.setVisible(false);
		new ManageParticipantesFrame(participantesAtivos, this);
	}

	public String loadFromBash(String location) {
		String state;
		ObjectInputStream input = null;

		try {
			input = new ObjectInputStream(new FileInputStream(location));
		} catch (FileNotFoundException exception) {
			exception.printStackTrace();
		} catch (IOException exception) {
			exception.printStackTrace();
		}
		try {
			if (input == null)
				state = "nada foi carregado";
			Participante[] loaded = (Participante[]) input.readObject();
			if (loaded == null)
				state = "nada foi carregado";
			for (int i = 0; i < loaded.length; i++)
				participantes.add(loaded[i]);
			state = "carregado com sucesso";
		} catch (ClassNotFoundException | IOException exception) {
			exception.printStackTrace();
			state = "nada foi carregado";
		}

		ativosframe = new AtivosFrame(participantes, this);
		return state;
	}

	public String load(JFrame frame) {
		String state;
		var loadFile = new FileDialog(frame, "salvar arquivo como ", FileDialog.LOAD);
		loadFile.setDirectory("./");
		loadFile.setVisible(true);
		var location = loadFile.getFile();
		if (location == null || location.length() == 0)
			state = "nada foi carregado";

		ObjectInputStream input = null;

		try {
			input = new ObjectInputStream(new FileInputStream(location));
		} catch (FileNotFoundException exception) {
			exception.printStackTrace();
		} catch (IOException exception) {
			exception.printStackTrace();
		}
		try {
			if (input == null)
				state = "nada foi carregado";
			Participante[] loaded = (Participante[]) input.readObject();
			if (loaded == null)
				state = "nada foi carregado";
			for (int i = 0; i < loaded.length; i++)
				participantes.add(loaded[i]);
			state = "carregado com sucesso";
			if (loaded != null) {
				participantesAtivos.clear();
				recalculaParticipantesAtivos();
			}
		} catch (ClassNotFoundException | IOException exception) {
			exception.printStackTrace();
			state = "nada foi carregado";
		}

		ativosframe = new AtivosFrame(participantes, this);
		return state;
	}

	public String save(JFrame frame) {
		if (participantesAtivos == null || participantesAtivos.isEmpty()) {
			return "não há participantes ainda adicione " + "mais alguns para pode salvar";
		}

		var saveFile = new FileDialog(frame, "salvar arquivo como ", FileDialog.SAVE);
		saveFile.setDirectory("saves/");
		saveFile.setVisible(true);
		var location = saveFile.getFile();
		if (location == null || location.length() == 0)
			return "nada foi salvo";
		location += "/";

		ObjectOutputStream out = null;

		try {
			out = new ObjectOutputStream(new FileOutputStream(location));
		} catch (FileNotFoundException exception) {
			// TODO Auto-generated catch-block stub.
			exception.printStackTrace();
		} catch (IOException exception) {
			// TODO Auto-generated catch-block stub.
			exception.printStackTrace();
		}

		try {
			if (out == null)
				return " nada foi salvo";
			Participante[] ps = participantesAtivos.toArray(new Participante[0]);
			out.writeObject(ps);
			return "salvo com sucesso";
		} catch (IOException exception) {
			exception.printStackTrace();
			return "nada foi salvo";
		}
	}

	public String RandomGenerator(PicturePanel de, PicturePanel para) {
		if (de == null || para == null)
			return "";

		String text;
		if (participantesAtivos == null || participantesAtivos.isEmpty()) {
			de.setPicture(defoultPicture);
			para.setPicture(defoultPicture);
			return "nao ha participantes cadastrados ainda";
		}

		if (participantesAtivos.size() == 1) {
			de.setPicture(participantesAtivos.get(0).getPicture());
			para.setPicture(participantesAtivos.get(0).getPicture());
			return " há apenas um participante";
		}

		var participanteDe = participantesAtivos.get(random.nextInt(participantesAtivos.size()));
		text = participanteDe.getName();
		de.setPicture(participanteDe.getRandomPicture());

		var participantePara = participantesAtivos.get(random.nextInt(participantesAtivos.size()));
		para.setPicture(participantePara.getRandomPicture());

		while (participanteDe == participantePara) // verify memory adresses
			participantePara = participantesAtivos.get(random.nextInt(participantesAtivos.size()));

		para.setPicture(participantePara.getRandomPicture());
		text += " pergunta para ";
		text += participantePara.getName();
		return text;
	}

	public void showAtivosPanel() {
		ativosframe.setVisible(true);
	}

	public void setParticipantesAtivos() {
		participantesAtivos.clear();
		for (Participante p : participantes)
			if (p.isAtivo())
				participantesAtivos.add(p);
		ativosframe.setVisible(false);
		window.nextRound();
	}

	public void recalculaParticipantesAtivos() {
		ativosframe = new AtivosFrame(participantes, this);
		setParticipantesAtivos();
	}

	public void help() {
		if (helpFrame == null)
			helpFrame = new HelpFrame();
		else
			helpFrame.setVisible(true);
	}

	public boolean nomeExiste(String name) {
		if (participantes == null || participantes.isEmpty())
			return false;

		for (int i = 0; i < participantes.size(); i++)
			if (name.equals(participantes.get(i).getName()))
				return true;

		return false;
	}

	public static void main(String... strings) {
		var location = strings.length > 0 ? strings[0] : "";
		new Main(location);
	}
}
