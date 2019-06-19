package com.learnkotline.fragment

import `in`.eyehunt.retrofitandroidexamplekotlin.UsersList
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.learnkotline.R
import com.learnkotline.retrofit.APIInterface
import com.learnkotline.retrofit.ApiClient
import com.learnkotline.retrofit.ApiClient.Factory.retrofit
import kotlinx.android.synthetic.main.fragment_bottom_fragment2.*
import kotlinx.android.synthetic.main.fragment_bottom_fragment2.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [BottomRestApiFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [BottomRestApiFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class BottomRestApiFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var listener: OnFragmentInteractionListener? = null

    val BASE_URL = "https://api.github.com/search/"

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
        var view: View = inflater.inflate(R.layout.fragment_bottom_fragment2, container, false)

        view.progressBar.visibility = View.VISIBLE
        getUsers()
        return view
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
        fun newInstance(): BottomRestApiFragment = BottomRestApiFragment()
    }
    fun getUsers(){

        var retrofit: Retrofit? = ApiClient.getClient()


        var api = retrofit?.create(APIInterface::class.java)
        var call = api?.getUser()

        call?.enqueue(object: Callback<UsersList>{
            override fun onResponse(call: Call<UsersList>, response: Response<UsersList>) {
                progressBar.visibility = View.GONE
                var arraylistUser = response.body()
                Log.e(" success "," retrofit response : ${arraylistUser?.users}")
            }

            override fun onFailure(call: Call<UsersList>, t: Throwable) {
                progressBar.visibility = View.GONE
                Log.e(" faill "," retrofit response : ")
            }

        })
    }
}
