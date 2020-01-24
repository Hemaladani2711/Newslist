package com.example.newslist.repository

import androidx.lifecycle.LiveData
import com.example.newslist.repository.objects.Article

interface NewsRepository {

    fun downloadData():LiveData<List<Article>>
}