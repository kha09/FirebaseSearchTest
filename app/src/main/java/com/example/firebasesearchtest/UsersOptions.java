package com.example.firebasesearchtest;

public class UsersOptions {

    private String userChoice;
    private String userName;

    public UsersOptions() {
    }

    public String getUserChoice() {
        return userChoice;
    }

    public void setUserChoice(String userChoice) {
        this.userChoice = userChoice;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public UsersOptions(String userChoice, String userName) {
        this.userChoice = userChoice;
        this.userName = userName;
    }
}
