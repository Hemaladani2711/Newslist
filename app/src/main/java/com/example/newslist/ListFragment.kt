package com.example.newslist

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newslist.objects.Example
import com.example.newslist.webapi.RetrofitAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListFragment private constructor():Fragment() {

    lateinit var recyclerView: RecyclerView
    lateinit var listener: listFragmentListener



    interface listFragmentListener{
        fun clickListItem(url: String)
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        Log.d(TAG,"onAttach")
        listener = (activity) as listFragmentListener
    }
    override fun onDetach() {
        super.onDetach()
        Log.d(TAG,"onDetach")
    }


    companion object{
        val TAG = ListFragment::class.simpleName
        fun getInstance():ListFragment{
            val instance = ListFragment()
            return instance
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(TAG,"onCreateView")
        super.onCreateView(inflater, container, savedInstanceState)
        var view = inflater.inflate(R.layout.list_fragment,container,false)
        recyclerView = view.findViewById(R.id.recyclerview)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG,"onViewCreated")
        downloadData()
    }

    fun downloadData(){
        Log.d(TAG,"downloadData")
        var apiService: RetrofitAPI = RetrofitAPI.create()
        var mCallGetData: Call<Example> = apiService.getTopNewsData("us",RetrofitAPI.API_KEY)
        mCallGetData.enqueue(object : Callback<Example>{
            override fun onFailure(call: Call<Example>, t: Throwable) {
                Log.e(TAG,t.message);
            }

            override fun onResponse(call: Call<Example>, response: Response<Example>) {
                val data = response.body()
                val listArticles = data?.articles
                recyclerView.layoutManager =LinearLayoutManager(context)
                recyclerView.addItemDecoration(DividerItemDecoration(context,DividerItemDecoration.VERTICAL))
                val adapter = NewsAdapter(requireContext(), requireNotNull(listArticles))
                adapter.setOnClickListener(View.OnClickListener {it->
                    val pos = recyclerView.indexOfChild(it)
                    listener.clickListItem(requireNotNull(listArticles?.get(pos)?.url))
                })
                recyclerView.adapter = adapter
            }

        })
    }
}