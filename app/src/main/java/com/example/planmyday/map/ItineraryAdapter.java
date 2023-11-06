package com.example.planmyday.map;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import androidx.appcompat.widget.AppCompatButton;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.example.planmyday.R;

import com.example.planmyday.models.Attraction;
import com.example.planmyday.models.TourPlan;
import com.example.planmyday.models.TourStop;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

public class ItineraryAdapter extends ArrayAdapter<TourStop> {

    TourStop[] stops;

    Context mContext;
    public ItineraryAdapter(Context context, TourStop[] stops) {
        super(context, R.layout.individual_day);
        this.stops = stops;
        this.mContext=context;
        Log.d("check", "got here1");
    }

    public int getCount(){
        return stops.length;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder mViewHolder = new ViewHolder();
        Log.d("check", "got here1.25");
        if(convertView == null) {
            Log.d("check", "got here1.5");
            LayoutInflater mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.individual_day, parent, false);

//            mViewHolder.timeStart = (TextView) convertView.findViewById(R.id.timeStart);
//            mViewHolder.timeEnd= (TextView) convertView.findViewById(R.id.timeEnd);
            mViewHolder.time = (TextView) convertView.findViewById(R.id.timeTextView);
            mViewHolder.time2= (TextView) convertView.findViewById(R.id.timeTextView2);
            mViewHolder.location = (TextView) convertView.findViewById(R.id.locationNameTextView);
            mViewHolder.address = (TextView) convertView.findViewById(R.id.locationAddressTextView);
            mViewHolder.travel = (TextView) convertView.findViewById(R.id.travelTimeTextView);

            convertView.setTag(mViewHolder);
            Log.d("check", "got here2");
        }
        else{
            mViewHolder = (ViewHolder) convertView.getTag();
        }

        TourStop stop = stops[position];
        Attraction att = stop.getAttraction();

//        mViewHolder.timeStart.setText(stop.getStartTime());
//        mViewHolder.timeEnd.setText(stop.getEndTime());


        mViewHolder.time.setText(TourOptimizer.getTimeDisplayString(stop.getStartTime()));
        Log.d("adapter", att.getName());
        mViewHolder.time2.setText(TourOptimizer.getTimeDisplayString(stop.getEndTime()));
        mViewHolder.location.setText(att.getName());
        mViewHolder.address.setText(att.getAddress());
        if(position == stops.length - 1){
            mViewHolder.travel.setText("");
        }
        else{
            mViewHolder.travel.setText(TourOptimizer.getTravelTime(stop, stops[position+1]) +" min travel time");
        }

        Log.d("check", "got here3");

        return convertView;
    }

    static class ViewHolder{
        TextView time;
        TextView time2;
        TextView location;
        TextView address;
        TextView travel;

    }
}
