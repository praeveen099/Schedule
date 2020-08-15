package com.example.schedule;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class DisplayScheduleActivity extends AppCompatActivity {

    // recycler view we use
    private RecyclerView mRecyclerView;
    // the bridge between recycler view and the data
    private RecyclerView.Adapter mAdapter;
    // aligns the single items in the list
    private RecyclerView.LayoutManager mLayoutManager;


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_schedule);


        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        // an array list that contains the 7 days and dates after today
        ArrayList<ExampleItem> listOfDayAndDate = new ArrayList<>();

        // format for the days and date
        SimpleDateFormat formatForDate = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat formatForDay = new SimpleDateFormat("EEEE");


        // Today's date
        Date todayDate = new Date();
        String todayDateString = formatForDate.format(todayDate);

        // Getting today's day
        String todayDay = formatForDay.format(todayDate);

        // Use the Calendar class to subtract one day
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(todayDate);
        calendar.add(Calendar.DAY_OF_YEAR, -1);

        Date oneDateBefore = calendar.getTime();
        String oneDateBeforeString = formatForDate.format(oneDateBefore);

        //get the day of the day before
        String yesterday = formatForDay.format(oneDateBefore);

        // Add yesterday and today into listOfDayAndDate array lists
        listOfDayAndDate.add(new ExampleItem(yesterday, oneDateBeforeString));
        listOfDayAndDate.add(new ExampleItem(todayDay, todayDateString));

        // for loop to add the next five days after today into the array list
        for (int daysAfterToday = 1; daysAfterToday <= 5; daysAfterToday++)
        {
            calendar = Calendar.getInstance();
            calendar.setTime(todayDate);
            calendar.add(Calendar.DAY_OF_YEAR, daysAfterToday);

            // get the date string
            Date dateToAddToList = calendar.getTime();
            String dateToAddListString = formatForDate.format(dateToAddToList);

            // get the day string
            String dayToAdd = formatForDay.format(dateToAddToList);

            listOfDayAndDate.add(new ExampleItem(dayToAdd, dateToAddListString));

        } // for loop


        // initialise the recycler view, layout manager and the adapters
        mRecyclerView = findViewById(R.id.recyclerViewToHoldDateAndDays);
        // recycler view won't change size
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new ExampleAdapter(listOfDayAndDate);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);





    }
}