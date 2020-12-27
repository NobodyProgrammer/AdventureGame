package Items;

public class LevelB extends Item {
    public LevelB(String name, int p) {
        super(name, p);
        this.addition = 1.5;
    }

    public int getPrice() {
        return this.price;
    }

    public double getAddition() {
        return this.addition;
    }

    public String getName() {
        return this.name;
    }
}
