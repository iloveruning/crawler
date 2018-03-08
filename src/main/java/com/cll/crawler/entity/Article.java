package com.cll.crawler.entity;

import javax.persistence.*;

/**
 * @author chenliangliang
 * @date: 2017/12/6
 */
@Entity
@Table(name = "article")
public class Article {

    @Column(name = "author",length = 32)
    private String author;

    @Column(name = "title",length = 128)
    private String title;

    @Column(name = "cover")
    private String cover;

    @Column(name = "url")
    private String url;

    @Column(name = "digest",length = 128)
    private String digest;

    @Column(name = "fileid")
    private Integer fileid;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDigest() {
        return digest;
    }

    public void setDigest(String digest) {
        this.digest = digest;
    }

    public Integer getFileid() {
        return fileid;
    }

    public void setFileid(Integer fileid) {
        this.fileid = fileid;
    }

    public Article() {
    }

    public Article(String author, String title, String cover, String url, String digest, Integer fileid) {
        this.author = author;
        this.title = title;
        this.cover = cover;
        this.url = url;
        this.digest = digest;
        this.fileid = fileid;
    }

    @Override
    public String toString() {
        return "Article{" +
                "author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", cover='" + cover + '\'' +
                ", url='" + url + '\'' +
                ", digest='" + digest + '\'' +
                ", fileid=" + fileid +
                '}';
    }
}
