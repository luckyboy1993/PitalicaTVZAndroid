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

public class UsersTakenActivity extends AppCompatActivity {

    private APIService mAPIService;
    private ArrayList<String> uExamNames = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taken);


        final ListView uExamList;
        final Bundle bundle = getIntent().getExtras();
        final UserList userList = (UserList) bundle.getSerializable("userList");
        final int examId = bundle.getInt("examId");
        mAPIService = ApiUtils.getAPIService();

        for (User user : userList.getUserList()) {
            uExamNames.add(user.getFullName());
        }

        final ArrayAdapter<String> mArrayAdapter;
        mArrayAdapter = new ArrayAdapter<>(this, R.layout.simple_list_item, uExamNames);
        uExamList = (ListView) findViewById(R.id.bExams);
        uExamList.setAdapter(mArrayAdapter);

        uExamList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                int userId = userList.getUserList().get(i).getId();
                tryGetExamResult(examId, userId);
            }
        });
    }

    public void tryGetExamResult(int examId, int userId) {

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
    }

    public void showToast(String message){
        Toast.makeText(UsersTakenActivity.this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResume() {
        super.onResume();

    }

}
