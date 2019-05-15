package com.learnkotline.adapter

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.learnkotline.R
import com.learnkotline.model.MovieDataClass
import kotlinx.android.synthetic.main.item_list.view.*

class MovieAdapter(private val moviesList: List<MovieDataClass>,val clickListener: (MovieDataClass) -> Unit ) : RecyclerView.Adapter<MovieAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(part: MovieDataClass, clickListener: (MovieDataClass) -> Unit) {
            itemView.textview_title.text = part.title
            itemView.textview_year.text = part.year
            itemView.setOnClickListener { clickListener(part)}
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)

        return MyViewHolder(itemView)
    }
    /*override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        // Populate ViewHolder with data that corresponds to the position in the list
        // which we are told to load

    }*/
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        (holder as MyViewHolder).bind(moviesList[position], clickListener)

    }

    override fun getItemCount(): Int {
        return moviesList.size
    }




}