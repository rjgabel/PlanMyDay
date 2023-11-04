package com.example.planmyday.planning;

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

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class MyAdapter extends ArrayAdapter<String> {

    Attraction[] attractions;

    Context mContext;
    public MyAdapter(Context context, Attraction[] attractions) {
        super(context, R.layout.individual_location);
        this.attractions=attractions;
        this.mContext=context;
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

        ViewHolder finalMViewHolder = mViewHolder;
        mViewHolder.favoritesButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {

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

    }
}
