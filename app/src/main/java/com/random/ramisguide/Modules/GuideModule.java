package com.random.ramisguide.Modules;

public class GuideModule {
    String title;
    String Content;
    String img;

    public GuideModule(String title, String content, String img) {
        this.title = title;
        Content = content;
        this.img = img;
    }

    public GuideModule() {
    }

    public String gettitle() {
        return title;
    }

    public void settitle(String name) {
        this.title = name;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
