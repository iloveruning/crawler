package com.cll.crawler;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author chenliangliang
 * @date: 2017/12/18
 */
public class StringTest {


    @Test
    public void test1() {
        String str = "ff[img]http://yjwpja.natappfree.cc/6ceygayctj4nspbbysbb.o6zAJs_XCs6GTDOWY6dC29MJfZm0.b5e860857b05accd8f12a0699e17905d.png[/img][img]http://yjwpja.natappfree.cc/6ceygayctj4nspbbysbb.o6zAJs_XCs6GTDOWY6dC29MJfZm0.b5e860857b05accd8f12a0699e17905d.png[/img][img]http://yjwpja.natappfree.cc/6ceygayctj4nspbbysbb.o6zAJs_XCs6GTDOWY6dC29MJfZm0.b5e860857b05accd8f12a0699e17905d.png[/img][img]http://yjwpja.natappfree.cc/6ceygayctj4nspbbysbb.o6zAJs_XCs6GTDOWY6dC29MJfZm0.b5e860857b05accd8f12a0699e17905d.png[/img]";
        String regex = "/[img](.*?)[\\/img](.*)/";
//        Pattern pattern = Pattern.compile(regex);
//
//       Matcher matcher = pattern.matcher(str);

        char[] chars= str.toCharArray();


        //String[] ss=StringUtils.split(str,"[");
        //String res=StringUtils.replaceAll(str,regex,"-");


        System.out.println(Arrays.toString(str.split(regex)));


    }

    /**
     * 正则表达式匹配两个指定字符串中间的内容
     * @param soap
     * @return
     */
    public static List<String> getSubUtil(String soap,String rgex){
        List<String> list = new ArrayList<String>();
        Pattern pattern = Pattern.compile(rgex);// 匹配的模式
        Matcher m = pattern.matcher(soap);
        while (m.find()) {
            int i = 1;
            list.add(m.group(i));
            i++;
        }
        return list;
    }

    /**
     * 返回单个字符串，若匹配到多个的话就返回第一个，方法与getSubUtil一样
     * @param soap
     * @param rgex
     * @return
     */
    public static String getSubUtilSimple(String soap,String rgex){
        Pattern pattern = Pattern.compile(rgex);// 匹配的模式
        Matcher m = pattern.matcher(soap);
        while(m.find()){
            return m.group(1);
        }
        return "";
    }

    /**
     * 测试
     * @param args
     */
    public static void main(String[] args) {
        String str = "abcabcabcfgjhgabcgfjabc";
        String rgex = "abc(.*?)abc";
        System.out.println(getSubUtil(str,rgex));
        System.out.println(getSubUtilSimple(str, rgex));
    }

    //private final char START_CHAR='[';
    //private final char
    /*private String match(String s){
        char[] chars=s.toCharArray();
        int n=chars.length;
        char[] res=new char[n];

        int state=0;
        char c;
        int m=0;
        for (int i = 0; i < n; i+=5) {
            c=chars[i];
            if (c=='['){
                state++;
            }
        }
    }*/


    @Test
    public void test7(){

        String s="B.增强改革创新本领，增强科学发展本领";

        String[] strs = s.split("[A-F].");
        System.out.println(strs.length);
        System.out.println(Arrays.toString(strs));
    }
}
