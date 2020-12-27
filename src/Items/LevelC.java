package Items;

public class LevelC extends Item {
    public LevelC(String name, int p) {
        super(name, p);
        this.addition = 1;
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
