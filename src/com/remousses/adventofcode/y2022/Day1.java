package com.remousses.adventofcode.y2022;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Day1 extends AbstractDay {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		new Day1().readCode("/2022/day1.txt");
	}
	
	@Override
	protected void writeCode(final BufferedReader br) throws NumberFormatException, IOException {
		String line;
        String elf = "Elf"; 
        Map<String, Integer> fileRes = new HashMap<>();
        int i = 1;
        while ((line = br.readLine()) != null) {
            if (line.isEmpty()) {
            	i++;
	            fileRes.put(elf + i, 0);
            	continue;
            }
            Integer calories = fileRes.get(elf + i);
            if (calories == null) {
            	calories = 0;
            }
            calories += Integer.valueOf(line);
        	fileRes.put(elf + i, calories);
        }
        fileRes.entrySet().stream()
	       .sorted(Map.Entry.comparingByValue())
	       .skip(fileRes.values().size() - 3)
	       .map(data -> data.getValue())
	       .collect(Collectors.toList())
	       .forEach(System.out::println);
	}
}
