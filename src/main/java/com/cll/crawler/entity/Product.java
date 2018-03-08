package com.cll.crawler.entity;

import org.jeecgframework.poi.excel.annotation.Excel;

import java.io.Serializable;
import java.util.Date;

/**
 * @author chenliangliang
 * @date 2017/12/26
 */
public class Product implements Serializable {


    private int id;

    @Excel(name = "商品名",needMerge = true,width = 30.0)
    private String name;

    @Excel(name = "价格",needMerge = true,width = 20.0)
    private double price;

    @Excel(name = "购买时间", exportFormat="yyyy/mm/dd", needMerge = true)
    private Date time;


    public Product() {
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
