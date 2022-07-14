package ocd.iramuteq.hachoir;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import ocd.iramuteq.hachoir.service.HachoirService;

@Component
public class HachoirCommandLineRunner implements CommandLineRunner {
	
    private static Logger LOG = LoggerFactory.getLogger(HachoirCommandLineRunner.class);

	private static String INPUT  = "src/main/resources/big.xlsx";
	private static String OUTPUT = "src/main/resources/small.xlsx";

	private final HachoirService hachoirService;

	public HachoirCommandLineRunner(HachoirService hachoirService) {
	    LOG.info("STARTING : command line runner");
		this.hachoirService = hachoirService;
	}

	@Override
	public void run(String... args) throws Exception {
		int count = 0;
        LOG.info("EXECUTING : command line runner");
		try {
			final String input = INPUT;
			final String output = OUTPUT;
			FileInputStream 	inputFile 	= new FileInputStream (new File(input) );
			FileOutputStream 	outputFile 	= new FileOutputStream(new File(output));

			count 							= hachoirService.write(inputFile, outputFile);  
			inputFile.close();

			outputFile.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			String msg = String.format("count = %s", count);
			System.out.println(msg);
		}

	}

}
