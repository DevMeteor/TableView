package cn.devmeteor.tableview;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

public class LessonView extends FrameLayout {

    private LinearLayout content;
    private TextView name;
    private TextView place;

    public LessonView(Context context) {
        this(context, null);
    }

    public LessonView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LessonView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public interface LessonClickListener {
        void onClick(Lesson lesson);
    }

    private void init(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_lesson, this, false);
        addView(view);
        content = view.findViewById(R.id.item_lesson_content);
        name = view.findViewById(R.id.item_lesson_name);
        place = view.findViewById(R.id.item_lesson_place);
    }

    public void setLesson(final Lesson lesson, final LessonClickListener lessonClickListener) {
        content.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (lessonClickListener != null)
                    lessonClickListener.onClick(lesson);
            }
        });
        name.setText(lesson.getName());
        place.setText("@" + lesson.getPlace());
        LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (int) (getContext().getResources().getDimension(R.dimen.height_per_lesson) * (lesson.getEnd() - lesson.getStart() + 1)));
        layoutParams.topMargin = (int) ((lesson.getStart() - 1) * (getContext().getResources().getDimension(R.dimen.height_per_lesson)));
        setLayoutParams(layoutParams);
    }

    public void setBgColor(int color) {
        GradientDrawable gradientDrawable = (GradientDrawable) content.getBackground();
        gradientDrawable.setColor(color);
    }

    public void setTextColor(int color) {
        name.setTextColor(color);
    }

}