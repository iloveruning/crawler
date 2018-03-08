package com.cll.crawler;

import com.cll.crawler.config.UserInfo;
import com.cll.crawler.crawler.Hfuter;
import com.cll.crawler.crawler.OCR;
import com.cll.crawler.entity.Grade;
import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlPasswordInput;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;
import com.gargoylesoftware.htmlunit.util.Cookie;
import com.xiaoleilu.hutool.http.HttpRequest;
import com.xiaoleilu.hutool.http.HttpResponse;
import okhttp3.*;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.HeadMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpHead;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;
import us.codecraft.webmagic.Spider;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * @author chenliangliang
 * @date: 2017/12/12
 */
public class TestHttp {


    @Test
    public void test1() {

        HttpResponse response = HttpRequest.get("http://jxglstu.hfut.edu.cn/eams5-student/for-std/grade/sheet")
                .cookie("SESSION=f0821f8a-ef3a-4f49-8438-afdc456d542c; _gscu_1417998747=04197356f20z6135; Hm_lvt_703ac6a264dd3fdbeb458d71c429c1a1=1504588185; lzstat_uv=6918996042373318569|A0015; UM_distinctid=15fc43e19d7cc-089684709463fa-6450712f-144000-15fc43e19d874e; SRVID=s110; iPlanetDirectoryPro=AQIC5wM2LY4SfcyHjFXxWxAyfUeQOmqsbyiVpJA%2FqV2mq1Q%3D%40AAJTSQACMDE%3D%23")
                .timeout(10000)
                .execute();

        int status = response.getStatus();
        String uurl = response.header("Location");

        System.out.println(status);
        System.out.println(uurl);
    }


    @Test
    public void test3() {

        HttpResponse response = HttpRequest.post("http://ids1.hfut.edu.cn/amserver/UI/Login")
                .body("IDToken0=&IDToken1=2015213679&IDToken2=cll5022511922&IDButton=Submit&goto=aHR0cDovL2p4Z2xzdHUuaGZ1dC5lZHUuY246ODAvZWFtczUtc3R1ZGVudC93aXNjb20tc3NvL2xvZ2lu&encoded=true&inputCode=&gx_charset=UTF-8")
                //.cookie("SESSION=f0821f8a-ef3a-4f49-8438-afdc456d542c; _gscu_1417998747=04197356f20z6135; Hm_lvt_703ac6a264dd3fdbeb458d71c429c1a1=1504588185; lzstat_uv=6918996042373318569|A0015; UM_distinctid=15fc43e19d7cc-089684709463fa-6450712f-144000-15fc43e19d874e; SRVID=s110; iPlanetDirectoryPro=AQIC5wM2LY4SfcyHjFXxWxAyfUeQOmqsbyiVpJA%2FqV2mq1Q%3D%40AAJTSQACMDE%3D%23")
                .timeout(10000)
                .execute();

        int status = response.getStatus();
        String uurl = response.header("Location");

        System.out.println(status);
        System.out.println(response.body());
    }


    @Test
    public void test2() throws Exception {
        WebClient client = new WebClient(BrowserVersion.CHROME);
        client.getOptions().setCssEnabled(false);
        client.getOptions().setDownloadImages(false);
        client.getOptions().setJavaScriptEnabled(true);
        client.getOptions().setTimeout(10000);
        client.getOptions().setRedirectEnabled(true);
        client.getOptions().setThrowExceptionOnScriptError(false);
        client.getOptions().setActiveXNative(false);
        client.getOptions().setAppletEnabled(false);
        //client.waitForBackgroundJavaScript(600 * 1000);

        HtmlPage page = client.getPage("http://ids1.hfut.edu.cn/amserver/UI/Login?goto=http%3A%2F%2Fjxglstu.hfut.edu.cn%3A80%2Feams5-student%2Fwiscom-sso%2Flogin");


        client.waitForBackgroundJavaScript(2000);

        HtmlTextInput username = page.getHtmlElementById("IDToken1");
        HtmlPasswordInput pw = page.getHtmlElementById("IDToken2");

        username.setValueAttribute("2015213679");
        pw.type("cll5022511922");

        HtmlElement submit = page.getFirstByXPath("/html/body/table/tr[2]/td/table/tbody/tr[3]/td[2]/table/tr[3]/td/table/tr/td[2]/img");

        HtmlPage result = submit.click();

        System.out.println(result.asText());


        Set<Cookie> cookieSet = client.getCookieManager().getCookies();

        cookieSet.forEach(it -> {
            String name = it.getName();
            String value = it.getValue();
            System.out.println("name: " + name + "  value: " + value);
        });


    }


    @Test
    public void test4() throws Exception {

        HttpURLConnection connection = (HttpURLConnection) new URL("").openConnection();
        connection.setInstanceFollowRedirects(false);
        connection.setConnectTimeout(5000);
        String url = connection.getHeaderField("Location");
        System.out.println(url);
    }

    @Test
    public void test5() throws Exception {

        String host = "http://jxglstu.hfut.edu.cn";

        OkHttpClient client = new OkHttpClient().newBuilder()
                .followRedirects(false)
                .followSslRedirects(false)
                .cookieJar(new CookieJar() {
                    List<okhttp3.Cookie> cookies;

                    @Override
                    public void saveFromResponse(HttpUrl httpUrl, List<okhttp3.Cookie> list) {
                        this.cookies = list;
                    }

                    @Override
                    public List<okhttp3.Cookie> loadForRequest(HttpUrl httpUrl) {
                        if (cookies != null) {
                            return cookies;
                        }
                        return new ArrayList<>();
                    }
                })
                .build();


        Request request = new Request.Builder()
                .url("http://jxglstu.hfut.edu.cn/eams5-student/for-std/grade/sheet")
                .addHeader("Cookie", "SESSION=476d7f9d-c31c-49e9-990f-fa0d0ff87086; _gscu_1417998747=04197356f20z6135; Hm_lvt_703ac6a264dd3fdbeb458d71c429c1a1=1504588185; lzstat_uv=6918996042373318569|A0015; UM_distinctid=15fc43e19d7cc-089684709463fa-6450712f-144000-15fc43e19d874e; iPlanetDirectoryPro=AQIC5wM2LY4SfcxR%2FChUXXcFHmFQzanbrv8AsyZ8HxrhCTc%3D%40AAJTSQACMDE%3D%23; SRVID=s114")
                .addHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/39.0.2171.95 Safari/537.36 MicroMessenger/6.5.2.501 NetType/WIFI WindowsWechat QBCore/3.43.691.400 QQBrowser/9.0.2524.400")
                .build();

        Response response = client.newCall(request).execute();
        System.out.println(response.code());
        String location = response.header("Location");

        System.out.println("Location: " + location);

        String redirectUrl = host + location + "?semester=32";


        Request request1 = new Request.Builder()
                .url(redirectUrl)
                .get()
                .addHeader("Cookie", "SESSION=476d7f9d-c31c-49e9-990f-fa0d0ff87086; _gscu_1417998747=04197356f20z6135; Hm_lvt_703ac6a264dd3fdbeb458d71c429c1a1=1504588185; lzstat_uv=6918996042373318569|A0015; UM_distinctid=15fc43e19d7cc-089684709463fa-6450712f-144000-15fc43e19d874e; iPlanetDirectoryPro=AQIC5wM2LY4SfcxR%2FChUXXcFHmFQzanbrv8AsyZ8HxrhCTc%3D%40AAJTSQACMDE%3D%23; SRVID=s114")
                .addHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/39.0.2171.95 Safari/537.36 MicroMessenger/6.5.2.501 NetType/WIFI WindowsWechat QBCore/3.43.691.400 QQBrowser/9.0.2524.400")
                .build();

        Response response1 = client.newCall(request1).execute();

        System.out.println(response1.body().string());

    }


    /**
     * 模拟用户登录
     *
     * @throws Exception
     */
    @Test
    public void test6() throws Exception {

        HttpClient client = new HttpClient();

        //String cookie;

        HttpMethod method = new HeadMethod("http://my.hfut.edu.cn/");

        //HttpParams params= client.getParams();

        //params.setParameter(AllClientPNames.HANDLE_REDIRECTS,false);

        client.executeMethod(method);

        /*Header[] headers=method.getResponseHeaders();
        StringBuilder sb=new StringBuilder();
        Header header;
        for(int i=0;i<headers.length;i++){
            header=headers[i];
            if (StringUtils.equals(header.getName(),"Set-Cookie")){
                String value=header.getValue();
                String[] str=StringUtils.split(value,";");
                sb.append(str[0]);
                if (i!=headers.length-1){
                    sb.append(";");
                }
            }
        }
       cookie=sb.toString();


       System.out.println("cookie: "+cookie);*/

        GetMethod method1 = new GetMethod("http://my.hfut.edu.cn/captchaGenerate.portal?s=0.1265189051288731");
        //method1.addRequestHeader("Cookie",cookie);
        /*Stream.of(headers).filter(header -> header.getName().equals("Set-Cookie"))
                .peek(header -> {
                    String value=header.getValue();
                    String[] str=StringUtils.split(value,";");


                }).forEach(it->System.out.println("name: "+it.getName()+"  value: "+it.getValue()));

*/
        String code = null;
        for (int i = 0; i < 3; i++) {
            client.executeMethod(method1);

            InputStream in = method1.getResponseBodyAsStream();

            BufferedImage image = ImageIO.read(in);
            in.close();
            code = new OCR().getCode(image);
            if (!code.contains("?")) {
                break;
            }
        }


        System.out.println("code: " + code);

        // Header[] headers1 = method1.getRequestHeaders();

        //System.out.println(Arrays.toString(headers1));

        PostMethod method2 = new PostMethod("http://my.hfut.edu.cn/userPasswordValidate.portal");

        method2.setParameter("Login.Token1", UserInfo.UESRNAME);
        method2.setParameter("Login.Token2", UserInfo.PASSWORD);
        method2.setParameter("captchaField", code);
        method2.setParameter("goto", "http%3A%2F%2Fmy.hfut.edu.cn%2FloginSuccess.portal");
        method2.setParameter("gotoOnFail", "http%3A%2F%2Fmy.hfut.edu.cn%2FloginFailure.portal");

        int status = client.executeMethod(method2);

        System.out.println("status: " + status);

        if (status == 200) {

            GetMethod method3=new GetMethod("http://opac.hfut.edu.cn:8080/reader/hwthau.php");

            client.executeMethod(method3);




           /* GetMethod method4=new GetMethod("http://opac.hfut.edu.cn:8080/reader/book_hist.php");

            client.executeMethod(method4);

             InputStream in=method4.getResponseBodyAsStream();
            InputStreamReader input=new InputStreamReader(in,"UTF-8");
            BufferedReader reader=new BufferedReader(input);
            String line;
            while ((line=reader.readLine())!=null){
                System.out.println(line);
            }
*/
           /* DataInputStream dataInputStream=new DataInputStream(method4.getResponseBodyAsStream());

            System.out.println(dataInputStream.readUTF());


            dataInputStream.close();*/
            // System.out.println(method2.getResponseBodyAsString());
            //Header[] headers2 = method2.getResponseHeaders();

            //System.out.println(Arrays.toString(headers2));

            /*GetMethod method3 = new GetMethod("http://my.hfut.edu.cn/index.portal");

            client.executeMethod(method3);*/
            //System.out.println(method3.getResponseBodyAsString());
/*
           //一卡通
            GetMethod method5=new GetMethod("http://pcard.hfut.edu.cn:8080/loginpotaljz.action");
            client.executeMethod(method5);
            //System.out.println(method4.getResponseBodyAsString());

            //流水账
            PostMethod method6=new PostMethod("http://pcard.hfut.edu.cn:8080/gettrjndataList.action");

            *//*method6.setQueryString(new org.apache.commons.httpclient.NameValuePair[]{
                    new org.apache.commons.httpclient.NameValuePair("page","1"),
                    new org.apache.commons.httpclient.NameValuePair("rp","50"),
                    new org.apache.commons.httpclient.NameValuePair("sortname","jndatetime"),
                    new org.apache.commons.httpclient.NameValuePair("sortorder","desc"),
                    new org.apache.commons.httpclient.NameValuePair("query",""),
                    new org.apache.commons.httpclient.NameValuePair("qtype",""),
                    new org.apache.commons.httpclient.NameValuePair("accquary","87722"),
                    new org.apache.commons.httpclient.NameValuePair("trjnquary","7")
            });*//*

            method6.setParameter("page","1");
            method6.setParameter("rp","50");
            method6.setParameter("sortname","jndatetime");
            method6.setParameter("sortorder","desc");
            method6.setParameter("query","");
            method6.setParameter("qtype","");
            method6.setParameter("accquary","87722");
            method6.setParameter("trjnquary","7");

            client.executeMethod(method6);

            System.out.println(method6.getResponseBodyAsString());*/


          /*  //捡获卡信息
            GetMethod method6=new GetMethod("http://pcard.hfut.edu.cn:8080/toNFixcard.action");
            client.executeMethod(method5);
            System.out.println(method5.getResponseBodyAsString());*/


            //新教务系统登录
            HeadMethod method6 = new HeadMethod("http://jxglstu.hfut.edu.cn/eams5-student/wiscom-sso/login");
            client.executeMethod(method6);
            //System.out.println(method6.getResponseBodyAsString());

            //获取成绩
            HeadMethod method7 = new HeadMethod("http://jxglstu.hfut.edu.cn/eams5-student/for-std/grade/sheet");
            method7.setFollowRedirects(false);
            client.executeMethod(method7);

            String value = method7.getResponseHeader("Location").getValue();
            String[] str = StringUtils.split(value, "/");
            String sno = str[str.length - 1];
            System.out.println("Sno: " + sno);
            String redirectUrl = "http://jxglstu.hfut.edu.cn/eams5-student/for-std/grade/sheet/info/" + sno + "?semester=";
            System.out.println(redirectUrl);


            Header[] headers=method7.getRequestHeaders("Cookie");

            String[] str1= StringUtils.split(headers[0].getValue(),";");
            String session=str1[1].substring(9);
            System.out.println("SESSION: "+session);

            String[] str2=StringUtils.split(headers[1].getValue(),";");
            String iPlanetDirectoryPro=str2[1].substring(21);
            System.out.println("iPlanetDirectoryPro: "+iPlanetDirectoryPro);

            Spider.create(new Hfuter(iPlanetDirectoryPro,session))
                    .addUrl("http://jxglstu.hfut.edu.cn/eams5-student/for-std/grade/sheet/info/85589?semester=")
                    .run();
           /* GetMethod method8 = new GetMethod(redirectUrl);
            client.executeMethod(method8);

            String html = method8.getResponseBodyAsString();
            System.out.println(html);
            Document document = Jsoup.parse(html);

            Elements elements = document.getElementsByClass("row");
            int size = elements.size();

            Element element = elements.iterator().next();
            String ss = element.child(1).child(0).child(1).text();

            System.out.println(ss);*/


            //Stream.of(headers).forEach(h->System.out.println("name: "+h.getName()+"  value: "+h.getValue()));

        }


    }


    @Test
    public void testJsoup() {
        String html = "<!DOCTYPE html>\n" +
                "\n" +
                "<html lang=\"zh\" xmlns=\"http://www.w3.org/1999/xhtml\">\n" +
                "<head>\n" +
                "\t<title>学生成绩单</title>\n" +
                "  <meta charset=\"utf-8\" />\n" +
                "  <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\" />\n" +
                "  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\" />\n" +
                "  <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->\n" +
                "  <!--<title layout:title-pattern=\"$DECORATOR_TITLE - $CONTENT_TITLE\" th:text=\"#{system.title}\">教务系统</title>-->\n" +
                "\n" +
                "  <link rel=\"icon\" href=\"/eams5-student/static/favicon.png\" />\n" +
                "  <link rel=\"stylesheet\" href=\"/eams5-student/static/eams-ui/css/eams-ui.css?v=00003\" />\n" +
                "  <link rel=\"stylesheet\" href=\"/eams5-student/static/eams-ui/css/eams-ui-plugin.min.css?v=00003\" />\n" +
                "  <link rel=\"stylesheet\" href=\"/eams5-student/static/helper/css/info-page.css?v=00002\" />\n" +
                "  <link rel=\"stylesheet\" href=\"/eams5-student/static/helper/css/generate-template.css?v=00002\" />\n" +
                "  <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->\n" +
                "  <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->\n" +
                "  <!--[if lt IE 9]>\n" +
                "  <script src=\"/eams5-student/static/eams-ui/js/html5shiv.min.js?v=00002\"></script>\n" +
                "  <script src=\"/eams5-student/static/eams-ui/js/respond.min.js?v=00002\"></script>\n" +
                "  <![endif]-->\n" +
                "\n" +
                "  <script src=\"/eams5-student/static/eams-ui/js/eams-ui.min.js?v=00011\"></script>\n" +
                "  <script src=\"/eams5-student/static/eams-ui/js/eams-ui.zh.min.js?v=00002\"></script>\n" +
                "  <script>\n" +
                "    // 设置 context path, js会需要用\n" +
                "    window.CONTEXT_PATH = '/eams5-student';\n" +
                "    window.LOCALE = 'zh';\n" +
                "  </script>\n" +
                "  <script type=\"text/javascript\" src=\"/eams5-student/static/helper/search-page-helper1.js?v=00003\"></script>\n" +
                "  <script type=\"text/javascript\" src=\"/eams5-student/static/helper/notIndex-page-helper.js?v=00003\"></script>\n" +
                "  <script type=\"text/javascript\" src=\"/eams5-student/static/helper/info-page.js?v=00003\"></script>\n" +
                "  <script type=\"text/javascript\" src=\"/eams5-student/static/helper/student-selectize.js?v=00003\"></script>\n" +
                "  <script type=\"text/javascript\" src=\"/eams5-student/static/helper/datatable-column-tools.js?v=00003\"></script>\n" +
                "  <script>\n" +
                "    /*<![CDATA[*/\n" +
                "    $(function () {\n" +
                "      var infoMessages = null;\n" +
                "      if (!infoMessages) {\n" +
                "        infoMessages = [];\n" +
                "      }\n" +
                "      $.each(infoMessages, function (index, message) {\n" +
                "        new PNotify({\n" +
                "          title: '消息',\n" +
                "          text: message,\n" +
                "          type: 'info',\n" +
                "          delay: 1000\n" +
                "        });\n" +
                "      });\n" +
                "\n" +
                "      var successMessages = null;\n" +
                "      if (!successMessages) {\n" +
                "        successMessages = [];\n" +
                "      }\n" +
                "      $.each(successMessages, function (index, message) {\n" +
                "        new PNotify({\n" +
                "          title: '成功',\n" +
                "          text: message,\n" +
                "          type: 'success',\n" +
                "          delay: 1000\n" +
                "        });\n" +
                "      });\n" +
                "\n" +
                "      var errorMessages = null;\n" +
                "      if (!errorMessages) {\n" +
                "        errorMessages = [];\n" +
                "      }\n" +
                "      $.each(errorMessages, function (index, message) {\n" +
                "        new PNotify({\n" +
                "          title: '错误',\n" +
                "          text: message,\n" +
                "          type: 'error',\n" +
                "          delay: 5000\n" +
                "        });\n" +
                "      });\n" +
                "\n" +
                "      var noticeMessages = null;\n" +
                "      if (!noticeMessages) {\n" +
                "        noticeMessages = [];\n" +
                "      }\n" +
                "      $.each(noticeMessages, function (index, message) {\n" +
                "        new PNotify({\n" +
                "          title: '提示',\n" +
                "          text: message,\n" +
                "          type: 'notice',\n" +
                "          delay: 1000\n" +
                "        });\n" +
                "      });\n" +
                "\n" +
                "      $(document).ajaxError(function (event, request) {\n" +
                "        \n" +
                "        if (request.responseText && request.status == 200 && request.statusText == \"parsererror\") {\n" +
                "          window.location.href = self.location.href;\n" +
                "        }\n" +
                "\n" +
                "        if (!request.responseJSON) {\n" +
                "          return;\n" +
                "        }\n" +
                "\n" +
                "        var texts = [];\n" +
                "        var message = request.responseJSON.message;\n" +
                "        if (request.responseJSON.errors && request.responseJSON.errors.length > 0) {\n" +
                "          message = request.responseJSON.errors[0].defaultMessage;\n" +
                "        }\n" +
                "\n" +
                "        texts.push(\"exception:\\t\" + request.responseJSON.exception);\n" +
                "        texts.push(\"message:\\t\" + message);\n" +
                "        texts.push(\"path:\\t\" + request.responseJSON.path);\n" +
                "        texts.push(\"status:\\t\" + request.responseJSON.status);\n" +
                "        texts.push(\"timestamp:\\t\" + new Date(request.responseJSON.timestamp));\n" +
                "\n" +
                "        new PNotify({\n" +
                "          title: request.responseJSON.error,\n" +
                "          text: texts.join(\"\\n\"),\n" +
                "          type: 'error',\n" +
                "          delay: 50000,\n" +
                "          width: '600px',\n" +
                "          min_height: '150px'\n" +
                "        });\n" +
                "\n" +
                "      });\n" +
                "\n" +
                "    });\n" +
                "\n" +
                "    window.preventFromSubmitTwice = function($form) {\n" +
                "      $form.submit(function() {\n" +
                "        $(this).submit(function() {\n" +
                "          return false;\n" +
                "        });\n" +
                "        return true;\n" +
                "      });\n" +
                "    }\n" +
                "    window.preventFromSubmitTwice($('#save-form'));\n" +
                "\n" +
                "    window.semesterURIUtil = function (originalUri, insertBefore, semesterId) {\n" +
                "      var semesterPattern = \"/semester/\";\n" +
                "      if (originalUri.indexOf(semesterPattern) != -1) {\n" +
                "        // remove semester pattern\n" +
                "        var semesterPatternStartAt = originalUri.indexOf(semesterPattern);\n" +
                "        var semesterPatternEndAt = semesterPatternStartAt +\n" +
                "            originalUri.substring(semesterPatternStartAt, originalUri.length).indexOf(\"/\", semesterPattern.length);\n" +
                "        originalUri = originalUri.substring(0, semesterPatternStartAt) + originalUri.substring(semesterPatternEndAt, originalUri.length);\n" +
                "      }\n" +
                "      var appendIndex = originalUri.indexOf(insertBefore);\n" +
                "      return originalUri.substring(0, appendIndex) + semesterPattern + semesterId + originalUri.substring(appendIndex, originalUri.length);\n" +
                "    };\n" +
                "\n" +
                "\n" +
                "//    eamsUiUtil.cascadeScrollParentWindowToTop(window);\n" +
                "\n" +
                "    /*]]>*/\n" +
                "  </script>\n" +
                "\t<style>\n" +
                "    .student-grade-table > tbody > tr > td {\n" +
                "      vertical-align: middle;\n" +
                "    }\n" +
                "  </style>\n" +
                "</head>\n" +
                "\n" +
                "<body>\n" +
                "<div class=\"container-fluid\">\n" +
                "\n" +
                "  \n" +
                "    <div class=\"row\">\n" +
                "      <div class=\"col-sm-12\">\n" +
                "        <h3>2017-2018学年第一学期</h3>\n" +
                "      </div>\n" +
                "      <div class=\"col-sm-12\">\n" +
                "        <table class=\"student-grade-table table table-bordered table-striped table-hover table-condensed\">\n" +
                "          <thead>\n" +
                "          <tr>\n" +
                "            <th width=\"25%\">课程名称</th>\n" +
                "            <th width=\"15%\">课程代码</th>\n" +
                "            <th width=\"15%\">教学班代码</th>\n" +
                "            <th width=\"6%\">学分</th>\n" +
                "            <th width=\"6%\">绩点</th>\n" +
                "            <th width=\"6%\">成绩</th>\n" +
                "            <th width=\"15%\">成绩明细</th>\n" +
                "          </tr>\n" +
                "          </thead>\n" +
                "          <tbody>\n" +
                "          <tr>\n" +
                "            <td>形势与政策（5）</td>\n" +
                "            <td>1201151B</td>\n" +
                "            <td>1201151B--018</td>\n" +
                "            \n" +
                "              <td>0.25</td>\n" +
                "              <td>4</td>\n" +
                "              \n" +
                "              <td>94</td>\n" +
                "              \n" +
                "              \n" +
                "            \n" +
                "            \n" +
                "            <td>\n" +
                "              平时成绩:100<br />期末考试:99<br />提问及讨论:0\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "          <tr>\n" +
                "            <td>电子商务专业英语</td>\n" +
                "            <td>1150140X</td>\n" +
                "            <td>1150140X--001</td>\n" +
                "            \n" +
                "              <td>2.0</td>\n" +
                "              <td>2.7</td>\n" +
                "              \n" +
                "              <td>77</td>\n" +
                "              \n" +
                "              \n" +
                "            \n" +
                "            \n" +
                "            <td>\n" +
                "              平时成绩:100<br />期末考试:69<br />期中考试:69\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "          </tbody>\n" +
                "        </table>\n" +
                "      </div>\n" +
                "    </div>\n" +
                "  \n" +
                "\n" +
                "  \n" +
                "    <div class=\"row\">\n" +
                "      <div class=\"col-sm-12\">\n" +
                "        <h3>2016-2017学年第二学期</h3>\n" +
                "      </div>\n" +
                "      <div class=\"col-sm-12\">\n" +
                "        <table class=\"student-grade-table table table-bordered table-striped table-hover table-condensed\">\n" +
                "          <thead>\n" +
                "          <tr>\n" +
                "            <th width=\"25%\">课程名称</th>\n" +
                "            <th width=\"15%\">课程代码</th>\n" +
                "            <th width=\"15%\">教学班代码</th>\n" +
                "            <th width=\"6%\">学分</th>\n" +
                "            <th width=\"6%\">绩点</th>\n" +
                "            <th width=\"6%\">成绩</th>\n" +
                "            <th width=\"15%\">成绩明细</th>\n" +
                "          </tr>\n" +
                "          </thead>\n" +
                "          <tbody>\n" +
                "          <tr>\n" +
                "            <td>软件项目管理</td>\n" +
                "            <td>0500115X</td>\n" +
                "            <td>0500115X.0001</td>\n" +
                "            \n" +
                "              <td>1.0</td>\n" +
                "              <td>2</td>\n" +
                "              \n" +
                "              <td>中</td>\n" +
                "              \n" +
                "              \n" +
                "            \n" +
                "            \n" +
                "            <td>\n" +
                "              \n" +
                "            </td>\n" +
                "          </tr>\n" +
                "          <tr>\n" +
                "            <td>大学物理实验（下）</td>\n" +
                "            <td>1000023B</td>\n" +
                "            <td>1000023B.0077</td>\n" +
                "            \n" +
                "              <td>1.0</td>\n" +
                "              <td>4</td>\n" +
                "              \n" +
                "              <td>90</td>\n" +
                "              \n" +
                "              \n" +
                "            \n" +
                "            \n" +
                "            <td>\n" +
                "              \n" +
                "            </td>\n" +
                "          </tr>\n" +
                "          <tr>\n" +
                "            <td>运筹学</td>\n" +
                "            <td>1100012B</td>\n" +
                "            <td>1100012B.0004</td>\n" +
                "            \n" +
                "              <td>4.0</td>\n" +
                "              <td>4.3</td>\n" +
                "              \n" +
                "              <td>95</td>\n" +
                "              \n" +
                "              \n" +
                "            \n" +
                "            \n" +
                "            <td>\n" +
                "              \n" +
                "            </td>\n" +
                "          </tr>\n" +
                "          <tr>\n" +
                "            <td>电子商务概论</td>\n" +
                "            <td>1100132B</td>\n" +
                "            <td>1100132B.0001</td>\n" +
                "            \n" +
                "              <td>2.0</td>\n" +
                "              <td>3.7</td>\n" +
                "              \n" +
                "              <td>86</td>\n" +
                "              \n" +
                "              \n" +
                "            \n" +
                "            \n" +
                "            <td>\n" +
                "              \n" +
                "            </td>\n" +
                "          </tr>\n" +
                "          <tr>\n" +
                "            <td>离散数学</td>\n" +
                "            <td>1150032B</td>\n" +
                "            <td>1150032B.0001</td>\n" +
                "            \n" +
                "              <td>2.0</td>\n" +
                "              <td>4</td>\n" +
                "              \n" +
                "              <td>92</td>\n" +
                "              \n" +
                "              \n" +
                "            \n" +
                "            \n" +
                "            <td>\n" +
                "              \n" +
                "            </td>\n" +
                "          </tr>\n" +
                "          <tr>\n" +
                "            <td>网站建设与管理课程设计</td>\n" +
                "            <td>1150043B</td>\n" +
                "            <td>1150043B.0001</td>\n" +
                "            \n" +
                "              <td>2.0</td>\n" +
                "              <td>3</td>\n" +
                "              \n" +
                "              <td>良</td>\n" +
                "              \n" +
                "              \n" +
                "            \n" +
                "            \n" +
                "            <td>\n" +
                "              \n" +
                "            </td>\n" +
                "          </tr>\n" +
                "          <tr>\n" +
                "            <td>网络技术</td>\n" +
                "            <td>1150202B</td>\n" +
                "            <td>1150202B.0001</td>\n" +
                "            \n" +
                "              <td>3.0</td>\n" +
                "              <td>3.3</td>\n" +
                "              \n" +
                "              <td>84</td>\n" +
                "              \n" +
                "              \n" +
                "            \n" +
                "            \n" +
                "            <td>\n" +
                "              \n" +
                "            </td>\n" +
                "          </tr>\n" +
                "          <tr>\n" +
                "            <td>网站建设与管理</td>\n" +
                "            <td>1150602B</td>\n" +
                "            <td>1150602B.0001</td>\n" +
                "            \n" +
                "              <td>2.0</td>\n" +
                "              <td>4</td>\n" +
                "              \n" +
                "              <td>90</td>\n" +
                "              \n" +
                "              \n" +
                "            \n" +
                "            \n" +
                "            <td>\n" +
                "              \n" +
                "            </td>\n" +
                "          </tr>\n" +
                "          <tr>\n" +
                "            <td>电子商务案例分析</td>\n" +
                "            <td>1150800X</td>\n" +
                "            <td>1150800X.0001</td>\n" +
                "            \n" +
                "              <td>2.0</td>\n" +
                "              <td>3.7</td>\n" +
                "              \n" +
                "              <td>86</td>\n" +
                "              \n" +
                "              \n" +
                "            \n" +
                "            \n" +
                "            <td>\n" +
                "              \n" +
                "            </td>\n" +
                "          </tr>\n" +
                "          <tr>\n" +
                "            <td>移动开发技术</td>\n" +
                "            <td>1151100X</td>\n" +
                "            <td>1151100X.0001</td>\n" +
                "            \n" +
                "              <td>2.0</td>\n" +
                "              <td>4.3</td>\n" +
                "              \n" +
                "              <td>98</td>\n" +
                "              \n" +
                "              \n" +
                "            \n" +
                "            \n" +
                "            <td>\n" +
                "              \n" +
                "            </td>\n" +
                "          </tr>\n" +
                "          <tr>\n" +
                "            <td>XML</td>\n" +
                "            <td>1151400X</td>\n" +
                "            <td>1151400X.0001</td>\n" +
                "            \n" +
                "              <td>2.0</td>\n" +
                "              <td>3</td>\n" +
                "              \n" +
                "              <td>78</td>\n" +
                "              \n" +
                "              \n" +
                "            \n" +
                "            \n" +
                "            <td>\n" +
                "              \n" +
                "            </td>\n" +
                "          </tr>\n" +
                "          <tr>\n" +
                "            <td>中国近现代史纲要</td>\n" +
                "            <td>1200081B</td>\n" +
                "            <td>1200081B.0007</td>\n" +
                "            \n" +
                "              <td>2.5</td>\n" +
                "              <td>3.7</td>\n" +
                "              \n" +
                "              <td>85</td>\n" +
                "              \n" +
                "              \n" +
                "            \n" +
                "            \n" +
                "            <td>\n" +
                "              \n" +
                "            </td>\n" +
                "          </tr>\n" +
                "          <tr>\n" +
                "            <td>社会实践</td>\n" +
                "            <td>1200083B</td>\n" +
                "            <td>1200083B.0004</td>\n" +
                "            \n" +
                "              <td>2.0</td>\n" +
                "              <td>3.7</td>\n" +
                "              \n" +
                "              <td>87</td>\n" +
                "              \n" +
                "              \n" +
                "            \n" +
                "            \n" +
                "            <td>\n" +
                "              \n" +
                "            </td>\n" +
                "          </tr>\n" +
                "          <tr>\n" +
                "            <td>毛泽东思想与中国特色社会主义理论体系概论（下）</td>\n" +
                "            <td>1200151B</td>\n" +
                "            <td>1200151B.0019</td>\n" +
                "            \n" +
                "              <td>2.5</td>\n" +
                "              <td>3.7</td>\n" +
                "              \n" +
                "              <td>86</td>\n" +
                "              \n" +
                "              \n" +
                "            \n" +
                "            \n" +
                "            <td>\n" +
                "              \n" +
                "            </td>\n" +
                "          </tr>\n" +
                "          <tr>\n" +
                "            <td>《梦溪笔谈》导读</td>\n" +
                "            <td>1200512X</td>\n" +
                "            <td>1200512X.0001</td>\n" +
                "            \n" +
                "              <td>1.0</td>\n" +
                "              <td>0</td>\n" +
                "              \n" +
                "              \n" +
                "                <td style=\"color: red;\">13</td>\n" +
                "              \n" +
                "            \n" +
                "            \n" +
                "            <td>\n" +
                "              \n" +
                "            </td>\n" +
                "          </tr>\n" +
                "          <tr>\n" +
                "            <td>形势与政策（4）</td>\n" +
                "            <td>1201141B</td>\n" +
                "            <td>1201141B.0028</td>\n" +
                "            \n" +
                "              <td>0.25</td>\n" +
                "              <td>3.7</td>\n" +
                "              \n" +
                "              <td>89</td>\n" +
                "              \n" +
                "              \n" +
                "            \n" +
                "            \n" +
                "            <td>\n" +
                "              \n" +
                "            </td>\n" +
                "          </tr>\n" +
                "          <tr>\n" +
                "            <td>生物安全</td>\n" +
                "            <td>1300094X</td>\n" +
                "            <td>1300094X.0001</td>\n" +
                "            \n" +
                "              <td>1.0</td>\n" +
                "              <td>4.3</td>\n" +
                "              \n" +
                "              <td>97</td>\n" +
                "              \n" +
                "              \n" +
                "            \n" +
                "            \n" +
                "            <td>\n" +
                "              \n" +
                "            </td>\n" +
                "          </tr>\n" +
                "          <tr>\n" +
                "            <td>大学英语（4）</td>\n" +
                "            <td>1500041B</td>\n" +
                "            <td>1500041B.0070</td>\n" +
                "            \n" +
                "              <td>2.5</td>\n" +
                "              <td>2.7</td>\n" +
                "              \n" +
                "              <td>77</td>\n" +
                "              \n" +
                "              \n" +
                "            \n" +
                "            \n" +
                "            <td>\n" +
                "              \n" +
                "            </td>\n" +
                "          </tr>\n" +
                "          <tr>\n" +
                "            <td>大学体育4</td>\n" +
                "            <td>5100071B</td>\n" +
                "            <td>5100071B.0163</td>\n" +
                "            \n" +
                "              <td>0.75</td>\n" +
                "              <td>3</td>\n" +
                "              \n" +
                "              <td>80</td>\n" +
                "              \n" +
                "              \n" +
                "            \n" +
                "            \n" +
                "            <td>\n" +
                "              \n" +
                "            </td>\n" +
                "          </tr>\n" +
                "          <tr>\n" +
                "            <td>公益活动B（下）</td>\n" +
                "            <td>5700053B</td>\n" +
                "            <td>5700053B.0023</td>\n" +
                "            \n" +
                "              <td>0.0</td>\n" +
                "              <td>3</td>\n" +
                "              \n" +
                "              <td>良</td>\n" +
                "              \n" +
                "              \n" +
                "            \n" +
                "            \n" +
                "            <td>\n" +
                "              \n" +
                "            </td>\n" +
                "          </tr>\n" +
                "          </tbody>\n" +
                "        </table>\n" +
                "      </div>\n" +
                "    </div>\n" +
                "  \n" +
                "\n" +
                "  \n" +
                "    <div class=\"row\">\n" +
                "      <div class=\"col-sm-12\">\n" +
                "        <h3>2016-2017学年第一学期</h3>\n" +
                "      </div>\n" +
                "      <div class=\"col-sm-12\">\n" +
                "        <table class=\"student-grade-table table table-bordered table-striped table-hover table-condensed\">\n" +
                "          <thead>\n" +
                "          <tr>\n" +
                "            <th width=\"25%\">课程名称</th>\n" +
                "            <th width=\"15%\">课程代码</th>\n" +
                "            <th width=\"15%\">教学班代码</th>\n" +
                "            <th width=\"6%\">学分</th>\n" +
                "            <th width=\"6%\">绩点</th>\n" +
                "            <th width=\"6%\">成绩</th>\n" +
                "            <th width=\"15%\">成绩明细</th>\n" +
                "          </tr>\n" +
                "          </thead>\n" +
                "          <tbody>\n" +
                "          <tr>\n" +
                "            <td>环境与健康</td>\n" +
                "            <td>0900025X</td>\n" +
                "            <td>0900025X.0001</td>\n" +
                "            \n" +
                "              <td>1.0</td>\n" +
                "              <td>4.3</td>\n" +
                "              \n" +
                "              <td>95</td>\n" +
                "              \n" +
                "              \n" +
                "            \n" +
                "            \n" +
                "            <td>\n" +
                "              \n" +
                "            </td>\n" +
                "          </tr>\n" +
                "          <tr>\n" +
                "            <td>大学物理实验A（1）</td>\n" +
                "            <td>1000071B</td>\n" +
                "            <td>1000071B.0022</td>\n" +
                "            \n" +
                "              <td>1.0</td>\n" +
                "              <td>4</td>\n" +
                "              \n" +
                "              <td>93</td>\n" +
                "              \n" +
                "              \n" +
                "            \n" +
                "            \n" +
                "            <td>\n" +
                "              \n" +
                "            </td>\n" +
                "          </tr>\n" +
                "          <tr>\n" +
                "            <td>企业管理学</td>\n" +
                "            <td>1100022B</td>\n" +
                "            <td>1100022B.0002</td>\n" +
                "            \n" +
                "              <td>3.0</td>\n" +
                "              <td>2.3</td>\n" +
                "              \n" +
                "              <td>73</td>\n" +
                "              \n" +
                "              \n" +
                "            \n" +
                "            \n" +
                "            <td>\n" +
                "              \n" +
                "            </td>\n" +
                "          </tr>\n" +
                "          <tr>\n" +
                "            <td>JAVA程序设计课程设计</td>\n" +
                "            <td>1150033B</td>\n" +
                "            <td>1150033B.0001</td>\n" +
                "            \n" +
                "              <td>2.0</td>\n" +
                "              <td>3.9</td>\n" +
                "              \n" +
                "              <td>优</td>\n" +
                "              \n" +
                "              \n" +
                "            \n" +
                "            \n" +
                "            <td>\n" +
                "              \n" +
                "            </td>\n" +
                "          </tr>\n" +
                "          <tr>\n" +
                "            <td>消费者行为学</td>\n" +
                "            <td>1150600X</td>\n" +
                "            <td>1150600X.0001</td>\n" +
                "            \n" +
                "              <td>2.0</td>\n" +
                "              <td>3.7</td>\n" +
                "              \n" +
                "              <td>89</td>\n" +
                "              \n" +
                "              \n" +
                "            \n" +
                "            \n" +
                "            <td>\n" +
                "              \n" +
                "            </td>\n" +
                "          </tr>\n" +
                "          <tr>\n" +
                "            <td>网络交易</td>\n" +
                "            <td>1151002B</td>\n" +
                "            <td>1151002B.0001</td>\n" +
                "            \n" +
                "              <td>2.0</td>\n" +
                "              <td>3</td>\n" +
                "              \n" +
                "              <td>81</td>\n" +
                "              \n" +
                "              \n" +
                "            \n" +
                "            \n" +
                "            <td>\n" +
                "              \n" +
                "            </td>\n" +
                "          </tr>\n" +
                "          <tr>\n" +
                "            <td>信息经济学</td>\n" +
                "            <td>1151500X</td>\n" +
                "            <td>1151500X.0001</td>\n" +
                "            \n" +
                "              <td>2.0</td>\n" +
                "              <td>4</td>\n" +
                "              \n" +
                "              <td>90</td>\n" +
                "              \n" +
                "              \n" +
                "            \n" +
                "            \n" +
                "            <td>\n" +
                "              \n" +
                "            </td>\n" +
                "          </tr>\n" +
                "          <tr>\n" +
                "            <td>计量经济学</td>\n" +
                "            <td>1152200X</td>\n" +
                "            <td>1152200X.0001</td>\n" +
                "            \n" +
                "              <td>2.0</td>\n" +
                "              <td>4</td>\n" +
                "              \n" +
                "              <td>90</td>\n" +
                "              \n" +
                "              \n" +
                "            \n" +
                "            \n" +
                "            <td>\n" +
                "              \n" +
                "            </td>\n" +
                "          </tr>\n" +
                "          <tr>\n" +
                "            <td>Java程序设计</td>\n" +
                "            <td>1180382B</td>\n" +
                "            <td>1180382B.0001</td>\n" +
                "            \n" +
                "              <td>2.0</td>\n" +
                "              <td>4</td>\n" +
                "              \n" +
                "              <td>93</td>\n" +
                "              \n" +
                "              \n" +
                "            \n" +
                "            \n" +
                "            <td>\n" +
                "              \n" +
                "            </td>\n" +
                "          </tr>\n" +
                "          <tr>\n" +
                "            <td>马克思主义基本原理概论</td>\n" +
                "            <td>1200021B</td>\n" +
                "            <td>1200021B.0004</td>\n" +
                "            \n" +
                "              <td>3.0</td>\n" +
                "              <td>3</td>\n" +
                "              \n" +
                "              <td>78</td>\n" +
                "              \n" +
                "              \n" +
                "            \n" +
                "            \n" +
                "            <td>\n" +
                "              \n" +
                "            </td>\n" +
                "          </tr>\n" +
                "          <tr>\n" +
                "            <td>毛泽东思想与中国特色社会主义理论体系概论（上）</td>\n" +
                "            <td>1200141B</td>\n" +
                "            <td>1200141B.0024</td>\n" +
                "            \n" +
                "              <td>3.0</td>\n" +
                "              <td>3.3</td>\n" +
                "              \n" +
                "              <td>82</td>\n" +
                "              \n" +
                "              \n" +
                "            \n" +
                "            \n" +
                "            <td>\n" +
                "              \n" +
                "            </td>\n" +
                "          </tr>\n" +
                "          <tr>\n" +
                "            <td>中国历史文化</td>\n" +
                "            <td>1200302X</td>\n" +
                "            <td>1200302X.0003</td>\n" +
                "            \n" +
                "              <td>1.0</td>\n" +
                "              <td>4</td>\n" +
                "              \n" +
                "              <td>90</td>\n" +
                "              \n" +
                "              \n" +
                "            \n" +
                "            \n" +
                "            <td>\n" +
                "              \n" +
                "            </td>\n" +
                "          </tr>\n" +
                "          <tr>\n" +
                "            <td>形势与政策（3）</td>\n" +
                "            <td>1201131B</td>\n" +
                "            <td>1201131B.0026</td>\n" +
                "            \n" +
                "              <td>0.25</td>\n" +
                "              <td>4</td>\n" +
                "              \n" +
                "              <td>92</td>\n" +
                "              \n" +
                "              \n" +
                "            \n" +
                "            \n" +
                "            <td>\n" +
                "              \n" +
                "            </td>\n" +
                "          </tr>\n" +
                "          <tr>\n" +
                "            <td>生活中的毒理学</td>\n" +
                "            <td>1300104X</td>\n" +
                "            <td>1300104X.0001</td>\n" +
                "            \n" +
                "              <td>1.0</td>\n" +
                "              <td>3</td>\n" +
                "              \n" +
                "              <td>良</td>\n" +
                "              \n" +
                "              \n" +
                "            \n" +
                "            \n" +
                "            <td>\n" +
                "              \n" +
                "            </td>\n" +
                "          </tr>\n" +
                "          <tr>\n" +
                "            <td>线性代数</td>\n" +
                "            <td>1400071B</td>\n" +
                "            <td>1400071B.0018</td>\n" +
                "            \n" +
                "              <td>2.5</td>\n" +
                "              <td>4</td>\n" +
                "              \n" +
                "              <td>90</td>\n" +
                "              \n" +
                "              \n" +
                "            \n" +
                "            \n" +
                "            <td>\n" +
                "              \n" +
                "            </td>\n" +
                "          </tr>\n" +
                "          <tr>\n" +
                "            <td>概率论与数理统计</td>\n" +
                "            <td>1400091B</td>\n" +
                "            <td>1400091B.0029</td>\n" +
                "            \n" +
                "              <td>3.0</td>\n" +
                "              <td>4.3</td>\n" +
                "              \n" +
                "              <td>97</td>\n" +
                "              \n" +
                "              \n" +
                "            \n" +
                "            \n" +
                "            <td>\n" +
                "              \n" +
                "            </td>\n" +
                "          </tr>\n" +
                "          <tr>\n" +
                "            <td>大学英语（3）</td>\n" +
                "            <td>1500031B</td>\n" +
                "            <td>1500031B.0070</td>\n" +
                "            \n" +
                "              <td>3.0</td>\n" +
                "              <td>2.7</td>\n" +
                "              \n" +
                "              <td>76</td>\n" +
                "              \n" +
                "              \n" +
                "            \n" +
                "            \n" +
                "            <td>\n" +
                "              \n" +
                "            </td>\n" +
                "          </tr>\n" +
                "          <tr>\n" +
                "            <td>证券市场分析</td>\n" +
                "            <td>1800053X</td>\n" +
                "            <td>1800053X.0001</td>\n" +
                "            \n" +
                "              <td>1.0</td>\n" +
                "              <td>4</td>\n" +
                "              \n" +
                "              <td>94</td>\n" +
                "              \n" +
                "              \n" +
                "            \n" +
                "            \n" +
                "            <td>\n" +
                "              \n" +
                "            </td>\n" +
                "          </tr>\n" +
                "          <tr>\n" +
                "            <td>国际市场营销</td>\n" +
                "            <td>1800073X</td>\n" +
                "            <td>1800073X.0001</td>\n" +
                "            \n" +
                "              <td>1.0</td>\n" +
                "              <td>4.3</td>\n" +
                "              \n" +
                "              <td>98</td>\n" +
                "              \n" +
                "              \n" +
                "            \n" +
                "            \n" +
                "            <td>\n" +
                "              \n" +
                "            </td>\n" +
                "          </tr>\n" +
                "          <tr>\n" +
                "            <td>大学体育3</td>\n" +
                "            <td>5100061B</td>\n" +
                "            <td>5100061B.0161</td>\n" +
                "            \n" +
                "              <td>0.75</td>\n" +
                "              <td>4</td>\n" +
                "              \n" +
                "              <td>90</td>\n" +
                "              \n" +
                "              \n" +
                "            \n" +
                "            \n" +
                "            <td>\n" +
                "              \n" +
                "            </td>\n" +
                "          </tr>\n" +
                "          <tr>\n" +
                "            <td>走进交响音乐的世界</td>\n" +
                "            <td>5202016X</td>\n" +
                "            <td>5202016X.0001</td>\n" +
                "            \n" +
                "              <td>0.5</td>\n" +
                "              <td>4.3</td>\n" +
                "              \n" +
                "              <td>97</td>\n" +
                "              \n" +
                "              \n" +
                "            \n" +
                "            \n" +
                "            <td>\n" +
                "              \n" +
                "            </td>\n" +
                "          </tr>\n" +
                "          <tr>\n" +
                "            <td>公益活动B（上）</td>\n" +
                "            <td>5700043B</td>\n" +
                "            <td>5700043B.0023</td>\n" +
                "            \n" +
                "              <td>0.0</td>\n" +
                "              <td>3</td>\n" +
                "              \n" +
                "              <td>良</td>\n" +
                "              \n" +
                "              \n" +
                "            \n" +
                "            \n" +
                "            <td>\n" +
                "              \n" +
                "            </td>\n" +
                "          </tr>\n" +
                "          <tr>\n" +
                "            <td>大学生朋辈心理辅导</td>\n" +
                "            <td>5701031X</td>\n" +
                "            <td>5701031X.0001</td>\n" +
                "            \n" +
                "              <td>1.0</td>\n" +
                "              <td>3.7</td>\n" +
                "              \n" +
                "              <td>88</td>\n" +
                "              \n" +
                "              \n" +
                "            \n" +
                "            \n" +
                "            <td>\n" +
                "              \n" +
                "            </td>\n" +
                "          </tr>\n" +
                "          </tbody>\n" +
                "        </table>\n" +
                "      </div>\n" +
                "    </div>\n" +
                "  \n" +
                "\n" +
                "  \n" +
                "    <div class=\"row\">\n" +
                "      <div class=\"col-sm-12\">\n" +
                "        <h3>2015-2016学年第二学期</h3>\n" +
                "      </div>\n" +
                "      <div class=\"col-sm-12\">\n" +
                "        <table class=\"student-grade-table table table-bordered table-striped table-hover table-condensed\">\n" +
                "          <thead>\n" +
                "          <tr>\n" +
                "            <th width=\"25%\">课程名称</th>\n" +
                "            <th width=\"15%\">课程代码</th>\n" +
                "            <th width=\"15%\">教学班代码</th>\n" +
                "            <th width=\"6%\">学分</th>\n" +
                "            <th width=\"6%\">绩点</th>\n" +
                "            <th width=\"6%\">成绩</th>\n" +
                "            <th width=\"15%\">成绩明细</th>\n" +
                "          </tr>\n" +
                "          </thead>\n" +
                "          <tbody>\n" +
                "          <tr>\n" +
                "            <td>大学物理C</td>\n" +
                "            <td>1000251B</td>\n" +
                "            <td>1000251B.0008</td>\n" +
                "            \n" +
                "              <td>5.0</td>\n" +
                "              <td>1</td>\n" +
                "              \n" +
                "              <td>及格</td>\n" +
                "              \n" +
                "              \n" +
                "            \n" +
                "            \n" +
                "            <td>\n" +
                "              <br />补考成绩:及格\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "          <tr>\n" +
                "            <td>经济学</td>\n" +
                "            <td>1100062B</td>\n" +
                "            <td>1100062B.0002</td>\n" +
                "            \n" +
                "              <td>3.5</td>\n" +
                "              <td>4.3</td>\n" +
                "              \n" +
                "              <td>95</td>\n" +
                "              \n" +
                "              \n" +
                "            \n" +
                "            \n" +
                "            <td>\n" +
                "              \n" +
                "            </td>\n" +
                "          </tr>\n" +
                "          <tr>\n" +
                "            <td>程序设计语言</td>\n" +
                "            <td>1110521B</td>\n" +
                "            <td>1110521B.0002</td>\n" +
                "            \n" +
                "              <td>2.0</td>\n" +
                "              <td>2.3</td>\n" +
                "              \n" +
                "              <td>73</td>\n" +
                "              \n" +
                "              \n" +
                "            \n" +
                "            \n" +
                "            <td>\n" +
                "              \n" +
                "            </td>\n" +
                "          </tr>\n" +
                "          <tr>\n" +
                "            <td>管理认知实习</td>\n" +
                "            <td>1150013B</td>\n" +
                "            <td>1150013B.0001</td>\n" +
                "            \n" +
                "              <td>1.0</td>\n" +
                "              <td>3.7</td>\n" +
                "              \n" +
                "              <td>89</td>\n" +
                "              \n" +
                "              \n" +
                "            \n" +
                "            \n" +
                "            <td>\n" +
                "              \n" +
                "            </td>\n" +
                "          </tr>\n" +
                "          <tr>\n" +
                "            <td>电子商务综合实践实训</td>\n" +
                "            <td>1150403B</td>\n" +
                "            <td>1150403B.0001</td>\n" +
                "            \n" +
                "              <td>2.0</td>\n" +
                "              <td>3.3</td>\n" +
                "              \n" +
                "              <td>83</td>\n" +
                "              \n" +
                "              \n" +
                "            \n" +
                "            \n" +
                "            <td>\n" +
                "              \n" +
                "            </td>\n" +
                "          </tr>\n" +
                "          <tr>\n" +
                "            <td>形势与政策（2）</td>\n" +
                "            <td>1201121B</td>\n" +
                "            <td>1201121B.0018</td>\n" +
                "            \n" +
                "              <td>0.25</td>\n" +
                "              <td>3.7</td>\n" +
                "              \n" +
                "              <td>85</td>\n" +
                "              \n" +
                "              \n" +
                "            \n" +
                "            \n" +
                "            <td>\n" +
                "              \n" +
                "            </td>\n" +
                "          </tr>\n" +
                "          <tr>\n" +
                "            <td>高等数学A（下）</td>\n" +
                "            <td>1400221B</td>\n" +
                "            <td>1400221B.0036</td>\n" +
                "            \n" +
                "              <td>6.0</td>\n" +
                "              <td>3.3</td>\n" +
                "              \n" +
                "              <td>84</td>\n" +
                "              \n" +
                "              \n" +
                "            \n" +
                "            \n" +
                "            <td>\n" +
                "              \n" +
                "            </td>\n" +
                "          </tr>\n" +
                "          <tr>\n" +
                "            <td>大学英语（2）</td>\n" +
                "            <td>1500021B</td>\n" +
                "            <td>1500021B.0070</td>\n" +
                "            \n" +
                "              <td>3.0</td>\n" +
                "              <td>3</td>\n" +
                "              \n" +
                "              <td>79</td>\n" +
                "              \n" +
                "              \n" +
                "            \n" +
                "            \n" +
                "            <td>\n" +
                "              \n" +
                "            </td>\n" +
                "          </tr>\n" +
                "          <tr>\n" +
                "            <td>大学体育2</td>\n" +
                "            <td>5100051B</td>\n" +
                "            <td>5100051B.0027</td>\n" +
                "            \n" +
                "              <td>0.75</td>\n" +
                "              <td>4</td>\n" +
                "              \n" +
                "              <td>90</td>\n" +
                "              \n" +
                "              \n" +
                "            \n" +
                "            \n" +
                "            <td>\n" +
                "              \n" +
                "            </td>\n" +
                "          </tr>\n" +
                "          <tr>\n" +
                "            <td>大学生心理健康</td>\n" +
                "            <td>5200021B</td>\n" +
                "            <td>5200021B.0008</td>\n" +
                "            \n" +
                "              <td>1.5</td>\n" +
                "              <td>3.3</td>\n" +
                "              \n" +
                "              <td>82</td>\n" +
                "              \n" +
                "              \n" +
                "            \n" +
                "            \n" +
                "            <td>\n" +
                "              \n" +
                "            </td>\n" +
                "          </tr>\n" +
                "          <tr>\n" +
                "            <td>西方电影欣赏</td>\n" +
                "            <td>5201372X</td>\n" +
                "            <td>5201372X.0001</td>\n" +
                "            \n" +
                "              <td>1.0</td>\n" +
                "              <td>3</td>\n" +
                "              \n" +
                "              <td>良</td>\n" +
                "              \n" +
                "              \n" +
                "            \n" +
                "            \n" +
                "            <td>\n" +
                "              \n" +
                "            </td>\n" +
                "          </tr>\n" +
                "          <tr>\n" +
                "            <td>音乐美学</td>\n" +
                "            <td>5201606X</td>\n" +
                "            <td>5201606X.0001</td>\n" +
                "            \n" +
                "              <td>1.0</td>\n" +
                "              <td>3.9</td>\n" +
                "              \n" +
                "              <td>优</td>\n" +
                "              \n" +
                "              \n" +
                "            \n" +
                "            \n" +
                "            <td>\n" +
                "              \n" +
                "            </td>\n" +
                "          </tr>\n" +
                "          <tr>\n" +
                "            <td>工程训练D</td>\n" +
                "            <td>5300043B</td>\n" +
                "            <td>5300043B.0018</td>\n" +
                "            \n" +
                "              <td>1.0</td>\n" +
                "              <td>3.3</td>\n" +
                "              \n" +
                "              <td>83</td>\n" +
                "              \n" +
                "              \n" +
                "            \n" +
                "            \n" +
                "            <td>\n" +
                "              \n" +
                "            </td>\n" +
                "          </tr>\n" +
                "          <tr>\n" +
                "            <td>大学生职业生涯规划</td>\n" +
                "            <td>5600017X</td>\n" +
                "            <td>5600017X.0007</td>\n" +
                "            \n" +
                "              <td>1.0</td>\n" +
                "              <td>4</td>\n" +
                "              \n" +
                "              <td>92</td>\n" +
                "              \n" +
                "              \n" +
                "            \n" +
                "            \n" +
                "            <td>\n" +
                "              \n" +
                "            </td>\n" +
                "          </tr>\n" +
                "          <tr>\n" +
                "            <td>公益活动A（下）</td>\n" +
                "            <td>5700033B</td>\n" +
                "            <td>5700033B.0027</td>\n" +
                "            \n" +
                "              <td>0.0</td>\n" +
                "              <td>3</td>\n" +
                "              \n" +
                "              <td>良</td>\n" +
                "              \n" +
                "              \n" +
                "            \n" +
                "            \n" +
                "            <td>\n" +
                "              \n" +
                "            </td>\n" +
                "          </tr>\n" +
                "          <tr>\n" +
                "            <td>企业形象</td>\n" +
                "            <td>9900153X</td>\n" +
                "            <td>9900153X.0001</td>\n" +
                "            \n" +
                "              <td>1.0</td>\n" +
                "              <td>4</td>\n" +
                "              \n" +
                "              <td>92</td>\n" +
                "              \n" +
                "              \n" +
                "            \n" +
                "            \n" +
                "            <td>\n" +
                "              \n" +
                "            </td>\n" +
                "          </tr>\n" +
                "          </tbody>\n" +
                "        </table>\n" +
                "      </div>\n" +
                "    </div>\n" +
                "  \n" +
                "\n" +
                "  \n" +
                "    <div class=\"row\">\n" +
                "      <div class=\"col-sm-12\">\n" +
                "        <h3>2015-2016学年第一学期</h3>\n" +
                "      </div>\n" +
                "      <div class=\"col-sm-12\">\n" +
                "        <table class=\"student-grade-table table table-bordered table-striped table-hover table-condensed\">\n" +
                "          <thead>\n" +
                "          <tr>\n" +
                "            <th width=\"25%\">课程名称</th>\n" +
                "            <th width=\"15%\">课程代码</th>\n" +
                "            <th width=\"15%\">教学班代码</th>\n" +
                "            <th width=\"6%\">学分</th>\n" +
                "            <th width=\"6%\">绩点</th>\n" +
                "            <th width=\"6%\">成绩</th>\n" +
                "            <th width=\"15%\">成绩明细</th>\n" +
                "          </tr>\n" +
                "          </thead>\n" +
                "          <tbody>\n" +
                "          <tr>\n" +
                "            <td>工程图学C</td>\n" +
                "            <td>0200051B</td>\n" +
                "            <td>0200051B.0010</td>\n" +
                "            \n" +
                "              <td>3.0</td>\n" +
                "              <td>4</td>\n" +
                "              \n" +
                "              <td>94</td>\n" +
                "              \n" +
                "              \n" +
                "            \n" +
                "            \n" +
                "            <td>\n" +
                "              \n" +
                "            </td>\n" +
                "          </tr>\n" +
                "          <tr>\n" +
                "            <td>电子商务专业导论</td>\n" +
                "            <td>1150012B</td>\n" +
                "            <td>1150012B.0001</td>\n" +
                "            \n" +
                "              <td>1.0</td>\n" +
                "              <td>3</td>\n" +
                "              \n" +
                "              <td>良</td>\n" +
                "              \n" +
                "              \n" +
                "            \n" +
                "            \n" +
                "            <td>\n" +
                "              \n" +
                "            </td>\n" +
                "          </tr>\n" +
                "          <tr>\n" +
                "            <td>计算机科学基础</td>\n" +
                "            <td>1150021B</td>\n" +
                "            <td>1150021B.0002</td>\n" +
                "            \n" +
                "              <td>2.0</td>\n" +
                "              <td>3.7</td>\n" +
                "              \n" +
                "              <td>87</td>\n" +
                "              \n" +
                "              \n" +
                "            \n" +
                "            \n" +
                "            <td>\n" +
                "              \n" +
                "            </td>\n" +
                "          </tr>\n" +
                "          <tr>\n" +
                "            <td>思想道德修养与法律基础</td>\n" +
                "            <td>1200051B</td>\n" +
                "            <td>1200051B.0024</td>\n" +
                "            \n" +
                "              <td>3.0</td>\n" +
                "              <td>3.3</td>\n" +
                "              \n" +
                "              <td>83</td>\n" +
                "              \n" +
                "              \n" +
                "            \n" +
                "            \n" +
                "            <td>\n" +
                "              \n" +
                "            </td>\n" +
                "          </tr>\n" +
                "          <tr>\n" +
                "            <td>形势与政策（1）</td>\n" +
                "            <td>1201111B</td>\n" +
                "            <td>1201111B.0019</td>\n" +
                "            \n" +
                "              <td>0.25</td>\n" +
                "              <td>2.7</td>\n" +
                "              \n" +
                "              <td>75</td>\n" +
                "              \n" +
                "              \n" +
                "            \n" +
                "            \n" +
                "            <td>\n" +
                "              \n" +
                "            </td>\n" +
                "          </tr>\n" +
                "          <tr>\n" +
                "            <td>高等数学A（上）</td>\n" +
                "            <td>1400211B</td>\n" +
                "            <td>1400211B.0020</td>\n" +
                "            \n" +
                "              <td>6.0</td>\n" +
                "              <td>4</td>\n" +
                "              \n" +
                "              <td>91</td>\n" +
                "              \n" +
                "              \n" +
                "            \n" +
                "            \n" +
                "            <td>\n" +
                "              \n" +
                "            </td>\n" +
                "          </tr>\n" +
                "          <tr>\n" +
                "            <td>大学英语（1）</td>\n" +
                "            <td>1500011B</td>\n" +
                "            <td>1500011B.0070</td>\n" +
                "            \n" +
                "              <td>2.5</td>\n" +
                "              <td>3.3</td>\n" +
                "              \n" +
                "              <td>84</td>\n" +
                "              \n" +
                "              \n" +
                "            \n" +
                "            \n" +
                "            <td>\n" +
                "              \n" +
                "            </td>\n" +
                "          </tr>\n" +
                "          <tr>\n" +
                "            <td>大学体育1</td>\n" +
                "            <td>5100041B</td>\n" +
                "            <td>5100041B.0015</td>\n" +
                "            \n" +
                "              <td>0.75</td>\n" +
                "              <td>3.3</td>\n" +
                "              \n" +
                "              <td>84</td>\n" +
                "              \n" +
                "              \n" +
                "            \n" +
                "            \n" +
                "            <td>\n" +
                "              \n" +
                "            </td>\n" +
                "          </tr>\n" +
                "          <tr>\n" +
                "            <td>军事理论</td>\n" +
                "            <td>5200011B</td>\n" +
                "            <td>5200011B.0001</td>\n" +
                "            \n" +
                "              <td>1.5</td>\n" +
                "              <td>3</td>\n" +
                "              \n" +
                "              <td>78</td>\n" +
                "              \n" +
                "              \n" +
                "            \n" +
                "            \n" +
                "            <td>\n" +
                "              \n" +
                "            </td>\n" +
                "          </tr>\n" +
                "          <tr>\n" +
                "            <td>军事训练</td>\n" +
                "            <td>5200023B</td>\n" +
                "            <td>5200023B.0045</td>\n" +
                "            \n" +
                "              <td>2.0</td>\n" +
                "              <td>3.7</td>\n" +
                "              \n" +
                "              <td>88</td>\n" +
                "              \n" +
                "              \n" +
                "            \n" +
                "            \n" +
                "            <td>\n" +
                "              \n" +
                "            </td>\n" +
                "          </tr>\n" +
                "          <tr>\n" +
                "            <td>入学教育</td>\n" +
                "            <td>5700013B</td>\n" +
                "            <td>5700013B.0045</td>\n" +
                "            \n" +
                "              <td>0.0</td>\n" +
                "              <td>3.7</td>\n" +
                "              \n" +
                "              <td>89</td>\n" +
                "              \n" +
                "              \n" +
                "            \n" +
                "            \n" +
                "            <td>\n" +
                "              \n" +
                "            </td>\n" +
                "          </tr>\n" +
                "          <tr>\n" +
                "            <td>公益活动A（上）</td>\n" +
                "            <td>5700023B</td>\n" +
                "            <td>5700023B.0029</td>\n" +
                "            \n" +
                "              <td>0.0</td>\n" +
                "              <td>3</td>\n" +
                "              \n" +
                "              <td>良</td>\n" +
                "              \n" +
                "              \n" +
                "            \n" +
                "            \n" +
                "            <td>\n" +
                "              \n" +
                "            </td>\n" +
                "          </tr>\n" +
                "          </tbody>\n" +
                "        </table>\n" +
                "      </div>\n" +
                "    </div>\n" +
                "  \n" +
                "</div>\n" +
                "\n" +
                "<script>\n" +
                "  /*<![CDATA[*/\n" +
                "  /*]]>*/\n" +
                "</script>\n" +
                "</body>\n" +
                "</html>";

        Document document = Jsoup.parse(html);
        Elements elements = document.getElementsByClass("row");

        Iterator<Element> it;
        Element element;
        String semester;
        Grade grade=null;
        for (Element e : elements) {
            semester = e.child(0).text();
            System.out.println(semester);
            System.out.println("=================================================");
            element = e.child(1).child(0).child(1);
            element.children().forEach(ch -> {
                ch.children().forEach(i -> System.out.println(i.text()));
            });
            System.out.println("=================================================");
        }


    }


    private String semesterToNum(String semester) {
        String year=semester.substring(0,4);
        String term=semester.substring(12,13);
        int a=Integer.parseInt(year);
        int t;
        if ("一".equals(term)){
            t=2*(a-2001);
        }else {
            t=2*(a-2001)+1;
        }
        return t+"";
    }

    private String numToSemester(String num){
        int n=Integer.parseInt(num);
        int t=n%2;
        int a;
        String s;
        if (t==0){
            a=n/2;
            s="一";
        }else {
            a=(n-1)/2;
            s="二";
        }
        return (2001+a)+"-"+(2002+a)+"学年第"+s+"学期";

    }

    @Test
    public void testS(){
        String semester="2021-2022学年第一学期"; //32
        //2017-2018学年第二学期 33   1984
        //2017-2018学年第一学期 32   1985
        //2016-2017学年第二学期 31   1985
        //2016-2017学年第一学期 30   1986
        //2015-2016学年第二学期 29   1986
        //2015-2016学年第一学期 28   1987
        //2014-2015学年第二学期 27   1987
        //2014-2015学年第一学期 26   1988
        //2013-2014学年第二学期 25   1988
        //2013-2014学年第一学期 24   1989
        //2012-2013学年第二学期 23   1989
        //2012-2013学年第一学期 22   1990
        //2011-2012学年第二学期 21   1990
        //2011-2012学年第一学期 20   1991
        //2010-2011学年第二学期 19   1991
        //2010-2011学年第一学期 18   1992
        //2009-2010学年第二学期 17   1992
        //2009-2010学年第一学期 16   1993
        //2008-2009学年第二学期 15   1993
        //2008-2009学年第一学期 14   1994
        //2007-2008学年第二学期 13   1994
        //2007-2008学年第一学期 12   1995
        //2006-2007学年第二学期 11   1995
        //2006-2007学年第一学期 10   1996
        //2005-2006学年第二学期  9   1996
        //2005-2006学年第一学期  8   1997
        //2004-2005学年第二学期  7   1997
        //2004-2005学年第一学期  6   1998
        //2003-2004学年第二学期  5   1998
        //2003-2004学年第一学期  4   1999
        //2002-2003学年第二学期  3   1999
        //2002-2003学年第一学期  2   2000
        //2001-2002学年第二学期  1   2000
        //2001-2002学年第一学期  0   2001

        //2017-2018学年第二学期 33   1984
        //2017-2001=16   2001-1984=16   16*2+1=33
        //======================================
        //2017-2018学年第一学期 32   1985
        //2017-2001=16   2001-1984=16   16*2=32

        System.out.println(semesterToNum(semester));

        System.out.println(numToSemester("40"));


    }


    @Test
    public void test7() throws Exception {

        CookieStore cookieStore = new BasicCookieStore();
        RequestConfig config = RequestConfig.custom().setRedirectsEnabled(false).build();
        //CloseableHttpClient client= HttpClients.createDefault();
        CloseableHttpClient client = HttpClients.custom().setDefaultCookieStore(cookieStore).setDefaultRequestConfig(config).build();
        HttpHead head = new HttpHead("http://my.hfut.edu.cn/");
        CloseableHttpResponse response = client.execute(head);

        //org.apache.http.Header[] headers=response.getAllHeaders();

        //System.out.println(Arrays.toString(headers));

        //请求验证码
        HttpGet get = new HttpGet("http://my.hfut.edu.cn/captchaGenerate.portal?s=0.1265189051288731");

        //验证码识别
        String code = null;
        for (int i = 0; i < 3; i++) {
            CloseableHttpResponse response1 = client.execute(get);

            InputStream in = response1.getEntity().getContent();
            BufferedImage image = ImageIO.read(in);
            in.close();
            code = new OCR().getCode(image);
            if (!code.contains("?")) {
                break;
            }
        }


        System.out.println("code: " + code);

        //模拟登录
        HttpPost post = new HttpPost("http://my.hfut.edu.cn/userPasswordValidate.portal");

        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("Login.Token1", UserInfo.UESRNAME));
        params.add(new BasicNameValuePair("Login.Token2", UserInfo.PASSWORD));
        params.add(new BasicNameValuePair("captchaField", code));
        params.add(new BasicNameValuePair("goto", "http%3A%2F%2Fmy.hfut.edu.cn%2FloginSuccess.portal"));
        params.add(new BasicNameValuePair("gotoOnFail", "http%3A%2F%2Fmy.hfut.edu.cn%2FloginFailure.portal"));

        HttpEntity entity = new UrlEncodedFormEntity(params);

        post.setEntity(entity);

        CloseableHttpResponse response1 = client.execute(post);
        if (response1.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            //登录成功

            //登录新教务系统
            HttpHead head1 = new HttpHead("http://jxglstu.hfut.edu.cn/eams5-student/wiscom-sso/login");
            CloseableHttpResponse response2 = client.execute(head1);

            if (response2.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                //登录成功

                HttpHead head2 = new HttpHead("http://jxglstu.hfut.edu.cn/eams5-student/for-std/grade/sheet");


                CloseableHttpResponse response3 = client.execute(head2);


                System.out.println(response3.getFirstHeader("Location").toString());
            }

        }


    }


    @Test
    public void test99() {
        String s = "SESSION=7335a8d3-84ae-4d8a-b2e1-5a09edb164e6";
        System.out.println(s.startsWith("E", 1));
        System.out.println(s.substring(8));
    }

    @Test
    public void test88() {
        //LocalTime.now().getNano()
    }


}
