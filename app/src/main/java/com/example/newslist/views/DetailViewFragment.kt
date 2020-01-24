package com.example.newslist.views

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import androidx.fragment.app.Fragment
import com.example.newslist.R

class DetailViewFragment private constructor():Fragment() {

    lateinit var webView: WebView
    companion object{
        val TAG = DetailViewFragment::class.simpleName
        val URL = "url";
        fun getInstance(url:String): DetailViewFragment {
            val instance = DetailViewFragment()
            val bundle = Bundle()
            bundle.putString(URL,url)
            instance.arguments = bundle
            return instance
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        Log.d(TAG,"onAttach")
    }

    override fun onDetach() {
        super.onDetach()
        Log.d(TAG,"onDetach")

    }

    override fun onResume() {
        super.onResume()
        webView.loadUrl(arguments?.getString(URL))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        var view = inflater.inflate(R.layout.detail_view_fragment,container,false)
        webView = view.findViewById(R.id.webview)
        var webSettings = webView.settings
        webSettings.javaScriptEnabled = true
        webSettings.domStorageEnabled = true
        webView.loadUrl(arguments?.getString(URL))
        return view
    }

}