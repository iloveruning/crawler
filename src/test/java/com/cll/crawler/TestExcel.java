package com.cll.crawler;

import com.cll.crawler.entity.Student;
import com.cll.crawler.entity.WeChat;
import com.cll.crawler.mapper.WeChatMapper;
import org.apache.poi.ss.usermodel.Workbook;
import org.jeecgframework.poi.excel.ExcelExportUtil;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * @author chenliangliang
 * @date 2017/12/26
 */


@RunWith(SpringRunner.class)
@SpringBootTest
public class TestExcel {

    @Test
    public void test1() {

        List<Student> list = new LinkedList<>();
        Student stu1 = new Student();
        stu1.setId("1");
        stu1.setBirthday(new Date());
        stu1.setName("json");
        stu1.setSex(1);
        stu1.setRegistrationDate(new Date());
        list.add(stu1);
        Student stu2 = new Student();
        stu2.setId("2");
        stu2.setBirthday(new Date());
        stu2.setName("Tom");
        stu2.setSex(2);
        stu2.setRegistrationDate(new Date());
        list.add(stu2);


        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("计算机一班学生", "学生"),
                Student.class, list);
        OutputStream out = null;
        try {
            out = new FileOutputStream("F:/wexin/student.xlsx");
            workbook.write(out);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {

                }
            }
        }


    }

    @Autowired
    private WeChatMapper weChatMapper;

    @Test
    public void test2() {
        ImportParams params = new ImportParams();
        params.setTitleRows(1);
        params.setHeadRows(1);

        List<WeChat> list = ExcelImportUtil.importExcel(new File("F:/wexin/-数据分析-2017-12-26 (1).xlsx"), WeChat.class, params);

        System.out.println(list.size());
        List<List<WeChat>> wechats = subList(list, 25);

        wechats.forEach(it -> {
            System.out.println(weChatMapper.save(it));
            System.out.println("==============");
        });

    }


    private List<List<WeChat>> subList(List<WeChat> list, int offSet) {
        int size = list.size();
        int n = size / offSet + 1;
        List<List<WeChat>> res = new ArrayList<>(n * 2);
        int from = 0, to;
        for (int i = 0; i < n; i++) {
            to = from + offSet;
            if (to > size) {
                to = size;
            }
            res.add(list.subList(from, to));
            from = to;
        }
        return res;
    }


    @Test
    public void test() {
        List<String> parentList = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            parentList.add(String.valueOf(i));
        }

        List<String> subList = parentList.subList(3, 10);
        for (String s : subList) {
            System.out.println(s);//output: 1, 2
        }

    }

}
