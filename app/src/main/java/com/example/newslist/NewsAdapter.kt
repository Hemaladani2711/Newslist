package com.example.newslist

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.newslist.repository.objects.Article
import com.squareup.picasso.Picasso


class NewsAdapter(val context: Context,val listArticles:List<Article>) : RecyclerView.Adapter<NewsAdapter.NewsListViewHolder>() {

    lateinit var clickListener: View.OnClickListener


    class NewsListViewHolder(view: View):RecyclerView.ViewHolder(view){
        var thumbnail:ImageView
        var title:TextView
        var author:TextView
        //var source:TextView
        init{
            title = view.findViewById(R.id.txtTitle)
            author = view.findViewById(R.id.txtAuthor)
            //source = view.findViewById(R.id.txtSource)
            thumbnail = view.findViewById(R.id.thumbNail)
        }

    }

    fun setOnClickListener(callback: View.OnClickListener){
        clickListener = callback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsListViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.news_item,parent,false)
        val holder = NewsListViewHolder(view)
        holder.itemView.setOnClickListener {view-> clickListener.onClick(view)}
        return holder
    }

    override fun getItemCount(): Int {
        return listArticles.size;
    }

    override fun onBindViewHolder(holder: NewsListViewHolder, position: Int) {
            val article = listArticles.get(position)
            holder.title.text = article.title
            holder.author.text = article.author
            //holder.source.text = article.source?.name
            Picasso.get().load(article.urlToImage).resize(100,100).centerCrop().into(holder.thumbnail)

    }
}