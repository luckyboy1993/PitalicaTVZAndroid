package com.example.pitalicatvzandroid.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class UserExamTaken implements Serializable {
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("userId")
    @Expose
    private int userId;

    @SerializedName("user")
    @Expose
    private User user;
    @SerializedName("examId")
    @Expose
    private int examId;
    @SerializedName("exam")
    @Expose
    private Exam exam;

    public int getId() {return id;}

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public User getUser() { return user; }

    public void setUser(User user) {
        this.user = user;
    }

    public int getExamId() {
        return examId;
    }

    public void setExamId(int examId) {
        this.examId = examId;
    }

    public Exam getExam() {
        return exam;
    }

    public void setExam(Exam exam) {
        this.exam = exam;
    }
}
