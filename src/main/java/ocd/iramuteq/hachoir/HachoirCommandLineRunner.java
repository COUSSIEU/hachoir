package ocd.iramuteq.hachoir;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import ocd.iramuteq.hachoir.service.HachoirService;

@Component
public class HachoirCommandLineRunner implements CommandLineRunner {
	
    private static Logger LOG = LoggerFactory.getLogger(HachoirCommandLineRunner.class);

	private static String INPUT  = "src/main/resources/La-Porte-du-fond.txt";
	private static String OUTPUT = "src/main/resources/decoupe.txt";

	private final HachoirService hachoirService;
	private String input = INPUT;
	private String output = OUTPUT;

	public HachoirCommandLineRunner(HachoirService hachoirService) {
	    LOG.info("STARTING : command line runner");
		this.hachoirService = hachoirService;
	}

	@Override
	public void run(String... args) throws Exception {
        switch (args.length) {
        	case 2: {
        		input 	= args[0];
        		output 	= args[1];
        		break;
        	}
        	case 1: {
        		input 	= args[0];
        		break;
        	}
        	default:
        }
        LOG.info("EXECUTING : command line runner");
		int count 	= hachoirService.write(input, output);  
		String msg 	= String.format("count = %s", count);
		System.out.println(msg);

	}

}
