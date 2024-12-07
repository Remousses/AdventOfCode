package com.remousses.adventofcode.y2024;

import com.remousses.adventofcode.AbstractDay;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Day1Part2 extends AbstractDay {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		new Day1Part2().readCode("/2024/day1.txt");
	}
	
	@Override
	protected void writeCode(final BufferedReader br) throws NumberFormatException, IOException {
		String line;
		Integer total = 0;
		List<Integer> leftList = new ArrayList<>();
		List<Integer> rightList = new ArrayList<>();
        while ((line = br.readLine()) != null) {
            if (line.isEmpty()) {
            	break;
            }
			String[] splitLine = line.split("   ");
			Integer leftNumber = Integer.parseInt(splitLine[0]);
			Integer rightNumber = Integer.parseInt(splitLine[1]);

			leftList.add(leftNumber);
			rightList.add(rightNumber);
        }

		for (Integer leftNumber : leftList) {
			total += leftNumber * Collections.frequency(rightList, leftNumber);
		}

		System.out.println("total : " + total);
	}
}
