package ocd.iramuteq.hachoir.service;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public interface HachoirService {

	int write(FileInputStream 	inputFile
			, FileOutputStream 	outputFile);

}
