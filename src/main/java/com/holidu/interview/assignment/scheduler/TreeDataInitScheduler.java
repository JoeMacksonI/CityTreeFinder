package com.holidu.interview.assignment.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.node.ObjectNode;

@Configuration
@EnableScheduling
public class TreeDataInitScheduler implements CommandLineRunner {

	private static final Logger logger = LoggerFactory.getLogger(TreeDataInitScheduler.class);

	private RestTemplate restTemplate;

	private ObjectNode[] apiResponseData;

	@Value("${api.cityofnewyork.url}")
	private String apiEndpoint;

	@Autowired
	public TreeDataInitScheduler(RestTemplate restTemplate) {
		super();
		this.restTemplate = restTemplate;
	}

	public ObjectNode[] getApiResponseData() {
		return apiResponseData;
	}

	/**
	 * Fetch data from rest endpoint
	 * 
	 * @return
	 */
	private ObjectNode[] treeData() {
		apiResponseData = restTemplate.getForObject(apiEndpoint, ObjectNode[].class);
		logger.debug("Api response data size: " + apiResponseData.length);
		return apiResponseData;
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		scheduledJob();
	}

	@Scheduled(cron = "0 0 9 * * *")
	public void scheduledJob() throws Exception {
		treeData();
	}

}
