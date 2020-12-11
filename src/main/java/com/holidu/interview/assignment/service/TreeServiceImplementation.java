package com.holidu.interview.assignment.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.holidu.interview.assignment.domain.TreeCoordinates;
import com.holidu.interview.assignment.exception.DataParseException;
import com.holidu.interview.assignment.exception.HoliduGeneralException;
import com.holidu.interview.assignment.scheduler.TreeDataInitializer;
import com.holidu.interview.assignment.utility.NullKeySerializer;

@Service
public class TreeServiceImplementation implements TreeService{
	
	@Autowired
    protected ObjectMapper objectMapper;

    private static final double METERS_TO_FEET = 0.305;

    private Set<TreeCoordinates> getAllTrees() throws DataParseException {
    	
        try {
        	ObjectNode[] objNode = TreeDataInitializer.apiResponseData;
        	objectMapper.getSerializerProvider().setNullKeySerializer(new NullKeySerializer());
            return objectMapper.convertValue(objNode, new TypeReference<Set<TreeCoordinates>>() {
            });
        } catch (IllegalArgumentException e) {
            throw new DataParseException(e.getMessage());
        }
    }
	
	@Override
	public Map<String, Long> getTreeDataByCircle(Double x, Double y, Double radius) throws DataParseException, HoliduGeneralException{
		// TODO Auto-generated method stub
		Map<String, Long> resultFiltered = new HashMap<String, Long>();
		Set<TreeCoordinates> treeSet = getAllTrees();
		
		try {	
		double radiusSquare = Math.pow(metersToFeet(radius), 2);
		
		treeSet.stream().filter(tree ->
        (Math.pow(tree.getX() - x, 2) + Math.pow(tree.getY() - y, 2)) <= radiusSquare).
			forEach(tree -> resultFiltered.put(tree.getTreeName(),
					resultFiltered.getOrDefault(tree.getTreeName(), 0L)+1));

		} catch(Exception e) {
			throw new HoliduGeneralException(e.getMessage());
		}
			
		return resultFiltered;
		
	}
	
    private double metersToFeet(double meters){
        return meters * METERS_TO_FEET;
    }

}
