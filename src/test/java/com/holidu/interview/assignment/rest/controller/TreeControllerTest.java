package com.holidu.interview.assignment.rest.controller;

import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.holidu.interview.assignment.context.HoliduMvcTest;
import com.holidu.interview.assignment.exception.HoliduGeneralException;
import com.holidu.interview.assignment.fixtures.TreeFixtures;
import com.holidu.interview.assignment.service.TreeService;

@RunWith(SpringRunner.class)
@HoliduMvcTest(TreeController.class)
public class TreeControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private TreeService treeService;

	@Test
	public void expect_tree_data_by_circle() throws Exception {

		when(treeService.getTreeDataByCircle(anyDouble(), anyDouble(), anyDouble())).thenReturn(TreeFixtures.result());
		mockMvc.perform(get("/trees/points/1/2/radius/200")).andExpect(status().isOk())
				.andExpect(jsonPath("$.Maple").value("5"));
	}

	@Test
	public void expect_tree_data_by_circle_negative() throws Exception {

		when(treeService.getTreeDataByCircle(anyDouble(), anyDouble(), anyDouble())).thenReturn(TreeFixtures.result());
		mockMvc.perform(get("/trees/points/-10/-200/radius/2000")).andExpect(status().isOk())
				.andExpect(jsonPath("$.Maple").value("5"));
	}

	@Test
	public void expect_tree_data_by_circle_boundaries() throws Exception {

		when(treeService.getTreeDataByCircle(anyDouble(), anyDouble(), anyDouble())).thenReturn(TreeFixtures.result());
		mockMvc.perform(get("/trees/points/123589654278/-200/radius/2000")).andExpect(status().isOk())
				.andExpect(jsonPath("$.Maple").value("5"));
	}

	@Test
	public void expect_tree_data_by_circle_Exception() throws Exception {

		when(treeService.getTreeDataByCircle(anyDouble(), anyDouble(), anyDouble()))
				.thenThrow(new HoliduGeneralException("Error in processing request."));
		mockMvc.perform(get("/trees/points/1/2/radius/200")).andExpect(status().isInternalServerError())
				.andExpect(status().reason("Error in processing request."));
	}
}
