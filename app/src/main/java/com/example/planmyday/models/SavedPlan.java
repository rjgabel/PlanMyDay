package com.example.planmyday.models;

import java.sql.Array;
import java.sql.DataTruncation;
import java.time.LocalDate;
import java.util.ArrayList;

public class SavedPlan {
    private ArrayList<Attraction> attractions;
    private int numDays;
    private String date;

    public SavedPlan(ArrayList<Attraction> attractions, int numDays, String date){
        this.attractions = attractions;
        this.numDays = numDays;
        this.date = date;
    }

    public SavedPlan(){

    }

    public ArrayList<Attraction> getAttractions() {
        return attractions;
    }

    public void setAttractions(ArrayList<Attraction> attractions) {
        this.attractions = attractions;
    }

    public int getNumDays() {
        return numDays;
    }

    public void setNumDays(int numDays) {
        this.numDays = numDays;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
