package com.example.newslist.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.newslist.repository.NewsRepository
import com.example.newslist.repository.NewsRepositoryImpl
import com.example.newslist.repository.objects.Article

class ListViewModel(private val repository: NewsRepository = NewsRepositoryImpl()):ViewModel() {

    fun downloadNewsData(): LiveData<List<Article>> {
        return repository.downloadData()
    }

}