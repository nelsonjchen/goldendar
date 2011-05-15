package com.mindflakes.goldendar;

/**
 * Created by IntelliJ IDEA.
 * User: nelson
 * Date: 5/15/11
 * Time: 2:25 AM
 * To change this template use File | Settings | File Templates.
 */
public class Course {
    private String name;
    private String number;
    private String subjectArea;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getSubjectArea() {
        return subjectArea;
    }

    public void setSubjectArea(String subjectArea) {
        this.subjectArea = subjectArea;
    }

    public String toString(){
        return subjectArea + " " + number + " "+ "-" + " "+ name;
    }
}
