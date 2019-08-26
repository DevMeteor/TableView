package cn.devmeteor.timetableview;

import cn.devmeteor.tableview.Lesson;

public class CustomLesson extends Lesson {

    private String teacher;
    private String date;
    private String identifier;

    public CustomLesson(String term, String week, String name, String weekday, int start, int end, String place, String teacher, String date, String identifier) {
        super(term, week, name, weekday, start, end, place);
        this.teacher = teacher;
        this.date = date;
        this.identifier = identifier;
    }


    public String getTeacher() {
        return teacher;
    }

    public String getDate() {
        return date;
    }

    public String getIdentifier() {
        return identifier;
    }

    @Override
    public String toString() {
        return getTerm()+getWeek()+getName()+getWeekday()+getStart()+getEnd()+getPlace()+teacher+date+identifier;
    }
}
