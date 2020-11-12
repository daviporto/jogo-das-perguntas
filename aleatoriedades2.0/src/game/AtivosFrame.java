package game;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import controller.Main;
import dataBaseStuff.Participante;

public class AtivosFrame extends JFrame implements Comparator<JCheckBox> {
	private static final long serialVersionUID = 1L;
	private JButton done;
	private List<JCheckBox> boxes;
	private JPanel boxesPanel;
	private JPanel donePanel;
	private JScrollPane scrollBoxes;

	public AtivosFrame(List<Participante> participantes, Main main) {
		if (participantes == null)
			return;
		setLayout(null);
		setBounds(0, 0, 300, 500);
		getContentPane().setBackground(Color.DARK_GRAY);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

		boxesPanel = new JPanel();
		boxesPanel.setBounds(0, 0, 350, 450);
		boxesPanel.setBackground(Color.DARK_GRAY);
		boxesPanel.setLayout(new BoxLayout(boxesPanel, BoxLayout.Y_AXIS));

		donePanel = new JPanel();
		donePanel.setBackground(Color.DARK_GRAY);
		donePanel.setBounds(0, 420, 300, 50);

		done = new JButton("done");
		done.setForeground(Color.WHITE);
		done.setBackground(Color.DARK_GRAY);
		done.setAlignmentX(0);
		done.setFont(Main.DEFAULTFONT);
		done.setBorderPainted(false);
		done.addActionListener(event -> main.setParticipantesAtivos());
		donePanel.add(done);

		boxes = new ArrayList<JCheckBox>();

		for (Participante p : participantes) {
			JCheckBox current = new JCheckBox(p.getName(), p.isAtivo());
			current.setFont(Main.DEFAULTFONT);
			current.setBackground(Color.DARK_GRAY);
			current.setForeground(Color.WHITE);
			current.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					var p = Participante.getByName(participantes, current.getText());
					if (current.isSelected()) {
						if (p == null)
							return;
						p.SetAtive();
					} else
						p.setInative();
				}
			});
			boxes.add(current);
		}
		boxes.sort(this);
		boxes.forEach(boxesPanel::add);

		scrollBoxes = new JScrollPane(boxesPanel);
		scrollBoxes.setBounds(0, 0, 400, 420);
		scrollBoxes.setBackground(Color.DARK_GRAY);
		scrollBoxes.setBorder(null);

		getContentPane().add(scrollBoxes);
		getContentPane().add(donePanel);

	}

	@Override
	public int compare(JCheckBox c1, JCheckBox c2) {
		return c1.getText().compareTo(c2.getText());
	}

}
