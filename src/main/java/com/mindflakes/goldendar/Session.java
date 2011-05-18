package com.mindflakes.goldendar;

import org.joda.time.*;

/**
 * Created by IntelliJ IDEA.
 * User: nelson
 * Date: 5/16/11
 * Time: 1:00 PM
 * To change this template use File | Settings | File Templates.
 */
public class Session {
    private boolean[] weekdays;
    private LocalTime begin;
    private LocalTime end;
    private String building;
    private String room;


    public Session() {
        weekdays = new boolean[7];
        for (boolean b: weekdays){
            b = false;
        }
    }

    public void toggleDay(int i){
        weekdays[i - 1] = !weekdays[i];
    }

    public void setDayOn(int i){
        weekdays[i - 1] = true;
    }

    public void setDayOff(int i){
        weekdays[i - 1] = false;
    }

    public boolean[] getWeekdays() {
        return weekdays;
    }

    public void setBegin(int h, int m, boolean pm){
        begin = new LocalTime(h,m);
        if (pm){
            begin = begin.plus(Hours.hours(12));
        }
    }

    public void setEnd(int h, int m, boolean pm){
        end = new LocalTime(h,m);
        if (pm){
            end = end.plus(Hours.hours(12));
        }
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }
}
