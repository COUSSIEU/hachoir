package ocd.iramuteq.hachoir.service;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.stereotype.Service;

@Service
public class FirstHachoirService implements HachoirService {

	@Override
	public int write(String source, String destination) throws IOException {
		System.out.println("@@@ Hachoir. started");
		try (
				InputStream is = 
					new BufferedInputStream(Files.newInputStream(Paths.get(source)));
				OutputStream os = 
					new BufferedOutputStream(Files.newOutputStream(Paths.get(destination)))
				)
			{
				is.transferTo(os);
			}
		return 0;
	}

}
