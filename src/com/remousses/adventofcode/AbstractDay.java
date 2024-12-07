package com.remousses.adventofcode;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public abstract class AbstractDay {
	
	protected void readCode(final String fileName) throws FileNotFoundException, IOException {
		final File file = new File(AbstractDay.class.getResource(fileName).getFile());
		System.out.println(file);
	    try (FileInputStream inputStream = new FileInputStream(file);
	    	 BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
	    	writeCode(br);
	    }
	}
	
	protected void writeCode(final BufferedReader br) throws NumberFormatException, IOException { }
}
