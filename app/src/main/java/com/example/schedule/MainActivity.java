package com.example.schedule;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

import static android.view.View.generateViewId;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{
    // the text box that will hold the beginning time of the activity
    TextView beginDateTxt, activityStartTimeTxt, activityEndTime;
    private int currentYear, currentMonth, currentDay, currentHour, currentMinute;
    Button uploadButton;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);







        // get the text views that we are going to use for the date and time dialogs
        beginDateTxt= findViewById(R.id.dateToReschedule);
        activityStartTimeTxt = findViewById(R.id.activityStartTime);
        activityEndTime = findViewById(R.id.activityEndTime);
        uploadButton = findViewById(R.id.uploadButton);


/*

        ConstraintLayout cl = (ConstraintLayout) findViewById(R.id.constraintLayout);

        // create the set of constraints
        ConstraintSet c = new ConstraintSet();


        TextView textView = new TextView(this);
        textView.setText("the text");
        // ConstraintLayout.LayoutParams textViewLayoutParams = new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.WRAP_CONTENT, ConstraintLayout.LayoutParams.WRAP_CONTENT);
        // textView.setLayoutParams(textViewLayoutParams);
        cl.addView(textView);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            textView.setId(generateViewId());
        }
        else
            textView.setId(21);

        // get the id of the view
        int idOfTextView = textView.getId();
        c.clone(cl);
        c.connect(idOfTextView, ConstraintSet.TOP, uploadButton.getId(), ConstraintSet.BOTTOM, 0 );
        c.connect(idOfTextView, ConstraintSet.RIGHT, uploadButton.getId(), ConstraintSet.LEFT, 20);
        c.applyTo(cl);
*/


         // get the linear layout
        RelativeLayout relativeLayoutForActivityScrollView = findViewById(R.id.relativeLayoutToHoldActivities);
        TextView textView = new TextView(this);
        textView.setText("the text");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            textView.setId(generateViewId());
        }
        else
            textView.setId(21);

        ViewGroup.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        textView.setLayoutParams(params);
        relativeLayoutForActivityScrollView.addView(textView);



        beginDateTxt.setOnClickListener(this);
        activityStartTimeTxt.setOnClickListener(this);
        activityEndTime.setOnClickListener(this);
        uploadButton.setOnClickListener(this);

    }

    // override the current onClick method
    @Override
    public void onClick(View v)
    {
        if (v == beginDateTxt)
        {
            Calendar currentDateCalendar = Calendar.getInstance();
            // get the current date from calendar
            currentYear = currentDateCalendar.get(Calendar.YEAR);
            currentMonth = currentDateCalendar.get(Calendar.MONTH);
            currentDay = currentDateCalendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener()
                    {
                        // what to do when date is set
                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth)
                        {
                            beginDateTxt.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                        }
                    } , currentYear, currentMonth, currentDay);
            datePickerDialog.show();
        }
        if (v == activityStartTimeTxt)
        {
            // Get Current Time
            final Calendar c = Calendar.getInstance();
            currentHour = c.get(Calendar.HOUR_OF_DAY);
            currentMinute = c.get(Calendar.MINUTE);

            // Launch Time Picker Dialog
            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute)
                        {
                            activityStartTimeTxt.setText(hourOfDay + ":" + minute );

                        }
            }, currentHour, currentMinute, false);

            // show the time picker dialog
            timePickerDialog.show();
        }

        if (v == activityEndTime)
        {
            // Get Current Time
            final Calendar c = Calendar.getInstance();
            currentHour = c.get(Calendar.HOUR_OF_DAY);
            currentMinute = c.get(Calendar.MINUTE);

            // Launch Time Picker Dialog
            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute)
                        {
                            activityEndTime.setText(hourOfDay + ":" + minute );

                        }
                    }, currentHour, currentMinute, false);

            // show the time picker dialog
            timePickerDialog.show();
        }


    }
}