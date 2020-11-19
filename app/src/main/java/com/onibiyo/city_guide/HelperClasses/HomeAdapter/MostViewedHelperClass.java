package com.onibiyo.city_guide.HelperClasses.HomeAdapter;

public class MostViewedHelperClass {
    int image;
    String title, description;

    public MostViewedHelperClass(int image, String title) {
        this.image = image;
        this.title = title;
    }

    public int getImage() {
        return image;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
}
