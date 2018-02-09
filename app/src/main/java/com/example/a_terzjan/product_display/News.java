package com.example.a_terzjan.product_display;

/**
 * Created by a_terzjan on 09.02.2018.
 */

public class News {

    private String info;
    private String more;
    private String image;

    public News(String info, String image, String more ){

        this.info=info;
        this.image = image;
        this.more = more;

    }

    public String getInfo() {
        return this.info;
    }

    public void setInfo(String name) {
        this.info = info;
    }

    public String getMore() {
        return this.more;
    }

    public void setMore(String company) {
        this.more = more;
    }

    public String getImage() {
        return this.image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}