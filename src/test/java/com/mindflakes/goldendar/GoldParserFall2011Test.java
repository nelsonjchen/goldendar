package com.mindflakes.goldendar;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.number.OrderingComparison.greaterThan;
import static org.junit.Assert.assertThat;

/**
 * Created by IntelliJ IDEA.
 * User: nelson
 * Date: 5/14/11
 * Time: 6:12 PM
 * To change this template use File | Settings | File Templates.
 */
public class GoldParserFall2011Test extends Base {

    @Test
    public void testTrue(){
        assertThat(true,is(true));
    }

    @Test
    public void testFalse(){
        assertThat(false,is(not(true)));
    }

    @Test
    public void testFileIsRead() throws Exception{
        StringBuffer content = loadContent("fall2011.html");
        assertThat(content.toString().length(),is(greaterThan(10)));
    }

    @Test
    public void testSchedule() throws Exception {
        StringBuffer content = loadContent("fall2011.html");
        GoldScraper scraper = new GoldScraper();
        scraper.parseContent(content.toString());
    }

    @Test
    public void testFirstClassIsCS178() throws Exception {
        StringBuffer content = loadContent("fall2011.html");
        GoldScraper scraper = new GoldScraper();
        Schedule schedule = scraper.parseContent(content.toString());
        Course course = schedule.getCourses().get(0);
        String subject_area = course.getSubjectArea();
        String number = course.getNumber();
        String name = course.getName();
        assertThat(subject_area,is("CMPSC"));
        assertThat(number,is("138"));
        assertThat(name,is("AUT & FORML LANG"));


    }

}
