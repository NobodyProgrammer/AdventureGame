package Factory;

import main.Status;

public abstract class LowLevel{
    private Status status;
    public LowLevel(){
    }
    protected void setStatus(String name,int a,int b,int m,int p) {
    	this.status=new Status(name);
    	this.status.setStatus(a,b,m,p);
    }
    public Status getStatus() {
    	return this.status;
    }
}
