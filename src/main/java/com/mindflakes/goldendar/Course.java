package com.mindflakes.goldendar;

import java.util.ArrayList;


public class Course {
    private String name;
    private String number;
    private String subjectArea;
    private ArrayList<Session> sessions;

    public Course(){
        sessions = new ArrayList<Session>();
    }

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

    public ArrayList<Session> getSessions() {
        return sessions;
    }

    public String toString(){
        return subjectArea + " " + number + " "+ "-" + " "+ name;
    }

}
