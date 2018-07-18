package com.example.a_terzjan.product_display.Product.Format.level_2;

/**
 * Created by a_terzjan on 01.03.2018.
 */

public class DetailsDescription {


    private String parameters;
    private String value;



    public DetailsDescription(String parameters, String value){
        this.parameters=parameters;
        this.value=value;

    }

    public String getParameters() {
        return this.parameters;
    }

    public void setParameters(String parameters) {
        this.parameters = parameters;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}