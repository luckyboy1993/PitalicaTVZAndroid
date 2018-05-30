package com.example.pitalicatvzandroid.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.pitalicatvzandroid.R;
import com.example.pitalicatvzandroid.models.Exam;
import com.example.pitalicatvzandroid.models.ExamList;
import com.example.pitalicatvzandroid.remote.APIService;
import com.example.pitalicatvzandroid.remote.ApiUtils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MenuActivity extends AppCompatActivity {

    private APIService mAPIService;

    @Override
    protected void onCreate (@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Button taken = (Button) findViewById(R.id.btn_taken);
        Button notTaken = (Button) findViewById(R.id.btn_notTaken);
        Button created = (Button) findViewById(R.id.btn_created);
        Button createExam = (Button) findViewById(R.id.btn_createExam);

        final Bundle bundle = getIntent().getExtras();
        final String fullName = bundle.getString("fullName");
        final int userId = bundle.getInt("userId");

        mAPIService = ApiUtils.getAPIService();

        taken.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getExamsByUserId(userId, "taken");
            }
        });

        notTaken.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getExamsByUserId(userId, "notTaken");
            }
        });

        created.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getExamsByUserId(userId, "created");
            }
        });

        createExam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), CreateExamScrollingActivity.class);
                bundle.putString("fullName", fullName);
                bundle.putInt("userId", userId);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    public void getExamsByUserId(final int userId, String type) {
        Call<List<Exam>> call = null;
        Class tempClass = null;

        switch(type){
            case"taken":
                tempClass = TakenActivity.class;
                call = mAPIService.getExamsTakenByUserId(userId);
                break;
            case"notTaken":
                tempClass = NotTakenActivity.class;
                call = mAPIService.getExamsNotTakenByUserId(userId);
                break;
            case"created":
                tempClass = CreatedActivity.class;
                call = mAPIService.getExamsCreatedByUserId(userId);
                break;
        }

        final Class finalTempClass = tempClass;

        call.enqueue(new Callback<List<Exam>>() {
            @Override
            public void onResponse(Call<List<Exam>> call, Response<List<Exam>> response) {
                try {
                    ExamList exams = new ExamList();
                    exams.setExams(response.body());

                    if (exams != null) {
                        Intent intent = new Intent(getBaseContext(), finalTempClass);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("exams", exams);
                        bundle.putInt("userId", userId);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }

                } catch (Exception e) {
                    Log.d("onResponse", "There is an error");
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<List<Exam>> call, Throwable t) {
                showToast(t.getMessage());
            }
        });
    }

    public void showToast(String message){
        Toast.makeText(MenuActivity.this, message, Toast.LENGTH_SHORT).show();
    }
}