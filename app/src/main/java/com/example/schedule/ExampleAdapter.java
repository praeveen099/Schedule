package com.example.schedule;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ExampleAdapter extends RecyclerView.Adapter<ExampleAdapter.ExampleViewHolder>
{

    // example list is the list list of day and dates that we want to use
    private ArrayList<ExampleItem> mExampleList;

    public static class ExampleViewHolder extends RecyclerView.ViewHolder{
        // these are the text views for the example item
        public TextView dayView;
        public TextView dateView;

        public ExampleViewHolder(@NonNull View itemView)
        {
            super(itemView);
            // initialise the day and date views
            dayView = itemView.findViewById(R.id.Day);
            dateView = itemView.findViewById(R.id.Date);
        }
    }

    public ExampleAdapter(ArrayList<ExampleItem> exampleList)
    {
        mExampleList = exampleList;
    }

    @NonNull
    @Override
    public ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.example_item, parent, false);
        ExampleViewHolder evh = new ExampleViewHolder(v);
        return evh;
    }

    @Override
    public void onBindViewHolder(@NonNull ExampleViewHolder holder, int position)
    {
        ExampleItem currentItem = mExampleList.get(position);

        // get the day and date text viewss
        holder.dayView.setText(currentItem.getDayFromTextView());
        holder.dateView.setText(currentItem.getDateFromTextView());

    }

    @Override
    public int getItemCount()
    {
        // return the number of items in our array list of example items
        return mExampleList.size();
    }
}
