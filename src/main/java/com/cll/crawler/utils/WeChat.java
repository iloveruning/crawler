package com.cll.crawler.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cll.crawler.entity.Article;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chenliangliang
 * @date: 2017/12/5
 */
public class WeChat implements PageProcessor {


    private JSONObject json;

    private static final String WXUIN="1571895134";

    private Site site = Site.me()
            .setRetrySleepTime(10)
            .setSleepTime(1000)
            .setTimeOut(5000)
            .addCookie("wxtokenkey", "48994c404ba255d3347a4df324f1f42a51ebf92cdac40be1692106cd4fe5b245")
            //不变
            .addCookie("wxuin", WXUIN)
            .addCookie("pass_ticket", "MrxUJtYuP7OHrE7Br9q+VDOw6ZEp89S6DmvOBBa1UOhH80ceSdlqPNft7734+zf/")
            .addCookie("wap_sid2", "CN7uxO0FElxuTmpGZ1NlcUNWcG1WdW9IWWpsSHo5bnhXOXJRdE9vTHhac2x6TWVFdU9rOWJsM3VPcDJRUXc3WUJDWUhNNjJZREtWTGhISW42NVFQNGFadXRrVmJncVlEQUFBfjDZvrTRBTgMQJRO")
            .setUserAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/39.0.2171.95 Safari/537.36 MicroMessenger/6.5.2.501 NetType/WIFI WindowsWechat QBCore/3.43.691.400 QQBrowser/9.0.2524.400");


    @Override
    public void process(Page page) {
        String str = page.getJson().get();
        System.out.println(str);

        json = JSONObject.parseObject(str);


    }


    public JSONObject getJson() {
        return json;
    }


    public Integer getContinue() {
        return json.getInteger("can_msg_continue");
    }

    public Integer getOffset() {
        return json.getInteger("next_offset");
    }


    public List<Article> getArticles() {
        List<Article> articles = new ArrayList<>(10);

        JSONArray list = json.getJSONObject("general_msg_list").getJSONArray("list");

        JSONObject object;
        Article article;
        for (int i = 0; i < list.size(); i++) {
            object = list.getJSONObject(i).getJSONObject("app_msg_ext_info");
           if (object!=null){
               article = new Article();
               article.setAuthor(object.getString("author"));
               article.setTitle(object.getString("title"));
               article.setCover(object.getString("cover"));
               article.setUrl(object.getString("content_url"));
               article.setDigest(object.getString("digest"));
               article.setFileid(object.getInteger("fileid"));
               articles.add(article);
           }

        }

        return articles;
    }




    @Override
    public Site getSite() {
        return this.site;
    }


}
