package com.know.Virus.ModelClasses;

import java.io.Serializable;
import java.util.List;

public class Stories implements Serializable {
    private String name,ImageUrl;
    private List<String> url;
    private long timestamp;

    public Stories(List<String> url, String name, long timestamp,String ImageUrl) {

        this.url = url;
        this.name = name;
        this.timestamp = timestamp;
        this.ImageUrl = ImageUrl;
    }


    public Stories() {
    }

    public List<String> getUrl() {
        return url;
    }

    public void setUrl(List<String> url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public String getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        ImageUrl = imageUrl;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
