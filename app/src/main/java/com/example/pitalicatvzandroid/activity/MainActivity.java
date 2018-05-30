package com.example.pitalicatvzandroid.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pitalicatvzandroid.R;
import com.example.pitalicatvzandroid.models.User;
import com.example.pitalicatvzandroid.remote.APIService;
import com.example.pitalicatvzandroid.remote.ApiUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private TextView mResponseTv;
    private APIService mAPIService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText titleEt = findViewById(R.id.et_title);
        final EditText bodyEt = findViewById(R.id.et_body);
        Button submitBtn = findViewById(R.id.btn_submit);
        Button createBtn = findViewById(R.id.btn_create);
        mResponseTv = findViewById(R.id.tv_response);

        mAPIService = ApiUtils.getAPIService();

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fullName = titleEt.getText().toString().trim();
                String password = bodyEt.getText().toString().trim();

                if (!TextUtils.isEmpty(fullName) && !TextUtils.isEmpty(password)) {
                    tryGetUser(fullName, password);
                }

            }
        });

        createBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fullName = titleEt.getText().toString().trim();
                String password = bodyEt.getText().toString().trim();

                if (!TextUtils.isEmpty(fullName) && !TextUtils.isEmpty(password)) {
                    tryCreateUser(fullName, password);
                }

            }
        });
    }

    public void tryGetUser(String fullName, String password) {

        User user=new User();
        user.setFullName(fullName);
        user.setPassword(password);

        mAPIService.tryGetUser(user).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.body()==null){
                    if(response.raw().code()==404)
                        showResponse("UserNotFound");
                }
                else {
                    showToast(response.body().toString());

                    Intent intent= new Intent(getBaseContext(), MenuActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("fullName", response.body().getFullName());
                    bundle.putInt("userId", response.body().getId());
                    intent.putExtras(bundle);
                    startActivity(intent);

                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                showResponse(t.getMessage());
            }
        });
    }

    public void tryCreateUser(String fullName, String password) {

        User user=new User();
        user.setFullName(fullName);
        user.setPassword(password);

        mAPIService.tryCreateUser(user).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.body()==null){
                    if(response.raw().code()==404)
                        showResponse("UserNotFound");
                }
                else {
                    showToast(response.body().toString());
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                showResponse(t.getMessage());
            }
        });
    }

    public void showResponse(String response) {
        if(mResponseTv.getVisibility() == View.GONE) {
            mResponseTv.setVisibility(View.VISIBLE);
        }
        mResponseTv.setText(response);
    }

    public void showToast(String message){
        Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
    }
}
