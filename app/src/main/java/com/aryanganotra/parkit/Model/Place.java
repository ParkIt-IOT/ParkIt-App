package com.aryanganotra.parkit.Model;

public class Place {


    public String place;
    public String dist;

    public Place(String place, String dist){
        this.place = place;
        this.dist = dist;
    }

    public void setDist(String dist) {
        this.dist = dist;
    }

    public String getDist() {
        return dist;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }
}
