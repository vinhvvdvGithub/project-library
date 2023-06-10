package com.practice.projectlibrary.common.Schedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SchedulingTask {
    private static final Logger log = LoggerFactory.getLogger(SchedulingTask.class);

    @Scheduled(fixedRate = 2000)
    public void updateTimeExLoanUser(){
        log.info("Update time ex");
    }

}
