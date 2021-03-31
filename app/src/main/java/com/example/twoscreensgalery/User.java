package com.example.twoscreensgalery;

import java.util.ArrayList;

public class User {
    public String username;
    public String password;
    public ArrayList<Information> informationList = new ArrayList<>();

    public User(String username, String password){
        this.username = username;
        this.password = password;
    }

    public void addInformation(Information info) {
        informationList.add(info);
    }
}
