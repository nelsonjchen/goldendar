package com.mindflakes.goldendar;

import java.util.ArrayList;

public class Schedule {
    private ArrayList<Course> courses;
    private String name;

    public Schedule() {
        courses = new ArrayList<Course>();
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
