package com.remousses.adventofcode.y2022;

import com.remousses.adventofcode.AbstractDay;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;

public class Day4Part2 extends AbstractDay {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		new Day4Part2().readCode("/2022/day4.txt");
	}
	
	@Override
	protected void writeCode(final BufferedReader br) throws NumberFormatException, IOException {
		String line;
		int contains = 0;
        while ((line = br.readLine()) != null) {
            if (line.isEmpty()) {
            	break;
            }
            String[] pairs = line.split(",");
            int[][] t = new int[2][2];
            for (int i = 0; i < pairs.length; i++) {
            	int[] sectionsId = Arrays.stream(pairs[i].split("-")).mapToInt(Integer::valueOf).toArray();
            	
            	t[i] = sectionsId;
			}
        	if ((t[1][0] <= t[0][0] && t[0][0]<= t[1][1]) || (t[1][0] <= t[0][1] && t[0][1] <= t[1][1])
       			 || (t[0][0] <= t[1][0] && t[1][0] <= t[0][1]) || (t[0][0] <= t[1][1] && t[1][1] <= t[0][1])) {
	       		contains++;
	       	}
        }
//        827
        System.out.println(contains);
	}

	private boolean toCompare(int before, int value, int after) {
		return before <= value && value <= after;
	}
}