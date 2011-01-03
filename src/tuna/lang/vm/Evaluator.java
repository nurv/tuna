package tuna.lang.vm;

import java.util.ArrayList;

public class Evaluator {
    public final Context context;
    public Evaluator(Context context) {
	this.context = context;
    }
    
    private Object[] listToArray(Cons c){
	Cons current = c;
	ArrayList<Object> data = new ArrayList<Object>();
	while (current != null){
	    data.add(current.first);
	    current = (Cons) current.rest;
	}
	return data.toArray();
    }
    
    public Object eval(Object o) {
	if  (o instanceof Cons){
	    IFn func = (IFn) context.resolve((Symbol)((Cons) o).first);
	    return func.func(listToArray((Cons)((Cons) o).rest))[0];
	}else{
	    return null;
	}
    }
}
