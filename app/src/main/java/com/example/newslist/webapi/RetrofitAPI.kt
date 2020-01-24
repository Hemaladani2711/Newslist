package com.example.newslist.webapi

import com.example.newslist.objects.Example
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

interface RetrofitAPI {
    companion object {
        val API_KEY="257177c0aecc4ca4910388be82c92233"
        val BASE_URL: String = "https://newsapi.org/v2/"
        var longerTimeoutClient = OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .build()
        var gson = GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .create()

        var retrofitApiInstance = Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(longerTimeoutClient)
            .build()

        fun create(): RetrofitAPI {
            return retrofitApiInstance.create(RetrofitAPI::class.java)
        }
    }
    @GET("top-headlines")
    fun getTopNewsData(@Query("country")country:String?,@Query("apiKey")apiKey:String?): Call<Example>
}