package com.mad.kotlin_navigation_drawer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.learnkotline.R


/**
 * A simple [Fragment] subclass.
 */
class Fragment6 : androidx.fragment.app.Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view: View  =  inflater.inflate(R.layout.fragment_fragment6, container, false)
        return view
    }

}// Required empty public constructor
