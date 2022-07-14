package ocd.iramuteq.hachoir.service;

import java.io.IOException;

public interface HachoirService {

	int write(String source, String destination) throws IOException;

}
