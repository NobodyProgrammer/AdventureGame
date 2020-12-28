package main;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import Factory.*;

public class GameMap extends JPanel implements ActionListener {
	private int person_x = 750;
	private int person_y = 280;
	private int check_money = 0;
	private int count_boss = 0;
	private boolean monster_win[] = new boolean[7];
	private JLabel[] monster = new JLabel[6];
	private JLabel character;
	private Status MyPlayer;
	private JFrame mainField;
	private Battle battle;
	private Store st;

	public GameMap(JFrame jFrame, Status p) {
		for (int i = 0; i < 6; ++i) {
			monster_win[i] = false;
			this.monster[i] = new JLabel();
		}
		this.mainField = jFrame;
		this.MyPlayer = p;
		st = new Store(this);
		mainField.add(st);
		setSize(Toolkit.getDefaultToolkit().getScreenSize());
		characterLoc();
		storeEntry();
		bag_button();
		newBackground();
		this.setLayout(null);
		this.setKeyBoardListener();
		setFocusable(true);
		setVisible(true);
		requestFocusInWindow();

	}

	private void characterLoc() {

		for (int i = 0; i < 6; ++i) {
			ImageIcon img = new ImageIcon("./image/Monster/" + (i + 1) + ".gif");
			if (i <= 1) {
				img.setImage(img.getImage().getScaledInstance(250, 162, Image.SCALE_DEFAULT));
			}
			this.monster[i] = new JLabel(img);
			this.monster[i].setSize(200, 160);
			this.add(monster[i]);
		}

		this.monster[0].setLocation(500, 80);
		this.monster[1].setLocation(40, 60);
		this.monster[2].setLocation(280, 490);
		this.monster[3].setLocation(270, 0);
		this.monster[4].setLocation(950, 450);
		this.monster[5].setLocation(570, 500);
		character = new JLabel(new ImageIcon("./image/MapPlayer/front1.png"));
		character.setSize(60, 70);
		character.setLocation(person_x, person_y);
		this.add(character);
		this.requestFocusInWindow();

	}

	private void newBackground() {
		JLabel jlb = new JLabel();
		int width = 1300, height = 700;
		ImageIcon image = new ImageIcon("./image/Map.png");
		image.setImage(image.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
		jlb.setIcon(image);
		jlb.setSize(width, height);

		this.add(jlb);
	}

	private void setKeyBoardListener() {
		ImageIcon down_1 = new ImageIcon("./image/MapPlayer/front1.png");
		ImageIcon up_1 = new ImageIcon("./image/MapPlayer/back1.png");
		ImageIcon right_1 = new ImageIcon("./image/MapPlayer/right1.png");
		ImageIcon left_1 = new ImageIcon("./image/MapPlayer/left1.png");
		this.addKeyListener(new KeyListener() {
			public void keyPressed(KeyEvent e) {
				// System.out.println(e.getKeyChar());
				if (e.getKeyCode() == KeyEvent.VK_UP) {
					person_y -= 10;
					character.setLocation(person_x, person_y);
					character.setIcon(up_1);
				}
				if (e.getKeyCode() == KeyEvent.VK_DOWN) {
					person_y += 10;
					character.setLocation(person_x, person_y);
					character.setIcon(down_1);
				}
				if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
					person_x += 10;
					character.setLocation(person_x, person_y);
					character.setIcon(right_1);
				}
				if (e.getKeyCode() == KeyEvent.VK_LEFT) {
					person_x -= 10;
					character.setLocation(person_x, person_y);
					character.setIcon(left_1);
				}
				if (e.getKeyCode() == KeyEvent.VK_SPACE) {
					// if(person_x + 40 );
				}
				the_TA_locaton(person_x, person_y);
				isMeetMonster(person_x, person_y);
			}

			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub

			}

			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub

			}

		});
	}

	private void storeEntry() {
		ImageIcon store_image = new ImageIcon("./image/Store_icon.png");
		store_image.setImage(store_image.getImage().getScaledInstance(100, 60, Image.SCALE_DEFAULT));
		JButton store = new JButton(store_image);
		store.setActionCommand("store");
		store.setIcon(store_image);
		store.setSize(100, 60);
		// store.setLocation(1425, 735);
		store.setLocation(0, 0);
		store.addActionListener(this);
		store.setContentAreaFilled(false);
		setVisible(true);
		this.add(store);
	}

	private void bag_button() {
		ImageIcon bag_button = new ImageIcon("./image/Bag_icon.png");
		bag_button.setImage(bag_button.getImage().getScaledInstance(100, 60, Image.SCALE_DEFAULT));
		JButton bag = new JButton(bag_button);
		bag.setActionCommand("bag");
		bag.setIcon(bag_button);
		bag.setSize(100, 60);
		int bag_x = 1150;
		int bag_y = 600;
		bag.setLocation(bag_x, bag_y);
		// bag.setLocation(0, 0);
		bag.addActionListener(this);
		bag.setContentAreaFilled(false);
		setVisible(true);
		this.add(bag);
	}

	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
			case "store":
				System.out.println("open store");
				st.init(MyPlayer);
				// mainField.pack();
				// this.mainField.setSize(1300,500);
				break;
			case "bag":
				setVisible(false);
				break;
		}
		this.requestFocusInWindow();
	}

	public void the_TA_locaton(int person_x, int person_y) {
		if (person_x > 850 && person_x < 1300 && person_y > 0 && person_y < 400 && monster_win[0] == false) {
			Random random = new Random();
			int randomNum = random.nextInt(10);
			System.out.println(randomNum);
			if (randomNum == 1) {
				Monster monster = new Monster("TA");
				monster.setStatus(1, 1, 1, 1);
				enterBattle(monster, monster_win, 0);

			}
		}
	}

	public void isMeetMonster(int person_x, int person_y) {
		boolean enter = false;
		int x = -1;
		Monster monster = new Monster();
		if (person_x > 500 && person_x < 700 && person_y > 80 && person_y < 240 && monster_win[1] == false) {
			System.out.println("skeleton1");
			AbstractFactory factory_skeleton = new SkeletonFactory();
			HighLevel Hmonster = factory_skeleton.createHighLevel();
			monster = Hmonster.getMonster();
			enter = true;
			x = 1;
		}
		if (person_x > 40 && person_x < 240 && person_y > 60 && person_y < 220 && monster_win[2] == false) {
			System.out.println("skeleton2");
			AbstractFactory factory_skeleton = new SkeletonFactory();
			LowLevel Lmonster = factory_skeleton.createLowLevel();
			monster = Lmonster.getMonster();
			enter = true;
			x = 2;
		}

		if (person_x > 280 && person_x < 480 && person_y > 490 && person_y < 650 && monster_win[3] == false) {
			System.out.println("wizard");
			AbstractFactory factory_longdis = new LongDistanceFactory();
			HighLevel Hmonster = factory_longdis.createHighLevel();
			monster = Hmonster.getMonster();
			enter = true;
			x = 3;
		}
		// 4
		if (person_x > 270 && person_x < 470 && person_y > 0 && person_y < 160 && monster_win[4] == false) {
			System.out.println("solider");
			AbstractFactory factory_solider = new SoilderFactory();
			HighLevel Hmonster = factory_solider.createHighLevel();
			monster = Hmonster.getMonster();
			enter = true;
			x = 4;
		}
		// 5
		if (person_x > 950 && person_x < 1150 && person_y > 450 && person_y < 610 && monster_win[5] == false) {
			System.out.println("archer");
			AbstractFactory factory_longdis = new LongDistanceFactory();
			LowLevel Lmonster = factory_longdis.createLowLevel();
			monster = Lmonster.getMonster();
			enter = true;
			x = 5;
		}
		// 6
		if (person_x > 570 && person_x < 770 && person_y > 500 && person_y < 660 && monster_win[6] == false) {
			System.out.println("pirate");
			AbstractFactory factory_solider = new SoilderFactory();
			LowLevel Lmonster = factory_solider.createLowLevel();
			monster = Lmonster.getMonster();
			enter = true;
			x = 6;
		}
		if (enter) {
			enterBattle(monster, monster_win, x);
		}
		if (count_boss == 6) {
			System.out.println("game over");
			System.exit(1);
		}

	}

	private void enterBattle(Monster M, boolean monster_win[], int x) {
		check_money = 0;
		MyPlayer.blood = 100;
		MyPlayer.attack = 10;
		battle = new Battle(MyPlayer, M);
		mainField.add(battle);
		this.setVisible(false);
		Timer listenEnd = new Timer();
		listenEnd.schedule(new TimerTask() {
			@Override
			public void run() {
				if (battle.isOver()) {
					check_money = backMap();
					listenEnd.cancel();
					if (check_money > 0 && x != 0) {
						monster_win[x] = true;
						monster[x].setVisible(false);
						++count_boss;
					}
				}
			}
		}, 0, 500);
	}

	private int backMap() {
		int y = 0;
		y = battle.getMoney();
		MyPlayer.updateMoney(y);
		battle.setVisible(false);
		this.setVisible(true);
		battle = null;
		this.requestFocusInWindow();// get key listener again
		return y;
	}

}
