package com.chatsample.user.models;

/**
 * Created by Ocs pl-79(17.2.2016) on 11/27/2017.
 */

public class User {
    public String uid;
    public String email;
    public String firebaseToken;

    public User(){

    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirebaseToken() {
        return firebaseToken;
    }

    public void setFirebaseToken(String firebaseToken) {
        this.firebaseToken = firebaseToken;
    }
}
