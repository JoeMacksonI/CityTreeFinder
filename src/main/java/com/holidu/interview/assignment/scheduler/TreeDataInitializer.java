package com.holidu.interview.assignment.scheduler;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.node.ObjectNode;

@Component
public class TreeDataInitializer implements CommandLineRunner, Runnable {
	
	private static final Logger logger = LoggerFactory.getLogger(TreeDataInitializer.class);
	
	private RestTemplate restTemplate;
	
	public static ObjectNode[] apiResponseData;
	
    @Value("${api.cityofnewyork.url}")
    private String apiEndpoint;
    
    @Value("${job.schedule.days}")
    private Integer jobDaysDelay;
	
	@Autowired
	public TreeDataInitializer(RestTemplate restTemplate) {
		super();
		this.restTemplate = restTemplate;
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		sceduledJob();
	}
	
	
	public void sceduledJob() {
		ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
		service.scheduleWithFixedDelay(TreeDataInitializer.this, 0, jobDaysDelay, TimeUnit.DAYS);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		treeData();
	}
	
	private ObjectNode[] treeData() {
		apiResponseData = restTemplate.getForObject(apiEndpoint, ObjectNode[].class);
		logger.debug("Api response data size: "+ apiResponseData.length);
		return apiResponseData;
	}

}
