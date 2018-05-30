package com.example.pitalicatvzandroid.models;

import java.io.Serializable;
import java.util.List;

public class UserList implements Serializable{
    private List<User> users;

    public List<User> getUserList() {
        return users;
    }

    public void setUserList(List<User> users) {
        this.users = users;
    }
}
