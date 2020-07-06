package com.example.schedule;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{
    // the text box that will hold the beginning time of the activity
    TextView beginDateTxt;
    private int currentYear, currentMonth, currentDay;
    Button uploadButton;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        beginDateTxt= findViewById(R.id.textView);
        uploadButton = findViewById(R.id.button6);

        beginDateTxt.setOnClickListener(this);
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


    }
}