package com.remousses.adventofcode.y2022;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Day3 extends AbstractDay {
	final static int LOWERCASE_SOUS = 96;
	final static int UPPERCASE_SOUS = 38;

	public static void main(String[] args) throws FileNotFoundException, IOException {
		new Day3().readCode("/2022/day3.txt");
	}
	
	@Override
	protected void writeCode(final BufferedReader br) throws NumberFormatException, IOException {
		String line;
        int prioritySum = 0;
        while ((line = br.readLine()) != null) {
            if (line.isEmpty()) {
            	continue;
            }
            
            int lineHalf = line.length() / 2;
            String compartment1 = line.substring(0, lineHalf);
            String compartment2 = line.substring(lineHalf);
            
            boolean found = false;
            for (int i = 0; i < compartment1.length(); i++) {
            	char comp1Char = compartment1.charAt(i);
				for (int j = 0; j < compartment2.length(); j++) {
					if (comp1Char == compartment2.charAt(j)) {
						found = true;
						break;
					}
				}
				if (found) {
					prioritySum += comp1Char - (Character.isLowerCase(comp1Char) ? LOWERCASE_SOUS : UPPERCASE_SOUS);
					break;
				}
			}
        }

        System.out.println(prioritySum);
	}
}