import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.util.jar.Attributes.Name;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

public class Monster extends Property{
	public int power;
	public JLabel m;
	public JProgressBar powerBar;
	private String name;
	
	public Monster(JPanel  fieldJPanel,Status Mstate) {
		// TODO Auto-generated constructor stub
		battleField=fieldJPanel;
		setMonster(Mstate);
		setMonsterProperty();
		
		m =new JLabel();
		setMonsterImage(false);
		battleField.add(m);
	}
	public void setMonster(Status s) {
		attack=s.attack;
		blood=s.blood;
		money=s.money;
		power=s.power;
		this.name=s.name;
		
		
		
	}
	public void setMonsterImage(boolean attackFlag) {
		int width=0;
		int height=0;
		int count=0;
		int locY=0;
		int locX=700;
		switch (name) {
		case "TA":
			width = 300;
			height = 300;
			count=0;
			locY=300;
			break;
		case "pein":
			width=500;
			height=600;
			locY=0;
			count=1;	
			break;
		case "DataStructure":
			width=500;
			height=600;
			locY=0;
			count=2;	
			break;
		case "ComputerScience":
			width=200;
			height=300;
			locY=250;
			count=3;
			break;
		case "monster4":
			width=200;
			height=300;
			locY=250;
			count=4;
			break;
		case "monster5":
			width=200;
			height=300;
			locY=250;
			count=5;
			break;
		case "monster6":
			width=200;
			height=300;
			locY=250;
			count=6;
			break;
		
		default:
			width=500;
			height=600;
			locY=0;
			count=2;
			break;
		}
		ImageIcon image2;
		if(attackFlag)
		{
			switch (name) {
			case "ComputerScience":
				width=400;
				height=500;
				locY=150;
				break;
			case "monster4":
				width=500;
				height=500;
				locY=100;
				locX=500;
				break;
			case "monster5":
				width=300;
				height=300;
				locY=250;
				locX=700;
				break;
			case "monster6":
				width=500;
				height=300;
				locY=250;
				locX=550;
				break;

			default:
				break;
			}
			 image2= new ImageIcon("image/monster/"+count+"fire.gif");//例項化ImageIcon 物件
		}	
		else {
			switch (name) {
			case "ComputerScience":
				width=250;
				height=300;
				locY=250;
				locX=700;
				break;
			case "monster4":
				width=250;
				height=300;
				locY=250;
				locX=700;
				break;
			case "monster5":
				width=250;
				height=300;
				locY=250;
				locX=700;
				break;
			case "monster6":
				width=250;
				height=300;
				locY=250;
				locX=700;
				break;
			default:
				break;
			}
			image2 = new ImageIcon("image/monster/"+count+".gif");//例項化ImageIcon 物件
		}
		image2.setImage(image2.getImage().getScaledInstance(width, height,Image.SCALE_DEFAULT ));

		//m.setOpaque(true);
		//m.setBackground(Color.red);
		m.setIcon(image2);
		m.setSize(width, height);
		m.setLocation(locX,locY);
		
	}
	public void setMonsterProperty() {
		bloodText=new JLabel("敵方血量");
		bloodText.setSize(100,50);
		bloodText.setLocation(1100,0);
		bloodText.setForeground(Color.white);
		bloodText.setFont(new Font("dialog", 1, 20));
		blooBar=new JProgressBar();
		blooBar.setMaximum(this.blood);
		blooBar.setMinimum(0);
		blooBar.setValue(this.blood);
		blooBar.setForeground(Color.red);
		blooBar.setSize(200,30);
		blooBar.setLocation(1100,50);
		attackText=new JLabel("攻擊力:"+this.attack);
		attackText.setSize(100,50);
		attackText.setLocation(1100,100);
		attackText.setForeground(Color.white);
		attackText.setFont(new Font("dialog", 1, 20));
		powerBar=new JProgressBar();
		powerBar.setMaximum(5);
		powerBar.setMinimum(0);
		powerBar.setValue(this.power);
		powerBar.setForeground(Color.yellow);
		powerBar.setLocation(1100,150);
		powerBar.setSize(200,20);
		battleField.add(blooBar);
		battleField.add(bloodText);
		battleField.add(attackText);
		battleField.add(powerBar);
	}
}
