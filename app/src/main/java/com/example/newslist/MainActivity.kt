package com.example.newslist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager.POP_BACK_STACK_INCLUSIVE

class MainActivity : AppCompatActivity(),ListFragment.listFragmentListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragment = ListFragment.getInstance()
        supportFragmentManager.beginTransaction().add(R.id.container_layout,fragment,ListFragment.TAG).addToBackStack(null).commit()
    }

    override fun clickListItem(url: String) {
        val detailFragment = DetailViewFragment.getInstance(url)
        supportFragmentManager.beginTransaction().setCustomAnimations(R.animator.slide,0).replace(R.id.container_layout,detailFragment,DetailViewFragment.TAG).addToBackStack(null).commit()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        if (supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStackImmediate(ListFragment.TAG, POP_BACK_STACK_INCLUSIVE)
        } else {
            finish()
        }
    }
}
