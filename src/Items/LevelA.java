package Items;

public class LevelA extends Item {
    public LevelA(String name, int p) {
        super(name, p);
        this.addition = 2;
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
