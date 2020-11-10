import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class Tutorial  extends JPanel implements ActionListener{
	 private GameMap2 map2;
	 private JButton start=new JButton("開始遊戲");
	 
	 public Tutorial(GameMap2 mp2) {
		setSize(Toolkit.getDefaultToolkit().getScreenSize());
		setLayout(null);
		setBackground(Color.yellow);
		newBackground();
		setVisible(true);
		map2=mp2;

	}

	private void newBackground() {
		JLabel text1=new JLabel("楓");
		JLabel text2=new JLabel("資");
		JLabel text3=new JLabel("拳");
		JLabel text4=new JLabel("皇");
		text1.setSize(100,100);
		text1.setLocation(300,100);
		text1.setForeground(Color.black);
		text1.setFont(new Font("Serif",Font.PLAIN,50));
		add(text1);
		
		
		text2.setSize(100,100);
		text2.setLocation(500,100);
		text2.setForeground(new Color(165, 42, 42));
		text2.setFont(new Font("Serif",Font.BOLD,80));
		
		add(text2);
		
		
		text3.setSize(100,100);
		text3.setLocation(700,100);
		text3.setForeground(Color.black);
		text3.setFont(new Font("Serif",Font.PLAIN,50));
		add(text3);
		
		
		text4.setSize(100,100);
		text4.setLocation(900,100);
		text4.setForeground(Color.black);
		text4.setFont(new Font("Serif",Font.PLAIN,50));
		add(text4);
		
		int width=200;
		int height=300;			
		ImageIcon i1=new ImageIcon("image\\m1.gif");
		i1.setImage(i1.getImage().getScaledInstance(width, height,Image.SCALE_DEFAULT ));
		JLabel m1=new JLabel();		
		m1.setSize(width,height);
		m1.setIcon(i1);
		m1.setLocation(1050,300);
		this.add(m1);
		ImageIcon i2=new ImageIcon("image\\m2.gif");
		i2.setImage(i2.getImage().getScaledInstance(width, height,Image.SCALE_DEFAULT ));
		JLabel m2=new JLabel();		
		m2.setSize(width,height);
		m2.setIcon(i2);
		m2.setLocation(50,300);
		this.add(m2);
		
		start.setSize(100,50);
		start.setLocation(600,500);
		start.addActionListener(this);
		add(start);
		
	}

	public void actionPerformed(ActionEvent e) {
		System.out.println("fick");
		 if(e.getActionCommand().equals("開始遊戲"))
		 {
			 this.setVisible(false);
			 map2.setVisible(true);
			 map2.requestFocusInWindow();
		 }
	}
}
