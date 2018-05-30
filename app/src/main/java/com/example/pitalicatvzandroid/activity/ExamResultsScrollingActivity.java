package com.example.pitalicatvzandroid.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
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
        Button submitBtn = findViewById(R.id.btn_submit);

        final Bundle bundle = getIntent().getExtras();
        final QuestionResultList questionResults = (QuestionResultList)bundle.getSerializable("questionResults");

        setTextExam(questionResults);

    }

    public void showToast(String message){
        Toast.makeText(ExamResultsScrollingActivity.this, message, Toast.LENGTH_SHORT).show();
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
                    String ans = ((TextView) holder).getText().toString();
                    if(((TextView) holder).getText().toString().equals(answered)){
                        ((TextView) holder).setTextColor(Color.RED);
                    }
                    if(((TextView) holder).getText().toString().equals(correctAnswer)){
                        ((TextView) holder).setTextColor(Color.GREEN);
                    }
                }

                m++;
                answers.clear();
            } else {

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
/*
    public void setText(QuestionResultList questionResults){
        Collections.shuffle(questionResults.getQuestionResults());
        Question question = new Question();
        int questionCount = 0;
        List<String> answers = new ArrayList<String>();
        String answered;
        String correctAnswer;
        ((EditText) findViewById(R.id.testName)).setText(questionResults.getQuestionResults().get(0).getExam().getExamTitle());

        if(questionCount<questionResults.getQuestionResults().size()) {

            answered = questionResults.getQuestionResults().get(0).getAnswered();
            correctAnswer = questionResults.getQuestionResults().get(0).getCorrectAnswer();

            question = questionResults.getQuestionResults().get(0).getQuestion();
            answers.add(question.getCorrectAnswer());
            answers.add(question.getWrongAnswer1());
            answers.add(question.getWrongAnswer2());
            answers.add(question.getWrongAnswer3());
            Collections.shuffle(answers);

            ((EditText) findViewById(R.id.question1)).setText(question.getQuestionText());
            ((EditText) findViewById(R.id.answer11)).setText(answers.get(0));
            ((EditText) findViewById(R.id.answer12)).setText(answers.get(1));
            ((EditText) findViewById(R.id.answer13)).setText(answers.get(2));
            ((EditText) findViewById(R.id.answer14)).setText(answers.get(3));

            if (((EditText) findViewById(R.id.answer11)).getText().equals(answered)) {
                ((EditText) findViewById(R.id.answer11)).setBackgroundColor(Color.RED);
            } else if (((EditText) findViewById(R.id.answer12)).getText().equals(answered)) {
                ((EditText) findViewById(R.id.answer12)).setBackgroundColor(Color.RED);
            } else if (((EditText) findViewById(R.id.answer13)).getText().equals(answered)) {
                ((EditText) findViewById(R.id.answer13)).setBackgroundColor(Color.RED);
            } else if (((EditText) findViewById(R.id.answer14)).getText().equals(answered)) {
                ((EditText) findViewById(R.id.answer14)).setBackgroundColor(Color.RED);
            }

            if (((EditText) findViewById(R.id.answer11)).getText().equals(correctAnswer)) {
                ((EditText) findViewById(R.id.answer11)).setBackgroundColor(Color.GREEN);
            } else if (((EditText) findViewById(R.id.answer12)).getText().equals(correctAnswer)) {
                ((EditText) findViewById(R.id.answer12)).setBackgroundColor(Color.GREEN);
            } else if (((EditText) findViewById(R.id.answer13)).getText().equals(correctAnswer)) {
                ((EditText) findViewById(R.id.answer13)).setBackgroundColor(Color.GREEN);
            } else if (((EditText) findViewById(R.id.answer14)).getText().equals(correctAnswer)) {
                ((EditText) findViewById(R.id.answer14)).setBackgroundColor(Color.GREEN);
            }
            questionCount++;

            if(questionCount<questionResults.getQuestionResults().size()) {
                answers.clear();

                answered = questionResults.getQuestionResults().get(1).getAnswered();
                correctAnswer = questionResults.getQuestionResults().get(1).getCorrectAnswer();

                question = questionResults.getQuestionResults().get(1).getQuestion();
                answers.add(question.getCorrectAnswer());
                answers.add(question.getWrongAnswer1());
                answers.add(question.getWrongAnswer2());
                answers.add(question.getWrongAnswer3());
                Collections.shuffle(answers);

                ((EditText) findViewById(R.id.question2)).setText(question.getQuestionText());
                ((EditText) findViewById(R.id.answer21)).setText(answers.get(0));
                ((EditText) findViewById(R.id.answer22)).setText(answers.get(1));
                ((EditText) findViewById(R.id.answer23)).setText(answers.get(2));
                ((EditText) findViewById(R.id.answer24)).setText(answers.get(3));

                if(((EditText) findViewById(R.id.answer21)).getText().equals(answered)){
                    ((EditText) findViewById(R.id.answer21)).setBackgroundColor(Color.RED);
                }else if(((EditText) findViewById(R.id.answer22)).getText().equals(answered)){
                    ((EditText) findViewById(R.id.answer22)).setBackgroundColor(Color.RED);
                }else if(((EditText) findViewById(R.id.answer23)).getText().equals(answered)){
                    ((EditText) findViewById(R.id.answer23)).setBackgroundColor(Color.RED);
                }else if(((EditText) findViewById(R.id.answer24)).getText().equals(answered)){
                    ((EditText) findViewById(R.id.answer24)).setBackgroundColor(Color.RED);
                }

                if(((EditText) findViewById(R.id.answer21)).getText().equals(correctAnswer)){
                    ((EditText) findViewById(R.id.answer21)).setBackgroundColor(Color.GREEN);
                }else if(((EditText) findViewById(R.id.answer22)).getText().equals(correctAnswer)){
                    ((EditText) findViewById(R.id.answer22)).setBackgroundColor(Color.GREEN);
                }else if(((EditText) findViewById(R.id.answer23)).getText().equals(correctAnswer)){
                    ((EditText) findViewById(R.id.answer23)).setBackgroundColor(Color.GREEN);
                }else if(((EditText) findViewById(R.id.answer24)).getText().equals(correctAnswer)){
                    ((EditText) findViewById(R.id.answer24)).setBackgroundColor(Color.GREEN);
                }
                questionCount++;

                if(questionCount<questionResults.getQuestionResults().size()) {

                    answers.clear();

                    answered = questionResults.getQuestionResults().get(2).getAnswered();
                    correctAnswer = questionResults.getQuestionResults().get(2).getCorrectAnswer();

                    question = questionResults.getQuestionResults().get(2).getQuestion();
                    answers.add(question.getCorrectAnswer());
                    answers.add(question.getWrongAnswer1());
                    answers.add(question.getWrongAnswer2());
                    answers.add(question.getWrongAnswer3());
                    Collections.shuffle(answers);

                    ((EditText) findViewById(R.id.question3)).setText(question.getQuestionText());
                    ((EditText) findViewById(R.id.answer31)).setText(answers.get(0));
                    ((EditText) findViewById(R.id.answer32)).setText(answers.get(1));
                    ((EditText) findViewById(R.id.answer33)).setText(answers.get(2));
                    ((EditText) findViewById(R.id.answer34)).setText(answers.get(3));

                    if(((EditText) findViewById(R.id.answer31)).getText().equals(answered)){
                        ((EditText) findViewById(R.id.answer31)).setBackgroundColor(Color.RED);
                    }else if(((EditText) findViewById(R.id.answer32)).getText().equals(answered)){
                        ((EditText) findViewById(R.id.answer32)).setBackgroundColor(Color.RED);
                    }else if(((EditText) findViewById(R.id.answer33)).getText().equals(answered)){
                        ((EditText) findViewById(R.id.answer33)).setBackgroundColor(Color.RED);
                    }else if(((EditText) findViewById(R.id.answer34)).getText().equals(answered)){
                        ((EditText) findViewById(R.id.answer34)).setBackgroundColor(Color.RED);
                    }

                    if(((EditText) findViewById(R.id.answer31)).getText().equals(correctAnswer)){
                        ((EditText) findViewById(R.id.answer31)).setBackgroundColor(Color.GREEN);
                    }else if(((EditText) findViewById(R.id.answer32)).getText().equals(correctAnswer)){
                        ((EditText) findViewById(R.id.answer32)).setBackgroundColor(Color.GREEN);
                    }else if(((EditText) findViewById(R.id.answer33)).getText().equals(correctAnswer)){
                        ((EditText) findViewById(R.id.answer33)).setBackgroundColor(Color.GREEN);
                    }else if(((EditText) findViewById(R.id.answer34)).getText().equals(correctAnswer)){
                        ((EditText) findViewById(R.id.answer34)).setBackgroundColor(Color.GREEN);
                    }
                    questionCount++;

                    if(questionCount<questionResults.getQuestionResults().size()) {

                        answers.clear();

                        answered = questionResults.getQuestionResults().get(3).getAnswered();
                        correctAnswer = questionResults.getQuestionResults().get(3).getCorrectAnswer();

                        question = questionResults.getQuestionResults().get(3).getQuestion();
                        answers.add(question.getCorrectAnswer());
                        answers.add(question.getWrongAnswer1());
                        answers.add(question.getWrongAnswer2());
                        answers.add(question.getWrongAnswer3());
                        Collections.shuffle(answers);

                        ((EditText) findViewById(R.id.question4)).setText(question.getQuestionText());
                        ((EditText) findViewById(R.id.answer41)).setText(answers.get(0));
                        ((EditText) findViewById(R.id.answer42)).setText(answers.get(1));
                        ((EditText) findViewById(R.id.answer43)).setText(answers.get(2));
                        ((EditText) findViewById(R.id.answer44)).setText(answers.get(3));

                        if(((EditText) findViewById(R.id.answer41)).getText().equals(answered)){
                            ((EditText) findViewById(R.id.answer41)).setBackgroundColor(Color.RED);
                        }else if(((EditText) findViewById(R.id.answer42)).getText().equals(answered)){
                            ((EditText) findViewById(R.id.answer42)).setBackgroundColor(Color.RED);
                        }else if(((EditText) findViewById(R.id.answer43)).getText().equals(answered)){
                            ((EditText) findViewById(R.id.answer43)).setBackgroundColor(Color.RED);
                        }else if(((EditText) findViewById(R.id.answer44)).getText().equals(answered)){
                            ((EditText) findViewById(R.id.answer44)).setBackgroundColor(Color.RED);
                        }

                        if(((EditText) findViewById(R.id.answer41)).getText().equals(correctAnswer)){
                            ((EditText) findViewById(R.id.answer41)).setBackgroundColor(Color.GREEN);
                        }else if(((EditText) findViewById(R.id.answer42)).getText().equals(correctAnswer)){
                            ((EditText) findViewById(R.id.answer42)).setBackgroundColor(Color.GREEN);
                        }else if(((EditText) findViewById(R.id.answer43)).getText().equals(correctAnswer)){
                            ((EditText) findViewById(R.id.answer43)).setBackgroundColor(Color.GREEN);
                        }else if(((EditText) findViewById(R.id.answer44)).getText().equals(correctAnswer)){
                            ((EditText) findViewById(R.id.answer44)).setBackgroundColor(Color.GREEN);
                        }
                        questionCount++;

                        if(questionCount<questionResults.getQuestionResults().size()) {

                            answers.clear();

                            answered = questionResults.getQuestionResults().get(4).getAnswered();
                            correctAnswer = questionResults.getQuestionResults().get(4).getCorrectAnswer();

                            question = questionResults.getQuestionResults().get(4).getQuestion();
                            answers.add(question.getCorrectAnswer());
                            answers.add(question.getWrongAnswer1());
                            answers.add(question.getWrongAnswer2());
                            answers.add(question.getWrongAnswer3());
                            Collections.shuffle(answers);

                            ((EditText) findViewById(R.id.question5)).setText(question.getQuestionText());
                            ((EditText) findViewById(R.id.answer51)).setText(answers.get(0));
                            ((EditText) findViewById(R.id.answer52)).setText(answers.get(1));
                            ((EditText) findViewById(R.id.answer53)).setText(answers.get(2));
                            ((EditText) findViewById(R.id.answer54)).setText(answers.get(3));

                            if(((EditText) findViewById(R.id.answer51)).getText().equals(answered)){
                                ((EditText) findViewById(R.id.answer51)).setBackgroundColor(Color.RED);
                            }else if(((EditText) findViewById(R.id.answer52)).getText().equals(answered)){
                                ((EditText) findViewById(R.id.answer52)).setBackgroundColor(Color.RED);
                            }else if(((EditText) findViewById(R.id.answer53)).getText().equals(answered)){
                                ((EditText) findViewById(R.id.answer53)).setBackgroundColor(Color.RED);
                            }else if(((EditText) findViewById(R.id.answer54)).getText().equals(answered)){
                                ((EditText) findViewById(R.id.answer54)).setBackgroundColor(Color.RED);
                            }

                            if(((EditText) findViewById(R.id.answer51)).getText().equals(correctAnswer)){
                                ((EditText) findViewById(R.id.answer51)).setBackgroundColor(Color.GREEN);
                            }else if(((EditText) findViewById(R.id.answer52)).getText().equals(correctAnswer)){
                                ((EditText) findViewById(R.id.answer52)).setBackgroundColor(Color.GREEN);
                            }else if(((EditText) findViewById(R.id.answer53)).getText().equals(correctAnswer)){
                                ((EditText) findViewById(R.id.answer53)).setBackgroundColor(Color.GREEN);
                            }else if(((EditText) findViewById(R.id.answer54)).getText().equals(correctAnswer)) {
                                ((EditText) findViewById(R.id.answer54)).setBackgroundColor(Color.GREEN);
                            }
                        }
                    }
                }
            }
        }

        int scoreFinal = 0;
        int scoreMax = 0;
        for(int i = 0; i<questionResults.getQuestionResults().size();i++){
            scoreMax += 2;
            if(questionResults.getQuestionResults().get(i).getAnswered().equals(questionResults.getQuestionResults().get(i).getCorrectAnswer()))
                scoreFinal += 2;
        }

        ((EditText) findViewById(R.id.scoreFinal)).setText(scoreFinal + "/" + scoreMax);
    }
    */
}
