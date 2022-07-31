package ocd.iramuteq.hachoir.service;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class FirstHachoirService implements HachoirService {
	
	private static Logger LOG = LoggerFactory.getLogger(FirstHachoirService.class);

	@Override
	public int write(String source, String destination) throws IOException {
		LOG.info("DEB write ( source="+source+", destination="+destination+")");
		int count = 0;
        try (FileReader reader = new FileReader(source);
             BufferedReader br = new BufferedReader(reader);
        	 FileWriter writer = new FileWriter(destination);
             BufferedWriter bw = new BufferedWriter(writer);
            ) {
        	Phrase phrase = new Phrase(bw);
               // read line by line
        	String line;
            while ((line = br.readLine()) != null) 
            	count += phrase.add(line); 
        } catch (IOException e) {
       		LOG.error("MID write (...) IOException: %s%n", e);
        } finally {
    		LOG.info("FIN write (...) = "+count+" lignes");
        }

		return count;
	}



}
