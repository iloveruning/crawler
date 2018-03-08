package com.cll.crawler.entity;

import org.jeecgframework.poi.excel.annotation.Excel;

import java.io.Serializable;
import java.util.Date;

/**
 * @author chenliangliang
 * @date 2017/12/26
 */
public class WeChat implements Serializable {

    //=====================文章信息=========================================

    @Excel(name = "文章标题", height = 20, width = 30, isImportField = "true_st")
    private String title;

    @Excel(name = "推送日期", format = "yyyy/MM/dd", isImportField = "true_st", width = 20)
    private Date time;

    //=======================粉丝数=============================================

    @Excel(name = "涨粉数", height = 20, width = 30, isImportField = "true_st")
    private int inFans;

    @Excel(name = "扫码关注人数", height = 20, width = 30, isImportField = "true_st")
    private int scan;

    @Excel(name = "掉粉数", height = 20, width = 30, isImportField = "true_st")
    private int deFans;

    @Excel(name = "净增粉丝数", height = 20, width = 30, isImportField = "true_st")
    private int addFans;

    @Excel(name = "粉丝数", height = 20, width = 30, isImportField = "true_st")
    private int fans;

    //======================文章质量======================================

    @Excel(name = "送达人数", height = 20, width = 30, isImportField = "true_st")
    private int send;

    @Excel(name = "图文阅读人数", height = 20, width = 30, isImportField = "true_st")
    private int read;

    @Excel(name = "图文打开率", height = 20, width = 30, isImportField = "true_st")
    private double open;

    @Excel(name = "分享总人数", height = 20, width = 30, isImportField = "true_st")
    private int share;

    @Excel(name = "收藏人数", height = 20, width = 30, isImportField = "true_st")
    private int cellect;

    //=====================阅读量========================================

    @Excel(name = "公众号内打开人数", height = 20, width = 30, isImportField = "true_st")
    private int innerOpen;

    @Excel(name = "历史消息阅读人数", height = 20, width = 30, isImportField = "true_st")
    private int hisRead;

    @Excel(name = "从朋友圈阅读人数", height = 20, width = 30, isImportField = "true_st")
    private int freRead;

    @Excel(name = "通过转发阅读人数", height = 20, width = 30, isImportField = "true_st")
    private int shareRead;

    @Excel(name = "来自其他阅读人数", height = 20, width = 30, isImportField = "true_st")
    private int otherRead;

    //===========================分享到朋友圈============================================

    @Excel(name = "从公众号分享朋友圈人数", height = 20, width = 30, isImportField = "true_st")
    private int innerShare;

    @Excel(name = "从朋友圈分享朋友圈人数", height = 20, width = 30, isImportField = "true_st")
    private int freShare;

    @Excel(name = "从其他渠道分享朋友圈人数", height = 20, width = 30, isImportField = "true_st")
    private int otherShare;


    public WeChat() {
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public int getInFans() {
        return inFans;
    }

    public void setInFans(int inFans) {
        this.inFans = inFans;
    }

    public int getScan() {
        return scan;
    }

    public void setScan(int scan) {
        this.scan = scan;
    }

    public int getDeFans() {
        return deFans;
    }

    public void setDeFans(int deFans) {
        this.deFans = deFans;
    }

    public int getAddFans() {
        return addFans;
    }

    public void setAddFans(int addFans) {
        this.addFans = addFans;
    }

    public int getFans() {
        return fans;
    }

    public void setFans(int fans) {
        this.fans = fans;
    }

    public int getSend() {
        return send;
    }

    public void setSend(int send) {
        this.send = send;
    }

    public int getRead() {
        return read;
    }

    public void setRead(int read) {
        this.read = read;
    }

    public double getOpen() {
        return open;
    }

    public void setOpen(double open) {
        this.open = open;
    }

    public int getShare() {
        return share;
    }

    public void setShare(int share) {
        this.share = share;
    }

    public int getCellect() {
        return cellect;
    }

    public void setCellect(int cellect) {
        this.cellect = cellect;
    }

    public int getInnerOpen() {
        return innerOpen;
    }

    public void setInnerOpen(int innerOpen) {
        this.innerOpen = innerOpen;
    }

    public int getHisRead() {
        return hisRead;
    }

    public void setHisRead(int hisRead) {
        this.hisRead = hisRead;
    }

    public int getFreRead() {
        return freRead;
    }

    public void setFreRead(int freRead) {
        this.freRead = freRead;
    }

    public int getShareRead() {
        return shareRead;
    }

    public void setShareRead(int shareRead) {
        this.shareRead = shareRead;
    }

    public int getOtherRead() {
        return otherRead;
    }

    public void setOtherRead(int otherRead) {
        this.otherRead = otherRead;
    }

    public int getInnerShare() {
        return innerShare;
    }

    public void setInnerShare(int innerShare) {
        this.innerShare = innerShare;
    }

    public int getFreShare() {
        return freShare;
    }

    public void setFreShare(int freShare) {
        this.freShare = freShare;
    }

    public int getOtherShare() {
        return otherShare;
    }

    public void setOtherShare(int otherShare) {
        this.otherShare = otherShare;
    }


    @Override
    public String toString() {
        return new StringBuilder().append("WeChat{").append("title='").append(title).append('\'').append(", time=").append(time).append(", inFans=").append(inFans).append(", scan=").append(scan).append(", deFans=").append(deFans).append(", addFans=").append(addFans).append(", fans=").append(fans).append(", send=").append(send).append(", read=").append(read).append(", open='").append(open).append('\'').append(", share=").append(share).append(", cllect=").append(cellect).append(", innerOpen=").append(innerOpen).append(", hisRead=").append(hisRead).append(", freRead=").append(freRead).append(", shareRead=").append(shareRead).append(", otherRead=").append(otherRead).append(", innerShare=").append(innerShare).append(", freShare=").append(freShare).append(", otherShare=").append(otherShare).append('}').toString();
    }
}
