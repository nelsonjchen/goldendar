package com.mindflakes.goldendar;

import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.number.OrderingComparison.greaterThan;
import static org.junit.Assert.assertThat;

public class GoldCalendarFall2011Test extends Base {

    private String getScheduleString() throws IOException{
        return loadContent("fall2011.html").toString();
    }
}
