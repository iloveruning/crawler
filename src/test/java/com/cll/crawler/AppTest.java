package com.cll.crawler;

import com.cll.crawler.entity.Exam;
import com.cll.crawler.mapper.ExamMapper;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.*;

/**
 * @author chenliangliang
 * @date 2017/12/20
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AppTest {


//    @Autowired
//    private JobMapper jobMapper;
//
//    /**
//     * 爬取-合肥工业大学就业信息网-宣讲会数据
//     *
//     * @throws Exception
//     */
//    @Test
//    public void test7() throws Exception {
//        WebClient client = new WebClient(BrowserVersion.CHROME);
//        client.getOptions().setCssEnabled(false);
//        client.getOptions().setDownloadImages(false);
//        client.getOptions().setJavaScriptEnabled(false);
//        client.getOptions().setTimeout(10000);
//        client.getOptions().setThrowExceptionOnScriptError(false);
//        client.getOptions().setActiveXNative(false);
//        client.getOptions().setAppletEnabled(false);
//        // client.waitForBackgroundJavaScript(600 * 1000);
//
//        String url0 = "http://gdjy.hfut.edu.cn/products/list/1.html?list=a&page=1&per-page=18";
//
//        HtmlPage page = client.getPage(url0);
//
//        HtmlElement element = page.getFirstByXPath("//*[@id=\"w0\"]/div/b[2]");
//        String total = element.getTextContent();
//        int all = Integer.parseInt(total);
//        //int tol = jobMapper.count();
//        //int d = all - tol;
//        //int pageNum = d / 18 + 1;
//        int pageNum = all / 18 + 1;
//        for (int i = 1; i <= pageNum; i++) {
//
//
//            String url = "http://gdjy.hfut.edu.cn/products/list/1.html?list=a&page=" + i + "&per-page=18";
//            if (i != 1) {
//                page = client.getPage(url);
//            }
//
//
//            HtmlTableBody table = page.getFirstByXPath("//*[@id=\"w0\"]/table/tbody");
//
//            Iterable<DomNode> children = table.getChildren();
//
//
//            int count = 0;
//            for (DomNode c : children) {
//                NamedNodeMap map = c.getAttributes();
//                Node key = map.getNamedItem("data-key");
//                if (key != null) {
//                    /*if (count>=d){
//                        break;
//                    }*/
//
//                    String id = key.getNodeValue();
//                    String urll = "http://gdjy.hfut.edu.cn/products/" + id + ".html";
//
//                    Flowable.fromCallable(() -> {
//                        HtmlPage infoPage = client.getPage(urll);           ///html/body/div/div[2]/div/div/div/div[1]/div[3]
//                        HtmlElement ele = infoPage.getFirstByXPath("/html/body/div/div[2]/div/div/div/div[1]/div[3]");
//                        /*HtmlElement div=new HtmlDivision();
//
//                        Map<String, DomAttr> attributes=new HashMap<>(2);
//                        attributes.put("class",new DomAttr(infoPage,))
//                        ele.getElementsByTagName("table").forEach(tab->{
//                            ele.appendChild()
//                        });*/
//                        ele.getElementsByTagName("img").forEach(img -> {
//                            //"http://gdjy.hfut.edu.cn"+
//                            String src = img.getAttribute("src");
//                            if (!src.startsWith("http")) {
//                                src = "http://gdjy.hfut.edu.cn" + src;
//                            }
//                            System.out.println("img: " + src);
//                            img.setAttribute("src", src);
//                        });
//                        String info = ele.asXml();
//                        int res = jobMapper.updateInfoById(info, id);
//                        if (res == 1) {
//                            return id + ".html 保存成功！";
//                        } else {
//                            return id + ".html 保存失败！";
//                        }
//
//
//                    }).subscribeOn(Schedulers.io())
//                            .observeOn(Schedulers.single())
//                            .subscribe(System.out::println, Throwable::printStackTrace);
//
//                    System.out.println(urll);
//                   /* Iterator<DomNode> ch = c.getChildren().iterator();
//                    Job job = new Job();
//                    int temp = 0;
//                    while (ch.hasNext()) {
//                        String text = ch.next().getTextContent();
//                        switch (temp) {
//                            case 0:
//                                job.setCompany(text);
//                                break;
//                            case 1:
//                                job.setTime(text);
//                                break;
//                            case 2:
//                                job.setPlace(text);
//                                break;
//                            case 3:
//                                job.setClick(text);
//                                break;
//                            default:
//                                break;
//                        }
//                        ++temp;
//                    }
//                    job.setId(id);
//                    Flowable.fromCallable(() -> {
//                        HtmlPage infoPage = client.getPage(urll);           ///html/body/div/div[2]/div/div/div/div[1]/div[3]
//                        HtmlElement ele = infoPage.getFirstByXPath("/html/body/div/div[2]/div/div/div/div[1]/div[3]");
//                        ele.getElementsByTagName("img").forEach(img->{
//                            String src="http://gdjy.hfut.edu.cn"+img.getAttribute("src");
//                            System.out.println("img: "+src);
//                            img.setAttribute("src",src);
//                        });
//                        String info = ele.asXml();
//                        job.setInfo(info);
//                        if(jobMapper.isExist(id)==1){
//                            int re=jobMapper.updateClickAndInfo(job.getClick(),info,id);
//                            if (re == 1) {
//                                return id + ".html 更新成功！";
//                            } else {
//                                return id + ".html 更新失败！";
//                            }
//                        }else {
//                            int res = jobMapper.save(job);
//                            if (res == 1) {
//                                return id + ".html 保存成功！";
//                            } else {
//                                return id + ".html 保存失败！";
//                            }
//                        }
//
//
//
//
//                    }).subscribeOn(Schedulers.io())
//                            .observeOn(Schedulers.single())
//                            .subscribe(System.out::println, Throwable::printStackTrace);
//                    System.out.println("=========================");*/
//                    try {
//                        Thread.sleep(200);
//                    } catch (Exception e) {
//                        System.out.println(e.getMessage());
//                    }
//                    count++;
//                }
//            }
//
//
//        }
//    }
//
//
//    @Test
//    public void test1() {
//
//        //2017/10/09  14:00
//        //2017/12/19 08:30(昨天)
//        //2017/10/17  19:00
//        //2017/10/24 14：00
//        //2017/10/17  9:30
//
//        String m = "2017/10/17  9:30";
//        System.out.println(m.length());
//        switch (m.length()) {
//
//            case 16:
//                if (m.contains("：")) {
//                    System.out.println(m.replace("：", ":"));
//                } else {
//                    System.out.println(m);
//                }
//                break;
//            case 17:
//                System.out.println(m.substring(0, 10) + " " + m.substring(12, 17));
//
//                break;
//            case 18:
//                System.out.println(m.substring(0, 16));
//                break;
//            case 20:
//                System.out.println(m.substring(0, 16));
//                break;
//            default:
//                break;
//        }
//        System.out.println("===============");
//        String date, hour, minute;
//        String[] ss = StringUtils.split(m, " ");
//        if (ss.length == 1) {
//            ss = StringUtils.split(m, " ");
//        }
//        for (String s : ss) {
//            System.out.println(s);
//        }
//        date = ss[0];
//        String[] s2 = StringUtils.split(ss[1], ":");
//
//        if (s2.length == 1) {
//            s2 = StringUtils.split(ss[1], "：");
//        }
//        hour = s2[0];
//        if (hour.length()==1){
//            hour="0"+hour;
//        }
//        minute = s2[1];
//        if (minute.length()>2){
//            minute=minute.substring(0,2);
//        }
//        System.out.println(date+" "+hour+":"+minute+":00");
//
//    }


    @Autowired
    private ExamMapper examMapper;

    private final int STATUS_NEW = 0;

    private final int STATUS_QUESTION = 1;

    private final int STATUS_ANS_A = 2;

    private final int STATUS_ANS_B = 3;

    private final int STATUS_ANS_C = 4;

    private final int STATUS_ANS_D = 5;

    private final int STATUS_ANS_E = 6;

    private final int STATUS_ANS_F = 7;

    private final int STATUS_ANSWER = 8;

    private int status = 0;

    private Exam exam;

    @Test
    public void test() {

        try (InputStream is = new FileInputStream("F:/exam.txt");
             InputStreamReader isr = new InputStreamReader(is, "UTF-8");
             BufferedReader reader = new BufferedReader(isr)) {



            reader.lines().filter(line -> line.length() > 2).forEach(line -> {
                if (status == STATUS_NEW) {
                    exam = new Exam();
                }
                String str = line.replace("　", "");
                String start = str.substring(0, 2);
                if (isNumber(start)) {
                    status = STATUS_QUESTION;
                    assign(exam, status, str.substring(2));

                } else if (StringUtils.equals(start, "A.")) {
                    status = STATUS_ANS_A;
                    String[] ss = str.split("[A-F].");
                    for (int i = 1; i < ss.length; i++) {
                        assign(exam, status, ss[i]);
                        status++;
                    }
                } else if (StringUtils.equals(start, "B.")) {
                    status = STATUS_ANS_B;
                    String[] ss = str.split("[A-F].");
                    for (int i = 1; i < ss.length; i++) {
                        assign(exam, status, ss[i]);
                        status++;
                    }
                } else if (StringUtils.equals(start, "C.")) {
                    status = STATUS_ANS_C;
                    String[] ss = str.split("[A-F].");
                    for (int i = 1; i < ss.length; i++) {
                        assign(exam, status, ss[i]);
                        status++;
                    }
                } else if (StringUtils.equals(start, "D.")) {
                    status = STATUS_ANS_D;
                    String[] ss = str.split("[A-F].");
                    for (int i = 1; i < ss.length; i++) {
                        assign(exam, status, ss[i]);
                        status++;
                    }
                } else if (StringUtils.equals(start, "E.")) {
                    status = STATUS_ANS_E;
                    String[] ss = str.split("[A-F].");
                    for (int i = 1; i < ss.length; i++) {
                        assign(exam, status, ss[i]);
                        status++;
                    }
                } else if (StringUtils.equals(start, "F.")) {
                    status = STATUS_ANS_F;
                    String[] ss = str.split("[A-F].");
                    for (int i = 1; i < ss.length; i++) {
                        assign(exam, status, ss[i]);
                        status++;
                    }
                } else if (StringUtils.equals(start, "答案")) {
                    status = STATUS_ANSWER;
                    assign(exam, status, str.substring(3));
                    int res = examMapper.save(exam);
                    System.out.println(exam);
                    if (res==1){
                        System.out.println("插入成功");
                    }else {
                        System.out.println("插入失败");
                    }

                    status = STATUS_NEW;
                }
            });


        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    private boolean isNumber(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }


    private void assign(Exam exam, int status, String value) {
        switch (status) {
            case STATUS_QUESTION:
                exam.setQuestion(value);
                break;
            case STATUS_ANS_A:
                exam.setAnsA(value);
                break;
            case STATUS_ANS_B:
                exam.setAnsB(value);
                break;
            case STATUS_ANS_C:
                exam.setAnsC(value);
                break;
            case STATUS_ANS_D:
                exam.setAnsD(value);
                break;
            case STATUS_ANS_E:
                exam.setAnsE(value);
                break;
            case STATUS_ANS_F:
                exam.setAnsF(value);
                break;
            case STATUS_ANSWER:
                exam.setAnswer(value);
                if (value.length()>1){
                    exam.setType(1);
                }else {
                    exam.setType(0);
                }
                break;
        }
    }
}
