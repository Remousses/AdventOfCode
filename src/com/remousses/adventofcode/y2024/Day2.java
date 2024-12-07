package com.remousses.adventofcode.y2024;

import com.remousses.adventofcode.AbstractDay;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Day2 extends AbstractDay {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		new Day2().readCode("/2024/day2.txt");
	}
	
	@Override
	protected void writeCode(final BufferedReader br) throws NumberFormatException, IOException {
		String line;
		Integer total = 0;
        while ((line = br.readLine()) != null) {
            if (line.isEmpty()) {
            	break;
            }
			String[] splitLine = line.split(" ");

			boolean isSafe = isSafeReport(splitLine);

			if (isSafe) {
				total++;
			}
        }

		System.out.println("total : " + total);
	}

	private static boolean isSafeReport(String[] splitLine) {
		boolean isSafe = true;
		for (int i = 0; i < splitLine.length; i++) {
			if (i == splitLine.length - 1) {
				break;
			}
			Integer currentLevel = Integer.parseInt(splitLine[i]);
			Integer nextLevel = Integer.parseInt(splitLine[i + 1]);
			Integer gap = Math.abs(currentLevel - nextLevel);
			if (currentLevel.equals(nextLevel) || !(gap >= 0 && gap <= 3)) {
				isSafe = false;
				break;
			} else {
				if (i > 0){
					Integer previousLevel = Integer.parseInt(splitLine[i - 1]);

					if (!((previousLevel < currentLevel && currentLevel < nextLevel) || (nextLevel < currentLevel && currentLevel < previousLevel))) {
						isSafe = false;
						break;
					}
				}
			}
		}
		return isSafe;
	}
}
