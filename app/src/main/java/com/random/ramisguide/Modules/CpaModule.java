package com.random.ramisguide.Modules;

public class CpaModule {
    String img, url;

    public CpaModule(String img, String url) {
        this.img = img;
        this.url = url;
    }

    public CpaModule() {
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
