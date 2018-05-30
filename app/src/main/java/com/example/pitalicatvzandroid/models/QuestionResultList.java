package com.example.pitalicatvzandroid.models;

import java.io.Serializable;
import java.util.List;

public class QuestionResultList implements Serializable{
    private List<QuestionResult> questionResults;

    public List<QuestionResult> getQuestionResults() {
        return questionResults;
    }

    public void setQuestionResults(List<QuestionResult> questionResuls) {
        this.questionResults = questionResuls;
    }
}
