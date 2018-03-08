package com.cll.crawler;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.junit.Test;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;


/**
 * @author chenliangliang
 * @date 2017/12/29
 */
public class TestEnglishQuery {


    @Test
    public void test1() {

        String url = "http://www.chsi.com.cn/cet/query";
        String zkzh = "340021171202723";
        String xm = "陈亮亮";
        HttpClient client = new HttpClient();

        GetMethod getMethod = new GetMethod(url);
        getMethod.setRequestHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3298.3 Safari/537.36");
        getMethod.setRequestHeader("Cookie ", "JSESSIONID=F14B1AFD33D43E6733EE181C8A50B55A; acw_tc=AQAAAF0sNUBLFwoAm6XReKqt6T4Tm+Xa; __utma=65168252.974743820.1514557996.1514557996.1514557996.1; __utmc=65168252; __utmz=65168252.1514557996.1.1.utmcsr=exam8.com|utmccn=(referral)|utmcmd=referral|utmcct=/english/CET46/chengji/201712/4156494.html; __utmt=1; __utmb=65168252.1.10.1514557996");


        getMethod.setQueryString(new NameValuePair[]{
                new NameValuePair("zkzh", zkzh),
                new NameValuePair("xm", xm)
        });

        try {
            client.executeMethod(getMethod);
            System.out.println(getMethod.getResponseBodyAsString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void test2() throws Exception {
        WebClient client = new WebClient(BrowserVersion.CHROME);
        client.getOptions().setCssEnabled(false);
        client.getOptions().setDownloadImages(false);
        client.getOptions().setJavaScriptEnabled(true);
        client.getOptions().setTimeout(10000);
        client.getOptions().setThrowExceptionOnScriptError(false);
        client.getOptions().setActiveXNative(false);
        client.getOptions().setAppletEnabled(false);
        //client.waitForBackgroundJavaScript(600 * 1000);
        client.getOptions().setRedirectEnabled(true);
        client.getCookieManager().setCookiesEnabled(true);

        HtmlPage page = client.getPage("http://www.chsi.com.cn/cet/");
        HtmlForm form = page.getFormByName("form1");

        HtmlInput zkzh = form.getInputByName("zkzh");

        HtmlInput xm = form.getInputByName("xm");

        zkzh.type("340021171202723");

        xm.type("陈亮亮");

        HtmlSubmitInput submit = (HtmlSubmitInput) page.getElementById("submitCET");

        HtmlPage res = submit.click();

        System.out.println(res.asText());
    }

    public static void main(String[] args) throws Exception {
        String url = "http://cet.etest.net.cn/Home/ToRetrieveAccount";
        HttpClient client = new HttpClient();

        GetMethod getMethod = new GetMethod("http://cet.etest.net.cn/Home/VerifyCodeImg?a=" + Math.random());

        client.executeMethod(getMethod);

        InputStream in = getMethod.getResponseBodyAsStream();
        BufferedImage image = ImageIO.read(in);

        JFrame frame = new JFrame("验证码");
        frame.setLocation(400, 500);
        frame.setSize(300, 200);
        frame.setLayout(new FlowLayout());


        JLabel label = new JLabel();
        label.setIcon(new ImageIcon(image));

        JTextField textField = new JTextField();
        textField.setColumns(20);

        JButton button = new JButton("提交");

        button.addActionListener(e -> {
            String verificationCode = textField.getText();
            PostMethod postMethod = new PostMethod(url);

            postMethod.setParameter("provinceCode", "34");
            postMethod.setParameter("IDTypeCode", "1");
            postMethod.setParameter("IDNumber", "342623199608072711");
            postMethod.setParameter("Name", "陈亮亮");
            postMethod.setParameter("verificationCode", verificationCode);
            try {
                client.executeMethod(postMethod);
                System.out.println(postMethod.getResponseBodyAsString());
                frame.dispose();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });
        frame.getContentPane().add(label);
        frame.getContentPane().add(textField);
        frame.getContentPane().add(button);
        frame.setVisible(true);

    }

    @Test
    public void test3() throws Exception {


    }
}
