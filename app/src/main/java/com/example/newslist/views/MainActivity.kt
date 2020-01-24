package com.example.newslist.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentManager.POP_BACK_STACK_INCLUSIVE
import com.example.newslist.R

class MainActivity : AppCompatActivity(),
    ListFragment.listFragmentListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Loading list fragment by default
        val fragment = ListFragment.getInstance()

        supportFragmentManager.beginTransaction().add(
            R.id.container_layout,fragment,
            ListFragment.TAG
        ).addToBackStack(null).commit()
    }


    /**
     * Once user clicks item in the list.  Transition
     * to detail fragment
     * */
    override fun clickListItem(url: String) {
        val detailFragment = DetailViewFragment.getInstance(url)
        supportFragmentManager.beginTransaction().setCustomAnimations(R.animator.slide,0).replace(
            R.id.container_layout,detailFragment,
            DetailViewFragment.TAG).addToBackStack(null).commit()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        /**
         * If user is in detail view, will take user back to list screen.
         * if User is in List screen.  will close the activity.
         * */

        if (supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStackImmediate(ListFragment.TAG, POP_BACK_STACK_INCLUSIVE)
        } else {
            finish()
        }
    }
}
