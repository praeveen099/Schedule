package com.example.schedule;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import com.google.gson.reflect.TypeToken;
import com.google.gson.Gson;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;


import static android.view.View.generateViewId;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{
    // public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";

    // the file name  that will store the HashMap of activities
    public static final String FILE_NAME = "schedules.txt";

    // the text box that will hold the beginning time of the activity
    TextView beginDateTxt, activityStartTimeTxt, activityEndTimeTxt, stringDate;
    EditText activityDescriptionTxt;
    View aboveDateDivider, belowDateDivider;
    private int currentYear, currentMonth, currentDay, currentHour, currentMinute;
    Button uploadButton, confirmButton, cancelButton, calendarButton;

    // an activity to be added when the confirm button is pressed
    AnActivity activityToBeSaved;

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
        // buttons we are going to use
        uploadButton = findViewById(R.id.uploadButton);
        confirmButton = findViewById(R.id.confirmButton);
        cancelButton = findViewById(R.id.cancelButton);
        calendarButton = findViewById(R.id.calendarButton);

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
                            beginDateTxt.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
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
       if(v == cancelButton)
       {

           @SuppressLint("WrongViewCast")
           ConstraintLayout constraintLayoutToHoldActivities = findViewById(R.id.constraintLayoutToHoldActivities);

           // set divider and date string and buttons to be invisible
           cancelButton.setVisibility(View.INVISIBLE);
           confirmButton.setVisibility(View.INVISIBLE);
           aboveDateDivider.setVisibility(View.INVISIBLE);
           belowDateDivider.setVisibility(View.INVISIBLE);
           stringDate.setVisibility(View.INVISIBLE);

           // remove the views in the constraint layout that we dont want anymore
           removeViews(constraintLayoutToHoldActivities);
       }
        // when the upload data is pressed show the uploaded data
        if(v == uploadButton)
        {


            @SuppressLint("WrongViewCast")
            ConstraintLayout constraintLayoutToHoldActivities = findViewById(R.id.constraintLayoutToHoldActivities);

            // remove the views in the constraint layout that we dont want anymore
            removeViews(constraintLayoutToHoldActivities);


            // create the set of constraints
            ConstraintSet constraintSet = new ConstraintSet();


            if((!(beginDateTxt.getText()).equals("Click to set a date")) && (!(activityStartTimeTxt.getText()).equals("Click to set activity start time"))
            && !(activityEndTimeTxt.getText()).equals("Click to set activity end time") && !((activityDescriptionTxt.getText()).toString()).matches(""))
            {

                // set divider and date string and buttons to be visible
                aboveDateDivider.setVisibility(View.VISIBLE);
                belowDateDivider.setVisibility(View.VISIBLE);
                stringDate.setVisibility(View.VISIBLE);
                confirmButton.setVisibility(View.VISIBLE);
                cancelButton.setVisibility(View.VISIBLE);

                // check if the file exists
                File fileContainingHashMap = new File(FILE_NAME);

                // get the start and end times
                String startTime = activityStartTimeTxt.getText().toString();
                String endTime = activityEndTimeTxt.getText().toString();

                String dateUploadedText = beginDateTxt.getText().toString();

                // get the text from the description of activity
                String uploadedActivityDescription = activityDescriptionTxt.getText().toString();

                // create the activity that we may save if user presses confirm button
                activityToBeSaved = new AnActivity(uploadedActivityDescription, startTime, endTime);

                // the time to display from what to what time the activity is
                String timeFromAndUntilActivity = startTime + " - " + endTime;

                // if file exists
                if (fileContainingHashMap.isFile() && fileContainingHashMap.canRead())
                {
                    System.out.println("File exists");
                    // get the hashMap
                    HashMap<String, ArrayList<AnActivity>> hashMapOfDates = loadHashMapFromFile();

                    // get the array list from the hashMap
                    ArrayList<AnActivity> arrayListOfTheDate;
                    arrayListOfTheDate = hashMapOfDates.get(dateUploadedText);

                    // add an entry of the date to the array list
                    if (arrayListOfTheDate == null)
                    {
                        ArrayList<AnActivity> arrayListToAdd = new ArrayList();
                        hashMapOfDates.put(dateUploadedText, arrayListToAdd);

                        // then reassign the array list of the date
                        arrayListOfTheDate = hashMapOfDates.get(dateUploadedText);
                    } // if

                    // if nothing has been added to the array list of that date, then just show the
                    // activities to be added
                    if(arrayListOfTheDate.isEmpty())
                        createViewsAfterUploadIfHashMapNotExistOrNoValuesInArrayList(constraintLayoutToHoldActivities, timeFromAndUntilActivity);
                    // TO DO  else



                    
                }
                else
                {

                    // if file does not exist, just display the activity we want to add
                    createViewsAfterUploadIfHashMapNotExistOrNoValuesInArrayList(constraintLayoutToHoldActivities, timeFromAndUntilActivity);
                }
            } // if the entries are all set


        } // if calendar button is pressed
        if (v == calendarButton)
        {
            Intent intent = new Intent(this, DisplayScheduleActivity.class);
            // next bit is for testing purposes
            // String message= activityDescriptionTxt.getText().toString();
            // intent.putExtra(EXTRA_MESSAGE, message);
            startActivity(intent);


        } // calendar button
//        if (v == confirmButton)
//        {
//
//        }
    } // onClick

    public void removeViews(ConstraintLayout constraintLayoutWhereViewsWillBeRemovedFrom)
    {

        // get the count of children views in the constraint layout
        int count = constraintLayoutWhereViewsWillBeRemovedFrom.getChildCount();

        // if there are child elements
        if (count> 0 )
        {
            // set the previous view before we pressed the upload button
            View viewToBeRemoved;

            // remove all the views in the layout before we clicked upload except divider
            for (int i = count - 1; i >= 0 ; i--) {
                viewToBeRemoved = constraintLayoutWhereViewsWillBeRemovedFrom.getChildAt(i);
                if (!(viewToBeRemoved.equals(aboveDateDivider) || viewToBeRemoved.equals(belowDateDivider) || viewToBeRemoved.equals(stringDate)))
                    ((ViewGroup) viewToBeRemoved.getParent()).removeView(viewToBeRemoved);
            }
        }

    } // removeViews

    // create a method to display the activity if we have no hash map or the date has no activities present
    // in the array list
    @SuppressLint("ResourceType")
    public void createViewsAfterUploadIfHashMapNotExistOrNoValuesInArrayList (ConstraintLayout constraintLayoutToAddViews, String timeFromAndUntilActivity)
    {

        // create the set of constraints
        ConstraintSet constraintSet = new ConstraintSet();


        // create textViews for uploaded date, uploaded start time, end time and description
        // to be put at the display to hold activities
        TextView uploadedStartEndTimeTextView =  new TextView(this);
        TextView uploadedDescriptionTextView =  new TextView(this);
        TextView uploadDateTextView = new TextView(this);


        // the divider that will be added to the bottom of the time
        View dividerBelowTheActivityDescription = new View(this);
        System.out.println("File does not exist");

        // add the text and set some of the attributes
        uploadDateTextView.setText(beginDateTxt.getText());
        uploadDateTextView.setTextColor(Color.parseColor("#000000"));

        // set the text view for the uploaded start and end times
        uploadedStartEndTimeTextView.setText(timeFromAndUntilActivity);
        uploadedStartEndTimeTextView.setTextColor(Color.parseColor("#000000"));

        // set the text view for the uploaded activity description text view
        uploadedDescriptionTextView.setText(activityDescriptionTxt.getText().toString());
        uploadedDescriptionTextView.setTextColor(Color.parseColor("#000000"));
        uploadedDescriptionTextView.setPadding(24, 0, 24, 0);


        // set the ids for all the text views we are going to create
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            uploadedStartEndTimeTextView.setId(generateViewId());
            uploadedDescriptionTextView.setId(generateViewId());
            uploadDateTextView.setId(generateViewId());
            dividerBelowTheActivityDescription.setId(generateViewId());

        }
        else {

            uploadedStartEndTimeTextView.setId(25);
            uploadedDescriptionTextView.setId(23);
            uploadDateTextView.setId(21);
            dividerBelowTheActivityDescription.setId(27);
        }

        // add the views to the constraint layout
        constraintLayoutToAddViews.addView(uploadDateTextView);
        constraintLayoutToAddViews.addView(uploadedStartEndTimeTextView);
        constraintLayoutToAddViews.addView(uploadedDescriptionTextView);
        constraintLayoutToAddViews.addView(dividerBelowTheActivityDescription);


        ConstraintLayout.LayoutParams layoutParamsForDividerBelowTime = new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.MATCH_PARENT, 3);
        dividerBelowTheActivityDescription.setLayoutParams(layoutParamsForDividerBelowTime);
        int[] attrs = {android.R.attr.listDivider};
        TypedArray ta = getApplicationContext().obtainStyledAttributes(attrs);
        //Get Drawable and use as needed
        Drawable divider = ta.getDrawable(0);
        dividerBelowTheActivityDescription.setBackground(divider);
        ta.recycle();

        // get the id of the view for the uploaded date
        int idOfUploadedDateTextView = uploadDateTextView.getId();
        constraintSet.clone(constraintLayoutToAddViews);
        constraintSet.connect(idOfUploadedDateTextView, ConstraintSet.TOP, R.id.divider, ConstraintSet.TOP, 0);
        constraintSet.connect(idOfUploadedDateTextView, ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START, 0);
        constraintSet.connect(idOfUploadedDateTextView, ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END, 0);


        // get the id of the view for the uploaded start to end time
        int idOfUploadedStartEndTimeTextView = uploadedStartEndTimeTextView.getId();
        constraintSet.connect(idOfUploadedStartEndTimeTextView, ConstraintSet.TOP, R.id.divider2, ConstraintSet.TOP, 0);
        constraintSet.connect(idOfUploadedStartEndTimeTextView, ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START, 0);
        constraintSet.connect(idOfUploadedStartEndTimeTextView, ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END, 0);

        // get the id of the view for the uploaded activity description
        int idOfUploadedActivityDescription = uploadedDescriptionTextView.getId();
        // put the top of the activity description at the bottom of the start end time text view
        constraintSet.connect(idOfUploadedActivityDescription, ConstraintSet.TOP, idOfUploadedStartEndTimeTextView, ConstraintSet.BOTTOM, 0);
        constraintSet.connect(idOfUploadedActivityDescription, ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START, 0);
        constraintSet.connect(idOfUploadedActivityDescription, ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END, 0);

        // get the id of the view of the divider and put it under the times
        int idOfDividerBelowTheTimeActivityDesciption = dividerBelowTheActivityDescription.getId();
        constraintSet.connect(idOfDividerBelowTheTimeActivityDesciption, ConstraintSet.BOTTOM, idOfUploadedActivityDescription, ConstraintSet.BOTTOM, 0);
        constraintSet.connect(idOfDividerBelowTheTimeActivityDesciption, ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START, 0);
        constraintSet.connect(idOfDividerBelowTheTimeActivityDesciption, ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END, 0);


        constraintSet.applyTo(constraintLayoutToAddViews);

    } // createViewsAfterUploadIfHashMapNotExistOrNoValuesInArrayList

    public HashMap<String, ArrayList<AnActivity>> loadHashMapFromFile()
    {
        // initialise the file input stream
        FileInputStream fis = null;

        // to save the json holding the hashMap
        String toSave = "";
        String toGetFromFile;

        try
        {
            // open the file
            fis = openFileInput(FILE_NAME);

            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            // create the string builder
            StringBuilder sb = new StringBuilder();

            while ((toGetFromFile = br.readLine()) != null) {
                toSave = toGetFromFile;
                sb.append(toGetFromFile).append("\n");
                System.out.println(toGetFromFile);
            }
            br.close();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if (fis != null)
            {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } // if fis ! = null
        } // finally

        // get the hashMap from the json
        Gson gson = new Gson();
        Type type = new TypeToken<HashMap<String, ArrayList<AnActivity>>>(){}.getType();
        HashMap<String, ArrayList<AnActivity>> hashMapOfActivities = gson.fromJson(toSave, type);

        return  hashMapOfActivities;
    }
}