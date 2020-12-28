package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.util.Hashtable;
import java.util.jar.Attributes.Name;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

public class Monster {
	public JLabel monster_label = new JLabel();;
	public JLabel bloodText;
	public JLabel attackText;
	public JProgressBar powerBar;
	public JProgressBar blooBar;
	private String name;
	protected JPanel battleField;
	public int roundDelay = 0;
	private Hashtable<String, Integer> property = new Hashtable<String, Integer>();
	private Hashtable<String, Integer> animate_status = new Hashtable<String, Integer>();

	public Monster() {

	}

	public Monster(String name) {
		// TODO Auto-generated constructor stub
		this.name = name;
		this.animate_status.put("width", 0);
		this.animate_status.put("height", 0);
		this.animate_status.put("loc_x", 0);
		this.animate_status.put("loc_y", 0);
		this.animate_status.put("count", 0);
	}

	public void setStatus(int a, int b, int m, int p) {
		this.property.put("attack", a);
		this.property.put("blood", b);
		this.property.put("power", p);
		this.property.put("money", m);

	}

	public void setSize(int w, int h) {
		this.animate_status.replace("width", w);
		this.animate_status.replace("height", h);
	}

	public void setLoc(int x, int y, int c) {
		this.animate_status.replace("loc_x", x);
		this.animate_status.replace("loc_y", y);
		this.animate_status.replace("count", c);
	}

	public void setBattleField(JPanel field) {
		this.battleField = field;
		this.battleField.add(monster_label);
		setMonsterProperty();
		setMonsterImage(false);

	}

	public void setMonsterImage(boolean attackFlag) {
		int width = this.animate_status.get("width");
		int height = this.animate_status.get("height");
		int count = this.animate_status.get("count");
		int locX = this.animate_status.get("loc_x");
		int locY = this.animate_status.get("loc_y");
		ImageIcon monster_img;
		System.out.println(count);
		if (attackFlag)
			monster_img = new ImageIcon("./image/monster/" + count + "fire.gif");
		else
			monster_img = new ImageIcon("./image/monster/" + count + ".gif");
		monster_img.setImage(monster_img.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
		// m.setOpaque(true);
		// m.setBackground(Color.red);
		monster_label.setIcon(monster_img);
		monster_label.setSize(width, height);
		monster_label.setLocation(locX, locY);

	}

	private void setMonsterProperty() {
		bloodText = new JLabel("blood");
		bloodText.setSize(100, 50);
		bloodText.setLocation(1100, 0);
		bloodText.setForeground(Color.white);
		bloodText.setFont(new Font("dialog", 1, 20));
		blooBar = new JProgressBar();
		blooBar.setMaximum(this.property.get("blood"));
		blooBar.setMinimum(0);
		blooBar.setValue(this.property.get("blood"));
		blooBar.setForeground(Color.red);
		blooBar.setSize(200, 30);
		blooBar.setLocation(1100, 50);
		attackText = new JLabel("attack:" + this.property.get("attack"));
		attackText.setSize(100, 50);
		attackText.setLocation(1100, 100);
		attackText.setForeground(Color.white);
		attackText.setFont(new Font("dialog", 1, 20));
		powerBar = new JProgressBar();
		powerBar.setMaximum(5);
		powerBar.setMinimum(0);
		powerBar.setValue(this.property.get("power"));
		powerBar.setForeground(Color.yellow);
		powerBar.setLocation(1100, 150);
		powerBar.setSize(200, 20);
		battleField.add(blooBar);
		battleField.add(bloodText);
		battleField.add(attackText);
		battleField.add(powerBar);
	}

	public String getName() {
		return this.name;
	}

	public int getProperty(String name) {
		return this.property.get(name);
	}

	public void setProperty(String name, int v) {
		this.property.put(name, v);
	}
}
