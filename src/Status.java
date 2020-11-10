import java.util.Hashtable;
import java.util.jar.Attributes.Name;

public class Status {
	
	
	
	public int attack;
	public int blood;
	public int exp;
	public int money;
	public Hashtable<String,Integer> skill;
	public int power;
	public String name;
	Status(String n){

		this.name=n;
		setStatus();
		skill=new Hashtable<String, Integer>();
		skill.put("Normal",999);
	}
	private void setStatus() {
		switch (name) {
		case"player":
			attack=20;
			blood=0;
			money=10000000;
			break;
		case "pein":
			attack=40;
			blood=100;
			money=100;
			power=5;
			break;
			
		//
		case "monster4":
			attack=50;
			blood=100;
			money=100;
			power=5;
			break;
			
		//
		case "monster5":
			attack=60;
			blood=100;
			money=100;
			power=10;
			break;
		
		//小光光
		case "monster6":
			attack=70;
			blood=100;
			money=100;
			power=5;
			break;
		case"TA":
			attack=10;
			blood=10;
			money=30;
			power=5;
			break;
		case "DataStructure":
			attack=20;
			blood=100;
			money=50;
			power=5;		
			break;
		case "ComputerScience":
		//這邊打完所有教授的case
			attack=30;
			blood=100;
			money=50;
			power=5;
			break;
		
		default:
			break;
		}
	}
}

