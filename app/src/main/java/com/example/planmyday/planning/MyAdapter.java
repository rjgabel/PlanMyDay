package com.example.planmyday.planning;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import androidx.appcompat.widget.AppCompatButton;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.example.planmyday.R;

import com.example.planmyday.models.Attraction;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class MyAdapter extends ArrayAdapter<String> {

    Attraction[] attractions;
    String[] isAdded;
    Context mContext;
    String tourType;
    public MyAdapter(Context context, Attraction[] attractions, String tourType) {
        super(context, R.layout.individual_location);
        this.attractions=attractions;
        this.mContext=context;
        this.isAdded = new String[attractions.length];
        this.tourType = tourType;
        Arrays.fill(isAdded, "ADD");
        for (String str : isAdded) {
            Log.d("str13", str);
        }
    }

    @Override
    public int getCount() {
        return attractions.length;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder mViewHolder = new ViewHolder();
        if(convertView == null) {
            LayoutInflater mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.individual_location, parent, false);

            mViewHolder.locationImage = (ImageView) convertView.findViewById(R.id.locationImage);
            mViewHolder.locationName = (TextView) convertView.findViewById(R.id.locationName);
            mViewHolder.locationDesc = (TextView) convertView.findViewById(R.id.locationDescription);
            mViewHolder.favoritesButton = convertView.findViewById(R.id.favoritesButton);
            mViewHolder.duration = (TextView) convertView.findViewById(R.id.estimatedDuration);
            mViewHolder.distFromUSC = (TextView) convertView.findViewById(R.id.distFromUSC);

            convertView.setTag(mViewHolder);
        }
        else{
            mViewHolder = (ViewHolder) convertView.getTag();
        }

        try{
            URL imageurl = new URL(attractions[position].getImageURL());
            Glide.with(mContext).load(imageurl).into(mViewHolder.locationImage);
        } catch(IOException e){
            e.printStackTrace();
        }

            mViewHolder.locationName.setText(attractions[position].getName());
            mViewHolder.locationDesc.setText(attractions[position].getDescription());
            mViewHolder.favoritesButton.setText(isAdded[position]);
            mViewHolder.duration.setText("recommended duration: "+attractions[position].getTime() + " min");

            if(tourType.equals("la")){
                mViewHolder.distFromUSC.setText("distance from USC: "+attractions[position].getDistUSC() + " miles");
            }

        // Get background drawable
        Drawable buttonBackground = mViewHolder.favoritesButton.getBackground();

        if(isAdded[position].equals("ADD")) {
            // Set color on drawable
            buttonBackground.setColorFilter(Color.parseColor("#c2c4c3"), PorterDuff.Mode.SRC_IN);

        } else {
            buttonBackground.setColorFilter(Color.parseColor("#11CA9D"), PorterDuff.Mode.SRC_IN);
        }

// Set colored drawable as background
        mViewHolder.favoritesButton.setBackground(buttonBackground);

        ViewHolder finalMViewHolder = mViewHolder;
        mViewHolder.favoritesButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    if (isAdded[position].equals("ADD")){
                        isAdded[position] = "ADDED";
                    }
                    else{
                        isAdded[position] = "ADD";
                    }
                    notifyDataSetChanged();
                    if (mContext instanceof LocationsActivity) {
                        ((LocationsActivity) mContext).addToFavorites(attractions[position]);
                    }
                    Log.d("LocationsActivity", "Clicked");
                }
            });

       return convertView;
    }

    static class ViewHolder{
        ImageView locationImage;
        TextView locationName;
        TextView locationDesc;
        androidx.appcompat.widget.AppCompatButton favoritesButton;
        TextView duration;

        TextView distFromUSC;

    }
}
