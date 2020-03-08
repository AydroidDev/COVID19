package com.Corona.Virus.ModelClasses;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Prevention implements Serializable {
    private long Rank;
    private String Prevention,Title,Image;

    public Prevention(long rank, String prevention, String title, String image) {
        Rank = rank;
        Prevention = prevention;
        Title = title;
        Image = image;
    }

    public Prevention() {
    }

    public long getRank() {
        return Rank;
    }

    public void setRank(long rank) {
        Rank = rank;
    }

    public String getPrevention() {
        return Prevention;
    }

    public void setPrevention(String prevention) {
        Prevention = prevention;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }
}
