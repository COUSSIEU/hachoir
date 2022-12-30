package ocd.iramuteq.hachoir.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.StringWriter;
import org.junit.jupiter.api.Test;

public class PhraseTest {

    @Test
    public void testAdd() throws IOException {
        StringWriter sw = new StringWriter();
        BufferedWriter bw = new BufferedWriter(sw);
        Phrase phrase = new Phrase(bw);

        // Test avec une ligne vide
        int output = phrase.add("");
        assertEquals(0, output);

        // Test avec une ligne qui ne contient pas de point ni d'exclamation
        output = phrase.add("Ceci est une phrase simple");
        assertEquals(0, output);
        assertEquals("Ceci est une phrase simple", sw.toString());

        // Test avec une ligne qui contient un point
        sw = new StringWriter();
        bw = new BufferedWriter(sw);
        phrase = new Phrase(bw);
        output = phrase.add("Ceci est une phrase simple.");
        assertEquals(1, output);
        assertEquals("Ceci est une phrase simple.", sw.toString());

        // Test avec une ligne qui contient une exclamation
        sw = new StringWriter();
        bw = new BufferedWriter(sw);
        phrase = new Phrase(bw);
        output = phrase.add("Ceci est une phrase simple!");
        assertEquals(1, output);
        assertEquals("Ceci est une phrase simple!", sw.toString());

        // Test avec une ligne qui contient plusieurs phrases
        sw = new StringWriter();
        bw = new BufferedWriter(sw);
        phrase = new Phrase(bw);
        output = phrase.add("Ceci est une phrase simple. Cette phrase est la deuxième.");
        assertEquals(2, output);
        assertEquals("Ceci est une phrase simple.\nCette phrase est la deuxième.", sw.toString());
    }
}
