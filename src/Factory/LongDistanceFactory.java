package Factory;

public class LongDistanceFactory extends AbstractFactory {
    public HighLevel createHighLevel() {
        return new HLongDistance();
    }

    public LowLevel createLowLevel() {
        return new LLongDistance();
    }
}

class HLongDistance extends HighLevel {
    public HLongDistance() {
        super("wizard");
        this.setStatus(30, 100, 100, 10);
    }
}

class LLongDistance extends LowLevel {
    public LLongDistance() {
        super("archer");
        this.setStatus(10, 100, 100, 10);
    }
}