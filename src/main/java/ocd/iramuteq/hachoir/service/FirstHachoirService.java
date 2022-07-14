package ocd.iramuteq.hachoir.service;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.springframework.stereotype.Service;

@Service
public class FirstHachoirService implements HachoirService {

	@Override
	public int write(FileInputStream inputFile, FileOutputStream outputFile) {
		System.out.println("@@@ Hachoir. started");
		return 0;
	}

}
