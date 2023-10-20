package com.example.planmyday;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

//Class used to generate attractions into the database
//adds attraction objects to ensure app compatibility
public class CreateAttractions {
    Context context;
    ArrayList<Attraction> attractions;

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
//        String json = "{\n" +
//                "      \"name\": \"Ronald Tutor Campus Center\",\n" +
//                "      \"address\": \"3607 Trousdale Pkwy, Los Angeles, CA 90089\",\n" +
//                "      \"usc\": true,\n" +
//                "      \"description\": \"A great place to get food on campus!\",\n" +
//                "      \"time\": 45,\n" +
//                "      \"distUSC\": 0.0,\n" +
//                "      \"hours\": {\n" +
//                "        \"0\": [700, 2200],\n" +
//                "        \"1\": [700, 2200],\n" +
//                "        \"2\": [700, 2200],\n" +
//                "        \"3\": [700, 2200],\n" +
//                "        \"4\": [700, 2200],\n" +
//                "        \"5\": [700, 2200],\n" +
//                "        \"6\": [700, 2200]\n" +
//                "      }\n" +
//                "    }";
        Attraction[] attractions = gs.fromJson(jsonString, Attraction[].class);
        for (Attraction attraction : attractions){
            Log.d("Names", attraction.getName());
        }
        Log.d("JSONRESULT",jsonString);

    }

    public void addToDB(Attraction attraction){
        System.out.println(attraction);
    }
}
