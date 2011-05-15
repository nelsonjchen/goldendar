package com.mindflakes.goldendar;

import org.htmlcleaner.CleanerProperties;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;

/**
 * Created by IntelliJ IDEA.
 * User: nelson
 * Date: 5/15/11
 * Time: 2:28 AM
 * To change this template use File | Settings | File Templates.
 */
public class GoldScraper {
    public Schedule parseContent(String content) throws Exception{
        HtmlCleaner cleaner = new HtmlCleaner();
        CleanerProperties properties = cleaner.getProperties();
        properties.setOmitComments(true);
        TagNode response = cleaner.clean(content);
        TagNode[] schedule = response.getElementsByAttValue("id","ctl00_pageContent_CourseList",true,true);
        TagNode[] tbody = schedule[0].getElementsByName("tbody",false);
        for (int i = 1; i < tbody.length; i++){

        }


        return null;
    }
}
