package com.example.pitalicatvzandroid.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Question implements Serializable {
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("questionText")
    @Expose
    private String questionText;
    @SerializedName("correctAnswer")
    @Expose
    private String correctAnswer;
    @SerializedName("wrongAnswer1")
    @Expose
    private String wrongAnswer1;
    @SerializedName("wrongAnswer2")
    @Expose
    private String wrongAnswer2;
    @SerializedName("wrongAnswer3")
    @Expose
    private String wrongAnswer3;
    @SerializedName("score")
    @Expose
    private int score;

    @SerializedName("examId")
    @Expose
    private int examId;
    @SerializedName("visibility")
    @Expose
    private boolean visibility;

    @SerializedName("exam")
    @Expose
    private Exam exam;


    public int getId() {return id;}

    public void setId(int id) {
        this.id = id;
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

    public String getQuestionText() { return questionText; }

    public void setQuestionText(String questionText) { this.questionText = questionText; }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public String getWrongAnswer1() {
        return wrongAnswer1;
    }

    public void setWrongAnswer1(String wrongAnswer1) { this.wrongAnswer1 = wrongAnswer1; }

    public String getWrongAnswer2() {
        return wrongAnswer2;
    }

    public void setWrongAnswer2(String wrongAnswer2) {
        this.wrongAnswer2 = wrongAnswer2;
    }

    public String getWrongAnswer3() {
        return wrongAnswer3;
    }

    public void setWrongAnswer3(String wrongAnswer3) {
        this.wrongAnswer3 = wrongAnswer3;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public boolean isVisibility() {
        return visibility;
    }

    public void setVisibility(boolean visibility) {
        this.visibility = visibility;
    }


}
