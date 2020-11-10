
import java.awt.Color;
import java.awt.Font;
import 	java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.*;
public class Battle extends JPanel implements ActionListener{
	 private Player player;
	 private Monster monster;
	 private Timer AnnounceRound=new Timer();
	 private Timer AnnounceSkill=new Timer();
	 private Timer monsterTimer;
	 private int monsterPath;
	 private JLabel allAnnounce=new JLabel();
	 private JButton escape=new JButton("逃跑...");
	 private boolean endFlag=false;
	 private boolean winFlag=false;
	 private int Y_tempAttack;	
	 private int M_tempAttack;
	 
	 public String M_name;
	 public Battle(Status PlayerState,Status MonsterState) {
		setSize(Toolkit.getDefaultToolkit().getScreenSize());		
		this.setLayout(null);
		allAnnounce.setSize(500,100);
		allAnnounce.setForeground(Color.white);
		allAnnounce.setFont(new Font("dialog", 1, 50));
		allAnnounce.setLocation(500,250);
		this.add(allAnnounce);
		//這邊傳遞data
		M_name=MonsterState.name;
		player=new Player(this,PlayerState);
		monster=new Monster(this,MonsterState);
		addButtonEvent();
		newBackground();
		//這邊設定逃跑按鈕
		escape.setSize(100,50);
		escape.setLocation(1100,600);
		escape.addActionListener(this);
		add(escape);
		
		//開始遊戲
		allAnnounce.setText("我的回合!");
		AnnounceRound.schedule(new TimerTask() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				allAnnounce.setText("");
				for(int i=0;i<player.skillUse.size();++i)			
					player.skillUse.get(i).setEnabled(true);
			}
		}, 3000);
		
		
		
		//System.out.println("fuck");
	}
	private void newBackground() {
		Random random=new Random();
		int path=random.nextInt(5)+1;
		try {
			JLabel jlb = new JLabel();	//例項化JLble
			int width = 1300,height = 700;	//這是圖片和JLable的寬度和高度
			ImageIcon image = new ImageIcon("image/field1.png");//例項化ImageIcon 物件
	/*下面這句意思是:得到此圖示的 Image(image.getImage());
	在此基礎上建立它的縮放版本,縮放版本的寬度,高度與JLble一致(getScaledInstance(width, height,Image.SCALE_DEFAULT ))
	最後該影象就設定為得到的縮放版本(image.setImage)
	*/
			image.setImage(image.getImage().getScaledInstance(width, height,Image.SCALE_DEFAULT ));//可以用下面三句程式碼來代替
	//Image img = image.getImage();
	//img = img.getScaledInstance(width, height, Image.SCALE_DEFAULT);
	//image.setImage(img);
		jlb.setIcon(image);
		jlb.setSize(width, height);
		this.add(jlb);
	} catch (Exception e) {
		// TODO: handle exception
	System.out.println("fuck");
		}
	
		
	}
	
	public void actionPerformed(ActionEvent e) {
		//逃跑按鈕
		
		if(e.getActionCommand().equals("逃跑..."))
			this.endFlag=true;
		else {
			int blood;
			int attack;
			Y_tempAttack=player.attack;
			M_tempAttack=monster.attack;
			
			
			//減少數量
			int n=player.skill.get(e.getActionCommand());
			n--;
			player.skill.put(e.getActionCommand(), n);
			JLabel temp=new JLabel();
			temp=player.skillWatched.get(e.getActionCommand());
			temp.setText(Integer.toString(n));
			player.skillWatched.put(e.getActionCommand(), temp);
			
			switch (e.getActionCommand()) {
			case "coffee":
				blood=player.blooBar.getValue();
				player.blood=blood+10;
				player.blooBar.setValue(blood+10);
				allAnnounce.setText("使用咖啡!");		
			break;
			case "redBlue":
				blood=player.blooBar.getMaximum()/2;
				player.blood=blood;
				player.blooBar.setValue(blood);
				System.out.println(player.blooBar.getValue());
				allAnnounce.setText("使用redBlue!");		
			break;
			case "leg":
				
				attack=(player.attack+=10);
				player.attackText.setText("攻擊力:"+attack);
				allAnnounce.setText("使用學霸大腿!");
				
			break;
			case "underwear":
				//System.out.println(property.Y_attack);
				player.attack+=20;
				attack=player.attack;
				player.attackText.setText("攻擊力:"+attack);
				allAnnounce.setText("使用學霸小褲褲!");
			break;
			case "G_test":
				attack=(monster.attack-=10);
				monster.attackText.setText("攻擊力:"+attack);
				allAnnounce.setText("使用一般考古!");
			break;
			case "ticket":
				monster.roundDelay=1;
			allAnnounce.setText("使用回家車票!");
			break;
			
			
			//特殊技能
			case "phone":
				if(M_name.equals("monster5"))
				{
					monster.roundDelay=2;
					allAnnounce.setText("小孩電話接送!");
				}	
				else {
					allAnnounce.setText("沒什麼事發生...");
				}
				break;
			case "medician":
				if(M_name.equals("monster4"))
				{
					monster.blood/=2;
					monster.blooBar.setValue(monster.blood);
					allAnnounce.setText("吃中藥拉肚子!");
				}	
				else {
					allAnnounce.setText("沒什麼事發生...");
				}
				break;
			case "mask":
				if(M_name.equals("DataStructure"))
				{
					monster.roundDelay=2;
					allAnnounce.setText("口罩缺貨喔!");
				}	
				else {
					allAnnounce.setText("沒什麼事發生...");
				}
				break;
			case "H_test":
				if(M_name.equals("pein"))
				{
					monster.roundDelay=2;
					allAnnounce.setText("學霸給考古啦!");
				}	
				else {
					allAnnounce.setText("沒什麼事發生...");
				}
				break;
			default:
				allAnnounce.setText("直接攻擊!");
				break;
			}
			for(int i=0;i<player.skillUse.size();++i)
				//System.out.println(property.skillUse.get(i));
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
	private void addButtonEvent() {
		for(int i=0;i<player.skillUse.size();++i)
		{
			//System.out.println(property.skillUse.get(i));
			player.skillUse.get(i).addActionListener(this);
			player.skillUse.get(i).setEnabled(false);
			
		}
		//System.out.println(property.skillUse.get(0).getActionListeners().length);
		
	}
	public void playerRound() {
		int tempWidth=player.playerWidth;
		int tempHeight=player.playerHeight;
		int tempX=player.p.getLocation().x;
		int tempY=player.p.getLocation().y;
		
		//這裡跑腳色動畫
		player.playerImageNum=0;
		player.playerPath="image/playerAttack/player";
		player.playerWidth=400;
		player.playerHeight=500;
		player.p.setLocation(500,100);
		player.p.setSize(400,500);
		
		//腳色攻擊
		
		monster.blood-=player.attack;
		monster.blooBar.setValue(monster.blood);
		++monster.power;
		monster.powerBar.setValue(monster.power);//power上升
		
		//等技能跑完
		try {
			Thread.sleep(2300);
		} catch (InterruptedException exception) {
			// TODO: handle exception
			exception.printStackTrace();
		}
		
		
		//技能施展完要回來
		player.playerPath="image/player/player0";
		player.playerWidth=tempWidth;
		player.playerHeight=tempHeight;
		player.p.setLocation(tempX,tempY);
		player.p.setSize(tempWidth,tempHeight);
		
		player.attack=Y_tempAttack;
		monster.attack=M_tempAttack;
		player.attackText.setText("攻擊力:"+player.attack);
		monster.attackText.setText("攻擊力:"+monster.attack);
		
		
	}

	public void monsterRound() {
		
		allAnnounce.setText("敵方回合!");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException exception) {
			// TODO: handle exception
			exception.printStackTrace();
		}
		allAnnounce.setText("");
		
		monsterTimer=new Timer();
		if(monster.powerBar.getValue()==monster.powerBar.getMaximum())
		{
			allAnnounce.setText("敵方大絕!");
			try {
				Thread.sleep(3000);
			} catch (InterruptedException exception) {
				// TODO: handle exception
				exception.printStackTrace();
			}
			allAnnounce.setText("");
		}
		
		
		
		useMonsterSkill();
		//跑怪物動畫
		monster.setMonsterImage(true);
		//等動畫跑完
		try {
			Thread.sleep(1500);
		} catch (InterruptedException exception) {
			// TODO: handle exception
			exception.printStackTrace();
		}

		
		
		//回來最初動畫
		//攻擊動畫完畢施放教授大絕
		
		//放完大絕再判斷得失
		player.blood-=monster.attack;
		player.blooBar.setValue(player.blood);
		
		
		monster.setMonsterImage(false);
		
		
		//回來最初設定
		monster.attack=M_tempAttack;
		monster.attackText.setText("攻擊:"+monster.attack);
		

	}
	public void useMonsterSkill() {
		if(monster.powerBar.getValue()==monster.powerBar.getMaximum())
		{
			switch (this.M_name) {
			case "pein":
				player.roundDelay=2;
				break;
			case "DataStructure":
				monster.blood/=2;
				monster.blooBar.setValue(monster.blood);
				monster.attack*=2;
				monster.attackText.setText("攻擊:"+monster.attack);
				break;
			case "ComputerScience":
				if(monster.blooBar.getMaximum()/2>=monster.blood)
				{
					monster.blood*=2;
					monster.blooBar.setValue(monster.blood);
				}
				else {
					monster.blood=monster.blooBar.getMaximum();
					monster.blooBar.setValue(monster.blood);
				}
				
				break;
			case "monster4":
				player.roundDelay=2;
				break;
			case "monster5":
				player.attack/=2;
				player.attackText.setText("攻擊:"+player.attack);
				break;
			case "monster6":
				player.blood=1+monster.attack;
				
				break;
			default:
				break;
			}
			monster.powerBar.setValue(0);
			monster.power=0;
		}
	}
	//按下button會開始
 	public void StartGame() {
		//我的回合
		//代表你被對方效果延遲了
		if(player.roundDelay>0)
		{
			allAnnounce.setText("無法攻擊!");
			try {
				Thread.sleep(3000);
			} catch (InterruptedException exception) {
				// TODO: handle exception
				exception.printStackTrace();
			}
			--player.roundDelay;
		}
		else {
			playerRound();
			//這裡判斷對方是不是掛了
			isWinGame();
			
		}
		if(monster.roundDelay>0)
		{
			allAnnounce.setText("無法攻擊!");
			try {
				Thread.sleep(3000);
			} catch (InterruptedException exception) {
				// TODO: handle exception
				exception.printStackTrace();
			}
			--monster.roundDelay;
		}
		else {
			monsterRound();
			//這裡判斷我是不是掛了
			isWinGame();
		}

		//System.out.println("successful");
		allAnnounce.setText("我的回合!");
		AnnounceRound.schedule(new TimerTask() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				if(player.roundDelay>0)
				{
					StartGame();
				}
				else {
					allAnnounce.setText("");
					for(int i=0;i<player.skillUse.size();++i)
					{
						String skillText= player.skillUse.get(i).getText();
						//如果技能用為就不會true
						if(player.skill.get(skillText)>0)
							player.skillUse.get(i).setEnabled(true);
					}

				}

			}
		}, 3000);
		
	 }
	private void isWinGame() {
		if(player.blooBar.getValue()==0)
		{
			winFlag=false;
			allAnnounce.setText("You Lose ┳Д┳");
			//等宣言跑完
			try {
				Thread.sleep(2000);
			} catch (InterruptedException exception) {
				// TODO: handle exception
				exception.printStackTrace();
			}
			endFlag=true;
			try {
				Thread.sleep(500);
			} catch (InterruptedException exception) {
				// TODO: handle exception
				exception.printStackTrace();
			}
		}
		else if(monster.blooBar.getValue()==0) {
			allAnnounce.setText("You Win ≧▽≦");
			winFlag=true;
			//等宣言跑完
			try {
				Thread.sleep(2000);
			} catch (InterruptedException exception) {
				// TODO: handle exception
				exception.printStackTrace();
			}
			endFlag=true;
			try {
				Thread.sleep(500);
			} catch (InterruptedException exception) {
				// TODO: handle exception
				exception.printStackTrace();
			}
		}
	}
	public boolean isOver() {
		return endFlag;
	}
	public int getMoney() {
		if(winFlag)
			return monster.money;
		else {
			return 0;
		}
	}
}
