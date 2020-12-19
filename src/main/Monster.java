package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.util.jar.Attributes.Name;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

public class Monster extends Property {
	public JLabel m;
	public JProgressBar powerBar;
	private JPanel battleField;
	private String name;
	private int blood;
	private int attack;
	private int money;
	private int power;

	public Monster() {

	}

	public Monster(String name) {
		// TODO Auto-generated constructor stub
		this.name = name;

	}

	public void setStatus(int a, int b, int m, int p) {
		this.attack = a;
		this.blood = b;
		this.money = m;
		this.power = p;

	}

	public void setBattleField(JPanel field) {
		this.battleField = field;
		setMonsterProperty();
		setMonsterImage(false);
	}

	public void setMonsterImage(boolean attackFlag) {
		m = new JLabel();
		int width = 0;
		int height = 0;
		int count = 0;
		int locY = 0;
		int locX = 700;
		switch (name) {
			case "TA":
				width = 300;
				height = 300;
				count = 0;
				locY = 300;
				break;
			case "skeleton1":
				width = 500;
				height = 600;
				locY = 0;
				count = 1;
				break;
			case "skeleton2":
				width = 500;
				height = 600;
				locY = 0;
				count = 2;
				break;
			case "wizard":
				width = 200;
				height = 300;
				locY = 250;
				count = 3;
				break;
			case "soilder":
				width = 200;
				height = 300;
				locY = 250;
				count = 4;
				break;
			case "archer":
				width = 200;
				height = 300;
				locY = 250;
				count = 5;
				break;
			case "pirate":
				width = 200;
				height = 300;
				locY = 250;
				count = 6;
				break;

			default:
				width = 500;
				height = 600;
				locY = 0;
				count = 2;
				break;
		}
		ImageIcon image2;
		if (attackFlag) {
			switch (name) {
				case "wizard":
					width = 400;
					height = 500;
					locY = 150;
					break;
				case "soilder":
					width = 500;
					height = 500;
					locY = 100;
					locX = 500;
					break;
				case "archer":
					width = 300;
					height = 300;
					locY = 250;
					locX = 700;
					break;
				case "pirate":
					width = 500;
					height = 300;
					locY = 250;
					locX = 550;
					break;

				default:
					break;
			}
			image2 = new ImageIcon("./image/monster/" + count + "fire.gif");
		} else {
			switch (name) {
				case "wizard":
					width = 250;
					height = 300;
					locY = 250;
					locX = 700;
					break;
				case "soilder":
					width = 250;
					height = 300;
					locY = 250;
					locX = 700;
					break;
				case "archer":
					width = 250;
					height = 300;
					locY = 250;
					locX = 700;
					break;
				case "pirate":
					width = 250;
					height = 300;
					locY = 250;
					locX = 700;
					break;
				default:
					break;
			}
			image2 = new ImageIcon("./image/monster/" + count + ".gif");
		}
		image2.setImage(image2.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));

		// m.setOpaque(true);
		// m.setBackground(Color.red);
		m.setIcon(image2);
		m.setSize(width, height);
		m.setLocation(locX, locY);
		this.battleField.add(m);

	}

	public void setMonsterProperty() {
		bloodText = new JLabel("blood");
		bloodText.setSize(100, 50);
		bloodText.setLocation(1100, 0);
		bloodText.setForeground(Color.white);
		bloodText.setFont(new Font("dialog", 1, 20));
		blooBar = new JProgressBar();
		blooBar.setMaximum(this.blood);
		blooBar.setMinimum(0);
		blooBar.setValue(this.blood);
		blooBar.setForeground(Color.red);
		blooBar.setSize(200, 30);
		blooBar.setLocation(1100, 50);
		attackText = new JLabel("attack:" + this.attack);
		attackText.setSize(100, 50);
		attackText.setLocation(1100, 100);
		attackText.setForeground(Color.white);
		attackText.setFont(new Font("dialog", 1, 20));
		powerBar = new JProgressBar();
		powerBar.setMaximum(5);
		powerBar.setMinimum(0);
		powerBar.setValue(this.power);
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

	public int getAttack() {
		return this.attack;
	}

	public int getMoney() {
		return this.money;
	}

	public int getBlood() {
		return this.blood;
	}

	public int getPower() {
		return this.power;
	}

	public void setAttack(int a) {
		this.attack = a;
	}

	public void updateMoney(int m) {
		this.money = m;
	}

	public void setBlood(int b) {
		this.blood = b;
	}

	public void setPower(int p) {
		this.power = p;
	}
}
