package tuna.lang.vm;

public class Cons implements Thing{
    public Object first;
    public Object rest;

    @Override
    public String toString() {
	String result = "(";
	Cons current = this;
	while (true) {
	    result += current.first.toString();

	    if (current.rest instanceof Cons) {
		result += " ";
		current = (Cons) current.rest;
	    }else if(current.rest == null){
		return result + ")";
	    }else{
		return result + " . " + current.rest.toString() + ")";
	    }
	}

    }
}
