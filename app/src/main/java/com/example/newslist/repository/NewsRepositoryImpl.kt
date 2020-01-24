package com.example.newslist.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.newslist.repository.objects.Article
import com.example.newslist.repository.objects.Example
import com.example.newslist.network.NewsAPI
import com.example.newslist.views.ListFragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsRepositoryImpl:NewsRepository {

    override fun downloadData():LiveData<List<Article>>{
        val data = MutableLiveData<List<Article>>()
        Log.d(ListFragment.TAG,"downloadData")
        var apiService: NewsAPI = NewsAPI.create()
        var mCallGetData: Call<Example> = apiService.getTopNewsData("us", NewsAPI.API_KEY)
        mCallGetData.enqueue(object : Callback<Example> {
            override fun onFailure(call: Call<Example>, t: Throwable) {
                Log.e(ListFragment.TAG,t.message);
            }

            override fun onResponse(call: Call<Example>, response: Response<Example>) {
                val body = response.body()
                 data.value= body?.articles
            }

        })
        return data
    }
}