package com.cll.crawler.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author chenliangliang
 * @date: 2017/12/17
 */
@Entity
@Table(name = "grade")
public class Grade {

    @Id
    @Column(name = "sno",length = 16)
    private String sno;

    @Column(name = "cno",length = 8)
    private String cno;

    @Column(name = "cname",length = 64)
    private String cname;

    @Column(name = "clazz",length = 16)
    private String clazz;

    @Column(name = "credit",length = 4)
    private String credit;

    @Column(name = "kpi",length = 4)
    private String kpi;

    @Column(name = "score",length = 4)
    private String score;

    @Column(name = "detail",length = 64)
    private String detail;

    @Column(name = "semester",length = 4)
    private String semester;

    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
    }

    public String getCno() {
        return cno;
    }

    public void setCno(String cno) {
        this.cno = cno;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    public String getCredit() {
        return credit;
    }

    public void setCredit(String credit) {
        this.credit = credit;
    }

    public String getKpi() {
        return kpi;
    }

    public void setKpi(String kpi) {
        this.kpi = kpi;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public Grade() {
    }

    @Override
    public String toString() {
        return "Grade{" +
                "sno='" + sno + '\'' +
                ", cno='" + cno + '\'' +
                ", cname='" + cname + '\'' +
                ", clazz='" + clazz + '\'' +
                ", credit='" + credit + '\'' +
                ", kpi='" + kpi + '\'' +
                ", score='" + score + '\'' +
                ", detail='" + detail + '\'' +
                ", semester='" + semester + '\'' +
                '}';
    }

    public Grade(String sno, String cno, String cname, String clazz, String credit, String kpi, String score, String detail, String semester) {
        this.sno = sno;
        this.cno = cno;
        this.cname = cname;
        this.clazz = clazz;
        this.credit = credit;
        this.kpi = kpi;
        this.score = score;
        this.detail = detail;
        this.semester = semester;
    }
}
