package com.example.pitalicatvzandroid.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pitalicatvzandroid.R;
import com.example.pitalicatvzandroid.models.Exam;
import com.example.pitalicatvzandroid.models.Question;
import com.example.pitalicatvzandroid.remote.APIService;
import com.example.pitalicatvzandroid.remote.ApiUtils;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateExamScrollingActivity extends AppCompatActivity {

    private APIService mAPIService;
    private TextView mResponseTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_create_exam_scrolling);
        Button submitBtn = findViewById(R.id.btn_submit);
        mAPIService = ApiUtils.getAPIService();
        mResponseTv = findViewById(R.id.tv_response);

        final Bundle bundle = getIntent().getExtras();
        final String fullName = bundle.getString("fullName");
        final int userId = bundle.getInt("userId");

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tryCreateExam(userId, fullName);
            }
        });
    }

    public void tryCreateExam(final int userId, final String fullName) {

        final Exam exam = createExam(userId);

        mAPIService.postExam(exam).enqueue(new Callback<Exam>() {
            @Override
            public void onResponse(Call<Exam> call, Response<Exam> response) {
                if(response.body()==null){
                    if(response.raw().code()==404)
                        showResponse("Body = nulls");
                }
                else {
                    showToast(response.body().toString());

                    Intent intent= new Intent(getBaseContext(), MenuActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("fullName", fullName);
                    bundle.putInt("userId", userId);
                    intent.putExtras(bundle);
                    startActivity(intent);

                }
            }

            @Override
            public void onFailure(Call<Exam> call, Throwable t) {
                showToast(t.getMessage());
            }
        });
    }

    //lost when computer crashed, stayed hardcoded
    public Exam createExam(int id){
        Exam exam = new Exam();
        ArrayList<Question> questions = new ArrayList<>();

        Question question1 = new Question();
        Question question2 = new Question();
        Question question3 = new Question();
        Question question4 = new Question();
        Question question5 = new Question();

        question1.setQuestionText(((EditText) findViewById(R.id.question1)).getText().toString().trim());
        question1.setCorrectAnswer(((EditText) findViewById(R.id.answer11)).getText().toString().trim());
        question1.setWrongAnswer1(((EditText) findViewById(R.id.answer12)).getText().toString().trim());
        question1.setWrongAnswer2(((EditText) findViewById(R.id.answer13)).getText().toString().trim());
        question1.setWrongAnswer3(((EditText) findViewById(R.id.answer14)).getText().toString().trim());
        question1.setScore(2);
        question1.setVisibility(true);
        questions.add(question1);

        question2.setQuestionText(((EditText) findViewById(R.id.question2)).getText().toString().trim());
        question2.setCorrectAnswer(((EditText) findViewById(R.id.answer21)).getText().toString().trim());
        question2.setWrongAnswer1(((EditText) findViewById(R.id.answer22)).getText().toString().trim());
        question2.setWrongAnswer2(((EditText) findViewById(R.id.answer23)).getText().toString().trim());
        question2.setWrongAnswer3(((EditText) findViewById(R.id.answer24)).getText().toString().trim());
        question2.setScore(2);
        question2.setVisibility(true);
        questions.add(question2);

        question3.setQuestionText(((EditText) findViewById(R.id.question3)).getText().toString().trim());
        question3.setCorrectAnswer(((EditText) findViewById(R.id.answer31)).getText().toString().trim());
        question3.setWrongAnswer1(((EditText) findViewById(R.id.answer32)).getText().toString().trim());
        question3.setWrongAnswer2(((EditText) findViewById(R.id.answer33)).getText().toString().trim());
        question3.setWrongAnswer3(((EditText) findViewById(R.id.answer34)).getText().toString().trim());
        question3.setScore(2);
        question3.setVisibility(true);
        questions.add(question3);

        question4.setQuestionText(((EditText) findViewById(R.id.question4)).getText().toString().trim());
        question4.setCorrectAnswer(((EditText) findViewById(R.id.answer41)).getText().toString().trim());
        question4.setWrongAnswer1(((EditText) findViewById(R.id.answer42)).getText().toString().trim());
        question4.setWrongAnswer2(((EditText) findViewById(R.id.answer43)).getText().toString().trim());
        question4.setWrongAnswer3(((EditText) findViewById(R.id.answer44)).getText().toString().trim());
        question4.setScore(2);
        question4.setVisibility(true);
        questions.add(question4);

        question5.setQuestionText(((EditText) findViewById(R.id.question5)).getText().toString().trim());
        question5.setCorrectAnswer(((EditText) findViewById(R.id.answer51)).getText().toString().trim());
        question5.setWrongAnswer1(((EditText) findViewById(R.id.answer52)).getText().toString().trim());
        question5.setWrongAnswer2(((EditText) findViewById(R.id.answer53)).getText().toString().trim());
        question5.setWrongAnswer3(((EditText) findViewById(R.id.answer54)).getText().toString().trim());
        question5.setScore(2);
        question5.setVisibility(true);
        questions.add(question5);

        exam.setExamTitle(((EditText) findViewById(R.id.testName)).getText().toString().trim());

        exam.setQuestions(questions);
        exam.setCreatorId(id);

        return exam;
    }

    public void showToast(String message){
        Toast.makeText(CreateExamScrollingActivity.this, message, Toast.LENGTH_SHORT).show();
    }

    public void showResponse(String response) {
        if(mResponseTv.getVisibility() == View.GONE) {
            mResponseTv.setVisibility(View.VISIBLE);
        }
        mResponseTv.setText(response);
    }
}
