package com.example.newslist.views

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newslist.NewsAdapter
import com.example.newslist.R
import com.example.newslist.viewmodel.ListViewModel

class ListFragment private constructor():Fragment() {

    lateinit var recyclerView: RecyclerView
    lateinit var listener: listFragmentListener
    lateinit var viewModel: ListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ListViewModel::class.java)
    }

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
        fun getInstance(): ListFragment {
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

    /**This function will request topnewsdata and observe the results*/

    fun downloadData(){
       viewModel.downloadNewsData().observe( this, Observer { listArticles ->

           recyclerView.layoutManager =LinearLayoutManager(context)

           //sets divider for recyclerviews
           recyclerView.addItemDecoration(DividerItemDecoration(context,DividerItemDecoration.VERTICAL))

           val adapter = NewsAdapter(
               requireContext(),
               requireNotNull(listArticles)
           )

           /**
            * Once User clicks an item. It will pass the URL to the activity to
            * launch details fragment
            * */
           adapter.setOnClickListener(View.OnClickListener {it->
               val pos = recyclerView.indexOfChild(it)
               listener.clickListItem(requireNotNull(listArticles?.get(pos)?.url))
           })
           recyclerView.adapter = adapter

       })
    }

}