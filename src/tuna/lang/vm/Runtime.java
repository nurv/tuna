package tuna.lang.vm;

import java.util.HashMap;

public class Runtime {
    static HashMap<String, Namespace> namespaces;
    static public Namespace namespaceFor(String ns){
	return namespaces.get(ns);
    }
    public static void main(String[] args) throws Exception {
	Namespace ns = new Namespace();
	ns.name = "user";
	Context ctx = new Context();
	ctx.currentNamespace = ns;
	
	ns.bind(Symbol.intern("+", ctx), new IFn() {
	    
	    @Override
	    public Object[] func(Object[] args) {
		return new Object[] { (Integer) args[0] + (Integer) args[1] };
	    }
	});
	
	new Printer(ctx).print(new Evaluator(ctx).eval(new Reader(System.in,ctx).read()));
    }
}
