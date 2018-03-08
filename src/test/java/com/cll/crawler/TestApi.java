package com.cll.crawler;

import com.alibaba.fastjson.JSONObject;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.FetchRet;
import com.qiniu.util.Auth;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.io.FileUtils;
import org.junit.Test;
import sun.misc.BASE64Encoder;

import java.io.File;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author chenliangliang
 * @date: 2017/12/17
 */
public class TestApi {


    /**
     * 天气查询API
     *
     * @throws Exception
     */
    @Test
    public void testWeatherApi() throws Exception {

        String city = "肥东县";
        String appcode = "b0c95c9d96384309a9786808e7b4e331";
        HttpClient client = new HttpClient();

        GetMethod method = new GetMethod("http://jisutqybmf.market.alicloudapi.com/weather/query");

        method.addRequestHeader("Authorization", "APPCODE " + appcode);

        NameValuePair[] params = new NameValuePair[]{
                new NameValuePair("city", city),
                new NameValuePair("citycode", "citycode"),
                new NameValuePair("cityid", "cityid"),
                new NameValuePair("ip", "ip"),
                new NameValuePair("location", "location")
        };
        method.setQueryString(params);

        client.executeMethod(method);

        System.out.println(method.getResponseBodyAsString());

    }


    /**
     * 快递查询
     *
     * @throws Exception
     */
    @Test
    public void testDelivery() throws Exception {

        String num = "467022158023";
        HttpClient client = new HttpClient();

        GetMethod method = new GetMethod("http://www.kuaidi100.com/autonumber/autoComNum");

        //?resultv2=1&text=82042687

        NameValuePair[] params = new NameValuePair[]{
                new NameValuePair("resultv2", "1"),
                new NameValuePair("text", num)
        };
        method.setQueryString(params);

        client.executeMethod(method);

        String res1 = method.getResponseBodyAsString();

        JSONObject object = JSONObject.parseObject(res1);

        String comCode = object.getJSONArray("auto").getJSONObject(0).getString("comCode");

        GetMethod method1 = new GetMethod("http://www.kuaidi100.com/query");

        //?type=xinbangwuliu&postid=82042687&temp=0.6334333052577188

        NameValuePair[] params1 = new NameValuePair[]{
                new NameValuePair("type", comCode),
                new NameValuePair("postid", num),
                new NameValuePair("temp", Math.random() + "")
        };
        method1.setQueryString(params1);
        client.executeMethod(method1);

        System.out.println(method1.getResponseBodyAsString());
    }


    /**
     * 获取考题接口
     *
     * @throws Exception
     */
    @Test
    public void testDriverExamApi() throws Exception {


        String appcode = "b0c95c9d96384309a9786808e7b4e331";
        HttpClient client = new HttpClient();

        GetMethod method = new GetMethod("http://jisujiakao.market.alicloudapi.com/driverexam/query");

        method.addRequestHeader("Authorization", "APPCODE " + appcode);

        NameValuePair[] params = new NameValuePair[]{
                new NameValuePair("pagenum", "1"),
                new NameValuePair("pagesize", "20"),
                new NameValuePair("sort", "normal"),
                new NameValuePair("subject", "1"),
                new NameValuePair("type", "C1")
        };
        method.setQueryString(params);

        client.executeMethod(method);

        System.out.println(method.getResponseBodyAsString());

    }


    @Test
    public void testQiniu() {
        String accessKey = "GDY_33MtHpOgLHrnMPDdHuWXVlUk-CyeWRB55pak";
        String secretKey = "6J5Ut73dVXmIw1J-sMx_zHuoCFkC2d3cts0gL7DW";
        String bucket = "arunning6";
        String key = "database";
        String filePath = "C:\\Users\\Shinelon\\Desktop\\timg.jpg";
        //华南
        Zone zone = Zone.zone2();
        Configuration config = new Configuration(zone);

        Auth auth = Auth.create(accessKey, secretKey);
        String token = auth.uploadToken(bucket);
        UploadManager um = new UploadManager(config);
        try {
            Response response = um.put(filePath, key, token);
            System.out.println(response.bodyString());
        } catch (QiniuException ex) {
            Response r = ex.response;
            System.err.println(r.toString());
            try {
                System.err.println(r.bodyString());
            } catch (QiniuException ex2) {
                //ignore
            }
        }

        //返回: {"hash":"FjhhFX5J39TyTSJmio10W53l4YLe","key":"qiniu-test"}
    }


    @Test
    public void testQiniuDownload() {
        String accessKey = "GDY_33MtHpOgLHrnMPDdHuWXVlUk-CyeWRB55pak";
        String secretKey = "6J5Ut73dVXmIw1J-sMx_zHuoCFkC2d3cts0gL7DW";
        String bucket = "arunning6";
        String key = "qiniu-test";

        Auth auth = Auth.create(accessKey, secretKey);
        //华南
        Zone zone = Zone.zone2();
        Configuration config = new Configuration(zone);

        BucketManager bm = new BucketManager(auth, config);

        try {
            FetchRet fetchRet = bm.fetch("http://p1iczhjna.bkt.clouddn.com", bucket, key);
            System.out.printf("size: %d%n", fetchRet.fsize);
            System.out.printf("hash: %s%n", fetchRet.hash);
            System.out.printf("key: %s%n", fetchRet.key);
            System.out.printf("mimeType: %s%n", fetchRet.mimeType);
        } catch (QiniuException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void testDownload() throws Exception {
        HttpClient client = new HttpClient();

        GetMethod get = new GetMethod("http://p1iczhjna.bkt.clouddn.com/qiniu-test");

        client.executeMethod(get);

        FileUtils.copyToFile(get.getResponseBodyAsStream(), new File("F:/a.jpg"));
    }


    @Test
    public void test8() {
        String accessKey = "GDY_33MtHpOgLHrnMPDdHuWXVlUk-CyeWRB55pak";
        String secretKey = "6J5Ut73dVXmIw1J-sMx_zHuoCFkC2d3cts0gL7DW";
        String bucket = "arunning6";
        String key = "qiniu-test";

        Auth auth = Auth.create(accessKey, secretKey);
        String url = "http://p1iczhjna.bkt.clouddn.com/qiniu-test";

        String downloadUrl = auth.privateDownloadUrl(url, 120);

        System.out.println(downloadUrl);
    }


    @Test
    public void testMD5() {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            BASE64Encoder encoder = new BASE64Encoder();

            byte[] bytes = md5.digest("2".getBytes());
            String str = encoder.encode(bytes);
            System.out.println(str);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
}
