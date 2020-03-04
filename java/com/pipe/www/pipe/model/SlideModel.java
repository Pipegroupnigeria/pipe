package com.pipe.www.pipe.model;

public class SlideModel {

    int imageres;
    String title;
    String subtext;


    public SlideModel(int imageres, String title, String subtext) {
        this.imageres = imageres;
        this.title = title;
        this.subtext = subtext;
    }


    public int getImageres() {
        return imageres;
    }

    public void setImageres(int imageres) {
        this.imageres = imageres;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtext() {
        return subtext;
    }

    public void setSubtext(String subtext) {
        this.subtext = subtext;
    }
}
