package com.example.planmyday.home;

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

import com.example.planmyday.map.TourOptimizer;
import com.example.planmyday.models.Attraction;
import com.example.planmyday.models.TourPlan;
import com.example.planmyday.models.TourStop;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

public class HomeAdapter extends ArrayAdapter<String> {

    TourStop[] stops;

    Context mContext;
    public HomeAdapter(Context context, TourStop[] stops) {
        super(context, R.layout.individual_plan);
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
        com.example.planmyday.home.HomeAdapter.ViewHolder mViewHolder = new com.example.planmyday.home.HomeAdapter.ViewHolder();
        Log.d("check", "got here1.25");
        if(convertView == null) {
            Log.d("check", "got here1.5");
            LayoutInflater mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.individual_plan, parent, false);

            mViewHolder.planName = (TextView) convertView.findViewById(R.id.planName);
            mViewHolder.estimatedRoute= (TextView) convertView.findViewById(R.id.estimatedRoute);

            convertView.setTag(mViewHolder);
            Log.d("check", "got here2");
        }
        else{
            mViewHolder = (com.example.planmyday.home.HomeAdapter.ViewHolder) convertView.getTag();
        }

        mViewHolder.planName.setText(" Tour: " + " stops");
        mViewHolder.estimatedRoute.setText("estimated route time: ");

        if(position % 2 == 0){
            convertView.setBackgroundColor(Color.parseColor("#90FFD159"));
        }


        return convertView;
    }

    static class ViewHolder{
        TextView planName;
        TextView estimatedRoute;

    }
}
