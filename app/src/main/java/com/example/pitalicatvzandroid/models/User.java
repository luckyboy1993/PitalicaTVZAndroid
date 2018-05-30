package com.example.pitalicatvzandroid.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class User implements Serializable {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("fullName")
    @Expose
    private String fullName;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("examsCreated")
    @Expose
    private List<Exam> examsCreated;
    @SerializedName("examsTaken")
    @Expose
    private List<UserExamTaken> examsTaken;

    public int getId() {return id;}

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Exam> getExamsCreated() {
        return examsCreated;
    }

    public void setExamsCreated(List<Exam> examsCreated) {
        this.examsCreated = examsCreated;
    }

    public List<UserExamTaken> getExamsTaken() {
        return examsTaken;
    }

    public void setExamsTaken(List<UserExamTaken> examsTaken) {
        this.examsTaken = examsTaken;
    }

    @Override
    public String toString() {
        return "{" +
                "id='" + id + '\'' +
                "fullName='" + fullName + '\'' +
                ", password='" + password +
                '}';
    }
}