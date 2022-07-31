package ocd.iramuteq.hachoir.service;

import java.io.BufferedWriter;
import java.io.IOException;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

public class Phrase {
    private static final String TEXTE = "**** *var1_mod";

	private static final String PARAGRAPHE = "-*paragraphe_";

//	private static Logger LOG = LoggerFactory.getLogger(Phrase.class);
    
    private String 			line;
    private String[] 		phrases;
    private int 			nbPhrases;
	private BufferedWriter 	output;
	private boolean 		empty = true;
	private int 			compteur = 0;
    
	public Phrase(BufferedWriter bw) {
		this.output = bw;
	}

	public int add(String line) throws IOException {
//        LOG.info("DEB add "+line);
		int nbOutputs = 0;
		if (empty && Character.isUpperCase(line.charAt(0))) {
			monoPhrase(" ");
			if (compteur == 0) {
				monoPhrase(TEXTE+compteur ++);											
			} else {//				
				monoPhrase(PARAGRAPHE+compteur ++);							
			}
		}
		if (line.contains("!")) {
			nbPhrases = compterPhrases(line, "!");
			nbOutputs = nbPhrases > 0 ?  multiPhrases('!') : monoPhrase(line);   
		 } else if (line.contains(".")) {
			nbPhrases = compterPhrases(line, "\\.");
			nbOutputs = nbPhrases > 0 ?  multiPhrases('.') : monoPhrase(line);   
		 } else {
		     stocker(line);
		 }
//        LOG.info("FIN add "+nbOutputs);
		return nbOutputs;
	}

	protected int compterPhrases(String line, String splitter) {
		this.line = line;
		phrases =  line.split(splitter, 0);
		return phrases.length;
	}

	protected int monoPhrase(String line) throws IOException {
		output.append(line);
		return ecrire();
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
		output.append(phrasePasFinie);
		empty = false;
	}

	protected boolean isPhraseInachevee(int i) {
		boolean isDernierePhrase = i == nbPhrases - 1;
		return isDernierePhrase && !line.endsWith(".");
	}

	protected int ecrireComplet(String phrase, char terminateur) throws IOException {
		output.append(phrase).append(terminateur);  
		return ecrire();
	}

	protected int ecrire() throws IOException {
//        LOG.info("MIN ecrire ");
		output.flush();
		output.newLine();
		empty = true;
		return 1;
	}

}
