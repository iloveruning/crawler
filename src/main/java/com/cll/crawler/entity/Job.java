package com.cll.crawler.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author chenliangliang
 * @date: 2017/12/10
 */
@Entity
@Table(name = "job")
public class Job {

    @Id
    @Column(name = "id",length = 8)
    private String id;

    @Column(name = "company",length = 128)
    private String company;

    @Column(name = "time",length = 32)
    private String time;

    @Column(name = "place",length = 128)
    private String place;

    @Column(name = "click",length = 8)
    private String click;

    @Column(name = "info",columnDefinition = "text null")
    private String info;


    public Job() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getClick() {
        return click;
    }

    public void setClick(String click) {
        this.click = click;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
