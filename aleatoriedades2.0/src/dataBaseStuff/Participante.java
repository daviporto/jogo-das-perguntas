package dataBaseStuff;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

import javax.swing.ImageIcon;

import controller.Main;

public class Participante implements Serializable, Cloneable {

	private static final long serialVersionUID = 1L;
	private String name;
	private ImageIcon picture;
	private List<ImageIcon> pictures;
	private static Random random;
	private boolean active;


	public static List<Participante> participantes;

	static {
		participantes = new ArrayList<Participante>();
		random = new Random();
	}

	public Participante(String name, String path) {
		this.name = name;
		active = true;
		try {
			picture = new ImageIcon(Participante.class.getResource(path));
			pictures = new ArrayList<ImageIcon>();
			pictures.add(picture);

		} catch (Exception e) {
			Logger.getGlobal().warning("iamgem nao carregada na pessoa" + name + "\n o path é:" + path);
			e.printStackTrace();
		}
	}

	public static Participante getByName(List<Participante> participantes, String name) {
		for (Participante p : participantes)
			if (p.getName().equals(name))
				return p;
		return null;
	}

	public Participante(List<ImageIcon> pictures, String name) {
		this.name = name;
		this.pictures = pictures;
		active = true;
	}

	public String getName() {
		return this.name;
	}

	public List<ImageIcon> getPictures() {
		return pictures;
	}

	public ImageIcon getPicture() {
		if (pictures == null || pictures.isEmpty())
			return null;
		return pictures.get(0);
	}

	public void addPicture(ImageIcon picture) {
		pictures.add(picture);
	}

	public ImageIcon getRandomPicture() {
		if (pictures == null || pictures.isEmpty())
			return Main.defoultPicture;

		if (pictures.size() == 1)
			return pictures.get(0);

		return pictures.get(random.nextInt(pictures.size()));
	}

	public Participante clone() {
		var CPPictures = new ArrayList<ImageIcon>();

		pictures.forEach((image) -> CPPictures.add(new ImageIcon(image.getImage())));

		System.out.println(CPPictures.size());
		return new Participante(CPPictures, name);
	}

	public void SetAtive() {
		active = true;
	}

	public void setInative() {
		active = false;
	}

	public void SetAtive(boolean active) {
		this.active = active;
	}

	public boolean isAtivo() {
		return active;
	}

}
