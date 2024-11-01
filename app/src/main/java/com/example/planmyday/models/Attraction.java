package com.example.planmyday.models;

import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

@IgnoreExtraProperties
public class Attraction implements Serializable {

    private String name;
    private String address;

    //whether it is usc or not (LA)
    private boolean usc;
    private String description;

    //estimated time spent in minutes
    private int time;
    //distance from USC in miles
    private double distUSC;
    //0:mon, 1:tues...6:sun
    //hours are formatted as an int as HHMM
    private HashMap<String, ArrayList<Integer>> hours;
    private String imageUrl;

    private double latitude;
    private double longitude;

    public Attraction(){

    }

    public Attraction(String name, String address, Boolean usc, String description, int time, double distUSC, HashMap<String, ArrayList<Integer>> hours, String imageUrl, double latitude, double longitude) {
        this.name = name;
        this.address = address;
        this.usc = usc;
        this.description = description;;
        this.time = time;
        this.distUSC = distUSC;
        this.hours = hours;
        this.imageUrl = imageUrl;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    public boolean isUsc() {
        return usc;
    }

    public void setUsc(boolean usc) {
        this.usc = usc;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public double getDistUSC() {
        return distUSC;
    }

    public void setDistUSC(double distUSC) {
        this.distUSC = distUSC;
    }

    public HashMap<String, ArrayList<Integer>> getHours() {
        return hours;
    }

    public void setHours(HashMap<String, ArrayList<Integer>> hours) {
        this.hours = hours;
    }

    public String getImageURL() {
        return imageUrl;
    }

    public void setImageURL(String url) { this.imageUrl = url;}

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double lat) {
        this.latitude = lat;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

}
