package com.android.hootr.navigationfragments.test1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.android.hootr.navigationfragments.R

class MainTestActivity : AppCompatActivity() {

    var currentFragment: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_test)

        var fragment = FragmentUtil.getFragmentByTagName(supportFragmentManager, AFragment::class.java.simpleName)
        if(fragment == null) {
            fragment = AFragment()
        }
        supportFragmentManager.beginTransaction()
            .replace(R.id.container_f, AFragment(),AFragment::class.java.simpleName)
            .addToBackStack(null)
            .commit()

//        if (savedInstanceState == null || supportFragmentManager.backStackEntryCount == 0) {
//            supportFragmentManager.beginTransaction()
//                .replace(R.id.container_f, AFragment())
//                .addToBackStack(null)
//                .commit()
//        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("currentFragment", currentFragment)
    }

//    override fun onBackPressed() {
//
//        if (supportFragmentManager.backStackEntryCount > 0) {
//            supportFragmentManager.popBackStackImmediate()
//        } else {
//            super.onBackPressed()
//        }
//    }
}
