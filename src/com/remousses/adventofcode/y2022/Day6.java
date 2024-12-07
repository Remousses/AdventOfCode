package com.remousses.adventofcode.y2022;

import com.remousses.adventofcode.AbstractDay;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Day6 extends AbstractDay {

	private static final int SIZE_PART_1 = 4;
	private static final int SIZE_PART_2 = 14;

	public static void main(String[] args) throws IOException {
		new Day6().readCode("/2022/day6.txt");
	}
	
	@Override
	protected void writeCode(final BufferedReader br) throws NumberFormatException, IOException {
		String line;
		int markerPos = 0;
        while ((line = br.readLine()) != null) {
            if (line.isEmpty()) {
            	break;
            }
			for (int i = 0; i < line.length(); i++) {
				String toCompare;
				try {
					toCompare = line.substring(i, i + SIZE_PART_2);
				} catch (StringIndexOutOfBoundsException e) {
					System.out.println("line fully compared");
					break;
				}
				Set<Character> chars = new HashSet<>();
				for (int j = 0; j < toCompare.length(); j++) {
					chars.add(toCompare.charAt(j));
				}
				if (chars.size() == SIZE_PART_2) {
					System.out.println("end : " + toCompare);
					System.out.println(markerPos + SIZE_PART_2);
					return;
				}
				// 1876 (part 1)
				// 2202 (part 2)
				markerPos++;
			}
        }
	}
}