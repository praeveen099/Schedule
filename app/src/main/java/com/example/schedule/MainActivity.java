package com.example.schedule;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{
    // the text box that will hold the beginning time of the activity
    TextView beginDateTxt, activityStartTimeTxt, activityEndTime;
    private int currentYear, currentMonth, currentDay, currentHour, currentMinute;
    Button uploadButton;

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