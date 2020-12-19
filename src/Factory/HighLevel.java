package Factory;

import main.*;

public abstract class HighLevel {
    private Monster monster;

    public HighLevel(String name) {
        monster = new Monster(name);
    }

    protected void setStatus(int a, int b, int m, int p) {
        this.monster.setStatus(a, b, m, p);
    }

    public Monster getMonster() {
        return this.monster;
    }
}
