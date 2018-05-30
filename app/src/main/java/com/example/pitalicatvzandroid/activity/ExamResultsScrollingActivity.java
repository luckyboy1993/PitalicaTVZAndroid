package com.example.pitalicatvzandroid.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pitalicatvzandroid.R;
import com.example.pitalicatvzandroid.models.Question;
import com.example.pitalicatvzandroid.models.QuestionResultList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ExamResultsScrollingActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_exam_scrolling);

        final Bundle bundle = getIntent().getExtras();
        final QuestionResultList questionResults = (QuestionResultList)bundle.getSerializable("questionResults");

        setTextExam(questionResults);

    }

    public void setTextExam(QuestionResultList questionResultList){

        Question question = new Question();
        List<String> answers = new ArrayList<String>();

        String answered;
        String correctAnswer;
        View holder;

        //max questions is 5 or less
        int maxQuestions = questionResultList.getQuestionResults().size()>5 ? 5 : questionResultList.getQuestionResults().size();

        //setting name for test
        ((TextView) findViewById(R.id.testName)).setText(questionResultList.getQuestionResults().get(0).getExam().getExamTitle());

        //shuffling questions
        Collections.shuffle(questionResultList.getQuestionResults());

        LinearLayout layout = (LinearLayout)findViewById(R.id.testLayout);

        int m = 0;
        //setting text and answers for each question
        for (int i = 0; m < maxQuestions; i++) {
            answered = questionResultList.getQuestionResults().get(m).getAnswered();
            correctAnswer = questionResultList.getQuestionResults().get(m).getCorrectAnswer();

            //shuffling answers
            question = questionResultList.getQuestionResults().get(m).getQuestion();


            View v = layout.getChildAt(i);
            if (v instanceof LinearLayout) {
                v.setVisibility(View.VISIBLE);

                //shuffling answers
                answers.add(question.getCorrectAnswer());
                answers.add(question.getWrongAnswer1());
                answers.add(question.getWrongAnswer2());
                answers.add(question.getWrongAnswer3());
                Collections.shuffle(answers);

                //setting question text
                holder = ((LinearLayout) v).getChildAt(0);
                ((TextView) holder).setText(question.getQuestionText());

                //setting answers
                for(int j=0; j<answers.size();j++) {
                    holder = ((LinearLayout) v).getChildAt(j+1);
                    ((TextView) holder).setText(answers.get(j));
                    if(((TextView) holder).getText().toString().equals(answered)){
                        ((TextView) holder).setTextColor(Color.RED);
                    }
                    if(((TextView) holder).getText().toString().equals(correctAnswer)){
                        ((TextView) holder).setTextColor(Color.GREEN);
                    }
                }
                m++;
                answers.clear();
            }
        }

        int scoreFinal = 0;
        int scoreMax = 0;
        for(int i = 0; i<questionResultList.getQuestionResults().size();i++){
            scoreMax += 2;
            if(questionResultList.getQuestionResults().get(i).getAnswered().equals(questionResultList.getQuestionResults().get(i).getCorrectAnswer()))
                scoreFinal += 2;
        }

        ((TextView) findViewById(R.id.scoreFinal)).setText(scoreFinal + "/" + scoreMax);
    }
}
