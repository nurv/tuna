package tuna.lang.vm;

public class Context {
    public Namespace currentNamespace;
    public Object resolve(Symbol symbol){
	return symbol.ns.resolve(symbol);
    }
}
