package com.holidu.interview.assignment.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class CacheEvictScheduler {
	
	private static final Logger logger = LoggerFactory.getLogger(CacheEvictScheduler.class);
	
	@CacheEvict(allEntries = true, value = "treesCache")
	@Scheduled(cron = "0 55 8 * * *")
	public void scheduledJob() {
		logger.debug("Flush trees Cache ");
	}
}
