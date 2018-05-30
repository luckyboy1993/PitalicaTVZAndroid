package com.example.pitalicatvzandroid.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Exam implements Serializable{
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("creatorId")
    @Expose
    private int creatorId;
    @SerializedName("examTitle")
    @Expose
    private String examTitle;
    @SerializedName("creator")
    @Expose
    private User creator;

    @SerializedName("questions")
    @Expose
    private List<Question> questions;
    @SerializedName("students")
    @Expose
    private List<UserExamTaken> students;

    public int getId() {return id;}

    public void setId(int id) {
        this.id = id;
    }

    public int getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(int creatorId) {
        this.creatorId = creatorId;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public String getExamTitle() {
        return examTitle;
    }

    public void setExamTitle(String examTitle) {
        this.examTitle = examTitle;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public List<UserExamTaken> getStudents() {
        return students;
    }

    public void setStudents(List<UserExamTaken> students) {
        this.students = students;
    }
}
