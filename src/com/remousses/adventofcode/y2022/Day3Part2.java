package com.remousses.adventofcode.y2022;

import com.remousses.adventofcode.AbstractDay;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Day3Part2 extends AbstractDay {
	final static int LOWERCASE_SOUS = 96;
	final static int UPPERCASE_SOUS = 38;

	public static void main(String[] args) throws FileNotFoundException, IOException {
		new Day3Part2().readCode("/2022/day3.txt");
	}
	
	@Override
	protected void writeCode(final BufferedReader br) throws NumberFormatException, IOException {
		String line;
        int prioritySum = 0;
        String groupName = "Group";
        int groupId = 1;
        Map<String, List<String>> elfGroup = new HashMap<>();
        while ((line = br.readLine()) != null) {
            if (line.isEmpty()) {
            	continue;
            }
            List<String> group = elfGroup.computeIfAbsent(groupName + groupId, (k) -> new ArrayList<>());
            group.add(line);
            
            if (group.size() == 3) {
            	prioritySum += extracted(group);
            	groupId++;
            }
        }
        System.out.println(prioritySum);
	}

	private int extracted(List<String> group) {
		for (int i = 0; i < group.get(0).length(); i++) {
			char comp1Char = group.get(0).charAt(i);
			Function<String, Boolean> process = (elf) -> {
				for (int j = 0; j < elf.length(); j++) {
					if (comp1Char == elf.charAt(j)) {
						return true;
					}
				}
				return false;
			};
			if (process.apply(group.get(1)) && process.apply(group.get(2))) {
				return comp1Char - (Character.isLowerCase(comp1Char) ? LOWERCASE_SOUS : UPPERCASE_SOUS);
			}
		}
		return 0;
	}
}