package com.example.schedule;

public class ExampleItem {

    private String day;
    private String date;

    public ExampleItem(String requiredDay, String requiredDate) {
        day = requiredDay;
        date = requiredDate;
    }
    public String getDayFromTextView() {
        return day;
    }
    public String getDateFromTextView() {
        return date;
    }
}


