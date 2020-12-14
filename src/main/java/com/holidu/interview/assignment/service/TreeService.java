package com.holidu.interview.assignment.service;

import java.util.Map;

import com.holidu.interview.assignment.exception.DataParseException;
import com.holidu.interview.assignment.exception.HoliduGeneralException;

public interface TreeService {

	public Map<String, Long> getTreeDataByCircle(Double x, Double y, Double radius)
			throws DataParseException, HoliduGeneralException;

}
