package ocd.iramuteq.hachoir.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.StringWriter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

public class PhraseTest {
	private static final Injections injection = null;
	
    private Phrase phrase;
    private StringWriter sortie;
    private BufferedWriter stockage;
    private int nombreDeLignesProduites;

    
    @BeforeEach
    public void setUp() {
        sortie = new StringWriter();
        stockage = new BufferedWriter(sortie);
        phrase = new Phrase(stockage, injection);
    }

    /**
     * Ajouter une ligne vide ne fait pas progresser le nombre de lignes en sortie.
     * Judicieux ?
     */
    @Test
    @DisplayName ("Test avec une ligne vide")
    public void testAdd_EmptyLine() throws IOException {
       nombreDeLignesProduites = phrase.add("");
       assertEquals(0, nombreDeLignesProduites);
    }

    /**
     * Une ligne non terminée ne produit pas de sortie.
     * Il faut s'assurer qu'elle attend dans le buffer.
     */
    @Test
    @DisplayName ("Test avec une ligne qui ne contient pas de point ni d'exclamation")
    public void testAdd_SimpleLine() throws IOException {
        nombreDeLignesProduites = phrase.add("Ceci est une ligne non terminée");
        assertEquals(0, nombreDeLignesProduites);
        assertEquals("", sortie.toString());
        stockage.flush(); // sortie forcée
        assertEquals("Ceci est une ligne non terminée", sortie.toString());
    }

    /**
     * Une ligne contenant un point se retrouve en sortie jusqu'au point.
     * Elle incrémente le nombre de lignes.
     */
    @Test
    @DisplayName ("Test avec une ligne terminée par un point")
    public void testAdd_LineWithPeriod() throws IOException {
        nombreDeLignesProduites = phrase.add("Ceci est une ligne terminée par un point.");
        assertEquals(1, nombreDeLignesProduites);
        assertEquals("Ceci est une ligne terminée par un point.", sortie.toString());
    }

    /**
     * La ponctuation provoque un retour à la ligne
     * 
     */
    @Test
    @DisplayName ("Test avec une ligne qui contient une exclamation")
    public void testAdd_LineWithExclamation() throws IOException {
        nombreDeLignesProduites = phrase.add("Ceci est une ligne qui contient une exclamation! Et une suite pour la ligne suivante");
        assertEquals(1, nombreDeLignesProduites);
        assertEquals("Ceci est une ligne qui contient une exclamation!", sortie.toString());
        stockage.flush(); // sortie forcée
        assertEquals("Ceci est une ligne qui contient une exclamation!\r\n Et une suite pour la ligne suivante", sortie.toString());
    }

    /**
     * La ponctuation provoque un retour à la ligne
     * 
     */
    @Test
    @DisplayName ("Test avec ligne qui contient plusieurs phrases")
    public void testAdd_MultipleLines() throws IOException {
    	nombreDeLignesProduites = phrase.add("Ceci est une phrase simple. Cette phrase est la deuxième.");
        assertEquals(2, nombreDeLignesProduites);
        assertEquals("Ceci est une phrase simple.\r\n Cette phrase est la deuxième.", sortie.toString());
    }
}
