package com.example.tax.ApiCall


import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


class APIClient {
   // var BaseUrl = "http://mytxapi.techedgeinfo.com/"          //Testing
    var BaseUrl = "http://sh017.hostgator.tempwebhost.net/~easytxi6/"          //Testing
    var newBaseUrl = "http://itrplus.com/itr/"

    fun getClient(): Retrofit{
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val logger = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        val okHttp = OkHttpClient.Builder().addInterceptor(logger).build()


//        OkHttpClient.Builder()
//            .connectTimeout(5, TimeUnit.MINUTES)
//            .writeTimeout(5, TimeUnit.MINUTES)
//            .readTimeout(5, TimeUnit.MINUTES)
//            .addInterceptor(logger)
//            .build()
        val gson = GsonBuilder()
            .setLenient()
            .create()

        val retrofit = Retrofit.Builder()
//            .baseUrl(BaseUrl)
            .baseUrl(newBaseUrl)
            .client(okHttp)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        return retrofit
    }
}