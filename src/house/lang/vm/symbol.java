package house.lang.vm;

public class symbol {
    public final String name;
    public symbol(String name) {
	this.name = name;
    }
    
    @Override
    public String toString() {
        return this.name;
    }
}
