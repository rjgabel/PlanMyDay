package com.example.planmyday;

import java.util.ArrayList;

//UserAccount class which holds users;
public class UserAccount {
    private String name;
    private String email;
    private String password;

    //TODO: Change to arraylist of TourPlans
    private ArrayList<Integer> tours;

    public UserAccount(){}

    public UserAccount(String name, String email, String password){
        this.email = email;
        this.password = password;
    }

    public ArrayList<Integer> getTours() {
        return tours;
    }

    public void addTours(int tempTour) {
        this.tours.add(tempTour);
    }



}
