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

	public Battle(Status PlayerState, Status MonsterState) {
		setSize(Toolkit.getDefaultToolkit().getScreenSize());
		this.setLayout(null);
		allAnnounce.setSize(500, 100);
		allAnnounce.setForeground(Color.white);
		allAnnounce.setFont(new Font("dialog", 1, 50));
		allAnnounce.setLocation(500, 250);
		this.add(allAnnounce);
		M_name = MonsterState.name;
		player = new Player(this, PlayerState);
		monster = new Monster(this, MonsterState);
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
		}, 2000);
	}

	private void newBackground() {
		Random random = new Random();
		int path = random.nextInt(5) + 1;
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
			int blood;
			int attack;
			Y_tempAttack = player.attack;
			M_tempAttack = monster.attack;
			int n = player.skill.get(e.getActionCommand());
			n--;
			player.skill.put(e.getActionCommand(), n);
			JLabel temp = new JLabel();
			temp = player.skillWatched.get(e.getActionCommand());
			temp.setText(Integer.toString(n));
			player.skillWatched.put(e.getActionCommand(), temp);

			switch (e.getActionCommand()) {
				case "coffee":
					blood = player.blooBar.getValue();
					player.blood = blood + 10;
					player.blooBar.setValue(blood + 10);
					allAnnounce.setText("recover 10!");
					break;
				case "redBlue":
					blood = player.blooBar.getMaximum() / 2;
					player.blood = blood;
					player.blooBar.setValue(blood);
					System.out.println(player.blooBar.getValue());
					allAnnounce.setText("recover 50%!");
					break;
				case "addAttack1":

					attack = (player.attack += 10);
					player.attackText.setText("attack:" + attack);
					allAnnounce.setText("attack+10!");

					break;
				case "addAttack2":
					player.attack += 20;
					attack = player.attack;
					player.attackText.setText("attack:" + attack);
					allAnnounce.setText("attack+20!");
					break;
				case "subAttack":
					attack = (monster.attack -= 10);
					monster.attackText.setText("attack:" + attack);
					allAnnounce.setText("attack-10!");
					break;
				case "delayOne":
					monster.roundDelay = 1;
					allAnnounce.setText("jump one round!");
					break;
				case "special1":
					if (M_name.equals("archer")) {
						monster.roundDelay = 2;
						allAnnounce.setText("jump two round");
					} else {
						allAnnounce.setText("not work...");
					}
					break;
				case "special2":
					if (M_name.equals("soilder")) {
						monster.blood /= 2;
						monster.blooBar.setValue(monster.blood);
						allAnnounce.setText("succeed!substract the attack");
					} else {
						allAnnounce.setText("not work...");
					}
					break;
				case "special3":
					if (M_name.equals("skeleton")) {
						monster.roundDelay = 2;
						allAnnounce.setText("jump two round");
					} else {
						allAnnounce.setText("not work...");
					}
					break;
				case "special4":
					if (M_name.equals("skeleton1")) {
						monster.roundDelay = 2;
						allAnnounce.setText("jump two round!");
					} else {
						allAnnounce.setText("not work...");
					}
					break;
				default:
					allAnnounce.setText("attack!");
					break;
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
			}, 2000);

		}
	}
	public void StartGame() {
		if (player.roundDelay > 0) {
			allAnnounce.setText("can't attack!");
			try {
				Thread.sleep(2000);
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
				Thread.sleep(2000);
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
		}, 2000);

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
		monster.blood -= player.attack;
		monster.blooBar.setValue(monster.blood);
		++monster.power;
		monster.powerBar.setValue(monster.power);

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
		monster.attack = M_tempAttack;
		player.attackText.setText("attack:" + player.attack);
		monster.attackText.setText("attack:" + monster.attack);

	}
	public void monsterRound() {

		allAnnounce.setText("Monster round!");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException exception) {
			// TODO: handle exception
			exception.printStackTrace();
		}
		allAnnounce.setText("");

		monsterTimer = new Timer();
		if (monster.powerBar.getValue() == monster.powerBar.getMaximum()) {
			allAnnounce.setText("Monster skill!");
			try {
				Thread.sleep(2000);
			} catch (InterruptedException exception) {
				// TODO: handle exception
				exception.printStackTrace();
			}
			allAnnounce.setText("");
		}

		useMonsterSkill();

		// run the monster animation
		monster.setMonsterImage(true);
		try {
			Thread.sleep(1500);
		} catch (InterruptedException exception) {
			// TODO: handle exception
			exception.printStackTrace();
		}

		// back to the original animation
		// use the special skill after the attack animation

		// judge the blood after the special skill
		player.blood -= monster.attack;
		player.blooBar.setValue(player.blood);

		monster.setMonsterImage(false);

		monster.attack = M_tempAttack;
		monster.attackText.setText("attack:" + monster.attack);

	}
	public void useMonsterSkill() {
		if (monster.powerBar.getValue() == monster.powerBar.getMaximum()) {
			switch (this.M_name) {
				case "skeleton1":
					player.roundDelay = 1;
					break;
				case "skeleton":
					monster.blood /= 2;
					monster.blooBar.setValue(monster.blood);
					monster.attack *= 2;
					monster.attackText.setText("double attack:" + monster.attack);
					break;
				case "wizard":
					if (monster.blooBar.getMaximum() / 2 >= monster.blood) {
						monster.blood *= 2;
						monster.blooBar.setValue(monster.blood);
					} else {
						monster.blood = monster.blooBar.getMaximum();
						monster.blooBar.setValue(monster.blood);
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
					player.blood = 1 + monster.attack;

					break;
				default:
					break;
			}
			monster.powerBar.setValue(0);
			monster.power = 0;
		}
	}


	private void isWinGame() {
		if (player.blooBar.getValue() == 0) {
			winFlag = false;
			allAnnounce.setText("You Lose");
			try {
				Thread.sleep(2000);
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
				Thread.sleep(2000);
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
			return monster.money;
		else {
			return 0;
		}
	}
}
