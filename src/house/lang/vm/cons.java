package house.lang.vm;

public class cons {
    public Object first;
    public Object rest;

    @Override
    public String toString() {
	String result = "(";
	cons current = this;
	while (true) {
	    result += current.first.toString();

	    if (current.rest instanceof cons) {
		result += " ";
		current = (cons) current.rest;
	    }else if(current.rest == null){
		return result + ")";
	    }else{
		return result + " . " + current.rest.toString() + ")";
	    }
	}

    }
}
