package house.lang.vm;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.PushbackReader;

public class Reader {
    private final PushbackReader inputstream;

    public Reader(InputStream stream) {
	inputstream = new PushbackReader(new LineNumberReader(new InputStreamReader(stream)));
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

    public cons readList() throws Exception {
	cons firstCell = new cons();
	cons cell = firstCell;
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
		if (character != ')'){
		    throw new Exception("Ilegal char ");
		}
		return firstCell;
		
	    default:
		unread(character);
		cell.rest = new cons();
		cell = (cons) cell.rest;
		
	    }
	}
	
    }

    public Object readSymbol() throws IOException {
	String result = "";
	int character = character();

	while (!Character.isWhitespace(character) && character != ')') {
	    result += ((char)character);
	    character = character();
	}
	unread(character);

	return new symbol(result);
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
    
    public static void main(String[] args) throws Exception {
	System.out.print(new Reader(System.in).read().toString());
    }
}
