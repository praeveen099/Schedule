package com.example.schedule;

public class AnActivity {

    // the properties of an activity
    private String activityDescription;
    private String startTime;
    private String endTime;

    // will be used to index in the array list
    private int startEndTimeToBeUsedForIndexing;

    public AnActivity(String requiredActivityDescription, String requiredStartTime, String requiredEndTime)
    {
        // assign the properties in the constructor
        activityDescription = requiredActivityDescription;
        startTime = requiredStartTime;
        endTime = requiredEndTime;

        // create the start end time index
        startEndTimeToBeUsedForIndexing = createTheIndex(startTime, endTime);

    } // AnActivity constructor

    private int createTheIndex(String startTimeOfActivity, String endTimeOfActivity)
    {
        // remove the : character form the string
        String justStartTime = startTimeOfActivity.replace(":", "");
        String justEndTime = endTimeOfActivity.replace(":", "");

        // now join the start time to the end time and return it
        return Integer.parseInt(justStartTime + justEndTime);

    } // createTheIndex


    public String getActivityDescription() {
        return activityDescription;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public int getStartEndTimeToBeUsedForIndexing() {
        return startEndTimeToBeUsedForIndexing;
    }

} // AnActivity Class
