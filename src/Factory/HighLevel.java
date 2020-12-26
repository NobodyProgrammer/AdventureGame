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

    protected void setSize(int w, int h) {
        this.monster.setSize(w, h);
    }

    protected void setLoc(int x, int y, int c) {
        this.monster.setLoc(x, y, c);
    }

    public Monster getMonster() {
        return this.monster;
    }
}
