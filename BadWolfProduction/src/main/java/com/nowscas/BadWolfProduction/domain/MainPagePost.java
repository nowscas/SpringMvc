package com.nowscas.BadWolfProduction.domain;

import javax.persistence.*;

/**
 * Сущность записи поста в БД.
 */
@Entity
public class MainPagePost {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String postHeader;
    private String postBody;
    private String youtubeLink;
    private String filename;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User author;

    public MainPagePost(String postHeader, String postBody, User author) {
        this.postHeader = postHeader;
        this.postBody = postBody;
        this.author = author;
    }

    public MainPagePost() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPostHeader() {
        return postHeader;
    }

    public void setPostHeader(String postHeader) {
        this.postHeader = postHeader;
    }

    public String getPostBody() {
        return postBody;
    }

    public void setPostBody(String postBody) {
        this.postBody = postBody;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getAuthorName() {
        return author != null ? author.getUsername() : "unknown author";
    }

    public String getYoutubeLink() {
        return youtubeLink;
    }

    public void setYoutubeLink(String youtubeLink) {
        this.youtubeLink = youtubeLink;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
}
