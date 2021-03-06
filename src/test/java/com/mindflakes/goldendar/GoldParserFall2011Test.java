package com.mindflakes.goldendar;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.number.OrderingComparison.greaterThan;
import static org.junit.Assert.assertThat;

public class GoldParserFall2011Test extends Base {

    @Test
    public void testTrue() {
        assertThat(true, is(true));
    }

    @Test
    public void testFalse() {
        assertThat(false, is(not(true)));
    }

    @Test
    public void testFileIsRead() throws Exception {
        StringBuffer content = loadContent("fall2011.html");
        assertThat(content.toString().length(), is(greaterThan(10)));
    }

    @Test
    public void testSchedule() throws Exception {
        StringBuffer content = loadContent("fall2011.html");
        GoldScraper scraper = new GoldScraper();
        scraper.parseContent(content.toString());
    }

    @Test
    public void testScheduleIsFall2011() throws Exception {
        Schedule schedule = getSchedule();
        String name = schedule.getName();
        assertThat(name, is("Fall 2011"));
    }


    @Test
    public void testFirstClassIsCS178() throws Exception {
        Schedule schedule = getSchedule();
        Course course = schedule.getCourses().get(0);
        String subject_area = course.getSubjectArea();
        String number = course.getNumber();
        String name = course.getName();
        assertThat(subject_area, is("CMPSC"));
        assertThat(number, is("138"));
        assertThat(name, is("AUT & FORML LANG"));
    }

    @Test
    public void testSecondClassIsCS176A() throws Exception {
        Schedule schedule = getSchedule();
        Course course = schedule.getCourses().get(1);
        String subject_area = course.getSubjectArea();
        String number = course.getNumber();
        String name = course.getName();
        assertThat(subject_area, is("CMPSC"));
        assertThat(number, is("176A"));
        assertThat(name, is("COMP COMM NETWORKS"));
    }

    @Test
    public void testSecondClassIsMATH5C() throws Exception {
        Schedule schedule = getSchedule();
        Course course = schedule.getCourses().get(2);
        String subject_area = course.getSubjectArea();
        String number = course.getNumber();
        String name = course.getName();
        assertThat(subject_area, is("MATH"));
        assertThat(number, is("5C"));
        assertThat(name, is("VECTOR CALCULUS 2"));
    }

    private Schedule getSchedule() throws Exception {
        StringBuffer content = loadContent("fall2011.html");
        GoldScraper scraper = new GoldScraper();
        return scraper.parseContent(content.toString());
    }

}
