package Factory;

public class SoilderFactory extends AbstractFactory {
    public HighLevel createHighLevel() {
        return new HSoldier();
    }

    public LowLevel createLowLevel() {
        return new LSoldier();
    }
}

class HSoldier extends HighLevel {
    public HSoldier() {
        super("soilder");
        this.setStatus(30, 100, 100, 10);
        this.setSize(250, 300);
        this.setLoc(700, 250, 4);
    }
}

class LSoldier extends LowLevel {
    public LSoldier() {
        super("pirate");
        this.setSize(250, 300);
        this.setLoc(700, 250, 6);
    }
}