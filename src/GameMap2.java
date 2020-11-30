import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class GameMap2 extends JPanel implements ActionListener {
	private int person_x = 750;
	private int person_y = 280;
	public JLabel character;
	public JLabel skeleton1;
	public JLabel skeleton2;
	public JLabel wizard;
	public JLabel soldier;
	public JLabel archer;
	public JLabel pirate;
	int x_up = 0;
	int x_lower = 0;
	int y_up = 0;
	int y_lower = 0;
	ImageIcon down_1 = new ImageIcon("../image/MapPlayer/front1.png");
	ImageIcon down_2 = new ImageIcon("../image/MapPlayer/front2.png");
	ImageIcon down_3 = new ImageIcon("../image/MapPlayer/front3.png");
	ImageIcon up_1 = new ImageIcon("../image/MapPlayer/back1.png");
	ImageIcon up_2 = new ImageIcon("../image/MapPlayer/back2.png");
	ImageIcon up_3 = new ImageIcon("../image/MapPlayer/back3.png");
	ImageIcon right_1 = new ImageIcon("../image/MapPlayer/right1.png");
	ImageIcon right_2 = new ImageIcon("../image/MapPlayer/right2.png");
	ImageIcon right_3 = new ImageIcon("../image/MapPlayer/right3.png");
	ImageIcon left_1 = new ImageIcon("../image/MapPlayer/left1.png");
	ImageIcon left_2 = new ImageIcon("../image/MapPlayer/left2.png");
	ImageIcon left_3 = new ImageIcon("../image/MapPlayer/left3.png");
	private Status MyPlayer;
	private Timer listenEnd = new Timer();
	private JFrame mainField;
	private int Mymoney;
	private Battle battle;
	private Store st;
	private boolean monster_win[] = new boolean[7];
	private int check_money = 0;
	private int count_boss = 0;

	// public JLabel dame_over = new JLabel();

	public GameMap2(JFrame jFrame, Status p) {
		for (int i = 0; i < 7; ++i) {
			monster_win[i] = false;
		}
		this.mainField = jFrame;
		this.MyPlayer = p;
		st = new Store(this);
		mainField.add(st);
		setSize(Toolkit.getDefaultToolkit().getScreenSize());
		button_location();
		store_buttom();
		bag_buttom();
		newBackground();
		this.setLayout(null);
		this.setKeyBoardListener();
		setFocusable(true);
		setVisible(true);
		requestFocusInWindow();

	}

	private void button_location() {
		ImageIcon sk1_img = new ImageIcon("../image/Monster/1.gif");
		ImageIcon sk2_img = new ImageIcon("../image/Monster/2.gif");
		ImageIcon wizard_img = new ImageIcon("../image/Monster/3.gif");
		ImageIcon soldier_img = new ImageIcon("../image/Monster/4.gif");
		ImageIcon archer_img = new ImageIcon("../image/Monster/5.gif");
		ImageIcon pirate_img = new ImageIcon("../image/Monster/6.gif");
		sk1_img.setImage(sk1_img.getImage().getScaledInstance(250, 162, Image.SCALE_DEFAULT));
		sk2_img.setImage(sk2_img.getImage().getScaledInstance(250, 162, Image.SCALE_DEFAULT));
		skeleton1 = new JLabel(sk1_img);
		skeleton2 = new JLabel(sk2_img);
		wizard = new JLabel(wizard_img);
		soldier = new JLabel(soldier_img);
		archer = new JLabel(archer_img);
		pirate = new JLabel(pirate_img);
		skeleton1.setSize(200, 160);
		skeleton2.setSize(200, 160);
		wizard.setSize(200, 160);
		soldier.setSize(200, 160);
		archer.setSize(200, 160);
		pirate.setSize(200, 160);
		skeleton1.setLocation(500, 80);
		skeleton2.setLocation(40, 60);
		wizard.setLocation(280, 490);
		soldier.setLocation(270, 0);
		archer.setLocation(950, 450);
		pirate.setLocation(570, 500);
		character = new JLabel(down_1);
		// character.setContentAreaFilled(false);
		character.setSize(60, 70);
		character.setLocation(person_x, person_y);
		// setlocation(character);

		// character.setBorderPainted(false);

		// setVisible(true);

		this.add(character);
		this.add(skeleton1);
		this.add(skeleton2);
		this.add(wizard);
		this.add(soldier);
		this.add(archer);
		this.add(pirate);
		this.requestFocusInWindow();

	}

	private void newBackground() {
		JLabel jlb = new JLabel();
		int width = 1300, height = 700;
		ImageIcon image = new ImageIcon("../image/Map.png");
		image.setImage(image.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
		jlb.setIcon(image);
		jlb.setSize(width, height);

		this.add(jlb);
	}

	private void setKeyBoardListener() {
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
				enterBattle(person_x, person_y);
			}

			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub

			}

			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub

			}

		});
	}

	/*
	 * private void setlocation(JButton a) { a.setLocation(person_x + 40,person_y
	 * -10);
	 * 
	 * }
	 */

	private void store_buttom() {
		ImageIcon store_image = new ImageIcon("../image/Store_icon.png");
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

	private void bag_buttom() {
		ImageIcon bag_buttom = new ImageIcon("../image/Bag_icon.png");
		bag_buttom.setImage(bag_buttom.getImage().getScaledInstance(100, 60, Image.SCALE_DEFAULT));
		JButton bag = new JButton(bag_buttom);
		bag.setActionCommand("bag");
		bag.setIcon(bag_buttom);
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

				// 嚙踝蕭賹蕭嚙�
				Status M = new Status("TA");
				enterBattle(M, monster_win, 0);

			}
		}
	}

	public void enterBattle(int person_x, int person_y) {
		boolean enter = false;
		int x = -1;
		Status M = new Status("default");
		if (person_x > 500 && person_x < 700 && person_y > 80 && person_y < 240 && monster_win[1] == false) {
			System.out.println("skeleton1");
			// 嚙踐��鞎赤嚙踝��蕭
			// �謢塚蕭謅�蕭嚙踐�ss name
			M = new Status("skeleton1");
			enter = true;
			x = 1;
		}
		if (person_x > 40 && person_x < 240 && person_y > 60 && person_y < 220 && monster_win[2] == false) {
			System.out.println("skeleton2");

			M = new Status("skeleton2");
			enter = true;
			x = 2;
		}

		if (person_x > 280 && person_x < 480 && person_y > 490 && person_y < 650 && monster_win[3] == false) {
			System.out.println("wizard");

			M = new Status("wizard");
			enter = true;
			x = 3;
		}
		// 4
		if (person_x > 270 && person_x < 470 && person_y > 0 && person_y < 160 && monster_win[4] == false) {
			System.out.println("solider");

			M = new Status("soldier");
			enter = true;
			x = 4;
		}
		// 5
		if (person_x > 950 && person_x < 1150 && person_y > 450 && person_y < 610 && monster_win[5] == false) {
			System.out.println("archer");

			M = new Status("archer");
			enter = true;
			x = 5;
		}
		// 6
		if (person_x > 570 && person_x < 770 && person_y > 500 && person_y < 660 && monster_win[6] == false) {
			System.out.println("pirate");

			M = new Status("pirate");
			enter = true;
			x = 6;
		}
		if (enter) {
			enterBattle(M, monster_win, x);
		}
		if (count_boss == 6) {
			System.out.println("game over");
			System.exit(1);
		}

	}

	private void enterBattle(Status M, boolean monster_win[], int x) {
		check_money = 0;
		MyPlayer.blood = 100;
		battle = new Battle(MyPlayer, M);
		mainField.add(battle);
		this.setVisible(false);
		Timer listenEnd = new Timer();
		listenEnd.schedule(new TimerTask() {

			@Override

			public void run() {
				// TODO Auto-generated method stub
				// ����蕭蹓���嚙踐���蕭嚙�
				if (battle.isOver()) {
					check_money = backMap();
					// System.out.println("fuck");
					listenEnd.cancel();
					if (check_money > 0 && x != 0) {
						monster_win[x] = true;

						if (x == 1)
							skeleton1.setVisible(false);
						else if (x == 2)
							skeleton2.setVisible(false);
						else if (x == 3)
							wizard.setVisible(false);
						else if (x == 4)
							soldier.setVisible(false);
						else if (x == 5)
							archer.setVisible(false);
						else if (x == 6)
							pirate.setVisible(false);
						++count_boss;

					}

				}

			}
		}, 0, 500);
	}

	private int backMap() {
		int y = 0;
		y = battle.getMoney();
		MyPlayer.money += y;
		battle.setVisible(false);
		this.setVisible(true);
		battle = null;
		this.requestFocusInWindow();
		return y;
	}

	/*
	 * public void actionPerformed(ActionEvent e) { switch(e.getActionCommand()) {
	 * case "store": System.out.println("open store"); break; case "bag":
	 * System.out.println("open bag"); break; case "character":
	 * System.out.println("move"); break; case"center":
	 * System.out.println("center"); if(person_x == 100 && person_y == 280) { //from
	 * water Timer timer = new Timer(); TimerTask task = new TimerTask() {
	 * 
	 * @Override public void run() { person_x += 1;
	 * character.setLocation(person_x,person_y); if(person_x == 750) {
	 * //setlocation(character); timer.cancel(); } } };
	 * 
	 * long delay = 0; long intevalPeriod = 4; timer.scheduleAtFixedRate(task,
	 * delay, intevalPeriod); timer.schedule(task, delay);
	 * 
	 * 
	 * }
	 */

}
