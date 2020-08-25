package com.example.schedule;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ActivityForSpecificDateAdapter  extends RecyclerView.Adapter<ActivityForSpecificDateAdapter .ViewHolderForSpecificDate>
{

    private ArrayList<AnActivity> activityList;

    public static class ViewHolderForSpecificDate extends RecyclerView.ViewHolder{

        // contain the text views in the activity item
        public TextView activityDescription;
        public TextView activityTiming;

        public ViewHolderForSpecificDate(@NonNull View itemView)
        {
            super(itemView);
            itemView.findViewById(R.id.DescriptionOfActivity);
            itemView.findViewById(R.id.Timing);

        }
    }

    // constructor for the adapter, will contain the array list we want to display
    public ActivityForSpecificDateAdapter(ArrayList<AnActivity> requiredActivityList){

        // pass the example list from constructor to the activity list instance variable
        activityList = requiredActivityList;
        System.out.println("the activity" + activityList);
    }

    @NonNull
    @Override
    public ViewHolderForSpecificDate onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // may need to fix this
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_item, parent, false);
        ViewHolderForSpecificDate viewHolder = new ViewHolderForSpecificDate(v);
        System.out.println(viewHolder + "The view holder");
        return viewHolder;
    }

    // pass the information of the array list to the views
    @Override
    public void onBindViewHolder(@NonNull ViewHolderForSpecificDate holder, int position)
    {
        // TO DO
        // fix the reason why holder returns null
        AnActivity currentActivity = activityList.get(position);

        System.out.println(currentActivity.getActivityDescription());
        System.out.println(holder.activityDescription);
        // use get methods to pass information to the views
        // holder.activityDescription.setText(currentActivity.getActivityDescription());
        // sholder.activityTiming.setText(currentActivity.getTimeToBeUsedForDisplay());
    }

    @Override
    public int getItemCount() {
        return activityList.size();
    }
}
