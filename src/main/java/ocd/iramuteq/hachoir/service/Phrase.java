package ocd.iramuteq.hachoir.service;

import java.io.BufferedReader;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Phrase {
    private static Logger LOG = LoggerFactory.getLogger(Phrase.class);
	private StringBuffer output = new StringBuffer();
    private String line;

	public void readLineByLine(BufferedReader br) throws IOException {
            while ((line = br.readLine()) != null) {
				 if (line.contains(".")) {
					  point();
				 } else {
				     output.append(line);
				     System.out.println(" ");						  					 
				 }
		    }
	}

	protected void point() {
		final String[] eclats =  line.split("\\.", 0);
		  final int nbEclats = eclats.length;
		  if (nbEclats > 0) {
		      output.append(eclats[0]).append('.'); 
		      ecrire();                		  	
			for (int i = 1; i < nbEclats; i++) {
		          output.append(eclats[i]); 
//			                  System.out.println(output);						  
//			                  output = new StringBuffer();                		  	
			} 
		  } else {
		      output.append(line);
		      ecrire();                		  	
		  }
	}

	protected void ecrire() {
		System.out.println(output);						  
		  output = new StringBuffer();
	}

}
