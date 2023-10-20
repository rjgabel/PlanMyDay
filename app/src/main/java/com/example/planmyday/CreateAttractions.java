package com.example.planmyday;

import android.content.Context;
import android.util.Log;

import com.example.planmyday.models.Attraction;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

//Class used to generate attractions into the database
//adds attraction objects to ensure app compatibility
public class CreateAttractions {
    Context context;
    ArrayList<Attraction> attractions;
    DatabaseReference dbRef = FirebaseDatabase.getInstance().getReferenceFromUrl("https://planmyday-16506-default-rtdb.firebaseio.com/");

    CreateAttractions(Context context){
        attractions = new ArrayList<>();
        this.context = context;
    }

    public void generate(){
        //Read JSON into a string
        String jsonString = "";
        try {
            InputStream inputStream = context.getResources().openRawResource(R.raw.attractions);
            StringBuilder content = new StringBuilder();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;

            while ((line = reader.readLine()) != null) {
                content.append(line);
            }
            reader.close();

            // Convert StringBuilder to a String
            jsonString = content.toString();

            // Now you have your JSON content as a string (jsonString)
            Log.d("JSON",jsonString);


        } catch (IOException e){
            e.printStackTrace();
        }

        Gson gs = new Gson();

        Attraction[] attractions = gs.fromJson(jsonString, Attraction[].class);

        for (Attraction attraction : attractions){
            //TODO: add to database once all parts are filled in
            //dbRef.child("attractions").child(attraction.getName()).setValue(attraction);
            Log.d("Names", attraction.getName());
        }
        Log.d("JSONRESULT",jsonString);

    }

    public void addToDB(Attraction attraction){
        System.out.println(attraction);
    }
}
