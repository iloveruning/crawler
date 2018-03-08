package com.cll.exam;

/**
 * @author chenliangliang
 * @date 2018/3/1
 */

import com.alibaba.fastjson.JSON;
import com.cll.crawler.entity.Exam;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class TestReadFile {




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


            List<Exam> list=new ArrayList<>(134);

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
                    list.add(exam);
                    System.out.println(exam);
                    status = STATUS_NEW;
                }
            });


            FileUtils.write(new File("F:/JSON.txt"),JSON.toJSONString(list),"UTF-8");

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
