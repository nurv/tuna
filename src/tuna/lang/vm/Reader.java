package tuna.lang.vm;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.PushbackReader;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import sun.security.util.BigInt;

public class Reader {
    private final PushbackReader inputstream;
    private final Context context;

    public Reader(InputStream stream, Context context) {
	inputstream = new PushbackReader(new LineNumberReader(new InputStreamReader(stream)));
	this.context = context;
    }

    private int nextChar() throws IOException {
	int character = inputstream.read();

	while (Character.isWhitespace(character)) {
	    character = inputstream.read();
	}
	return character;
    }

    public int character() throws IOException {
	return inputstream.read();
    }

    public void unread(int character) throws IOException {
	inputstream.unread(character);
    }

    public Cons readList() throws Exception {
	Cons firstCell = new Cons();
	Cons cell = firstCell;
	while (true) {
	    cell.first = read();
	    int character = nextChar();

	    switch (character) {

	    case ')':
		cell.rest = null;
		return firstCell;

	    case '.':
		cell.rest = read();
		character = nextChar();
		if (character != ')') {
		    throw new Exception("Ilegal char ");
		}
		return firstCell;

	    default:
		unread(character);
		cell.rest = new Cons();
		cell = (Cons) cell.rest;

	    }
	}

    }

    static Pattern intPat = Pattern
	    .compile("[0-9]+");

    private static Object matchNumber(String s) {
	Matcher m = intPat.matcher(s);
	if(m.matches()){
	    return Integer.valueOf(s);
	}else{
	    return null;
	}
    }

    public Object readSymbol() throws IOException {
	String result = "";
	int character = character();

	while (!Character.isWhitespace(character) && character != ')') {
	    result += ((char) character);
	    character = character();
	}
	unread(character);

	Object m = matchNumber(result);
	if(m!=null){
	    return m;
	}else{
	    return Symbol.intern(result, context);
	}
    }

    public Object read() throws Exception {
	int character = nextChar();

	switch (character) {
	case '(':
	    return readList();
	case ')':
	    return null;
	default:
	    unread(character);
	    return readSymbol();
	}
    }
}
