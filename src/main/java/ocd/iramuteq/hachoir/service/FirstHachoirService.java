package ocd.iramuteq.hachoir.service;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

import org.springframework.stereotype.Service;

@Service
public class FirstHachoirService implements HachoirService {
	
	@Override
	public int write(String source, String destination) throws IOException {
		System.out.println("@@@ Hachoir. started");
		int count = 0;
        try (FileReader reader = new FileReader(source);
             BufferedReader br = new BufferedReader(reader);
        	 FileWriter writer = new FileWriter(destination);
             BufferedWriter bw = new BufferedWriter(writer);
            ) {
        	Phrase phrase = new Phrase(bw);
               // read line by line
        	String line;
            while ((line = br.readLine()) != null) {
            	phrase.add(line);
		    }
           } catch (IOException e) {
               System.err.format("IOException: %s%n", e);
           }

		return count;
	}



}
