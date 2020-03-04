package com.pipe.www.pipe.model;

import com.pipe.www.pipe.R;

public class User {


    private String fullname;
    private String email;
    private String institution;
    private String profilelink;
    private long timejoin;
    private long lastseen;
    private long onlinetime;
    private String userid;
    private double latitude;
    private double longitude;


    public User() {
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getInstitution() {
        return institution;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }

    public String getProfilelink() {
        return profilelink;
    }

    public void setProfilelink(String profilelink) {
        this.profilelink = profilelink;
    }

    public long getTimejoin() {
        return timejoin;
    }

    public void setTimejoin(long timejoin) {
        this.timejoin = timejoin;
    }

    public long getLastseen() {
        return lastseen;
    }

    public void setLastseen(long lastseen) {
        this.lastseen = lastseen;
    }

    public long getOnlinetime() {
        return onlinetime;
    }

    public void setOnlinetime(long onlinetime) {
        this.onlinetime = onlinetime;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
