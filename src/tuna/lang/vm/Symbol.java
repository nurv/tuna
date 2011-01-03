package tuna.lang.vm;

public class Symbol implements Thing {
    String name;
    Namespace ns;

    private Symbol(Namespace ns, String name) {
	this.name = name;
	this.ns = ns;
    }

    static public Symbol intern(String nsname, Context context) {
	int i = nsname.lastIndexOf(':');
	if (i == -1) {
	    return new Symbol(context.currentNamespace, nsname.intern());
	} else {
	    return new Symbol(Runtime.namespaceFor(nsname.substring(0, i).intern()), nsname.substring(i + 1).intern());
	}
    }

    @Override
    public boolean equals(Object obj) {
	return (obj instanceof Symbol) && ((Symbol) obj).name.equals(name) && ((Symbol) obj).ns == ns;
    }
    
    @Override
    public int hashCode() {
	return (this.ns.name + ":" + this.name).hashCode();
    }

    @Override
    public String toString() {
	return this.ns.name + ":" + this.name;
    }
}
