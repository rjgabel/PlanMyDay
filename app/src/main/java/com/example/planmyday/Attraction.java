package com.example.planmyday;

public class Attraction {
    public final static Attraction[] ATTRACTIONS = {new Attraction("Ronald Tutor Campus Center", "3607 Trousdale Pkwy, Los Angeles, CA 90089"), new Attraction("Fertitta Hall", "610 Childs Way, Los Angeles, CA 90089"),
//            new Location("Epstein Family Plaza"),
//            new Location("School of Cinematic Arts"),
//            new Location("USC Fisher Museum of Art"),
//            new Location("Leavey Library"),
//            new Location("Allyson Felix Field"),
//            new Location("USC Village"),
//            new Location("Griffith Observatory"),
//            new Location("The Last Bookstore"),
//            new Location("Santa Monica Beach"),
//            new Location("Universal Studios"),
//            new Location("LACMA"),
//            new Location("The Getty"),
//            new Location("The Grove"),
//            new Location("Natural History Museum"),
//            new Location("The Broad Museum"),
//            new Location("Little Tokyo"),
//            new Location("Venice Beach"),
//            new Location("Chinese Theater")
    };
    private final String name;
    private final String address;

    Attraction(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }
}
