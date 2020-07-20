package com.example.schedule;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

import static android.view.View.generateViewId;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{
    // the text box that will hold the beginning time of the activity
    TextView beginDateTxt, activityStartTimeTxt, activityEndTimeTxt, stringDate;
    EditText activityDescriptionTxt;
    View aboveDateDivider, belowDateDivider;
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
        activityEndTimeTxt = findViewById(R.id.activityEndTime);
        activityDescriptionTxt = findViewById(R.id.activityDescription);
        uploadButton = findViewById(R.id.uploadButton);

        // get the dividers
        aboveDateDivider = findViewById(R.id.divider);
        belowDateDivider = findViewById(R.id.divider2);

        // an indicator to show the date
        stringDate = findViewById(R.id.stringDate);

        beginDateTxt.setOnClickListener(this);
        activityStartTimeTxt.setOnClickListener(this);
        activityEndTimeTxt.setOnClickListener(this);
        uploadButton.setOnClickListener(this);

    }

    // override the current onClick method
    @SuppressLint("ResourceType")
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
                            // set the text area to have text which is black
                            beginDateTxt.setTextColor(Color.parseColor("#000000"));
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
                            // to ensure that time is displayed properly
                            if(minute < 10){
                                String minuteToBeDisplayed = "0" + minute;
                                activityStartTimeTxt.setText(hourOfDay + ":" + minuteToBeDisplayed );
                            }
                            else
                                activityStartTimeTxt.setText(hourOfDay + ":" + minute );

                            // set the text area to have text which is black
                            activityStartTimeTxt.setTextColor(Color.parseColor("#000000"));

                        }
            }, currentHour, currentMinute, false);

            // show the time picker dialog
            timePickerDialog.show();
        }

        if (v == activityEndTimeTxt)
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
                            // to ensure that time is displayed properly
                            if(minute < 10){
                                String minuteToBeDisplayed = "0" + minute;
                                activityEndTimeTxt.setText(hourOfDay + ":" + minuteToBeDisplayed );
                            }
                            else
                                activityEndTimeTxt.setText(hourOfDay + ":" + minute );

                            // set the text area to have text which is black
                            activityEndTimeTxt.setTextColor(Color.parseColor("#000000"));

                        }
                    }, currentHour, currentMinute, false);

            // show the time picker dialog
            timePickerDialog.show();
        }
        // when the upload data is pressed show the uploaded data
        if(v == uploadButton)
        {


            @SuppressLint("WrongViewCast")
            ConstraintLayout constraintLayoutToHoldActivities = findViewById(R.id.constraintLayoutToHoldActivities);



            // get the count of children views in the constraint layout
            int count = constraintLayoutToHoldActivities.getChildCount();

            // if there are child elements
            if (count> 0 )
            {
                // set the previous view before we pressed the upload button
                View viewToBeRemoved;

                // remove all the views in the layout before we clicked upload except divider
                for (int i = 0; i < count; i++) {
                    viewToBeRemoved = constraintLayoutToHoldActivities.getChildAt(i);
                    if (!(viewToBeRemoved.equals(aboveDateDivider) || viewToBeRemoved.equals(belowDateDivider) || viewToBeRemoved.equals(stringDate)))
                        ((ViewGroup) viewToBeRemoved.getParent()).removeView(viewToBeRemoved);
                }
            }


            // create the set of constraints
            ConstraintSet constraintSet = new ConstraintSet();


            // create textViews for uploaded date, uploaded start time, end time and description
            // to be put at the display to hold activities
            TextView uploadedStartTimeTextView =  new TextView(this);
            TextView uploadedEndTimeTextView =  new TextView(this);
            TextView uploadedDescriptionTextView =  new TextView(this);
            TextView uploadDateTextView = new TextView(this);


            if((!(beginDateTxt.getText()).equals("Click to set a date")) && (!(activityStartTimeTxt.getText()).equals("Click to set activity start time"))
            && !(activityEndTimeTxt.getText()).equals("Click to set activity end time") && !((activityDescriptionTxt.getText()).toString()).matches(""))
            {

                // set divider and date strinf to be visible
                aboveDateDivider.setVisibility(View.VISIBLE);
                belowDateDivider.setVisibility(View.VISIBLE);
                stringDate.setVisibility(View.VISIBLE);

                // add the text and set some of the attributes
                uploadDateTextView.setText(beginDateTxt.getText());
                uploadDateTextView.setTextColor(Color.parseColor("#000000"));


                // set the ids for all the text views we are going to create
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1)
                {
                    uploadedStartTimeTextView.setId(generateViewId());
                    uploadedEndTimeTextView.setId(generateViewId());
                    uploadedDescriptionTextView.setId(generateViewId());
                    uploadDateTextView.setId(generateViewId());

                }
                else {
                    uploadedStartTimeTextView.setId(25);
                    uploadedEndTimeTextView.setId(24);
                    uploadedDescriptionTextView.setId(23);
                    uploadDateTextView.setId(21);
                }

                // add the views to the constraint layout
                constraintLayoutToHoldActivities.addView(uploadDateTextView);


                // get the id of the view
                int idOfTextView = uploadDateTextView.getId();
                constraintSet.clone(constraintLayoutToHoldActivities);
                constraintSet.connect(idOfTextView, ConstraintSet.TOP, R.id.divider, ConstraintSet.TOP, 0 );
                constraintSet.connect(idOfTextView, ConstraintSet.START,  ConstraintSet.PARENT_ID, ConstraintSet.START, 0);
                constraintSet.connect(idOfTextView, ConstraintSet.END,  ConstraintSet.PARENT_ID, ConstraintSet.END, 0);
                constraintSet.applyTo(constraintLayoutToHoldActivities);
            } // if the entries are all set


        } // if upload button is pressed


    }
}