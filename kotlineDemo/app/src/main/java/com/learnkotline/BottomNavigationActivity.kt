package com.learnkotline

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.learnkotline.fragment.BottomMapFragment
import com.learnkotline.fragment.BottomRestApiFragment
import com.learnkotline.fragment.BottomFragment3
import kotlinx.android.synthetic.main.activity_bottom_navigation.*

class BottomNavigationActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bottom_navigation)
        //val bottomNavigation: BottomNavigationView = findViewById(R.id.bottom_navigation)

        val songsFragment = BottomMapFragment.newInstance()
        openFragment(songsFragment)
        bottom_navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }
    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_songs -> {
                val songsFragment = BottomMapFragment.newInstance()
                openFragment(songsFragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_albums -> {
                val songsFragment = BottomRestApiFragment.newInstance()
                openFragment(songsFragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_artists -> {
                val songsFragment = BottomFragment3.newInstance()
                openFragment(songsFragment)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }
    private fun openFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    override fun onBackPressed() {
        super.onBackPressed()

        if(bottom_navigation.selectedItemId == R.id.navigation_songs) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }else{
            bottom_navigation.selectedItemId = R.id.navigation_songs;
        }

    }
}
