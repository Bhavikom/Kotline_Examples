package com.learnkotline.fragment

import android.annotation.SuppressLint
import android.content.Context
import android.content.DialogInterface
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions


import com.learnkotline.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [BottomMapFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [BottomMapFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class BottomMapFragment : Fragment(), OnMapReadyCallback {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var listener: OnFragmentInteractionListener? = null

    var mapFragment : SupportMapFragment?=null
    lateinit var googleMap: GoogleMap
    val LOCATION_PERMISSION_CODE: Int = 100

    @SuppressLint("MissingPermission")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

        /*mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment? // working


        //mapFragment = supportFragmentManager.chifindFragmentById(R.id.map)
        //mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment?.getMapAsync(OnMapReadyCallback {
            googleMap = it
            googleMap.isMyLocationEnabled = true
            val location1 = LatLng(13.03,77.60)
            googleMap.addMarker(MarkerOptions().position(location1).title("My Location"))
            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(location1,5f))

            val location2 = LatLng(9.89,78.11)
            googleMap.addMarker(MarkerOptions().position(location2).title("Madurai"))


            val location3 = LatLng(13.00,77.00)
            googleMap.addMarker(MarkerOptions().position(location3).title("Bangalore"))

        })*/
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        var view: View =  inflater.inflate(R.layout.fragment_bottom_fragment1, container, false)

        mapFragment = childFragmentManager?.findFragmentById(R.id.map) as SupportMapFragment?


        if(checkLocationPermission()){
            Toast.makeText(activity," Location Permission are granted : ", Toast.LENGTH_SHORT).show()
            mapFragment?.getMapAsync(this)
        }
        return view;
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
        fun newInstance(): BottomMapFragment = BottomMapFragment()
    }
    @SuppressLint("MissingPermission")
    override fun onMapReady(it: GoogleMap?) {
        googleMap = it!!
        googleMap.isMyLocationEnabled = true
        val location1 = LatLng(13.03,77.60)
        googleMap.addMarker(MarkerOptions().position(location1).title("My Location"))
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(location1,5f))

        val location2 = LatLng(9.89,78.11)
        googleMap.addMarker(MarkerOptions().position(location2).title("Madurai"))


        val location3 = LatLng(13.00,77.00)
        googleMap.addMarker(MarkerOptions().position(location3).title("Bangalore"))
    }
    private fun checkLocationPermission(): Boolean{
        val accessFineLocation = ContextCompat.checkSelfPermission(this!!.context!!,android.Manifest.permission.ACCESS_FINE_LOCATION)
        val accessCoarsLocation = ContextCompat.checkSelfPermission(this!!.context!!,android.Manifest.permission.ACCESS_COARSE_LOCATION)

        val listedPermission = ArrayList<String>()
        if(accessCoarsLocation != PackageManager.PERMISSION_GRANTED){
            listedPermission.add(android.Manifest.permission.ACCESS_COARSE_LOCATION)
        }
        if(accessFineLocation != PackageManager.PERMISSION_GRANTED){
            listedPermission.add(android.Manifest.permission.ACCESS_FINE_LOCATION)
        }

        if(!listedPermission.isEmpty()){
            requestPermissions(listedPermission.toTypedArray(),LOCATION_PERMISSION_CODE)
            return false

        }
        return true
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when(requestCode){
            LOCATION_PERMISSION_CODE -> {
                val perms = HashMap<String,Int>()

                perms[android.Manifest.permission.ACCESS_COARSE_LOCATION] = PackageManager.PERMISSION_GRANTED
                perms[android.Manifest.permission.ACCESS_FINE_LOCATION] = PackageManager.PERMISSION_GRANTED

                if(grantResults.size > 0){
                    for (i in grantResults.indices) {
                        perms[permissions[i]] = grantResults[i]
                    }
                    if(perms[android.Manifest.permission.ACCESS_FINE_LOCATION] == PackageManager.PERMISSION_GRANTED &&
                            perms[android.Manifest.permission.ACCESS_COARSE_LOCATION] == PackageManager.PERMISSION_GRANTED){
                            // all location permission is granted
                            mapFragment?.getMapAsync(this)
                    }else{
                        if(shouldShowRequestPermissionRationale(android.Manifest.permission.ACCESS_COARSE_LOCATION) ||
                                shouldShowRequestPermissionRationale(android.Manifest.permission.ACCESS_FINE_LOCATION)){
                            showDialogOK("Requested Permissions are required for this app",
                                    DialogInterface.OnClickListener { dialog, which ->
                                        when (which) {
                                            DialogInterface.BUTTON_POSITIVE -> checkLocationPermission()
                                            DialogInterface.BUTTON_NEGATIVE ->
                                                // proceed with logic by disabling the related features or quit the app.
                                                dialog.cancel()
                                        }
                                    })
                        }
                    }
                }
            }

        }
    }
    private fun showDialogOK(message: String, okListener: DialogInterface.OnClickListener) {
        context?.let {
            AlertDialog.Builder(it)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", okListener)
                .create()
                .show()
        }
    }

}
