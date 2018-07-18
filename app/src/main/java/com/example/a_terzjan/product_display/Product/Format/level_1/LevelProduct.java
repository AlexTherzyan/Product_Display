package com.example.a_terzjan.product_display.product_level.Format.level_1;

import java.util.HashMap;

/**
 * Created by a_terzjan on 14.02.2018.
 */

public class LevelProduct {

    private String name;
    private String image;
    private String id;

    public LevelProduct(String image, String name){
  //      super();
        this.name=name;
        this.image = image;



//        super.put("title", title);
//        super.put("id", id);
//        super.put("image", image);

    }

    public String getTitle() {
        return this.name;
    }

    public void setTitle(String name) {
        this.name = name;
    }


    public String getImage() {
        return this.image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}




