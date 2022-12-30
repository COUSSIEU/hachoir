/**
 * 
 */
package ocd.iramuteq.hachoir.service;

import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.StringWriter;

import org.junit.jupiter.api.Test;

/**
 * @author olivi
 *
 */
class PhraseTest {

	private final StringWriter 		output 			= new StringWriter();
	private final BufferedWriter 	bufferedWriter 	= new BufferedWriter(output);
	private final Phrase 			sut 			= new Phrase(bufferedWriter);


	/**
	 * Test method for {@link ocd.iramuteq.hachoir.service.Phrase#add(java.lang.String)}.
	 * @throws IOException 
	 */
	@Test
	void testAdd() throws IOException {
		String line = null;
		int 	count 		= sut.add(line);
		String 	expected 	= "zz";// LATINOS;
		String 	obtained 	= output.toString();
		assertEquals(expected, obtained);
	}

	/**
	 * Test method for {@link ocd.iramuteq.hachoir.service.Phrase#compterPhrases(java.lang.String, java.lang.String)}.
	 */
	@Test
	void testCompterPhrases() {
		String line 	= "1.2.3";
		String splitter = "\\.";
		int expected 	= 3;
		int obtained	= sut.compterPhrases(line, splitter);
		assertEquals(expected, obtained);
	}

	/**
	 * Test method for {@link ocd.iramuteq.hachoir.service.Phrase#monoPhrase(java.lang.String)}.
	 * @throws IOException 
	 */
	@Test
	void testMonoPhrase() throws IOException {
		String line 	= null;
		int expected 	= 1;
		int obtained	= sut.monoPhrase(line);
		assertEquals(expected, obtained);
	}

	/**
	 * Test method for {@link ocd.iramuteq.hachoir.service.Phrase#multiPhrases(char)}.
	 * @throws IOException 
	 */
	@Test
	void testMultiPhrases() throws IOException {
		char terminateur = 0;
		int expected 	= 0;// LATINOS;
		int obtained	= sut.multiPhrases(terminateur);
		assertEquals(expected, obtained);
	}

	/**
	 * Test method for {@link ocd.iramuteq.hachoir.service.Phrase#stocker(java.lang.String)}.
	 * @throws IOException 
	 */
	@Test
	void testStocker() throws IOException {
		String phrasePasFinie 	= "pas finie";
		sut.stocker(phrasePasFinie);
		int expected 	= 1;
		int obtained	= sut.ecrire();
		assertEquals(expected, obtained);
		assertEquals(phrasePasFinie, output.toString());
	}

	/**
	 * Test method for {@link ocd.iramuteq.hachoir.service.Phrase#isPhraseInachevee(int)}.
	 * @throws IOException 
	 */
	@Test
	void testIsPhraseInachevee() throws IOException {
		String phrasePasFinie 	= "Finie. PAS FINIE";
		sut.add(phrasePasFinie);
		boolean obtained	= sut.isPhraseInachevee(0);
		assertTrue(obtained);
	}

	/**
	 * Test method for {@link ocd.iramuteq.hachoir.service.Phrase#ecrireComplet(java.lang.String, char)}.
	 * @throws IOException 
	 */
	@Test
	void testEcrireComplet() throws IOException {
		String line 	= null;
		int expected 	= 1;// LATINOS;
		int obtained	= sut.ecrireComplet(line, 's');
		assertEquals(expected, obtained);
	}

	/**
	 * Test method for {@link ocd.iramuteq.hachoir.service.Phrase#ecrire()}.
	 * @throws IOException 
	 */
	@Test
	void testEcrire() throws IOException {
		int expected 	= 1;
		int obtained	= sut.ecrire();
		assertEquals(expected, obtained);
	}

}
