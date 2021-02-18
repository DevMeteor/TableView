package cn.devmeteor.timetableview;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import cn.devmeteor.tableview.Lesson;
import cn.devmeteor.tableview.LessonView;
import cn.devmeteor.tableview.TableView;


/**
 * @author Meteor
 */
public class MainActivity extends AppCompatActivity {

    private TableView<CustomLesson> tableView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tableView = findViewById(R.id.main_table);
        tableView.setTimes(new String[]{
                "8:10",
                "9:05",
                "10:10",
                "11:05",
                "13:15",
                "14:10",
                "15:05",
                "16:00",
                "16:55",
                "17:50",
                "18:45",
                "19:40"
        }, new String[]{
                "8:55",
                "9:50",
                "10:55",
                "11:50",
                "14:00",
                "14:55",
                "15:50",
                "16:45",
                "17:40",
                "18:35",
                "19:30",
                "20:25"
        });
        try {
            tableView.setWeekStart(new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA).parse("2021-02-15"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        tableView.setLessons(getCustomLessons(), getBgMap(), new LessonView.LessonClickListener<CustomLesson>() {
            @Override
            public void onClick(CustomLesson lesson) {
                Toast.makeText(MainActivity.this, lesson.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private Map<String, Integer> getBgMap() {
        Map<String, Integer> bgMap = new HashMap<>(10);
        bgMap.put("高等数学A2", Color.parseColor("#CC459AFE"));
        bgMap.put("体育", Color.parseColor("#CC4CAF50"));
        bgMap.put("计算机基础及应用2", Color.parseColor("#CC4FAFA7"));
        bgMap.put("英国小说家与原著", Color.parseColor("#CCA8AF4C"));
        bgMap.put("大学外语", Color.parseColor("#CCAF4C7A"));
        bgMap.put("大学物理", Color.parseColor("#CCAF544C"));
        bgMap.put("大学物理实验", Color.parseColor("#CC5FBD2C"));
        bgMap.put("电路分析基础", Color.parseColor("#CCA6E252"));
        bgMap.put("思想道德修养与法律基础", Color.parseColor("#CC5271E2"));
        bgMap.put("电路分析实验", Color.parseColor("#CCE25263"));
        return bgMap;
    }

    private List<Lesson> getLessons() {
        List<Lesson> lessons = new ArrayList<>();
        lessons.add(new Lesson("2018-2019-2", "高等数学A2", "mon", 1, 2, "崇师"));
        lessons.add(new Lesson("2018-2019-2", "体育", "mon", 3, 4, "足球场"));
        lessons.add(new Lesson("2018-2019-2", "计算机基础及应用2", "mon", 5, 6, "行知"));
        lessons.add(new Lesson("2018-2019-2", "英国小说家与原著", "mon", 9, 10, "崇师"));
        lessons.add(new Lesson("2018-2019-2", "大学外语", "tue", 1, 2, "理二"));
        lessons.add(new Lesson("2018-2019-2", "大学物理", "tue", 3, 4, "理二"));
        lessons.add(new Lesson("2018-2019-2", "大学物理实验", "tue", 5, 10, "理二"));
        lessons.add(new Lesson("2018-2019-2", "高等数学A2", "wed", 1, 2, "理二"));
        lessons.add(new Lesson("2018-2019-2", "电路分析基础", "wed", 3, 4, "理二"));
        lessons.add(new Lesson("2018-2019-2", "思想道德修养与法律基础", "thu", 1, 2, "崇师"));
        lessons.add(new Lesson("2018-2019-2", "大学物理", "thu", 5, 6, "理二"));
        lessons.add(new Lesson("2018-2019-2", "电路分析基础", "fri", 1, 2, "理二"));
        lessons.add(new Lesson("2018-2019-2", "大学外语", "fri", 3, 4, "理二"));
        lessons.add(new Lesson("2018-2019-2", "电路分析实验", "fri", 5, 6, "理二"));
        return lessons;
    }

    private List<CustomLesson> getCustomLessons() {
        List<CustomLesson> lessons = new ArrayList<>();
        lessons.add(new CustomLesson("2018-2019-2", "第1周", "高等数学A2", "mon", 1, 2, "崇师", "教师", "日期", "编号"));
        lessons.add(new CustomLesson("2018-2019-2", "第1周", "体育", "mon", 3, 4, "足球场", "教师", "日期", "编号"));
        lessons.add(new CustomLesson("2018-2019-2", "第1周", "计算机基础及应用2", "mon", 5, 6, "行知", "教师", "日期", "编号"));
        lessons.add(new CustomLesson("2018-2019-2", "第1周", "英国小说家与原著", "mon", 9, 10, "崇师", "教师", "日期", "编号"));
        lessons.add(new CustomLesson("2018-2019-2", "第1周", "大学外语", "tue", 1, 2, "理二", "教师", "日期", "编号"));
        lessons.add(new CustomLesson("2018-2019-2", "第1周", "大学物理", "tue", 3, 4, "理二", "教师", "日期", "编号"));
        lessons.add(new CustomLesson("2018-2019-2", "第1周", "大学物理实验", "tue", 5, 10, "理二", "教师", "日期", "编号"));
        lessons.add(new CustomLesson("2018-2019-2", "第1周", "高等数学A2", "wed", 1, 2, "理二", "教师", "日期", "编号"));
        lessons.add(new CustomLesson("2018-2019-2", "第1周", "电路分析基础", "wed", 3, 4, "理二", "教师", "日期", "编号"));
        lessons.add(new CustomLesson("2018-2019-2", "第1周", "思想道德修养与法律基础", "thu", 1, 2, "崇师", "教师", "日期", "编号"));
        lessons.add(new CustomLesson("2018-2019-2", "第1周", "大学物理", "thu", 5, 6, "理二", "教师", "日期", "编号"));
        lessons.add(new CustomLesson("2018-2019-2", "第1周", "电路分析基础", "fri", 1, 2, "理二", "教师", "日期", "编号"));
        lessons.add(new CustomLesson("2018-2019-2", "第1周", "大学外语", "fri", 3, 4, "理二", "教师", "日期", "编号"));
        lessons.add(new CustomLesson("2018-2019-2", "第1周", "电路分析实验", "fri", 5, 6, "理二", "教师", "日期", "编号"));
        return lessons;
    }

}
