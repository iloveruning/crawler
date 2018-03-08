package com.cll.crawler;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.junit.Test;

import java.io.IOException;

/**
 * @author chenliangliang
 * @date 2017/12/28
 */
public class TestWexin {

    private static final String APP_ID = "wxa2c5a7b8b03588a7";

    private static final String APP_SECRET = "c49f03def5bb84555f0f810d0c6b8da5";

    private static final String ACCESS_TOKEN = "5_NQTVGME1Fu-WTHmL6_YzO9i4SdUamDmbSqE_cb37wlW8tQWLyg4C_Ir0mq845iYQzZua5XeTn0RkaWQxkjzkkSEk8YnJQ7vlLCD1y0eT2luTaGMpxiYh3DRs75xwjGs8ZSZtvnOVE8gKzzatFEReAAAUTG";

    @Test
    public void getAccessToken() {
        String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + APP_ID + "&secret=" + APP_SECRET;

        HttpClient client = new HttpClient();

        GetMethod getMethod = new GetMethod(url);

        try {
            client.executeMethod(getMethod);
            String res = getMethod.getResponseBodyAsString();
            System.out.println(res);
            JSONObject object = JSONObject.parseObject(res);
            String accessToken = object.getString("access_token");
            Integer expiresIn = object.getInteger("expires_in");
            System.out.printf("access_token: %s%n", accessToken);
            System.out.printf("expires_in: %s%n", expiresIn);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    @Test
    public void createMenu() {
        HttpClient client = new HttpClient();

        String url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=" + ACCESS_TOKEN;
        PostMethod postMethod = new PostMethod(url);
        String menu = "{\n" +
                "     \"button\":[\n" +
                "     {    \n" +
                "          \"type\":\"click\",\n" +
                "          \"name\":\"今日歌曲\",\n" +
                "          \"key\":\"V1001_TODAY_MUSIC\"\n" +
                "      },\n" +
                "      {\n" +
                "           \"name\":\"菜单\",\n" +
                "           \"sub_button\":[\n" +
                "           {    \n" +
                "               \"type\":\"view\",\n" +
                "               \"name\":\"聊天室\",\n" +
                "               \"url\":\"http://chat.chenliangliang.xin/webscoket/chat.html\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"type\":\"click\",\n" +
                "               \"name\":\"赞一下我们\",\n" +
                "               \"key\":\"V1001_GOOD\"\n" +
                "            }]\n" +
                "       }]\n" +
                " }";
        try {
            RequestEntity entity = new StringRequestEntity(menu, "text/json", "UTF-8");

            postMethod.setRequestEntity(entity);

            client.executeMethod(postMethod);
            String res = postMethod.getResponseBodyAsString();
            System.out.println(res);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
