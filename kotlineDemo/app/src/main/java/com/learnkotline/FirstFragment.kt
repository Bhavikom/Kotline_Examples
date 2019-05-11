package com.learnkotline

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.learnkotline.adapter.MovieAdapter
import com.learnkotline.model.MovieDataClass
import kotlinx.android.synthetic.main.fragment_blank.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [BlankFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [BlankFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class FirstFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var listener: OnFragmentInteractionListener? = null

    private var recyclerView: RecyclerView? = null
    var arraylistMoview: ArrayList<MovieDataClass> = ArrayList();
    private var adapter: MovieAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view: View  =  inflater.inflate(R.layout.fragment_blank, container, false)
        recyclerView = view.findViewById(R.id.recyclerview_movie) as RecyclerView
        adapter = MovieAdapter(arraylistMoview, { partItem : MovieDataClass -> partItemClicked(partItem) })
        val layoutManager = LinearLayoutManager(activity?.applicationContext)
        recyclerView!!.layoutManager = layoutManager
        recyclerView!!.itemAnimator = DefaultItemAnimator()
        recyclerView!!.adapter = adapter

        addMovie()

        return view
    }

    private fun partItemClicked(partItem: MovieDataClass) {
            Toast.makeText(activity," clicked on : "+partItem.title,Toast.LENGTH_SHORT).show();
    }

    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        /*if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }*/
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments]
     * (http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment BlankFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                FirstFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }
    fun addMovie(){
        arraylistMoview.add(MovieDataClass("Kal ho na ho","2004"))
        arraylistMoview.add(MovieDataClass("Veer Zaara","2004"))
        arraylistMoview.add(MovieDataClass("Swades","2004"))
        arraylistMoview.add(MovieDataClass("Main hoo na","2003"))
        arraylistMoview.add(MovieDataClass("Kabhi khushi kabhi gum","2001"))
        arraylistMoview.add(MovieDataClass("Kaho na pyar hai","2004"))
        arraylistMoview.add(MovieDataClass("Mujse Shadi karogi","2004"))
        arraylistMoview.add(MovieDataClass("Dosti","2004"))
        arraylistMoview.add(MovieDataClass("Bewafa","2005"))
        arraylistMoview.add(MovieDataClass("Sholey","1975"))
        arraylistMoview.add(MovieDataClass("Deewar","1980"))
        arraylistMoview.add(MovieDataClass("Student of the year","2013"))

        adapter?.notifyDataSetChanged()

    }

}
