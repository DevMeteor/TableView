# TableView

[中文](README.md)|English

TableView is an Android timetable UI library, using this library to achieve the basic course display, and the timetable UI can be basically customized.

### Functional features

- Flexible definition of data sources
- Total number of single-day courses customizable
- Automatically set the background color of the course block
- The colors of each part of the TableView can be set freely
- Written in native components, regardless of dependency conflicts

### Effect preview

![ex](images/ex.jpg)

### Use method

#### 1.Add dependency

​	Add the following to the build.gradle in the root directory of the project

```gradle
allprojects {
    repositories {
        ...
        maven{url 'https://jitpack.io'}
    }
}
```

​	Add the following to the build.gradle under app

```gradle
implementation 'com.github.DevMeteor:TableView:1.0'
```

#### 2.Attribute definition

|        Attribute        |  format   | description                                                  |
| :---------------------: | :-------: | ------------------------------------------------------------ |
|    tv_count_per_day     |  integer  | Total number of courses per day (required)                   |
|    tv_resolve_flags     | reference | An array of strings, corresponding to Monday to Sunday, for parsing Lessons, default to ["mon", "tue", "wed", "thur", "fri", "sat", "sun"]. It is recommended that you set this on your own. |
|  tv_indicator_bg_color  |   color   | Sidebar background color                                     |
| tv_indicator_text_color |   color   | Sidebar textcolor                                            |
|    tv_week_bg_color     |   color   | Week column background color                                 |
|   tv_week_text_color    |   color   | Week column text color                                       |
|  tv_lesson_text_color   |   color   | Text color of the course block                               |

​	**Note: the above background color is transparent by default, and the text color defaults to black. If you want to set the entire schedule background, you can directly set android:background= "xxx".**

The following parameters can be overridden in the **dimen** resource file

|      Attribute       | description                                                  |
| :------------------: | ------------------------------------------------------------ |
|  height_per_lesson   | The height of each course block and the height of the component is actually determined by this attribute and the total number of courses per day |
|   lesson_text_size   | Text size of the course block                                |
| lesson_border_radius | Radius of the four corners of the course block               |

#### 3.Basic use

​	Add the following to the layout file

```xml
<cn.devmeteor.tableview.TableView
        android:id="@+id/main_table"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        app:tv_count_per_day="10"
        android:background="#1F8F8F8F"
        app:tv_indicator_bg_color="#4DFFFFFF"
        app:tv_week_bg_color="#4DFFFFFF"
        app:tv_resolve_flags="@array/resolve_flags"/>
```

​	When you use it, you need to convert the bean file to a Lesson class or extends the Lesson class, and the constructor of the Lesson class is

```java
/**
     *
     * @param term
     * @param week
     * @param name Lesson name
     * @param weekday Items corresponding to tv_resolve_flags
     * @param start Start from ...
     * @param end Till...
     * @param place Place of lesson
     */
public Lesson(String term, String week, String name, String weekday, int start, int end, String place)
```

​	若需要可使bean文件继承Lesson类，示例：

```java
public class CustomLesson extends Lesson {

    private String teacher; 
    private String date; 
    private String identifier; //Course number

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
```

​	在Activity中使用

```java
@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ...
        tableView=findViewById(R.id.main_table);
        //
        tableView.setLessons(getLessons(),getBgMap(), new LessonView.LessonClickListener() {
            @Override
            public void onClick(Lesson lesson) {
            	Toast.makeText(MainActivity.this,lesson.toString(),Toast.LENGTH_LONG).show();
            }
        });
    }

//bgMap example
private Map<String,Integer> getBgMap(){
        Map<String,Integer> bgMap=new HashMap<>();
        bgMap.put("高等数学A2", Color.parseColor("#ccFFFFF0"));
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

//Simulated generation of course data, custom course bean can be replaced directly
private List<Lesson> getLessons(){
        List<Lesson> lessons=new ArrayList<>();
        lessons.add(new Lesson("2018-2019-2", "第1周", "高等数学A2","mon",1,2, "崇师"));
        lessons.add(new Lesson("2018-2019-2", "第1周", "体育","mon",3,4, "足球场"));
        lessons.add(new Lesson("2018-2019-2", "第1周", "计算机基础及应用2","mon",5,6, "行知"));
        lessons.add(new Lesson("2018-2019-2", "第1周", "英国小说家与原著","mon",9,10, "崇师"));
        lessons.add(new Lesson("2018-2019-2", "第1周", "大学外语","tue",1,2, "理二"));
        lessons.add(new Lesson("2018-2019-2", "第1周", "大学物理","tue",3,4, "理二"));
        lessons.add(new Lesson("2018-2019-2", "第1周", "大学物理实验","tue",5,10, "理二"));
        lessons.add(new Lesson("2018-2019-2", "第1周", "高等数学A2","wed",1,2, "理二"));
        lessons.add(new Lesson("2018-2019-2", "第1周", "电路分析基础","wed",3,4, "理二"));
        lessons.add(new Lesson("2018-2019-2", "第1周", "思想道德修养与法律基础","thu",1,2, "崇师"));
        lessons.add(new Lesson("2018-2019-2", "第1周", "大学物理","thu",5,6, "理二"));
        lessons.add(new Lesson("2018-2019-2", "第1周", "电路分析基础","fri",1,2, "理二"));
        lessons.add(new Lesson("2018-2019-2", "第1周", "大学外语","fri",3,4, "理二"));
        lessons.add(new Lesson("2018-2019-2", "第1周", "电路分析实验","fri",5,6, "理二"));
        return lessons;
    }

```

#### 4.setLessons()

```java
/**
     * 
     * @param lessons
     * @param bgMap The course block corresponds to the color, and the course name corresponds to the color value. If it is not set, the random color will be used to ensure the same color of the course with the same name, but the color of the course block with the same name may be similar and cannot be distinguished intuitively. It is recommended to add bgMap by yourself.
     * @param lessonClickListener Course block click event
     */
public void setLessons(List<? extends Lesson> lessons)
public void setLessons(List<? extends Lesson> lessons, Map<String, Integer> bgMap)
public void setLessons(List<? extends Lesson> lessons, LessonView.LessonClickListener lessonClickListener)
public void setLessons(List<? extends Lesson> lessons, Map<String, Integer> bgMap, LessonView.LessonClickListener lessonClickListener)
```
