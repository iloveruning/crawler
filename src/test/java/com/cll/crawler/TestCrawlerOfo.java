package com.cll.crawler;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cll.crawler.utils.HttpUtil;
import com.cll.crawler.utils.HttpsClient;
import org.apache.http.HttpResponse;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @author chenliangliang
 * @date 2018/2/14
 */
public class TestCrawlerOfo {

    //private HttpClient client;


    @Before
    @Ignore
    public void getClient() {
        //client=new HttpClient();
    }


    /*@Test
    public void test() throws IOException {

        PostMethod method=new PostMethod("https://san.ofo.so/ofo/Api/nearbyofoCar");

        method.addRequestHeader("");

        method.addParameters(new NameValuePair[]{
                new NameValuePair("token","2b6c3010-114b-11e8-a630-15e0f2adf8f3"),
                new NameValuePair("lat","117.302900"),
                new NameValuePair("lng","31.852750"),
                new NameValuePair("source","-5")
        });

        int response = client.executeMethod(method);


        System.out.println(method.getResponseBodyAsString());
    }*/

    @Test
    public void test1() throws Exception {
        String host = "https://san.ofo.so";

        String path = "/ofo/Api/nearbyofoCar";

        Map<String, String> headers = new HashMap<>();
        headers.put("referer", "https://servicewechat.com/wxdf8d030d4969ab43/16/page-frame.html");
        headers.put("content-type", "multipart/form-data;boundary=--------FormData961544");
        headers.put("User-Agent", "Mozilla/5.0 (Linux; Android 6.0.1; MI NOTE LTE Build/MMB29M; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/57.0.2987.132 MQQBrowser/6.2 TBS/043906 Mobile Safari/537.36 MicroMessenger/6.6.2.1240(0x26060236) NetType/WIFI Language/zh_CN MicroMessenger/6.6.2.1240(0x26060236) NetType/WIFI Language/zh_CN");
        headers.put("Host", "san.ofo.so");
        headers.put("Connection", "Keep-Alive");

        Map<String, String> query = new HashMap<>();
        query.put("token", "2b6c3010-114b-11e8-a630-15e0f2adf8f3");
        query.put("lat", "117.302900");
        query.put("lng", "31.852750");
        query.put("source", "-5");

        String body = "----------FormData961544\n" +
                " Content-Disposition: form-data; name=\"token\";\n" +
                " \n" +
                " 2b6c3010-114b-11e8-a630-15e0f2adf8f3\n" +
                " ----------FormData961544\n" +
                " Content-Disposition: form-data; name=\"lat\";\n" +
                " \n" +
                " 39.908859\n" +
                " ----------FormData961544\n" +
                " Content-Disposition: form-data; name=\"lng\";\n" +
                " \n" +
                " 116.397392\n" +
                " ----------FormData961544\n" +
                " Content-Disposition: form-data; name=\"source\";\n" +
                " \n" +
                " -5\n" +
                " ----------FormData961544--";

        HttpResponse response = HttpUtil.doPost(host, path, "POST", headers, query, body);

        try (ByteArrayOutputStream out = new ByteArrayOutputStream();) {
            response.getEntity().writeTo(out);
            byte[] bytes = out.toByteArray();
            String res = new String(bytes);

            System.out.println(res);
        }


    }

    private HttpsClient client;

    private Map<String, String> headers;

    private Map<String, String> params;

    private Map<String,String> querys;

    @Test
    public void testCrawlerMobike() throws IOException, InterruptedException {

        try (OutputStream os = new FileOutputStream("F:/mobike.txt");
             OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
             BufferedWriter bw = new BufferedWriter(osw)) {

            float startLon = 117.257875F;
            float endLon = 117.312492F;
            float startLat = 31.854607F;
            float endLat = 31.882401F;

            float step = 0.002770F;

            for (float i = startLon; i < endLon; i += step) {
                for (float j = startLat; j < endLat; j += step) {
                    Thread.sleep(10);

                    JSONArray data = getData(i, j);
                    System.out.println(data);
                    if (data != null) {
                        for (int k = 0; k < data.size(); k++) {
                            JSONObject object = data.getJSONObject(k);
                            Double lon=object.getDouble("distX");
                            Double lat=object.getDouble("distY");
                            String bikeId=object.getString("bikeIds");
                            String address=getAddress(lon,lat);
                            String res = "lon:" + lon.toString() + ",lat:" + lat.toString() +
                                    ",bikeId:" + bikeId.substring(0,bikeId.length()-2)+",address:"+address;
                            bw.write(res);
                            bw.newLine();
                        }
                    }
                }

            }

        }


    }


    @Test
    public void test2() {
        JSONArray data = getData(117.257875F, 31.882401F);
        System.out.println(data);
    }


    @Before
    public void init() {

        client = new HttpsClient();
        headers = new HashMap<>();
        // 添加头
        headers.put("Content-Type", " application/x-www-form-urlencoded");
        headers.put("mainSource", " 4003");
        headers.put("Accept", " */*");
        headers.put("eption", " 87365");
        headers.put("opensrc", " list");
        headers.put("wxcode", " 021zfnPs0F974f1YVJQs0UyaPs0zfnPH");
        headers.put("platform", " 3");
        headers.put("Accept-Language", " zh-cn");
        headers.put("citycode", " 0551");
        headers.put("lang", " zh");
        headers.put("User-Agent", " Mozilla/5.0 (iPhone; CPU iPhone OS 11_1_1 like Mac OS X) AppleWebKit/604.3.5 (KHTML, like Gecko) Mobile/15B150 MicroMessenger/6.5.22 Net");
        headers.put("Referer", " https");
        headers.put("Accept-Encoding", " br, gzip, deflate");
        headers.put("Connection", " keep-alive");
        headers.put("Cache-Control", " no-cache");

        params = new HashMap<>();

        //params.put("verticalAccuracy", "12");
        //params.put("speed", "0.18000000715255737");
        params.put("longitude", "117");
        //params.put("horizontalAccuracy", "10");
        params.put("errMsg", "getLocation%3Aok");
        params.put("latitude", "33");
        //params.put("accuracy", "100");
        params.put("altitude", "48.6485595703125");
        //params.put("citycode", "0551");
        //params.put("wxcode", "021zfnPs0F974f1YVJQs0UyaPs0zfnPH");

       querys = new HashMap<>();
        //117.25800637664548,lat:31.854623763939166


        querys.put("location", "");
        //radius=5&output=json&pois=1&ak=tEy1DxZqK7uDKBMc5U31avaXDWZYRInN
        querys.put("radius", "10");
        querys.put("output", "json");
        querys.put("ak", "tEy1DxZqK7uDKBMc5U31avaXDWZYRInN");

    }

    private Map<String, String> getParams(float lon, float lat) {
        params.replace("longitude", String.valueOf(lon));
        params.replace("latitude", String.valueOf(lat));
        return params;
    }

    private JSONArray getData(float lon, float lat) {


        // 添加必须参数

        /**
         * latitude=31.846794
         * &longitude=117.300507
         * &errMsg=getMapCenterLocation%3Aok
         * &citycode=0551
         * &wxcode=021zfnPs0F974f1YVJQs0UyaPs0zfnPH
         */

        try {
            String res = client.doPost("https://mwx.mobike.com/mobike-api/rent/nearbyBikesInfo.do", headers, getParams(lon, lat));
            System.out.println(res);
            if (res != null) {
                JSONObject object = JSONObject.parseObject(res);
                if (object.getInteger("code") == 0) {
                    return object.getJSONArray("object");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Test
    public void testBaiduMap() throws IOException {

    }

    private Map<String,String> getQuery(Double lon,Double lat){
        querys.replace("location", lat.toString() + "," + lon.toString());
        return querys;

    }


    private String getAddress(Double lon,Double lat){


        String res = null;
        try {
            res = client.doGet("http://api.map.baidu.com/geocoder/v2/", null, getQuery(lon, lat));
            if (res != null) {
                JSONObject object = JSONObject.parseObject(res);
                if (object.getInteger("status") == 0) {
                    return object.getJSONObject("result").getString("formatted_address");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        return res;
    }
}
