package com.random.ramisguide.Modules;

public class FeatrudeModule {
    String img, name, url;

    public FeatrudeModule(String img, String name, String url) {
        this.img = img;
        this.name = name;
        this.url = url;
    }

    public FeatrudeModule() {
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
