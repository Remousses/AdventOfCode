package com.remousses.adventofcode.y2024;

import com.remousses.adventofcode.AbstractDay;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day3 extends AbstractDay {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		new Day3().readCode("/2024/day3.txt");
	}
	
	@Override
	protected void writeCode(final BufferedReader br) throws NumberFormatException, IOException {
		String line;
		Integer total = 0;
		Pattern mulPattern = Pattern.compile("mul\\((\\d+),(\\d+)\\)");
        while ((line = br.readLine()) != null) {
            if (line.isEmpty()) {
            	break;
            }
			Matcher mulMatcher = mulPattern.matcher(line);
			// Find all matches and multiply the numbers
			while (mulMatcher.find()) {
				// Extract the two numbers from the match
				int firstNum = Integer.parseInt(mulMatcher.group(1));
				int secondNum = Integer.parseInt(mulMatcher.group(2));

				// Multiply the numbers and add to the sum
				total += firstNum * secondNum;
			}
        }


		System.out.println("total : " + total);
	}
}
