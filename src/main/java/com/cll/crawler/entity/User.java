package com.cll.crawler.entity;

import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelCollection;
import org.jeecgframework.poi.excel.annotation.ExcelEntity;
import org.jeecgframework.poi.excel.annotation.ExcelTarget;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author chenliangliang
 * @date 2017/12/26
 */
@ExcelTarget("User")
public class User implements Serializable{

    @Excel(name = "用户id" , needMerge = true)
    private int id;

    @Excel(name = "用户名" , needMerge = true,width = 15.0)
    private String name;

    @Excel(name = "年龄" , needMerge = true)
    private int age;

    @ExcelEntity(name = "商品")
    private Product product;

    @ExcelCollection(name = "商品序列")
    private List<Product> products;

    @Excel(name = "创建时间" ,exportFormat="yyyy-mm-dd" , needMerge = true )
    private Date time;

    @Excel(name="性别" , replace={"男_1","女_0"}, needMerge = true)
    private int sex;

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public static void main(String[] args){
        System.out.println(User.class.getResource(""));
        System.out.println(User.class.getResource("/"));
        System.out.println(User.class.getResource("/com/cll/crawler/"));

        System.out.println(User.class.getClassLoader().getResource("application.properties"));
        System.out.println(User.class.getProtectionDomain().getCodeSource().getLocation());

        System.out.println(User.class.getPackage().getName());
    }
}
