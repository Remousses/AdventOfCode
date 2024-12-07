package com.remousses.adventofcode.y2024;

import com.remousses.adventofcode.AbstractDay;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Day1 extends AbstractDay {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		new Day1().readCode("/2024/day1.txt");
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
		Collections.sort(leftList);
		Collections.sort(rightList);

		for (int i = 0; i < leftList.size(); i++) {
			total += Math.abs(leftList.get(i) - rightList.get(i));
		}

		System.out.println("total : " + total);
	}
}
