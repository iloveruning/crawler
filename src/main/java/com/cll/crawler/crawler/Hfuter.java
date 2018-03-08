package com.cll.crawler.crawler;

import org.apache.commons.httpclient.Header;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * @author chenliangliang
 * @date: 2017/12/12
 */
public class Hfuter implements PageProcessor {



    private Site site;

    public Hfuter() {
        site = Site.me()
                .setRetrySleepTime(100)
                .setSleepTime(600)
                .setTimeOut(5000)

                .addCookie("SESSION", "e6aad394-490d-4929-a5d2-3a0d0704614e")
                //不变
                .addCookie("_gscu_1417998747", "04197356f20z6135")
                //不变
                .addCookie("Hm_lvt_703ac6a264dd3fdbeb458d71c429c1a1", "1504588185")
                //不变
                .addCookie("lzstat_uv", "6918996042373318569|A0015")
                //不变
                .addCookie("UM_distinctid","15fc43e19d7cc-089684709463fa-6450712f-144000-15fc43e19d874e")

                .addCookie("iPlanetDirectoryPro","AQIC5wM2LY4Sfcyfl7nf3ls3lnZdYfAv6WBuGbm0UUl%2FN20%3D%40AAJTSQACMDE%3D%23")
                //不变
                .addCookie("SRVID","s114")
                .setUserAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/39.0.2171.95 Safari/537.36 MicroMessenger/6.5.2.501 NetType/WIFI WindowsWechat QBCore/3.43.691.400 QQBrowser/9.0.2524.400");


    }

    public Hfuter(String iPlanetDirectoryPro, String session) {
        site = Site.me()
                .setRetrySleepTime(100)
                .setSleepTime(600)
                .setTimeOut(5000)
                .addCookie("SESSION", session)
                //不变
                .addCookie("_gscu_1417998747", "04197356f20z6135")
                //不变
                .addCookie("Hm_lvt_703ac6a264dd3fdbeb458d71c429c1a1", "1504588185")
                //不变
                .addCookie("lzstat_uv", "6918996042373318569|A0015")
                //不变
                .addCookie("UM_distinctid","15fc43e19d7cc-089684709463fa-6450712f-144000-15fc43e19d874e")

                .addCookie("iPlanetDirectoryPro",iPlanetDirectoryPro)
                //不变
                .addCookie("SRVID","s110")
                .setUserAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/39.0.2171.95 Safari/537.36 MicroMessenger/6.5.2.501 NetType/WIFI WindowsWechat QBCore/3.43.691.400 QQBrowser/9.0.2524.400");

    }

    public Hfuter(@NotNull Header[] headers) {
        String[] str1=StringUtils.split(headers[0].getValue(),";");
        String session=str1[1].substring(9);
        System.out.println("SESSION: "+session);

        String[] str2=StringUtils.split(headers[1].getValue(),";");
        String iPlanetDirectoryPro=str2[1].substring(21);
        System.out.println("iPlanetDirectoryPro: "+iPlanetDirectoryPro);

       /* String[] str3=StringUtils.split(headers[2].getValue(),";");
        String SRVID=str3[1].substring(7);
        System.out.println("SRVID: "+SRVID);*/

        site = Site.me()
                .setRetrySleepTime(100)
                .setSleepTime(600)
                .setTimeOut(5000)

                .addCookie("SESSION", session)
                //不变
                .addCookie("_gscu_1417998747", "04197356f20z6135")
                //不变
                .addCookie("Hm_lvt_703ac6a264dd3fdbeb458d71c429c1a1", "1504588185")
                //不变
                .addCookie("lzstat_uv", "6918996042373318569|A0015")
                //不变
                .addCookie("UM_distinctid","15fc43e19d7cc-089684709463fa-6450712f-144000-15fc43e19d874e")

                .addCookie("iPlanetDirectoryPro",iPlanetDirectoryPro)
                //不变
                .addCookie("SRVID","s115")
                .setUserAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/39.0.2171.95 Safari/537.36 MicroMessenger/6.5.2.501 NetType/WIFI WindowsWechat QBCore/3.43.691.400 QQBrowser/9.0.2524.400");

    }

    @Override
    public void process(Page page) {



        //Map<String,List<String>> map=page.getHeaders();


        //System.out.println(page.getStatusCode());
        //System.out.println(page.getHtml());

        String s=page.getHtml().xpath("/html/body/div/div[1]/div[2]/table/tbody/tr[1]/td[1]").get();
        int  size=page.getHtml().getDocument().getElementsByClass("row").size();
        page.getHtml().getDocument().getElementsByClass("row").iterator().next();




        System.out.println(size);

    }

    @Override
    public Site getSite() {
        return site;
    }
}
