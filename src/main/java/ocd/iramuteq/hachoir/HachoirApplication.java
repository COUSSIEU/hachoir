package ocd.iramuteq.hachoir;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import ocd.iramuteq.hachoir.service.FirstHachoirService;
import ocd.iramuteq.hachoir.service.HachoirService;

@SpringBootApplication
public class HachoirApplication {
    private static Logger LOG = LoggerFactory.getLogger(HachoirApplication.class);

	public static void main(String[] args) {
        LOG.info("STARTING THE APPLICATION");
		SpringApplication.run(HachoirApplication.class, args);
        LOG.info("APPLICATION FINISHED");
	}

	@Bean 
	public HachoirService hachoirService() {
        LOG.info("CREATING THE BEAN");
		return 	 new FirstHachoirService();
	}

}
