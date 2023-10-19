package com.example.planmyday;

import com.google.firebase.database.IgnoreExtraProperties;

import java.util.ArrayList;

//UserAccount class which holds users;
@IgnoreExtraProperties
public class UserAccount {
    private String name;

    private String email;
    private String password;

    //TODO: Change to arraylist of TourPlans
    private ArrayList<TourPlan> tours;

    public UserAccount(){

    }

    public UserAccount(String name, String email, String password, ArrayList<TourPlan> tours){
        this.name = name;
        this.email = email;
        this.password = password;
        this.tours = tours;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ArrayList<TourPlan> getTours() {
        return tours;
    }

    public void setTours(ArrayList<TourPlan> tours) {
        this.tours = tours;
    }


//    public void addTours(int tempTour) {
//        this.tours.add(tempTour);
//    }



}
