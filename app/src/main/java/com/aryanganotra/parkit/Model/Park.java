package com.aryanganotra.parkit.Model;

public class Park {
    private boolean parked=false;
    private String code;

    public Park(String code, boolean status){
        this.code = code;
        this.parked = status;
    }

    public String getCode() {
        return code;
    }

    public boolean isParked() {
        return parked;
    }

    public void setParked(boolean parked) {
        this.parked = parked;
    }
}
