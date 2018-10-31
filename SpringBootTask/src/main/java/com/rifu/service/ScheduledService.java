package com.rifu.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * @Author Rifu
 * @Date 2018/10/18  15:24
 */
@Service
public class ScheduledService {

    /**
     * second ,minute ,hour ,day of month ,month ,day of week
     */
    @Scheduled(cron = "0 * * * * MON-FRI")
    public void hello(){
        System.out.println("hello...");
    }
}
