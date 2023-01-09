package ocd.iramuteq.hachoir.service;

import java.io.BufferedWriter;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Phrase {
    private static Logger LOG = LoggerFactory.getLogger(Phrase.class);

    private final BufferedWriter 	stockage;
	private final Injections		injection;
    
    private String 			line;
    private String[] 		phrases;
    private int 			nbPhrases;
	private boolean 		empty = true;
    
	public Phrase(BufferedWriter stockage) {
		this(stockage, new Injections());
	}

	public Phrase(BufferedWriter stockage, Injections injection) {
		this.stockage = stockage;
		this.injection = injection;
	}

	public int add(String line) throws IOException {
        LOG.info("DEB add "+line);
		if (line.length() == 0) {
			aLaLigne();	
			return 0;
		} 
		if (this.injection != null && empty) {
			monoPhrase(" ");
			if (Character.isUpperCase(line.charAt(0))) {
				monoPhrase(injection.toString());											
			}
		}
		return process(line);
	}

	protected int process(String line) throws IOException {
		int nbOutputs = 0;
		if (line.contains("!")) {
			nbPhrases = compterPhrases(line, "!");
			nbOutputs = nbPhrases > 0 ?  multiPhrases('!') : monoPhrase(line);   
		 } else if (line.contains(".")) {
			nbPhrases = compterPhrases(line, "\\.");
			nbOutputs = nbPhrases > 0 ?  multiPhrases('.') : monoPhrase(line);   
		 } else {
		     stocker(line);
		 }
		return nbOutputs;
	}

	protected int compterPhrases(String line, String splitter) {
		this.line = line;
		phrases =  line.split(splitter, 0);
		return phrases.length;
	}

	protected int monoPhrase(String line) throws IOException {
		stockage.append(line);
		return aLaLigne();
	}

	protected int multiPhrases(char terminateur) throws IOException {
		int nbOutputs = ecrireComplet(phrases[0], terminateur);                		  	
		for (int i = 1; i < nbPhrases; i++) {
			if (isPhraseInachevee(i)) {
				stocker(phrases[i]);
			} else {
				nbOutputs += ecrireComplet(phrases[i], terminateur);                		  	
			}
		}
		return nbOutputs;
	}

	protected void stocker(String phrasePasFinie) throws IOException {
		stockage.append(phrasePasFinie);
		empty = false;
	}

	protected boolean isPhraseInachevee(int i) {
		boolean isDernierePhrase = i == nbPhrases - 1;
		return isDernierePhrase && !line.endsWith(".");
	}

	protected int ecrireComplet(String phrase, char terminateur) throws IOException {
		stockage.append(phrase).append(terminateur);  
		return aLaLigne();
	}

	protected int aLaLigne() throws IOException {
//        LOG.info("MIN ecrire ");
		stockage.flush();
		stockage.newLine();
		empty = true;
		return 1;
	}

}
