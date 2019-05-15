package com.learnkotline

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.GravityCompat
import androidx.appcompat.app.ActionBarDrawerToggle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar
import com.learnkotline.helper.MyPreferences
import com.learnkotline.helper.replaceFragmenty
import com.mad.kotlin_navigation_drawer.*
import kotlinx.android.synthetic.main.activity_second.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.nav_header_main.*
import org.jetbrains.anko.share

class SecondActivity : BaseActivity(),NavigationView.OnNavigationItemSelectedListener{

    var sharedPreferences: MyPreferences? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        setSupportActionBar(toolbar)

        sharedPreferences = MyPreferences(this)
        fab.setOnClickListener { view ->
            showSnackBar("Replace with your own action")
        }

        /* getting extra from previous activity */
        val bundle: Bundle=intent.extras
        val samplename:String
        if(bundle!=null)
        {
            samplename = bundle.getString("message")
            Log.e(" get intent "," second activity : $samplename ")
        }

        val toggle = ActionBarDrawerToggle(this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)

        var headerLayout: View = nav_view.getHeaderView(0)

        var textView: TextView = headerLayout.findViewById(R.id.textviewUserName);
        textView.text= sharedPreferences!!.getValueString(MyPreferences.KEY_USERNAME)
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_camera -> {
                // Handle the camera action
                replaceFragmenty(
                        fragment = Fragment1(),
                        allowStateLoss = true,
                        containerViewId = R.id.mainContent
                )
                setTitle("Import")
            }
            R.id.nav_gallery -> {
                replaceFragmenty(
                        fragment = Fragment2(),
                        allowStateLoss = true,
                        containerViewId = R.id.mainContent
                )
                setTitle("Gallery")
            }
            R.id.nav_slideshow -> {
                replaceFragmenty(
                        fragment = Fragment3(),
                        allowStateLoss = true,
                        containerViewId = R.id.mainContent
                )
                setTitle("Slideshow")
            }
            R.id.nav_manage -> {
                replaceFragmenty(
                        fragment = Fragment4(),
                        allowStateLoss = true,
                        containerViewId = R.id.mainContent
                )
                setTitle("Tools")
            }
            R.id.nav_logout -> {
                showAlertDialog("Are you sure to logout?",
                        DialogInterface.OnClickListener { dialog, which ->
                            when (which) {
                                DialogInterface.BUTTON_POSITIVE ->{
                                    sharedPreferences?.clearSharedPreference()
                                    dialog.cancel()
                                    startActivity(Intent(this,LoginActivity::class.java))
                                    finish()
                                }
                                DialogInterface.BUTTON_NEGATIVE -> {
                                        // proceed with logic by disabling the related features or quit the app.
                                        dialog.cancel()
                                    }
                            }
                        })
            }
            R.id.nav_share -> {
                replaceFragmenty(
                        fragment = Fragment5(),
                        allowStateLoss = true,
                        containerViewId = R.id.mainContent
                )
                setTitle("Share")
            }
            R.id.nav_send -> {
                replaceFragmenty(
                        fragment = Fragment6(),
                        allowStateLoss = true,
                        containerViewId = R.id.mainContent
                )
                setTitle("Send")
            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
            val intent2 = Intent(this,MainActivity::class.java)
            startActivity(intent2)
            finish()
        }
    }
}
