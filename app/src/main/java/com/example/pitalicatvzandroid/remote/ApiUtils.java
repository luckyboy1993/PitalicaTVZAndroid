package com.example.pitalicatvzandroid.remote;

public class ApiUtils {

    private ApiUtils() {}

    public static final String BASE_URL = "http://88.207.10.164:80/";//localhost compa

    public static APIService getAPIService() {

        return RetrofitClient.getClient(BASE_URL).create(APIService.class);
    }
}