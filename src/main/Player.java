package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import Items.Decorator.*;

public class Player {
	public JLabel p;
	public ArrayList<JButton> skillUse = new ArrayList<JButton>();
	public Hashtable<String, Integer> skill = new Hashtable<String, Integer>();
	public Hashtable<String, JLabel> skillWatched = new Hashtable<String, JLabel>();
	public Hashtable<String, Component> skillContent = new Hashtable<String, Component>();
	private Timer playerIdel = new Timer();
	public int playerImageNum = 0;
	public String playerPath = "./image/player/player0";
	public int playerWidth = 150;
	public int playerHeight = 300;
	private int i = 0;
	protected JPanel battleField;
	public int attack;
	public int blood;
	public int money;
	public JLabel bloodText;
	public JLabel attackText;
	public JProgressBar blooBar;
	public Status status;
	public int roundDelay = 0;

	public Player(JPanel fieldJPanel, Status pStatus) {
		// TODO Auto-generated constructor stub
		battleField = fieldJPanel;
		setPlayer(pStatus);
		setPlayerProperty();
		// System.out.println(pStatus.skill.get("coffee"));
		newskillButton();
		playerIdel.schedule(new TimerTask() {

			@Override
			public void run() {

				// TODO Auto-generated method stub

				ImageIcon image = new ImageIcon(playerPath + playerImageNum + ".gif");
				image.setImage(image.getImage().getScaledInstance(playerWidth, playerHeight, Image.SCALE_DEFAULT));
				p.setIcon(image);

				++playerImageNum;
				if (playerImageNum >= 43)
					playerImageNum = 0;
			}
		}, 0, 50);

	}

	public void setPlayer(Status s) {
		p = new JLabel();
		int width = this.playerWidth;
		int height = this.playerHeight;
		p.setSize(width, height);
		p.setLocation(500, 300);
		// player.setOpaque(true);
		// player.setBackground(Color.red);
		battleField.add(p);
		this.attack = s.attack;
		this.blood = s.blood;
		this.money = s.getMoney();

		this.skill = s.skill;
		this.skillContent = s.skillContent;

	}

	public void setPlayerProperty() {
		bloodText = new JLabel("blood");
		bloodText.setSize(100, 50);
		bloodText.setForeground(Color.white);
		bloodText.setFont(new Font("dialog", 1, 20));
		blooBar = new JProgressBar();
		blooBar.setMaximum(100);
		blooBar.setMinimum(0);
		blooBar.setValue(this.blood);
		blooBar.setForeground(Color.red);
		blooBar.setSize(200, 30);
		blooBar.setLocation(0, 50);
		attackText = new JLabel("attack:" + this.attack);
		attackText.setSize(100, 50);
		attackText.setLocation(0, 100);
		attackText.setForeground(Color.white);
		attackText.setFont(new Font("dialog", 1, 20));
		battleField.add(blooBar);
		battleField.add(bloodText);
		battleField.add(attackText);

	}

	private void newskillButton() {
		// System.out.print(Y_skill[0]);
		skill.forEach((k, v) -> {
			if (v > 0) {
				JButton button = new JButton(k);
				button.setSize(150, 50);
				button.setVisible(true);
				button.setLocation(0 + 200 * (i / 5), 200 + 50 * (i % 5));
				JLabel label = new JLabel(v.toString());
				label.setSize(50, 20);
				label.setLocation(150 + 200 * (i / 5), 200 + 50 * (i % 5));
				label.setForeground(Color.white);
				label.setFont(new Font("dialog", 1, 20));

				skillWatched.put(k, label);
				skillUse.add(button);
				battleField.add(button);
				battleField.add(label);
				i++;
			}

		});

	}
}
