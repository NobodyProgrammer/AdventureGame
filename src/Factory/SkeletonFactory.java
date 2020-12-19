package Factory;

public class SkeletonFactory extends AbstractFactory {
    public HighLevel createHighLevel() {
        return new HSkeleton();
    }

    public LowLevel createLowLevel() {
        return new LSkeleton();
    }
}

class HSkeleton extends HighLevel {
    public HSkeleton() {
        super("skeleton1");
        this.setStatus(30, 100, 100, 10);
    }
}

class LSkeleton extends LowLevel {
    public LSkeleton() {
        super("skeleton2");
        this.setStatus(10, 100, 100, 10);
    }
}
