package com.holidu.interview.assignment.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.holidu.interview.assignment.domain.TreeCoordinates;
import com.holidu.interview.assignment.exception.DataParseException;
import com.holidu.interview.assignment.exception.HoliduGeneralException;
import com.holidu.interview.assignment.scheduler.TreeDataInitScheduler;
import com.holidu.interview.assignment.utility.NullKeySerializer;

@Service
public class TreeServiceImplementation implements TreeService {

	private static final Logger logger = LoggerFactory.getLogger(TreeServiceImplementation.class);

	protected ObjectMapper objectMapper;
	protected TreeDataInitScheduler treeDataInitScheduler;

	@Autowired
	public TreeServiceImplementation(ObjectMapper objectMapper, TreeDataInitScheduler treeDataInitScheduler) {
		super();
		this.objectMapper = objectMapper;
		this.treeDataInitScheduler = treeDataInitScheduler;
	}

	private static final double METERS_TO_FEET = 0.305;

	private Set<TreeCoordinates> getAllTrees() throws DataParseException {

		try {
			ObjectNode[] objNode = treeDataInitScheduler.getApiResponseData();
			objectMapper.getSerializerProvider().setNullKeySerializer(new NullKeySerializer());
			return objectMapper.convertValue(objNode, new TypeReference<Set<TreeCoordinates>>() {
			});
		} catch (IllegalArgumentException e) {
			throw new DataParseException(e.getMessage());
		}
	}

	@Override
	@Cacheable(value = "treesCache")
	public Map<String, Long> getTreeDataByCircle(Double x, Double y, Double radius)
			throws DataParseException, HoliduGeneralException {
		// TODO Auto-generated method stub
		Map<String, Long> resultFiltered = new HashMap<String, Long>();
		Set<TreeCoordinates> treeSet = getAllTrees();
		logger.debug("Data taken from Tree Data Initializer");
		try {
			double radiusSquare = Math.pow(metersToFeet(radius), 2);

			treeSet.stream().filter(getTreeInRadiusPredicate(x, y, radiusSquare)).forEach(tree -> resultFiltered
					.put(tree.getTreeName(), resultFiltered.getOrDefault(tree.getTreeName(), 0L) + 1));

		} catch (Exception e) {
			logger.debug("Exception in get Tree data by Circle");
			throw new HoliduGeneralException(e.getMessage());
		}

		return resultFiltered;

	}

	private Predicate<TreeCoordinates> getTreeInRadiusPredicate(Double x, Double y, double radiusSquare) {
		return tree -> (Math.pow(tree.getX() - x, 2) + Math.pow(tree.getY() - y, 2)) <= radiusSquare;
	}

	private double metersToFeet(double meters) {
		return meters * METERS_TO_FEET;
	}

}
