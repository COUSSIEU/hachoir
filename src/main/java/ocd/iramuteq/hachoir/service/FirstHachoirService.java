package ocd.iramuteq.hachoir.service;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import ocd.iramuteq.hachoir.HachoirCommandLineRunner;

@Service
public class FirstHachoirService implements HachoirService {

    private static Logger LOG = LoggerFactory.getLogger(FirstHachoirService.class);
	public static final int BUFFER_LEN = 1024;
	
	@Override
	public int write(String source, String destination) throws IOException {
		System.out.println("@@@ Hachoir. started");
		int count = 0;
		try (
				InputStream is = 
					new BufferedInputStream(Files.newInputStream(Paths.get(source)));
				OutputStream os = 
					new BufferedOutputStream(Files.newOutputStream(Paths.get(destination)))
				)
			{
				byte[] buffer = new byte[BUFFER_LEN];
				for (int i = is.read(buffer); i >= 0; i = is.read(buffer)) {
					count += hacher(buffer, os);					
				}
			}
		return count;
	}

	protected int hacher(byte[] buffer, OutputStream os) {
		
	    String contenu = new String(buffer, StandardCharsets.UTF_8);
		LOG.info(contenu);
		return 0;
	}

}
