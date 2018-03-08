package com.cll.crawler;

import com.baidu.aip.ocr.AipOcr;
import org.json.JSONObject;
import org.junit.Test;

import java.util.HashMap;

/**
 * @author chenliangliang
 * @date 2017/12/31
 */
public class TestBaiduOCR {

    private static final String APP_ID = "10608178";
    private static final String API_KEY = "rcluHxIGr6VWNSkL45uO71G8";
    private static final String SECRET_KEY = "T5ikRptvPnfxRIBuypqwXpWyBI78hNB0";


    @Test
    public void test(){

        AipOcr client=new AipOcr(APP_ID,API_KEY,SECRET_KEY);

        client.setConnectionTimeoutInMillis(5000);
        client.setSocketTimeoutInMillis(60000);


        String url="C:\\Users\\Shinelon\\Desktop\\0ff4d8c54f21af7d2b8a6425b7d7bed7.jpg";

       JSONObject res= client.basicGeneral(url,new HashMap<>());

        System.out.println(res);
    }

}
