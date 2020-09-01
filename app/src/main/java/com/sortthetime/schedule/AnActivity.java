package com.sortthetime.schedule;

public class AnActivity implements Comparable<AnActivity>
{


    // the properties of an activity
    private String activityDescription;
    private String startTime;
    private String endTime;

    private String timeToBeUsedForDisplay;

    // will be used to index in the array list
    private int startEndTimeToBeUsedForIndexing;

    public AnActivity(String requiredActivityDescription, String requiredStartTime, String requiredEndTime)
    {
        // assign the properties in the constructor
        activityDescription = requiredActivityDescription;
        startTime = requiredStartTime;
        endTime = requiredEndTime;

        timeToBeUsedForDisplay = startTime + " - " + endTime;

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

    @Override
    public int compareTo(AnActivity otherActivity)
    {
        return Double.compare(this.getStartEndTimeToBeUsedForIndexing(), otherActivity.getStartEndTimeToBeUsedForIndexing());
    } // compareTo

    @Override
    public String toString() {
        return "AnActivity [activityDescription=" + activityDescription + ", startTime=" + startTime + ", endTime=" + endTime + ", startEndTimeToBeUsedForIndexing=" + startEndTimeToBeUsedForIndexing + "]";
    }

    public String getActivityDescription() {
        return activityDescription;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public String getTimeToBeUsedForDisplay() {
        return timeToBeUsedForDisplay;
    }


    public int getStartEndTimeToBeUsedForIndexing() {
        return startEndTimeToBeUsedForIndexing;
    }

} // AnActivity Class
