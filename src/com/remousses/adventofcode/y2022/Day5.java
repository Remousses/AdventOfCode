package com.remousses.adventofcode.y2022;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Day5 extends AbstractDay {
	final static Pattern CRATES_PATTERN = Pattern.compile("[A-Za-z0-9_]{1}|\\s{4}");
	final static Pattern CRATES_COLUMN_NUMBER_PATTERN = Pattern.compile("\\d+");

	public static void main(String[] args) throws FileNotFoundException, IOException {
		new Day5().readCode("/2022/day5.txt");
	}
	
	@Override
	protected void writeCode(final BufferedReader br) throws NumberFormatException, IOException {
		String line;
        final Map<Integer, List<String>> initCratesMap = new HashMap<>();
        final Map<Integer, List<String>> customizeCratesMap = new HashMap<>();
        final Map<Integer, List<Integer>> columnToMoveMap = new HashMap<>();
        int i = 1;
        AtomicBoolean isCrateColumn = new AtomicBoolean(false);
        while ((line = br.readLine()) != null) {
            if (line.isEmpty()) {
            	continue;
            }
            
            if (isCrateColumn.get()) {
            	Matcher matcher = CRATES_COLUMN_NUMBER_PATTERN.matcher(line);
            	
            	List<Integer> columnToMove = matcher.results().map(d -> d.group().trim()).map(Integer::valueOf).collect(Collectors.toList());
            	columnToMoveMap.put(i, columnToMove);
            } else {
            	// Manage column
            	Matcher matcher = CRATES_PATTERN.matcher(line);
	            List<String> cratesList = matcher.results().map(d -> d.group().trim()).filter(s -> {
	            	if (s == null) {
	                    return false; 
	                }
	            	Matcher ccnp = CRATES_COLUMN_NUMBER_PATTERN.matcher(s);
					if (ccnp.find()) {
	            		isCrateColumn.set(true);
//	            		customizeCratesMap.put(Integer.valueOf(ccnp.group()), new ArrayList<>());
	            		return false;
	            	}
	            	return true;
	            }).collect(Collectors.toList());
	            
	            if (!isCrateColumn.get()) {
	            	initCratesMap.put(i, cratesList);
	            } else {
	            	i = 0;
	            }
            }
            i++;
        }
        
        // retrieve column order
        initCratesMap.entrySet().stream().forEach(val -> {
    		int key = 1;
    		for (String v : val.getValue()) {
        		List<String> crateList = customizeCratesMap.computeIfAbsent(key, (k) -> new ArrayList<>());
        		if (!v.isBlank()) {
        			crateList.add(0, v);
        		}
        		key++;
    		}
    	});
        
        System.out.println("custom");
        customizeCratesMap.entrySet().forEach((k) -> System.out.println("k : " + k.getKey() + ", v : " + k.getValue()));
        
        // manage movements
        columnToMoveMap.entrySet().forEach(val -> {
        	List<Integer> list = val.getValue();
        	Integer nbCrateToMove = list.get(0);

        	List<String> fromStack = customizeCratesMap.get(list.get(1));
        	List<String> toStack = customizeCratesMap.get(list.get(2));
        	int size = fromStack.size() - 1;
        	for (int e = 0; e < nbCrateToMove; e++) {
    			String crate = fromStack.get(size - e);
    			fromStack.remove(size - e);
        		toStack.add(crate);
        	}
        });
        
        System.out.println("res");
        customizeCratesMap.entrySet().forEach((k) -> System.out.println("k : " + k.getKey() + ", v : " + k.getValue()));
	}
}