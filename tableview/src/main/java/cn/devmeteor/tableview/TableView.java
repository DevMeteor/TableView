package cn.devmeteor.tableview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

/**
 * @author Meteor
 */
public class TableView<T extends Lesson> extends LinearLayout {

    private RelativeLayout mon;
    private RelativeLayout tue;
    private RelativeLayout wed;
    private RelativeLayout thu;
    private RelativeLayout fri;
    private RelativeLayout sat;
    private RelativeLayout sun;
    private TextView tvMon;
    private TextView tvTue;
    private TextView tvWed;
    private TextView tvThu;
    private TextView tvFri;
    private TextView tvSat;
    private TextView tvSun;
    private LinearLayout indicatorCon;

    private int countPerDay;
    private int lessonTextColor;
    private String[] flags;

    public TableView(Context context) {
        this(context, null);
    }

    public TableView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TableView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        if (attrs == null) {
            return;
        }
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TableView);
        countPerDay = typedArray.getInteger(R.styleable.TableView_tv_count_per_day, 0);
        int arrId = typedArray.getResourceId(R.styleable.TableView_tv_resolve_flags, 0);
        if (arrId != 0) {
            flags = context.getResources().getStringArray(arrId);
        }
        int indicatorBgColor = typedArray.getColor(R.styleable.TableView_tv_indicator_bg_color, Color.TRANSPARENT);
        int indicatorTextColor = typedArray.getColor(R.styleable.TableView_tv_indicator_text_color, Color.BLACK);
        int weekBgColor = typedArray.getColor(R.styleable.TableView_tv_week_bg_color, Color.TRANSPARENT);
        int weekTextColor = typedArray.getColor(R.styleable.TableView_tv_week_text_color, Color.BLACK);
        lessonTextColor = typedArray.getColor(R.styleable.TableView_tv_lesson_text_color, Color.WHITE);
        typedArray.recycle();
        setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (int) (countPerDay * context.getResources().getDimension(R.dimen.height_per_lesson))));
        View view = LayoutInflater.from(getContext()).inflate(R.layout.item_table_view, this, false);
        addView(view);
        mon = view.findViewById(R.id.item_table_view_mon);
        tue = view.findViewById(R.id.item_table_view_tue);
        wed = view.findViewById(R.id.item_table_view_wed);
        thu = view.findViewById(R.id.item_table_view_thu);
        fri = view.findViewById(R.id.item_table_view_fri);
        sat = view.findViewById(R.id.item_table_view_sat);
        sun = view.findViewById(R.id.item_table_view_sun);
        tvMon = view.findViewById(R.id.item_table_view_tv_mon);
        tvTue = view.findViewById(R.id.item_table_view_tv_tue);
        tvWed = view.findViewById(R.id.item_table_view_tv_wed);
        tvThu = view.findViewById(R.id.item_table_view_tv_thu);
        tvFri = view.findViewById(R.id.item_table_view_tv_fri);
        tvSat = view.findViewById(R.id.item_table_view_tv_sat);
        tvSun = view.findViewById(R.id.item_table_view_tv_sun);
        tvMon.setTextColor(weekTextColor);
        tvTue.setTextColor(weekTextColor);
        tvWed.setTextColor(weekTextColor);
        tvThu.setTextColor(weekTextColor);
        tvFri.setTextColor(weekTextColor);
        tvSat.setTextColor(weekTextColor);
        tvSun.setTextColor(weekTextColor);
        LinearLayout weekCon = view.findViewById(R.id.item_table_view_week_con);
        weekCon.setBackgroundColor(weekBgColor);
        indicatorCon = view.findViewById(R.id.item_table_view_indicator_con);
        indicatorCon.setBackgroundColor(indicatorBgColor);
        for (int i = 1; i <= countPerDay; i++) {
            LinearLayout partIndicator = new LinearLayout(context);
            partIndicator.setOrientation(VERTICAL);
            partIndicator.setGravity(Gravity.CENTER);
            partIndicator.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT,
                    (int) context.getResources().getDimension(R.dimen.height_per_lesson)));
            TextView textView = new TextView(context);
            textView.setText(String.valueOf(i));
            textView.setGravity(Gravity.CENTER);
            textView.setTextColor(indicatorTextColor);
            textView.getPaint().setFakeBoldText(true);
            partIndicator.addView(textView);
            TextView start = new TextView(context);
            start.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);
            start.setGravity(Gravity.CENTER);
            TextView end = new TextView(context);
            end.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);
            end.setGravity(Gravity.CENTER);
            partIndicator.addView(start);
            partIndicator.addView(end);
            indicatorCon.addView(partIndicator);
        }
    }

    public void setTimes(String[] starts, String[] ends) {
        if (starts.length != ends.length)
            throw new RuntimeException("开始时间与结束时间不对应");
        int count = Math.min(countPerDay, starts.length);
        for (int i = 0; i < count; i++) {
            LinearLayout indicator = (LinearLayout) indicatorCon.getChildAt(i);
            ((TextView) indicator.getChildAt(1)).setText(starts[i]);
            ((TextView) indicator.getChildAt(2)).setText(ends[i]);
        }
    }

    public void setWeekStart(Date date) {
        TextView month = findViewById(R.id.item_table_view_tv_month);
        month.setText(new SimpleDateFormat("M\n月", Locale.CHINA).format(date));
        getDateString(date, tvMon, 0);
        getDateString(date, tvTue, 1);
        getDateString(date, tvWed, 2);
        getDateString(date, tvThu, 3);
        getDateString(date, tvFri, 4);
        getDateString(date, tvSat, 5);
        getDateString(date, tvSun, 6);
    }

    private void getDateString(Date date, TextView tv, int offset) {
        Date d = new Date(date.getTime() + offset * 24 * 60 * 60 * 1000);
        String str = new SimpleDateFormat("E\nMM/dd", Locale.CHINA).format(d);
        SpannableString res = new SpannableString(str);
        res.setSpan(new AbsoluteSizeSpan(10, true), str.length() - 5, str.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        tv.setText(res);
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd",Locale.CHINA);
        if (format.format(new Date()).equals(format.format(d))){
            tv.setBackgroundColor(Color.parseColor("#66ababab"));
        }else{
            tv.setBackgroundColor(Color.TRANSPARENT);
        }
    }

    public void setLessons(List<T> lessons) {
        setLessons(lessons, null, null);
    }

    public void setLessons(List<T> lessons, Map<String, Integer> bgMap) {
        setLessons(lessons, bgMap, null);
    }

    public void setLessons(List<T> lessons, LessonView.LessonClickListener<T> lessonClickListener) {
        setLessons(lessons, null, lessonClickListener);
    }

    public void setLessons(List<T> lessons, Map<String, Integer> bgMap, LessonView.LessonClickListener<T> lessonClickListener) {
        Map<String, Integer> mBgMap = new HashMap<>();
        if (bgMap != null) {
            mBgMap.putAll(bgMap);
        }
        mon.removeAllViews();
        tue.removeAllViews();
        wed.removeAllViews();
        thu.removeAllViews();
        fri.removeAllViews();
        sat.removeAllViews();
        sun.removeAllViews();
        if (flags == null) {
            flags = new String[]{"mon", "tue", "wed", "thur", "fri", "sat", "sun"};
        }
        for (int i = 0; i < countPerDay; i++) {
            for (T lesson : lessons) {
                if (lesson.getStart() == i) {
                    LessonView<T> lessonView = new LessonView<>(getContext());
                    lessonView.setTextColor(lessonTextColor);
                    lessonView.setLesson(lesson, lessonClickListener);
                    if (mBgMap.get(lesson.getName()) != null) {
                        lessonView.setBgColor(mBgMap.get(lesson.getName()));
                    } else {
                        int randomColor = createRandomColor();
                        mBgMap.put(lesson.getName(), randomColor);
                        lessonView.setBgColor(randomColor);
                    }
                    if (lesson.getWeekday().equals(flags[0])) {
                        mon.addView(lessonView);
                    }
                    if (lesson.getWeekday().equals(flags[1])) {
                        tue.addView(lessonView);
                    }
                    if (lesson.getWeekday().equals(flags[2])) {
                        wed.addView(lessonView);
                    }
                    if (lesson.getWeekday().equals(flags[3])) {
                        thu.addView(lessonView);
                    }
                    if (lesson.getWeekday().equals(flags[4])) {
                        fri.addView(lessonView);
                    }
                    if (lesson.getWeekday().equals(flags[5])) {
                        sat.addView(lessonView);
                    }
                    if (lesson.getWeekday().equals(flags[6])) {
                        sun.addView(lessonView);
                    }
                }
            }
        }
    }

    public void setResolveFlags(String[] flags) {
        this.flags = flags;
    }

    private List<String> randomColors = new ArrayList<>();

    private int createRandomColor() {
        String R, G, B;
        int r, g, b;
        Random random = new Random();
        r = random.nextInt(256);
        g = random.nextInt(256);
        b = random.nextInt(256);
        if (r * 0.299 + g * 0.578 + b * 0.114 < 115) {
            return createRandomColor();
        }
        R = Integer.toHexString(r).toUpperCase();
        G = Integer.toHexString(g).toUpperCase();
        B = Integer.toHexString(b).toUpperCase();
        R = R.length() == 1 ? "0" + R : R;
        G = G.length() == 1 ? "0" + G : G;
        B = B.length() == 1 ? "0" + B : B;
        String color = "#CC" + R + G + B;
        for (String s : randomColors) {
            if (s.equals(color)) {
                return createRandomColor();
            }
        }
        randomColors.add(color);
        return Color.parseColor(color);
    }

}
