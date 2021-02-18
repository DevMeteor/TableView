package cn.devmeteor.tableview;

public class Lesson {

    private String term;
    private String name;
    private String weekday;
    private int start;
    private int end;
    private String place;

    public String getTerm() {
        return term;
    }


    public String getName() {
        return name;
    }

    public String getWeekday() {
        return weekday;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }

    public String getPlace() {
        return place;
    }


    public Lesson(String term, String name, String weekday, int start, int end, String place) {
        this.term = term;
        this.name = name;
        this.weekday = weekday;
        this.start = start;
        this.end = end;
        this.place = place;
    }

    @Override
    public String toString() {
        return term+name+place;
    }
}
