package com.example.pitalicatvzandroid.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.pitalicatvzandroid.R;
import com.example.pitalicatvzandroid.models.Exam;
import com.example.pitalicatvzandroid.models.ExamList;
import com.example.pitalicatvzandroid.models.QuestionResult;
import com.example.pitalicatvzandroid.models.QuestionResultList;
import com.example.pitalicatvzandroid.models.User;
import com.example.pitalicatvzandroid.models.UserList;
import com.example.pitalicatvzandroid.remote.APIService;
import com.example.pitalicatvzandroid.remote.ApiUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreatedActivity extends AppCompatActivity {

    private APIService mAPIService;
    private ArrayList<String> uExamNames = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taken);


        final ListView uExamList;
        final Bundle bundle = getIntent().getExtras();
        final ExamList exams = (ExamList) bundle.getSerializable("exams");
        mAPIService = ApiUtils.getAPIService();

        for (Exam exam : exams.getExams()) {
            uExamNames.add(exam.getExamTitle());
        }

        final ArrayAdapter<String> mArrayAdapter;
        mArrayAdapter = new ArrayAdapter<>(this, R.layout.simple_list_item, uExamNames);
        uExamList = (ListView) findViewById(R.id.bExams);
        uExamList.setAdapter(mArrayAdapter);

        uExamList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                int examId = exams.getExams().get(i).getId();
                tryGetExamStudents(examId);
            }
        });
    }

    public void tryGetExamStudents(final int examId) {

        mAPIService.tryGetExamStudents(examId).enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {

                try {
                    UserList userList = new UserList();
                    userList.setUserList(response.body());

                    if (userList != null) {
                        Intent intent = new Intent(getBaseContext(), UsersTakenActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("userList", userList);
                        bundle.putInt("examId", examId);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }

                } catch (Exception e) {
                    Log.d("onResponse", "There is an error");
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                showToast(t.getMessage());
            }
        });
    }

    public void showToast(String message){
        Toast.makeText(CreatedActivity.this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResume() {
        super.onResume();

    }
}
