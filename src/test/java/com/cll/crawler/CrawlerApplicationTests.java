package com.cll.crawler;

import com.cll.crawler.config.UserInfo;
import com.cll.crawler.crawler.Hfuter;
import com.cll.crawler.crawler.OCR;
import com.cll.crawler.dao.ArticleRepository;
import com.cll.crawler.dao.JobRepository;
import com.cll.crawler.mapper.JobMapper;
import com.cll.crawler.utils.WeChat;
import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebResponse;
import com.gargoylesoftware.htmlunit.html.*;
import com.gargoylesoftware.htmlunit.util.Cookie;
import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import us.codecraft.webmagic.Spider;

import javax.imageio.ImageReader;
import java.awt.image.BufferedImage;
import java.util.Iterator;
import java.util.Set;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class CrawlerApplicationTests {

    @Autowired
    private ArticleRepository repository;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private JobMapper jobMapper;

    /**
     * 爬取微信公众号历史文章
     *
     * @throws Exception
     */
    @Test
    public void test() throws Exception {


        String offset;
        String url0 = "https://mp.weixin.qq.com/mp/profile_ext?action=getmsg&__biz=MzA4MDQ4NzUwMA==&f=json" +
                "&offset=0" +
                "&count=10&is_ok=1&scene=124&uin=MTU3MTg5NTEzNA%3D%3D&key=1c2aaa2ef4c83e3a4678cf16b5cf2b10dfc90241821500a3a798152246f3127ab287445c3a01fda09683f8ee5a5aaa7ed52fc9e328c945be1a02f885321a4726b0c0ac6f95c958de150e618fc55ad38e&pass_ticket=MrxUJtYuP7OHrE7Br9q%2BVDOw6ZEp89S6DmvOBBa1UOhH80ceSdlqPNft7734%2Bzf%2F&wxtoken=&appmsg_token=934_BVBbdIemoeeYuVEF8ry__T55C4GD3QVAsQlk8A~~&x5=0&f=json";
        WeChat weChat = new WeChat();
        Spider spider = Spider.create(weChat);
        spider.addUrl(url0).run();
        offset = weChat.getOffset().toString();
        String url;
        Integer canContinue = 1;
        boolean flag = true;
        while (flag) {

            url = "https://mp.weixin.qq.com/mp/profile_ext?action=getmsg&__biz=MzA4MDQ4NzUwMA==&f=json" +
                    "&offset=" + offset +
                    "&count=10&is_ok=1&scene=124&uin=MTU3MTg5NTEzNA%3D%3D&key=1c2aaa2ef4c83e3a4678cf16b5cf2b10dfc90241821500a3a798152246f3127ab287445c3a01fda09683f8ee5a5aaa7ed52fc9e328c945be1a02f885321a4726b0c0ac6f95c958de150e618fc55ad38e&pass_ticket=MrxUJtYuP7OHrE7Br9q%2BVDOw6ZEp89S6DmvOBBa1UOhH80ceSdlqPNft7734%2Bzf%2F&wxtoken=&appmsg_token=934_BVBbdIemoeeYuVEF8ry__T55C4GD3QVAsQlk8A~~&x5=0&f=json";
            spider.addUrl(url).run();
            offset = weChat.getOffset().toString();
            canContinue = weChat.getContinue();
            System.out.println("offset:  " + offset);
            //repository.save(weChat.getArticles());
            Thread.sleep(600);


            if (canContinue != 1) {
                spider.addUrl(url).run();
                repository.save(weChat.getArticles());
                flag = false;
            }
        }
    }



    @Test
    public void test1(){
        Spider.create(new Hfuter("AQIC5wM2LY4SfcxsRXWgRzN3qb3e4tS80DKh3YwgryChmFc%3D%40AAJTSQACMDE%3D%23","3f749c86-8d19-4c7e-b245-38fb6d89506a"))
                .addUrl("http://jxglstu.hfut.edu.cn/eams5-student/for-std/grade/sheet/info/85589?semester=")
                .run();
    }


    @Test
    public void test2()throws Exception{
        WebClient client = new WebClient(BrowserVersion.CHROME);
        client.getOptions().setCssEnabled(false);
        client.getOptions().setDownloadImages(false);
        client.getOptions().setJavaScriptEnabled(true);
        client.getOptions().setTimeout(10000);
        client.getOptions().setThrowExceptionOnScriptError(false);
        client.getOptions().setActiveXNative(false);
        client.getOptions().setAppletEnabled(false);
        client.waitForBackgroundJavaScript(600 * 1000);
        client.getOptions().setRedirectEnabled(true);
        client.getCookieManager().setCookiesEnabled(true);
        client.getCookieManager().addCookie(new Cookie("http://jxglstu.hfut.edu.cn","_gscu_1417998747", "04197356f20z6135"));
        client.getCookieManager().addCookie(new Cookie("http://jxglstu.hfut.edu.cn","Hm_lvt_703ac6a264dd3fdbeb458d71c429c1a1", "1504588185"));
        client.getCookieManager().addCookie(new Cookie("http://jxglstu.hfut.edu.cn","lzstat_uv", "6918996042373318569|A0015"));
        client.getCookieManager().addCookie(new Cookie("http://jxglstu.hfut.edu.cn","UM_distinctid","15fc43e19d7cc-089684709463fa-6450712f-144000-15fc43e19d874e"));
        client.getCookieManager().addCookie(new Cookie("http://jxglstu.hfut.edu.cn","iPlanetDirectoryPro","AQIC5wM2LY4SfcxR%2FChUXXcFHmFQzanbrv8AsyZ8HxrhCTc%3D%40AAJTSQACMDE%3D%23"));
        client.getCookieManager().addCookie(new Cookie("http://jxglstu.hfut.edu.cn","SRVID","s110"));


        HtmlPage page=client.getPage("http://jxglstu.hfut.edu.cn/eams5-student/for-std/grade/sheet");

        System.out.println(page.asText());

        WebResponse response=page.getWebResponse();

        String location=response.getResponseHeaderValue("Location");

        System.out.println(location);
    }










    /**
     * 爬取-合肥工业大学就业信息网-宣讲会数据
     *
     * @throws Exception
     */
    @Test
    public void test7() throws Exception {
        WebClient client = new WebClient(BrowserVersion.CHROME);
        client.getOptions().setCssEnabled(false);
        client.getOptions().setDownloadImages(false);
        client.getOptions().setJavaScriptEnabled(false);
        client.getOptions().setTimeout(10000);
        client.getOptions().setThrowExceptionOnScriptError(false);
        client.getOptions().setActiveXNative(false);
        client.getOptions().setAppletEnabled(false);
        client.waitForBackgroundJavaScript(600 * 1000);

        String url0 = "http://gdjy.hfut.edu.cn/products/list/1.html?list=a&page=1&per-page=18";

        HtmlPage page = client.getPage(url0);

        HtmlElement element = page.getFirstByXPath("//*[@id=\"w0\"]/div/b[2]");
        String total = element.getTextContent();
        int all = Integer.parseInt(total);

        int pageNum = all / 18 + 1;

        for (int i = 1; i <= pageNum; i++) {


            String url = "http://gdjy.hfut.edu.cn/products/list/1.html?list=a&page=" + i + "&per-page=18";
            if (i != 1) {
                page = client.getPage(url);
            }


            HtmlTableBody table = page.getFirstByXPath("//*[@id=\"w0\"]/table/tbody");

            Iterable<DomNode> children = table.getChildren();


            children.forEach(it -> {

                NamedNodeMap map = it.getAttributes();
                Node key = map.getNamedItem("data-key");
                if (key != null) {
                    String id = key.getNodeValue();
                    String urll = "http://gdjy.hfut.edu.cn/products/" + id + ".html";

                    Flowable.fromCallable(() -> {
                        HtmlPage infoPage = client.getPage(urll);           ///html/body/div/div[2]/div/div/div/div[1]/div[3]
                        HtmlElement ele = infoPage.getFirstByXPath("/html/body/div/div[2]/div/div/div/div[1]/div[3]");
                        String info = ele.asXml();
                        int res = jobMapper.updateInfoById(info, id);
                        if (res == 1) {
                            return id + ".html 保存成功！";
                        } else {
                            return id + ".html 保存失败！";
                        }


                    }).subscribeOn(Schedulers.io())
                            .observeOn(Schedulers.single())
                            .subscribe(System.out::println, Throwable::printStackTrace);

                    System.out.println(urll);
                    //Iterator<DomNode> ch = it.getChildren().iterator();
                    /*Job job = new Job();
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
                    jobRepository.save(job);
                    System.out.println("=========================");*/
                    try {
                        Thread.sleep(500);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }

                }


            });


        }
    }


    @Test
    public void test5() {

        String id = "100";
        String info = "test";
        //jobRepository.updateInfoById(info, id);
        int res = jobMapper.updateInfoById(info, id);
        System.out.println(res == 1);
    }


    @Test
    public void test6() throws Exception{
        WebClient client = new WebClient(BrowserVersion.CHROME);
        client.getOptions().setCssEnabled(false);
        client.getOptions().setDownloadImages(false);
        client.getOptions().setJavaScriptEnabled(true);
        client.getOptions().setTimeout(10000);
        client.getOptions().setThrowExceptionOnScriptError(false);
        client.getOptions().setActiveXNative(false);
        client.getOptions().setAppletEnabled(false);
        client.waitForBackgroundJavaScript(600 * 1000);

        HtmlPage page=client.getPage("http://my.hfut.edu.cn/");

        HtmlForm form=(HtmlForm) page.getElementById("loginForm");
        HtmlTextInput username=form.getInputByName("user");
        HtmlElement pw=form.getInputByName("pwd");
        HtmlTextInput captcha=form.getInputByName("captcha");
        HtmlElement submit=page.getElementByName("btn");
        HtmlImage img=(HtmlImage) page.getElementById("captchaImg");
        ImageReader imageReader = img.getImageReader();
        BufferedImage bufferedImage = imageReader.read(0);

        String code=new OCR().getCode(bufferedImage);

        username.setValueAttribute(UserInfo.UESRNAME);
        pw.type(UserInfo.PASSWORD);
        captcha.setValueAttribute(code);

        HtmlPage indexPage=submit.click();




        //新教务系统-fail
        HtmlElement newEduSystem=indexPage.getFirstByXPath("//*[@id='pf7802']/div/div[2]/table/tbody/tr[2]/td[1]/table/tbody/tr[1]/td/a");
        //HtmlPage newEduSystemPage=newEduSystem.click();
        HtmlPage result=newEduSystem.click();
        //HtmlElement clas=newEduSystemPage.getFirstByXPath("//*[@id='e-op-area']/div/div/div/div/div[4]/div");
        //HtmlPage result=clas.click();

        //图书馆系统
        /*HtmlElement library=indexPage.getFirstByXPath("//*[@id='pf7802']/div/div[2]/table/tbody/tr[2]/td[3]/table/tbody/tr[1]/td/a");

        HtmlPage libPage=library.click();

        HtmlElement currentBorrow=libPage.getFirstByXPath("//*[@id='nav_mylib']/li[2]/a");
        HtmlPage result=currentBorrow.click();*/

//		HtmlTextInput input=(HtmlTextInput) page.getElementById("kw");
//		HtmlElement submit=(HtmlElement) page.getElementById("su");
//
//
//		input.setValueAttribute("java");
//		input.setText("java");
//		page=submit.click();
        //System.out.println(result.asText());
       Set<Cookie> cookieSet=client.getCookieManager().getCookies();
//        String iPlanetDirectoryPro=cookieSet.stream().filter(it->it.getName().equals("iPlanetDirectoryPro"))
////                .findAny().get().getValue();

        String iPlanetDirectoryPro=null;
        String session=null;
        Iterator<Cookie> it=cookieSet.iterator();
        Cookie cookie;
       while (it.hasNext()){
           cookie=it.next();
           if (cookie.getName().equals("iPlanetDirectoryPro")){
               iPlanetDirectoryPro=cookie.getValue();
               continue;
           }

           if (cookie.getName().equals("SESSION")){
               session=cookie.getValue();
           }
       }

       System.out.println("iPlanetDirectoryPro: "+iPlanetDirectoryPro);
        System.out.println("SESSION: "+session);

       String redirectUrl=getRedirectUrl(session,iPlanetDirectoryPro);

        System.out.println(redirectUrl);

        HtmlPage grade=client.getPage(redirectUrl);
       //HtmlPage grade=client.getPage("http://jxglstu.hfut.edu.cn/eams5-student/for-std/course-table/info/85589");

        System.out.println(grade.asText());
    }


    private String getRedirectUrl(String session,String iPlanetDirectoryPro) throws Exception{
        String host="http://jxglstu.hfut.edu.cn";

        OkHttpClient client=new OkHttpClient().newBuilder()
                .followRedirects(false)
                .followSslRedirects(false)
                .build();


        Request request=new Request.Builder()
                .url("http://jxglstu.hfut.edu.cn/eams5-student/for-std/grade/sheet")
                .addHeader("Cookie","SESSION="+session+"; _gscu_1417998747=04197356f20z6135; Hm_lvt_703ac6a264dd3fdbeb458d71c429c1a1=1504588185; lzstat_uv=6918996042373318569|A0015; UM_distinctid=15fc43e19d7cc-089684709463fa-6450712f-144000-15fc43e19d874e; iPlanetDirectoryPro="+iPlanetDirectoryPro+"; SRVID=s114")
                .addHeader("User-Agent","Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/39.0.2171.95 Safari/537.36 MicroMessenger/6.5.2.501 NetType/WIFI WindowsWechat QBCore/3.43.691.400 QQBrowser/9.0.2524.400")
                .build();

        Response response= client.newCall(request).execute();
        System.out.println(response.code());
        String location=response.header("Location");

        System.out.println("Location: "+location);

        return host+location+"?semester=";



    }



}
