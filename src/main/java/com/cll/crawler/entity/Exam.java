package com.cll.crawler.entity;

import java.io.Serializable;

/**
 * @author chenliangliang
 * @date 2018/3/1
 */
public class Exam implements Serializable {

    private Integer id;

    private Integer type;

    private String question;

    private String ansA;

    private String ansB;

    private String ansC;

    private String ansD;

    private String ansE;

    private String ansF;

    private String answer;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnsA() {
        return ansA;
    }

    public void setAnsA(String ansA) {
        this.ansA = ansA;
    }

    public String getAnsB() {
        return ansB;
    }

    public void setAnsB(String ansB) {
        this.ansB = ansB;
    }

    public String getAnsC() {
        return ansC;
    }

    public void setAnsC(String ansC) {
        this.ansC = ansC;
    }

    public String getAnsD() {
        return ansD;
    }

    public void setAnsD(String ansD) {
        this.ansD = ansD;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getAnsE() {
        return ansE;
    }

    public void setAnsE(String ansE) {
        this.ansE = ansE;
    }

    public String getAnsF() {
        return ansF;
    }

    public void setAnsF(String ansF) {
        this.ansF = ansF;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Exam{" +
                "id=" + id +
                ", type=" + type +
                ", question='" + question + '\'' +
                ", ansA='" + ansA + '\'' +
                ", ansB='" + ansB + '\'' +
                ", ansC='" + ansC + '\'' +
                ", ansD='" + ansD + '\'' +
                ", ansE='" + ansE + '\'' +
                ", ansF='" + ansF + '\'' +
                ", answer='" + answer + '\'' +
                '}';
    }
}
