package main;

import java.awt.Frame;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import javax.swing.*;

public class AdvantureGame extends JFrame {
	public AdvantureGame() {
		Status player = new Status("player");
		setSize(0, 0);
		setVisible(true);
		setLayout(null);
		GameMap2 map2 = new GameMap2(this, player);
		add(map2);

		map2.requestFocusInWindow();
		map2.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				map2.requestFocusInWindow();
			}

		});

		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new AdvantureGame();

	}

}