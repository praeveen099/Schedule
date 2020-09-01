package com.sortthetime.schedule;

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
    private ArrayList<DayAndDate> mExampleList;
    private OnItemClickListener mListener;


    // create an interface with one method that will pass the position of the item
    // we have clicked on
    public interface OnItemClickListener
    {
        void onItemClick(int position);
    }


    public void setOnItemClickListener(OnItemClickListener listener)
    {
        mListener = listener;
    }

    public static class ExampleViewHolder extends RecyclerView.ViewHolder{
        // these are the text views for the example item
        public TextView dayView;
        public TextView dateView;

        public ExampleViewHolder(@NonNull View itemView, final OnItemClickListener listener)
        {
            super(itemView);
            // initialise the day and date views
            dayView = itemView.findViewById(R.id.Day);
            dateView = itemView.findViewById(R.id.Date);

            // set an on click listener to the item view
            // when we click our item view which our card, we want to call the
            // on item click
            itemView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    if (listener != null)
                    {
                        //  we need to get the position
                        int position = getAdapterPosition();
                        // checks if position is valid
                        if (position != RecyclerView.NO_POSITION)
                        {
                            // we pass the position to the onItemClick method
                            // in our interface OnItemClickListener
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }

    public ExampleAdapter(ArrayList<DayAndDate> exampleList)
    {
        mExampleList = exampleList;
    }

    @NonNull
    @Override
    public ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.example_item, parent, false);
        // we need to pass the listener
        ExampleViewHolder evh = new ExampleViewHolder(v, mListener);
        return evh;
    }

    @Override
    public void onBindViewHolder(@NonNull ExampleViewHolder holder, int position)
    {
        DayAndDate currentItem = mExampleList.get(position);

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
