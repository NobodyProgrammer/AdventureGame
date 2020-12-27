package Items.Decorator;

import Items.Item;
import main.Player;
import main.Monster;

public abstract class Component extends Item {
    protected Item item;

    Component(Item i, String n, int p) {
        this.item = i;
        this.price = p;
        this.name = n;
    }

    public abstract int getPrice();

    public abstract String getName();

    public abstract String characterEffect(Player p, Monster m);

    public abstract double getAddition();

}
