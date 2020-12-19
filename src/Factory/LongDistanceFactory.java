package Factory;

public class LongDistanceFactory extends AbstractFactory{
    public HighLevel createHighLevel(){
        return new HLongDistance();
    }
    public LowLevel createLowLevel(){
        return new LLongDistance();
    }
}
class HLongDistance extends HighLevel{
    public HLongDistance(){
        super();
        this.setStatus("wizard",30,100,100,10);
    }
}
class LLongDistance extends LowLevel{
    public LLongDistance() {
        super();
        this.setStatus("archer",10,100,100,10);
    }
}