package com.holidu.interview.assignment.rest.controller;

import java.util.Map;

import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.holidu.interview.assignment.exception.DataParseException;
import com.holidu.interview.assignment.exception.HoliduGeneralException;
import com.holidu.interview.assignment.service.TreeService;

@RestController
@RequestMapping(value = "/trees")
public class TreeController {

	@Autowired
	private TreeService treeService;

	@RequestMapping(value = "/points/{x}/{y}/radius/{radius}", method = RequestMethod.GET, produces = {
			"application/json" })
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody Map<String, Long> getTreeCountInArea(@PathVariable("x") @Min(0) Double x,
			@PathVariable("y") @Min(0) Double y, @PathVariable("radius") @Min(0) Double radius)
			throws DataParseException, HoliduGeneralException {
		return treeService.getTreeDataByCircle(x, y, radius);
	}

}
