package com.know.Virus.ModelClasses;

import java.io.Serializable;

public class Myths implements Serializable {
    private String Myth,Reality,Image;
    private long Rank;

    public Myths(String myth, String reality, String image, long rank) {
        Myth = myth;
        Reality = reality;
        Image = image;
        Rank = rank;
    }

    public long getRank() {
        return Rank;
    }

    public void setRank(long rank) {
        Rank = rank;
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
