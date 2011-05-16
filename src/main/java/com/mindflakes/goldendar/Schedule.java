package com.mindflakes.goldendar;

import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: nelson
 * Date: 5/15/11
 * Time: 2:29 AM
 * To change this template use File | Settings | File Templates.
 */
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
