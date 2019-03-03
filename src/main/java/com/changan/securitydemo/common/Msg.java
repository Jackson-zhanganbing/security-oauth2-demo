package com.changan.securitydemo.common;

public class Msg<T> {
    private String title;
    private T content;
    private String etraInfo;

    public Msg(String title, T content, String etraInfo) {
        super();
        this.title = title;
        this.content = content;
        this.etraInfo = etraInfo;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public T getContent() {
        return content;
    }
    public void setContent(T content) {
        this.content = content;
    }
    public String getEtraInfo() {
        return etraInfo;
    }
    public void setEtraInfo(String etraInfo) {
        this.etraInfo = etraInfo;
    }

}