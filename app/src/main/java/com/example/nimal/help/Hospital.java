package com.example.nimal.help;

/**
 * Created by Nimal on 24/04/2017.
 */

public class Hospital {
    public String name;
    public String city;
    public String latitude;
    public String longitude;
    public String speciality;
    public String phno;
    public String rating;

    public Hospital() {

    }

    public Hospital(String name, String city, String latitude, String longitude, String speciality, String phno, String rating) {

        this.name = name;
        this.city = city;
        this.latitude = latitude;
        this.longitude = longitude;
        this.speciality = speciality;
        this.phno = phno;
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getSpeciality() {
        return speciality;
    }

    public String getPhno() {
        return phno;
    }

    public String getRating() {
        return rating;
    }
}
