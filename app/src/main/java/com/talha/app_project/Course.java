package com.talha.app_project;

public class Course {
    public String title;
    public String author;
    public float rating;
    public int progress;
    public int imageRes;

    public Course(String title, String author, float rating, int progress, int imageRes) {
        this.title = title;
        this.author = author;
        this.rating = rating;
        this.progress = progress;
        this.imageRes = imageRes;
    }
}
