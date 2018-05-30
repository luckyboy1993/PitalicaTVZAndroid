package com.example.pitalicatvzandroid.models;

import java.io.Serializable;
import java.util.List;

public class ExamList implements Serializable{
    private List<Exam> exams;

    public List<Exam> getExams() {
        return exams;
    }

    public void setExams(List<Exam> exams) {
        this.exams = exams;
    }
}
