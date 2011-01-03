package tuna.lang.vm;

public class Printer {
    
    private final Context context;
    
    public Printer(Context context) {
	this.context = context;
    }
    
    public void print(Object o) {
	System.out.print(o);
    }
}
