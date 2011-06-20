package com.mindflakes.goldendar;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
import org.htmlcleaner.CleanerProperties;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;
import org.joda.time.DateTimeConstants;

import java.util.List;

public class GoldScraper {
    private Schedule schedule;

    public Schedule parseContent(String content) throws Exception{
        HtmlCleaner cleaner = new HtmlCleaner();
        CleanerProperties properties = cleaner.getProperties();
        properties.setOmitComments(true);
        properties.setTransSpecialEntitiesToNCR(true);
        properties.setRecognizeUnicodeChars(true);
        properties.setTransResCharsToNCR(true);

        schedule = new Schedule();

        TagNode response = cleaner.clean(content);

        TagNode[] quarter_option_selected = response.getElementsByAttValue("selected","selected",true,false);
        String raw_quarter = quarter_option_selected[0].getText().toString();
        schedule.setName(raw_quarter);

        TagNode[] schedule_table = response.getElementsByAttValue("id", "ctl00_pageContent_CourseList", true, true);
        @SuppressWarnings("unchecked")
        List<TagNode> tr_course = schedule_table[0].getElementsByName("tbody",false)[0].getChildren();
        for (int i = 1; i < tr_course.size(); i++){
            Course course = new Course();

            TagNode tr = tr_course.get(i);
            TagNode[] title_elements = tr.getElementsByAttValue("style","padding-left:3px;" +
                    " font-weight:bold;" +
                    " font-size:10px;" +
                    " float: left;", true, false);

            TagNode[] meeting_times_list = tr.getElementsByAttValue("id",
                    "ctl00_pageContent_CourseList_ctl0"+ i + "_MeetingTimesList", true, false);
            populateNamesAndNumberFromTitleElement(course,title_elements[0]);
            populateTimesSessionsFromElement(course,meeting_times_list[0]);
            schedule.getCourses().add(course);
        }

        return schedule;
    }

    private void populateTimesSessionsFromElement(Course course, TagNode element) {
        @SuppressWarnings("unchecked")
        List<TagNode> tbody = element.getChildren();
        @SuppressWarnings("unchecked")
        List<TagNode> tr_per_session_kind = tbody.get(0).getChildren();
        for (TagNode tr : tr_per_session_kind){
            Session session = new Session();

            TagNode td = (TagNode) tr.getChildren().get(0);
            TagNode table = (TagNode) td.getElementListByName("table",true).get(0);
            TagNode tbody_nested = (TagNode) table.getChildren().get(0);
            TagNode tr_nested = (TagNode) tbody_nested.getChildren().get(1);
            TagNode week_days_td = (TagNode) tr_nested.getChildren().get(0);
            TagNode times_td = (TagNode) tr_nested.getChildren().get(1);

            String raw_weekday_text = week_days_td.getText().toString();
            raw_weekday_text = raw_weekday_text.replaceAll("&nbsp;"," ");
            raw_weekday_text = StringEscapeUtils.unescapeHtml(raw_weekday_text);

            String[] week_days = StringUtils.split(raw_weekday_text);

            for (String day : week_days){
                char day_char = day.charAt(0);
                switch (day_char){
                    case 'M': session.setDayOn(DateTimeConstants.MONDAY); break;
                    case 'T': session.setDayOn(DateTimeConstants.TUESDAY); break;
                    case 'W': session.setDayOn(DateTimeConstants.WEDNESDAY); break;
                    case 'R': session.setDayOn(DateTimeConstants.THURSDAY); break;
                    case 'F': session.setDayOn(DateTimeConstants.FRIDAY); break;
                    case 'S': session.setDayOn(DateTimeConstants.SATURDAY); break;
                    case 'U': session.setDayOn(DateTimeConstants.SUNDAY); break;
                }
            }

            String raw_time_text = times_td.getText().toString();
            raw_time_text = raw_time_text.replaceAll("&nbsp;"," ");
            raw_time_text = StringEscapeUtils.unescapeHtml(raw_time_text);
            String[] time_text_token = StringUtils.split(raw_time_text," :-");


            int h = new Integer(time_text_token[0]);
            int m = new Integer(time_text_token[1]);
            boolean pm = false;
            if (time_text_token[2].equals("PM")){
                pm = true;
            }
            session.setBegin(h,m,pm);

            h = new Integer(time_text_token[3]);
            m = new Integer(time_text_token[4]);
            pm = time_text_token[5].equals("PM");
            session.setEnd(h,m,pm);

            TagNode location_td = (TagNode) tr_nested.getChildren().get(2);
            @SuppressWarnings("unchecked")
            List<TagNode> location_a = location_td.getElementListByAttValue("class","BuildingLocationLink",true,false);
            String location_raw_text = location_a.get(0).getText().toString();
            String[] location_token = StringUtils.split(location_raw_text);
            session.setBuilding(location_token[0]);
            session.setRoom(location_token[1]);

            course.getSessions().add(session);
        }
    }

    private void populateNamesAndNumberFromTitleElement(Course course, TagNode title_element){
        String title = title_element.getText().toString();
        title = StringEscapeUtils.unescapeHtml(title);
        title = title.trim().replaceAll(" +", " ");
        title = title.trim();

        String[] title_token = StringUtils.split(title, "-");
        String[] course_num_token = StringUtils.split(title_token[0].trim()," ");
        String subject_area = course_num_token[0];
        String course_number = course_num_token[1];
        String name = title_token[1].trim();

        course.setName(name);
        course.setNumber(course_number);
        course.setSubjectArea(subject_area);
    }
}
