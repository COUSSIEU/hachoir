package ocd.iramuteq.hachoir.service;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;

import org.springframework.stereotype.Service;

@Service
public class FirstHachoirService implements HachoirService {
	
	@Override
	public int write(String source, String destination) throws IOException {
		System.out.println("@@@ Hachoir. started");
		int count = 0;
        try (FileReader reader = new FileReader(source);
                BufferedReader br = new BufferedReader(reader)) {
        	Phrase phrase = new Phrase();
               // read line by line
        	phrase.readLineByLine(br);

           } catch (IOException e) {
               System.err.format("IOException: %s%n", e);
           }

		return count;
	}



}
