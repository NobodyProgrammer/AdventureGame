package main;

import java.util.Hashtable;
import java.util.jar.Attributes.Name;

public class Status {

	public int attack;
	public int blood;
	public int exp;
	public int money;
	public Hashtable<String, Integer> skill;
	public int power;
	public String name;

	public Status(String n) {

		this.name = n;
		setStatus();
		skill = new Hashtable<String, Integer>();
		skill.put("Normal", 999);
	}

	private void setStatus() {
		switch (name) {
			case "player":
				attack = 20;
				blood = 0;
				money = 10000000;
				break;
			case "skeleton1":
				attack = 40;
				blood = 100;
				money = 100;
				power = 5;
				break;
			case "skeleton2":
				attack = 20;
				blood = 100;
				money = 50;
				power = 5;
				break;
			case "wizard":
				attack = 30;
				blood = 100;
				money = 50;
				power = 5;
				break;
			//
			case "soilder":
				attack = 50;
				blood = 100;
				money = 100;
				power = 5;
				break;

			//
			case "archer":
				attack = 60;
				blood = 100;
				money = 100;
				power = 10;
				break;
			case "pirate":
				attack = 70;
				blood = 100;
				money = 100;
				power = 5;
				break;
			case "TA":
				attack = 10;
				blood = 10;
				money = 30;
				power = 5;
				break;

			default:
				break;
		}
	}
}
