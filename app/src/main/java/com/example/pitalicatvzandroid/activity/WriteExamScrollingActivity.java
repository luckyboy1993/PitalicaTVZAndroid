package com.example.pitalicatvzandroid.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pitalicatvzandroid.R;
import com.example.pitalicatvzandroid.models.Exam;
import com.example.pitalicatvzandroid.models.Question;
import com.example.pitalicatvzandroid.models.QuestionResult;
import com.example.pitalicatvzandroid.models.QuestionResultList;
import com.example.pitalicatvzandroid.remote.APIService;
import com.example.pitalicatvzandroid.remote.ApiUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WriteExamScrollingActivity extends AppCompatActivity {

    private APIService mAPIService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_exam_scrolling);

        Button submitBtn = findViewById(R.id.btn_submit);
        final Bundle bundle = getIntent().getExtras();
        final int userId = bundle.getInt("userId");
        final Exam exam = (Exam) bundle.getSerializable("exam");
        mAPIService = ApiUtils.getAPIService();

        final List<Question> questions = setText(exam);

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tryPostExamResults(questions, userId);
            }
        });

    }

    public void tryPostExamResults(List<Question> questions, int userId){
        List<QuestionResult> examResults = createExamResults(questions, userId);
        tryPostExamResults(userId, examResults);

    }

    public void tryPostExamResults(final int userId, final List<QuestionResult> examResults) {

        final QuestionResultList questionResults = new QuestionResultList();
        questionResults.setQuestionResults(examResults);
        final int examId = questionResults.getQuestionResults().get(0).getExamId();

        mAPIService.postExamResults(questionResults).enqueue(new Callback<QuestionResultList>() {
            @Override
            public void onResponse(Call<QuestionResultList> call, Response<QuestionResultList> response) {

                if(response.body()==null){
                    if(response.raw().code()==404)
                        showToast("Body = nulls");
                }
                else {
                    try {
                        mAPIService.tryGetExamResults(examId, userId).enqueue(new Callback<List<QuestionResult>>() {
                            @Override
                            public void onResponse(Call<List<QuestionResult>> call, Response<List<QuestionResult>> response) {

                                try {
                                    QuestionResultList questionResults = new QuestionResultList();
                                    questionResults.setQuestionResults(response.body());

                                    if (questionResults != null) {
                                        Intent intent = new Intent(getBaseContext(), ExamResultsScrollingActivity.class);
                                        Bundle bundle = new Bundle();
                                        bundle.putSerializable("questionResults", questionResults);
                                        intent.putExtras(bundle);
                                        startActivity(intent);
                                    }

                                } catch (Exception e) {
                                    Log.d("onResponse", "There is an error");
                                    e.printStackTrace();
                                }
                            }

                            @Override
                            public void onFailure(Call<List<QuestionResult>> call, Throwable t) {
                                showToast(t.getMessage());
                            }
                        });
                    } catch (Exception e) {
                        Log.d("onResponse", "There is an error");
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<QuestionResultList> call, Throwable t) {
                showToast(t.getMessage());
            }
        });
    }

    public ArrayList<QuestionResult> createExamResults(List<Question> questions, int userId){
        ArrayList<QuestionResult> examResults = new ArrayList<>();
        View radioGroup;
        View holder;

        LinearLayout layout = (LinearLayout)findViewById(R.id.testLayout);

        int m=0;
        for (int i = 0; m < questions.size(); i++) {

            View v = layout.getChildAt(i);
            if (v instanceof LinearLayout) {
                radioGroup = ((LinearLayout) v).getChildAt(1);
                for(int j=0; j<4;j++) {
                    holder = ((RadioGroup) radioGroup).getChildAt(j);
                    if(((RadioButton) holder).isChecked()){
                        examResults.add(new QuestionResult());
                        examResults.get(m).setAnswered(((RadioButton) holder).getText().toString());
                        examResults.get(m).setExamId(questions.get(m).getExamId());
                        examResults.get(m).setCorrectAnswer(questions.get(m).getCorrectAnswer());
                        examResults.get(m).setQuestionId(questions.get(m).getId());
                        examResults.get(m).setScore(questions.get(m).getScore());
                        examResults.get(m).setUserId(userId);
                    }
                }
                m++;
            }
        }

        return  examResults;
    }

    public List<Question> setText(Exam exam){

            List<Question> questions = exam.getQuestions();
            Question question = new Question();
            List<String> answers = new ArrayList<String>();

            View holder;
            View radioGroup;

            //max questions is 5 or less
            int maxQuestions = questions.size()>5 ? 5 : questions.size();

            LinearLayout layout = (LinearLayout)findViewById(R.id.testLayout);

            //setting name for test
            ((TextView) findViewById(R.id.testName)).setText(exam.getExamTitle());

            //shuffling questions
            Collections.shuffle(questions);

            int m = 0;
            //setting text and answers for each question
            for (int i = 0; m < maxQuestions; i++) {
                question = questions.get(m);

                View v = layout.getChildAt(i);
                if (v instanceof LinearLayout) {
                    //shuffling answers
                    answers.add(question.getCorrectAnswer());
                    answers.add(question.getWrongAnswer1());
                    answers.add(question.getWrongAnswer2());
                    answers.add(question.getWrongAnswer3());
                    Collections.shuffle(answers);

                    //setting question text
                    holder = ((LinearLayout) v).getChildAt(0);
                    ((TextView) holder).setText(question.getQuestionText());

                    radioGroup = ((LinearLayout) v).getChildAt(1);

                    //setting answers
                    for(int j=0; j<answers.size();j++) {
                        holder = ((RadioGroup) radioGroup).getChildAt(j);
                        ((RadioButton) holder).setText(answers.get(j));
                    }

                    holder = ((LinearLayout) v).getChildAt(2);
                    ((TextView) holder).setText(question.getScore()+"");

                    m++;
                    answers.clear();
                }
            }
        return questions;
    }

    public void showToast(String message){
        Toast.makeText(WriteExamScrollingActivity.this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResume() {
        super.onResume();

    }
}
