package com.example.pitalicatvzandroid.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class QuestionResult implements Serializable {
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("score")
    @Expose
    private int score;
    @SerializedName("answered")
    @Expose
    private String answered;
    @SerializedName("correctAnswer")
    @Expose
    private String correctAnswer;

    @SerializedName("questionId")
    @Expose
    private int questionId;
    @SerializedName("userId")
    @Expose
    private int userId;
    @SerializedName("examId")
    @Expose
    private int examId;

    @SerializedName("user")
    @Expose
    private User user;
    @SerializedName("question")
    @Expose
    private Question question;
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

    public int getExamId() {
        return examId;
    }

    public void setExamId(int examId) {
        this.examId = examId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getAnswered() {
        return answered;
    }

    public void setAnswered(String answered) {
        this.answered = answered;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public Exam getExam() {
        return exam;
    }

    public void setExam(Exam exam) {
        this.exam = exam;
    }

}
