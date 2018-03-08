package com.cll.crawler;

import org.junit.Test;

/**
 * @author chenliangliang
 * @date 2018/1/2
 */
public class TestReflect {

    @Test
    public void test1(){
        // 当前类(class)所在的包目录
        //结果：file:/E:/ideaWorkSpace/crawler/target/test-classes/com/cll/crawler/
        System.out.println(TestReflect.class.getResource(""));

        // class path根目录
        //结果：file:/E:/ideaWorkSpace/crawler/target/test-classes/
        System.out.println(TestReflect.class.getResource("/"));

        // TestReflect.class在target/test-classes/com.cll.crawler包中
        // 2.properties  在target/test-classes/com.cll.crawler包中
        //结果：file:/E:/ideaWorkSpace/crawler/target/test-classes/com/cll/crawler/a.txt
        System.out.println(TestReflect.class.getResource("a.txt"));

        System.out.println(TestReflect.class.getResource("reflect/b.txt"));

        System.out.println(CrawlerApplication.class.getClassLoader().getResource(""));

        System.out.println(Thread.currentThread().getContextClassLoader().getResource(""));
    }
}
