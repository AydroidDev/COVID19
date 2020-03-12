package com.know.Virus.ModelClasses;

public class gridContent {
    int imageOfTheVirus;
    String titleOfTheImage;

    public gridContent(int imageOfTheVirus, String titleOfTheImage) {
        this.imageOfTheVirus = imageOfTheVirus;
        this.titleOfTheImage = titleOfTheImage;
    }

    public int getImageOfTheVirus() {
        return imageOfTheVirus;
    }

    public void setImageOfTheVirus(int imageOfTheVirus) {
        this.imageOfTheVirus = imageOfTheVirus;
    }

    public String getTitleOfTheImage() {
        return titleOfTheImage;
    }

    public void setTitleOfTheImage(String titleOfTheImage) {
        this.titleOfTheImage = titleOfTheImage;
    }
}
