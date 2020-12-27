package Items;

public abstract class Item {
    protected int price;
    protected double addition;
    protected String name;

    public Item() {

    }

    Item(String n, int p) {
        this.name = n;
        this.price = p;
    }

    public abstract int getPrice();

    public abstract double getAddition();

    public abstract String getName();
}
