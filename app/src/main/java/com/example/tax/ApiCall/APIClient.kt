package com.example.tax.ApiCall


import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import okhttp3.OkHttpClient




class APIClient {
   // var BaseUrl = "http://mytxapi.techedgeinfo.com/"          //Testing
    var BaseUrl = "http://sh017.hostgator.tempwebhost.net/~easytxi6/"          //Testing

    fun getClient(): Retrofit{
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
// OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        OkHttpClient.Builder()
            .connectTimeout(5, TimeUnit.MINUTES)
            .writeTimeout(5, TimeUnit.MINUTES)
            .readTimeout(5, TimeUnit.MINUTES)
            .addInterceptor(interceptor)
            .build()


        val retrofit = Retrofit.Builder()
            .baseUrl(BaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit
    }
}