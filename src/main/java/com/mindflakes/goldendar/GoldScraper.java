package com.mindflakes.goldendar;

import org.htmlcleaner.CleanerProperties;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: nelson
 * Date: 5/15/11
 * Time: 2:28 AM
 * To change this template use File | Settings | File Templates.
 */
public class GoldScraper {
    Schedule schedule;

    public Schedule parseContent(String content) throws Exception{
        HtmlCleaner cleaner = new HtmlCleaner();
        CleanerProperties properties = cleaner.getProperties();
        properties.setOmitComments(true);
        properties.setTransSpecialEntitiesToNCR(true);
        properties.setRecognizeUnicodeChars(true);
        properties.setTransResCharsToNCR(true);

        schedule = new Schedule();

        TagNode response = cleaner.clean(content);
        TagNode[] schedule_table = response.getElementsByAttValue("id","ctl00_pageContent_CourseList",true,true);
        List<TagNode> tr_course = schedule_table[0].getElementsByName("tbody",false)[0].getChildren();
        for (int i = 1; i < tr_course.size(); i++){
            Course course = new Course();
            TagNode tr = tr_course.get(i);
            TagNode[] title_elements = tr.getElementsByAttValue("style","padding-left:3px;" +
                    " font-weight:bold;" +
                    " font-size:10px;" +
                    " float: left;", true, false);
            String title = title_elements[0].getText().toString();
            course.setName(title);
            schedule.getCourses().add(course);
        }


        return schedule;
    }
}
