package com.cll.crawler.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * @author chenliangliang
 * @date: 2017/12/11
 */
@Configuration
@EnableScheduling
public class CrawlerSchedule {


    private final Logger logger= LoggerFactory.getLogger(CrawlerSchedule.class);


    /**
     * 每天中午12点爬取一次
     */
    @Scheduled(cron = "0 0 12 * * ?")
    public void crawler(){
        Thread current = Thread.currentThread();
        System.out.println("开始执行爬虫任务:"+current.getId());
        logger.info("ScheduledTest.executeUploadTask 爬虫任务:"+current.getId() + ",name:"+current.getName());



    }



    /*@Scheduled(cron = "0 0/1 8-20 * * ?")
    public void test(){
        logger.info("测试定时任务");
        System.out.println("测试定时任务");
        // 间隔1分钟,执行工单上传任务
        Thread current = Thread.currentThread();
        System.out.println("定时任务2:"+current.getId());
        logger.info("ScheduledTest.executeUploadTask 定时任务2:"+current.getId() + ",name:"+current.getName());
    }*/

}
