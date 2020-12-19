package main;

import java.util.Hashtable;
import java.util.jar.Attributes.Name;

public class Status {

	public int attack;
	public int blood;
	public int exp;
	private int money;
	public Hashtable<String, Integer> skill;
	public int power;
	public String name;

	public Status(String n) {
		this.name = n;
		skill = new Hashtable<String, Integer>();
		skill.put("Normal", 999);
	}

	public void setStatus(int a, int b, int m, int p) {
		this.attack = a;
		this.blood = b;
		this.money = m;
		this.power = p;
	}

	public void updateMoney(int m) {
		this.money += m;
	}

	public int getMoney() {
		return this.money;
	}
}
