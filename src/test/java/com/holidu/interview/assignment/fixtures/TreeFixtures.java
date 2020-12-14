package com.holidu.interview.assignment.fixtures;

import java.util.HashMap;
import java.util.Map;

import com.holidu.interview.assignment.domain.TreeCoordinates;

public class TreeFixtures {

	public static Map<String, Long> result() {
		Map<String, Long> map = new HashMap<String, Long>();
		map.put("Maple", 5L);
		return map;
	}

	public static final TreeCoordinates tree1 = new TreeCoordinates(101d, "Red Maple", 10d, 20d);
	public static final TreeCoordinates tree2 = new TreeCoordinates(102d, "Red Maple", 30d, 60d);

}
