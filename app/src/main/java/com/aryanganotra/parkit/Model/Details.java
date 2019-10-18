package com.aryanganotra.parkit.Model;

public class Details {

    String place, time, id, slot_code;

    public Details(){

    }

    public Details(String place, String time, String id, String slot_code) {
        this.place = place;
        this.time = time;
        this.id = id;
        this.slot_code = slot_code;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSlot_code() {
        return slot_code;
    }

    public void setSlot_code(String slot_code) {
        this.slot_code = slot_code;
    }
}
