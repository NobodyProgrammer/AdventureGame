package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.*;

import Items.Decorator.Component;

public class Battle extends JPanel implements ActionListener {
	private Player player;
	private Monster monster;
	private Timer AnnounceRound = new Timer();
	private Timer AnnounceSkill = new Timer();
	private Timer monsterTimer;
	private JLabel allAnnounce = new JLabel();
	private JButton escape = new JButton("escape...");
	private boolean endFlag = false;
	private boolean winFlag = false;
	private int Y_tempAttack;
	private int M_tempAttack;

	public String M_name;

	public Battle(Status PlayerState, Monster M) {
		setSize(Toolkit.getDefaultToolkit().getScreenSize());
		this.setLayout(null);
		allAnnounce.setSize(500, 100);
		allAnnounce.setForeground(Color.white);
		allAnnounce.setFont(new Font("dialog", 1, 50));
		allAnnounce.setLocation(500, 250);
		this.add(allAnnounce);
		player = new Player(this, PlayerState);
		M.setBattleField(this);
		this.M_name = M.getName();
		this.monster = M;
		addButtonEvent();
		newBackground();
		escape.setSize(100, 50);
		escape.setLocation(1100, 600);
		escape.addActionListener(this);
		add(escape);

		allAnnounce.setText("battle!");
		AnnounceRound.schedule(new TimerTask() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				allAnnounce.setText("");
				for (int i = 0; i < player.skillUse.size(); ++i)
					player.skillUse.get(i).setEnabled(true);
			}
		}, 500);
	}

	private void newBackground() {
		try {
			JLabel jlb = new JLabel();
			int width = 1300, height = 700;
			ImageIcon image = new ImageIcon("./image/field1.png");
			image.setImage(image.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
			jlb.setIcon(image);
			jlb.setSize(width, height);
			this.add(jlb);
		} catch (Exception e) {
			System.out.println("new background false");
		}
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("escape..."))
			this.endFlag = true;
		else {
			Y_tempAttack = player.attack;
			M_tempAttack = monster.getProperty("attack");
			int n = player.skill.get(e.getActionCommand());
			n--;
			player.skill.put(e.getActionCommand(), n);
			JLabel showRemain = new JLabel();
			showRemain = player.skillWatched.get(e.getActionCommand());
			showRemain.setText(Integer.toString(n));
			player.skillWatched.put(e.getActionCommand(), showRemain);
			if (player.skillContent.containsKey(e.getActionCommand())) {
				Component Item = player.skillContent.get(e.getActionCommand());
				String text = Item.characterEffect(player, monster);
				allAnnounce.setText(text);
			} else {
				allAnnounce.setText("attack!");
			}
			for (int i = 0; i < player.skillUse.size(); ++i)
				// System.out.println(property.skillUse.get(i));
				player.skillUse.get(i).setEnabled(false);

			AnnounceSkill.schedule(new TimerTask() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					allAnnounce.setText("");

					StartGame();

				}
			}, 500);

		}
	}

	public void StartGame() {
		if (player.roundDelay > 0) {
			allAnnounce.setText("can't attack!");
			try {
				Thread.sleep(500);
			} catch (InterruptedException exception) {
				// TODO: handle exception
				exception.printStackTrace();
			}
			--player.roundDelay;
		} else {
			playerRound();
			isWinGame();
		}

		if (monster.roundDelay > 0) {
			allAnnounce.setText("can't attack!");
			try {
				Thread.sleep(500);
			} catch (InterruptedException exception) {
				// TODO: handle exception
				exception.printStackTrace();
			}
			--monster.roundDelay;
		} else {
			monsterRound();
			isWinGame();
		}

		// System.out.println("successful");
		allAnnounce.setText("Your Round!");
		AnnounceRound.schedule(new TimerTask() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				if (player.roundDelay > 0) {
					StartGame();
				} else {
					allAnnounce.setText("");
					for (int i = 0; i < player.skillUse.size(); ++i) {
						String skillText = player.skillUse.get(i).getText();
						if (player.skill.get(skillText) > 0)
							player.skillUse.get(i).setEnabled(true);
					}

				}

			}
		}, 500);

	}

	public void playerRound() {
		int tempWidth = player.playerWidth;
		int tempHeight = player.playerHeight;
		int tempX = player.p.getLocation().x;
		int tempY = player.p.getLocation().y;

		// here to run animation of player
		player.playerImageNum = 0;
		player.playerPath = "./image/playerAttack/player";
		player.playerWidth = 400;
		player.playerHeight = 500;
		player.p.setLocation(500, 100);
		player.p.setSize(400, 500);

		// player attack
		int blood = (int) monster.getProperty("blood") - player.attack;
		int power = (int) monster.getProperty("power");
		monster.setProperty("blood", blood);
		monster.blooBar.setValue(blood);
		monster.setProperty("power", ++power);
		monster.powerBar.setValue(power);

		// wait the animation of skill
		try {
			Thread.sleep(2300);
		} catch (InterruptedException exception) {
			// TODO: handle exception
			exception.printStackTrace();
		}

		// back to original animation after the skill
		player.playerPath = "./image/player/player0";
		player.playerWidth = tempWidth;
		player.playerHeight = tempHeight;
		player.p.setLocation(tempX, tempY);
		player.p.setSize(tempWidth, tempHeight);

		player.attack = Y_tempAttack;
		monster.setProperty("attack", M_tempAttack);
		player.attackText.setText("attack:" + player.attack);
		monster.attackText.setText("attack:" + monster.getProperty("attack"));

	}

	public void monsterRound() {
		allAnnounce.setText("Monster round!");
		try {
			Thread.sleep(500);
		} catch (InterruptedException exception) {
			// TODO: handle exception
			exception.printStackTrace();
		}
		allAnnounce.setText("");

		monsterTimer = new Timer();
		if (monster.powerBar.getValue() == monster.powerBar.getMaximum()) {
			allAnnounce.setText("Monster skill!");
			try {
				Thread.sleep(500);
			} catch (InterruptedException exception) {
				// TODO: handle exception
				exception.printStackTrace();
			}
			allAnnounce.setText("");
			useMonsterSkill();
		}
		// run the monster animation
		monster.setMonsterImage(true);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException exception) {
			// TODO: handle exception
			exception.printStackTrace();
		}

		// back to the original animation
		// use the special skill after the attack animation

		// judge the blood after the special skill
		player.blood -= monster.getProperty("attack");
		player.blooBar.setValue(player.blood);

		monster.setMonsterImage(false);

		monster.setProperty("attack", M_tempAttack);
		monster.attackText.setText("attack:" + monster.getProperty("attack"));

	}

	public void useMonsterSkill() {
		int blood;
		int attack;
		switch (this.M_name) {
			case "skeleton1":
				player.roundDelay = 1;
				break;
			case "skeleton":
				blood = (int) monster.getProperty("blood") / 2;
				monster.setProperty("blood", blood);
				monster.blooBar.setValue(blood);
				attack = (int) monster.getProperty("attack") * 2;
				monster.setProperty("attack", attack);
				monster.attackText.setText("double attack:" + attack);
				break;
			case "wizard":
				if (monster.blooBar.getMaximum() / 2 >= monster.getProperty("attack")) {
					blood = (int) monster.getProperty("blood") * 2;
					monster.setProperty("blood", blood);
					monster.blooBar.setValue(blood);
				} else {
					monster.setProperty("blood", monster.blooBar.getMaximum());
					monster.blooBar.setValue((int) monster.getProperty("blood"));
				}

				break;
			case "soilder":
				player.roundDelay = 1;
				break;
			case "archer":
				player.attack /= 2;
				player.attackText.setText("attack:" + player.attack);
				break;
			case "pirate":
				player.blood = 1 + (int) monster.getProperty("attack");

				break;
			default:
				break;
		}
		monster.powerBar.setValue(0);
		monster.setProperty("power", 0);
	}

	private void isWinGame() {
		if (player.blooBar.getValue() == 0) {
			winFlag = false;
			allAnnounce.setText("You Lose");
			try {
				Thread.sleep(500);
			} catch (InterruptedException exception) {
				// TODO: handle exception
				exception.printStackTrace();
			}
			endFlag = true;
			try {
				Thread.sleep(500);
			} catch (InterruptedException exception) {
				// TODO: handle exception
				exception.printStackTrace();
			}
		} else if (monster.blooBar.getValue() == 0) {
			allAnnounce.setText("You Win");
			winFlag = true;
			try {
				Thread.sleep(500);
			} catch (InterruptedException exception) {
				// TODO: handle exception
				exception.printStackTrace();
			}
			endFlag = true;
			try {
				Thread.sleep(500);
			} catch (InterruptedException exception) {
				// TODO: handle exception
				exception.printStackTrace();
			}
		}
	}

	private void addButtonEvent() {
		for (int i = 0; i < player.skillUse.size(); ++i) {
			// System.out.println(property.skillUse.get(i));
			player.skillUse.get(i).addActionListener(this);
			player.skillUse.get(i).setEnabled(false);
		}
		// System.out.println(property.skillUse.get(0).getActionListeners().length);
	}

	public boolean isOver() {
		return endFlag;
	}

	public int getMoney() {
		if (winFlag)
			return (int) monster.getProperty("money");
		else {
			return 0;
		}
	}
}
