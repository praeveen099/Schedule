package com.example.schedule;

public class DayAndDate {

    private String day;
    private String date;

    public DayAndDate(String requiredDay, String requiredDate) {
        day = requiredDay;
        date = requiredDate;
    }

    public void changeDay(String text) {
        day = text;
    }


    public String getDayFromTextView() {
        return day;
    }
    public String getDateFromTextView() {
        return date;
    }
}


