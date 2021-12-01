package com.ashpex.rssfeed;

public class Newspaper {
    private int resourceImage;
    private String name;

    public Newspaper(int resourceImage, String name) {
        this.resourceImage = resourceImage;
        this.name = name;
    }

    public int getResourceImage() {
        return resourceImage;
    }

    public String getName() {
        return name;
    }

    public void setResourceImage(int resourceImage) {
        this.resourceImage = resourceImage;
    }

    public void setName(String name) {
        this.name = name;
    }
}
