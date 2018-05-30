package com.example.pitalicatvzandroid.remote;

import com.example.pitalicatvzandroid.models.Exam;
import com.example.pitalicatvzandroid.models.QuestionResult;
import com.example.pitalicatvzandroid.models.QuestionResultList;
import com.example.pitalicatvzandroid.models.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface APIService {

    @POST("/api/Users/TryGetUser")
    Call<User> tryGetUser(@Body User user);

    @POST("/api/Users")
    Call<User> tryCreateUser(@Body User user);

    @POST("/api/Exams/{id}")
    Call<Exam> tryGetExam(@Path("id") int id);

    @POST("/api/Exams/GetExamStudents/{id}")
    Call<List<User>> tryGetExamStudents(@Path("id") int id);

    @POST("/api/Exams/insertExam")
    Call<Exam> postExam(@Body Exam exam);

    @POST("/api/Exams/insertExamResults")
    Call<QuestionResultList> postExamResults(@Body QuestionResultList examResults);

    @POST("/api/Exams/GetExamResults/{examId}/{userId}")
    Call<List<QuestionResult>> tryGetExamResults(@Path("examId") int examId, @Path("userId") int userId);

    @POST("/api/Exams/GetExamsTaken/{id}")
    Call<List<Exam>> getExamsTakenByUserId(@Path("id") int id);

    @POST("/api/Exams/GetExamsNotTaken/{id}")
    Call<List<Exam>> getExamsNotTakenByUserId(@Path("id") int id);

    @POST("/api/Exams/GetExamsCreated/{id}")
    Call<List<Exam>> getExamsCreatedByUserId(@Path("id") int id);


}