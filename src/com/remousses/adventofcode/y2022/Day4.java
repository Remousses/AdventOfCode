package com.remousses.adventofcode.y2022;

import com.remousses.adventofcode.AbstractDay;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;

public class Day4 extends AbstractDay {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		new Day4().readCode("/2022/day4.txt");
	}
	
	@Override
	protected void writeCode(final BufferedReader br) throws NumberFormatException, IOException {
		String line;
		int fullyContain = 0;
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
        	if ((t[0][0] <= t[1][0] && t[0][1] >= t[1][1]) || (t[1][0] <= t[0][0] && t[1][1] >= t[0][1])) {
        		fullyContain++;
        	}
        }
//        503
        System.out.println(fullyContain);
	}
	
//	private boolean check() {
//		return t[i][0] <= t[1][0] && t[i][1] >= t[1][1]
//	}
}