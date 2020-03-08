package com.Corona.Virus.ModelClasses;

import java.io.Serializable;

public class Myths implements Serializable {
    private String Myth,Reality,Image;

    public Myths(String myth, String reality, String image) {
        Myth = myth;
        Reality = reality;
        Image = image;
    }

    public Myths() {
    }

    public String getMyth() {
        return Myth;
    }

    public void setMyth(String myth) {
        Myth = myth;
    }

    public String getReality() {
        return Reality;
    }

    public void setReality(String reality) {
        Reality = reality;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }
}
