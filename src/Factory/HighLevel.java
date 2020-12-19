package Factory;
import main.*;
public abstract class HighLevel{
    private Status status;
    public HighLevel(){
    }
    protected void setStatus(String name,int a,int b,int m,int p) {
    	this.status=new Status(name);
    	this.status.setStatus(a,b,m,p);
    }
    public Status getStatus() {
    	return this.status;
    }
}
