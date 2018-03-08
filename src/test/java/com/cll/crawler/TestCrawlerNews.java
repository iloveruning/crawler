package com.cll.crawler;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * @author chenliangliang
 * @date 2018/3/7
 */
public class TestCrawlerNews {







    class News implements PageProcessor{



        @Override
        public void process(Page page) {
            

        }

        @Override
        public Site getSite() {
            return Site.me()
                    .setRetrySleepTime(100)
                    .setSleepTime(100)
                    .setTimeOut(5000)
                    .setCharset("UTF-8")
                    .setUserAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/39.0.2171.95 Safari/537.36 MicroMessenger/6.5.2.501 NetType/WIFI WindowsWechat QBCore/3.43.691.400 QQBrowser/9.0.2524.400");
        }
    }
}
