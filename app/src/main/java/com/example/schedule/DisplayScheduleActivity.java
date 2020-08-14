package com.example.schedule;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class DisplayScheduleActivity extends AppCompatActivity {

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


        /*String todayDate = java.time.LocalDate.now().toString();

        System.out.println(todayDate);
        */

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date todayDate = new Date();
        System.out.println(formatter.format(todayDate));

        String dayOfWeek = new SimpleDateFormat("EEEE").format(todayDate);
        System.out.println(dayOfWeek);



    }
}