package com.cll.crawler.crawler;

import com.cll.crawler.entity.Job;
import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomNode;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlTableBody;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author chenliangliang
 * @date: 2017/12/11
 */
public class Crawler {


    private WebClient client;

    private final String initUrl;


    public Crawler() {

        client = new WebClient(BrowserVersion.CHROME);
        client.getOptions().setCssEnabled(false);
        client.getOptions().setDownloadImages(false);
        client.getOptions().setJavaScriptEnabled(false);
        client.getOptions().setTimeout(10000);
        client.getOptions().setThrowExceptionOnScriptError(false);
        client.getOptions().setActiveXNative(false);
        client.getOptions().setAppletEnabled(false);

        this.initUrl = "http://gdjy.hfut.edu.cn/products/list/1.html?list=a&page=1&per-page=18";

    }


    public List<Job> run(int pageNum, int size) throws Exception{
        if (pageNum<=0){
            pageNum=1;
        }

        if (size>18){
            size=18;
        }

        List<Job> jobList=new ArrayList<>(24);

        String url = "http://gdjy.hfut.edu.cn/products/list/1.html?list=a&page=" + pageNum + "&per-page="+size;
        HtmlPage page=client.getPage(url);

        HtmlTableBody table = page.getFirstByXPath("//*[@id=\"w0\"]/table/tbody");

        Iterable<DomNode> children = table.getChildren();


        children.forEach(it -> {

            NamedNodeMap map = it.getAttributes();
            Node key = map.getNamedItem("data-key");
            if (key != null) {
                String id = key.getNodeValue();
                String urll = "http://gdjy.hfut.edu.cn/products/" + id + ".html";

                System.out.println(urll);
                Iterator<DomNode> ch = it.getChildren().iterator();
                    Job job = new Job();
                    int temp = 0;
                    while (ch.hasNext()) {
                        String text = ch.next().getTextContent();
                        switch (temp) {
                            case 0:
                                job.setCompany(text);
                                break;
                            case 1:
                                job.setTime(text);
                                break;
                            case 2:
                                job.setPlace(text);
                                break;
                            case 3:
                                job.setClick(text);
                                break;
                            default:
                                break;
                        }
                        ++temp;
                    }
                    job.setId(id);

                    System.out.println("=========================");
                try {
                    Thread.sleep(500);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }

            }


        });




        return jobList;
    }






    public void close() {
        if (client != null) {
            client.close();
        }
    }


}
