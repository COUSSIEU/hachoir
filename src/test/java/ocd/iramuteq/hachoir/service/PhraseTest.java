package ocd.iramuteq.hachoir.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.StringWriter;
import org.junit.jupiter.api.Test;

public class PhraseTest {

	private static final String INJECTION = " \r\n**** *var1_mod0\r\n";
	StringWriter sw = new StringWriter();
	BufferedWriter bw = new BufferedWriter(sw);
	Phrase phrase = new Phrase(bw);
	
   @Test
    public void testAddEmpty() throws IOException {
        // Test avec une ligne vide
        int output = phrase.add("");
        assertEquals(0, output);


   }
    
    @Test
    public void testAdd_Line() throws IOException {
        final String expected = INJECTION;
        // Test avec une ligne qui ne contient pas de point ni d'exclamation
    	int output = phrase.add("Ceci est une phrase simple");
        assertEquals(0, output);
		assertEquals(expected, sw.toString());
    }
    
    @Test
    public void testAdd_LineWithPoint() throws IOException {
        final String expected =INJECTION+ "Ceci est une phrase simple" + ".";
        // Test avec une ligne qui contient un point
    	int output = phrase.add("Ceci est une phrase simple" + ".");
        assertEquals(1, output);
		assertEquals(expected, sw.toString());
     }
    
    @Test
    public void testAdd_LineWithExclamation() throws IOException {
        final String expected =INJECTION+ "Ceci est une phrase simple" + "!";
        // Test avec une ligne qui contient une exclamation
        int output = phrase.add("Ceci est une phrase simple" + "!");
        assertEquals(1, output);
        assertEquals(expected, sw.toString());
    }

    @Test
    public void testAdd_MultipleLines() throws IOException {
        // Test avec une ligne qui contient plusieurs phrases
        final String expected =INJECTION+ "Ceci est une phrase simple" + ".\n Cette phrase est la deuxième.";
        int output = phrase.add("Ceci est une phrase simple. Cette phrase est la deuxième.");
        assertEquals(2, output);
		assertEquals(expected, sw.toString());
    }

}
