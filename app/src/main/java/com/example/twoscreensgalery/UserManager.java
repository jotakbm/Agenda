package com.example.twoscreensgalery;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class UserManager {
    private static UserManager instance;
    private static ArrayList<User> usersList = new ArrayList<>();
    private User actualUser;

    public static UserManager getInstance() {

        if (instance == null) {
            instance = new UserManager();
            OnCreated();
        }

        return instance;
    }

    private static void OnCreated() {
        instance.CreateNewAccount("adm", "adm");
        usersList.get(0).addInformation(new Information("a", "teste01", "1111-1111", 0));
        usersList.get(0).addInformation(new Information("b", "teste02", "2222-2222", 1));
        usersList.get(0).addInformation(new Information("c", "teste03", "3333-3333", 2));
        usersList.get(0).addInformation(new Information("final", "teste04", "4444-4444", 3));
    }

    public boolean CheckIfUserExists(String username, String password) {
        return usersList.contains(GetUser(username, password));
    }

    public void CreateNewAccount(String username, String password) {
        User account = new User(username, password);
        usersList.add(account);
    }

    public void DeleteAccount(String username, String password) {
        usersList.remove(GetUser(username, password));
    }

    public boolean CheckIfUsernameExists(String username) {
        for (User user : usersList) {
            if (username.equals(user.username))
                return true;
        }
        return false;
    }

    public void LogIn(String username, String password) {
        actualUser = GetUser(username, password);
    }

    public void LogOut() {
        actualUser = null;
    }

    User GetUser(String username, String password){
        for (User user : usersList) {
            if (username.equals(user.username) && password.equals(user.password))
                return user;
        }
        return null;
    }

    public void AddInformationToUser(Information info) {
        actualUser.addInformation(info);
    }

    public ArrayList<String> GetInformationNameList() {
        ArrayList<String> nameList = new ArrayList<>();
        for (Information information: actualUser.informationList) {
            nameList.add(information.name);
        }
        return nameList;
    }
    public Information GetInformationById(int id) {
        return actualUser.informationList.get(id);
    }
}
