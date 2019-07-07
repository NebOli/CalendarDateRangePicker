package com.archit.calendardaterangepickerdemo;

import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.archit.calendardaterangepicker.customviews.DateRangeCalendarView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private DateRangeCalendarView calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        calendar = findViewById(R.id.calendar);

        Typeface typeface = Typeface.createFromAsset(getAssets(), "JosefinSans-Regular.ttf");
//        Typeface typeface = Typeface.createFromAsset(getAssets(), "LobsterTwo-Regular.ttf");
        calendar.setFonts(typeface);

        calendar.setCalendarListener(new DateRangeCalendarView.CalendarListener() {
            @Override
            public void onFirstDateSelected(Calendar startDate) {
            }

            @Override
            public void onDateRangeSelected(Calendar startDate, Calendar endDate) {
            }

        });

        findViewById(R.id.btnReset).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar.resetAllSelectedViews();
            }
        });


//        calendar.setNavLeftImage(ContextCompat.getDrawable(this,R.drawable.ic_left));
//        calendar.setNavRightImage(ContextCompat.getDrawable(this,R.drawable.ic_right));

        Calendar now = Calendar.getInstance();
        Calendar later = (Calendar) now.clone();
        later.add(Calendar.MONTH, 0);

        calendar.setVisibleMonthRange(now, later);

        Calendar startSelectionDate = Calendar.getInstance();
        startSelectionDate.add(Calendar.MONTH, -1);
        Calendar endSelectionDate = (Calendar) startSelectionDate.clone();
        endSelectionDate.add(Calendar.DATE, 40);

        calendar.setSelectedDateRange(startSelectionDate, endSelectionDate);

        Calendar current = Calendar.getInstance();
        calendar.setCurrentMonth(current);


        Date d;
        final List<Calendar> cl = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

        try {
            d = new Date(sdf.parse("07-07-2019").getTime());
            cl.add(dateToCalendar(d));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        try {
            d = new Date(sdf.parse("08-07-2019").getTime());
            cl.add(dateToCalendar(d));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        try {
            d = new Date(sdf.parse("30-07-2019").getTime());
            cl.add(dateToCalendar(d));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                System.out.println("ooooo ADD BOOKED LIST ");
                calendar.setBookedDatesList(cl);
            }
        }, 1000);


    }

    //Convert Date to Calendar
    private Calendar dateToCalendar(Date date) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;

    }

}
