package tuna.lang.vm;

import java.util.HashMap;


public class Namespace {
    public String name;
    private HashMap<Symbol, Bind> binds = new HashMap<Symbol, Bind>();
    
    public Object resolve (Symbol symbol){
	Bind obj = binds.get(symbol);
	if (obj == null || obj.value == null){
	    throw new RuntimeException("The variable '" +  symbol.toString() + "' is unbound.");
	}else{
	    return obj.value;
	}
    }
    
    public void bind (Symbol symbol, Object value){
	binds.put(symbol, new Bind(value));
    }
}
