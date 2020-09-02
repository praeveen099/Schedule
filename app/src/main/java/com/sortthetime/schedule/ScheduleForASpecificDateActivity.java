package com.sortthetime.schedule;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;

public class ScheduleForASpecificDateActivity extends AppCompatActivity {

    // the file name  that will store the HashMap of activities
    public static final String FILE_NAME = "schedules.txt";

    private RecyclerView recyclerViewHoldingActivities;
    private ActivityForSpecificDateAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private ArrayList<AnActivity> activitiesForTheDate;
    private HashMap<String, ArrayList<AnActivity>> hashMapOfDates;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_for_a_specific_date);

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        String dateOfActivity = intent.getStringExtra(DisplayScheduleActivity.EXTRA_MESSAGE_DATE);
        String dayOfActivity = intent.getStringExtra(DisplayScheduleActivity.EXTRA_MESSAGE_DAY);
        // set the title to be the date and day
        setTitle(dayOfActivity + ", " + dateOfActivity);


        activitiesForTheDate = new ArrayList<>();

        // if file exists
        if (fileExist(FILE_NAME))
        {
            // get the hashMap
            hashMapOfDates = loadHashMapFromFile();

            // then check if there are activities for that date
            activitiesForTheDate = hashMapOfDates.get(dateOfActivity);


            // the array list is in the hashMap and its not empty
            if (activitiesForTheDate != null && !activitiesForTheDate.isEmpty()){
                buildRecyclerView();
            } // if

        }

    }


    // method to check the file exists since the file is in a private mode area
    public boolean fileExist(String fname){
        File file = getBaseContext().getFileStreamPath(fname);
        return file.exists();
    }

    // build the recycler view
    private void buildRecyclerView()
    {
        // set the recycler view
        recyclerViewHoldingActivities = findViewById(R.id.recyclerViewToHoldActivitiesInADay);
        recyclerViewHoldingActivities.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new ActivityForSpecificDateAdapter(activitiesForTheDate);

        recyclerViewHoldingActivities.setLayoutManager(mLayoutManager);
        recyclerViewHoldingActivities.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new ActivityForSpecificDateAdapter.OnItemClickListener() {
            @Override
            public void onDeleteClick(int position) {
                removeItem(position);
            }
        });

    }

    public void removeItem(int position)
    {
        // remove the item from the array list of activities
        activitiesForTheDate.remove(position);

        // then save to hashMap the changes
        saveHashMap(hashMapOfDates);

        mAdapter.notifyItemRemoved(position);
    }


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
    } // loadHashMapFromFile()

    public void saveHashMap(HashMap<String, ArrayList<AnActivity>> hashMapToBeSavedToTextFile)
    {

        // get the string version of the hashMap
        Gson gson = new Gson();
        String jsonStringOfTheHashMap = gson.toJson(hashMapToBeSavedToTextFile);

        // get the file output stream to be used to save the json string
        FileOutputStream fos = null;

        try
        {
            fos = openFileOutput(FILE_NAME, MODE_PRIVATE);

            // write to the file using the bytes of the string
            fos.write(jsonStringOfTheHashMap.getBytes());



        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        // finally we close the file output stream
        finally
        {
            if (fos != null)
            {
                try {
                    fos.close();

                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }


    } // save HashMap
}