package com.learnkotline

import android.Manifest
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.text.TextUtils
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.appcompat.app.AlertDialog
import android.util.Log
import android.widget.Button
import android.widget.Toast
import android.widget.Toast.makeText
import com.learnkotline.helper.Helper
import com.learnkotline.helper.MyPreferences
import com.learnkotline.model.Person
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity
import java.util.*
import kotlin.collections.ArrayList


class MainActivity : BaseActivity(){


    //private val button: Button by bind(R.id.btn_checknullpointer) // initialize button replace findviewby id
    //private val buttonStartActivity: Button by bind(R.id.btn3)
    private val buttonSum: Button by bind(R.id.btn_sum)
    var test : String = "non null value"
    var test2 : String = "Kotlination.com = Be Kotlineer - Be Simple - Be Connective"

    //declare variables like below
    private var stringVariable : String? = null //declaring string variable
    private var integerVariable : Int? = 10 //value of this variable can be changed
    private val readOnlyVariable: Int = 1 //value of this variable can not be changed
    var sharedPreferences: MyPreferences? = null
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sharedPreferences = MyPreferences(this)
        if(!TextUtils.isEmpty(sharedPreferences!!.getValueString(MyPreferences.KEY_LOGIN))){
                if(sharedPreferences!!.getValueString(MyPreferences.KEY_LOGIN).toString().equals("yes")){
                    // just stay here
                }else{
                    startActivity(Intent(this,SplashActivity::class.java))
                    finish()
                }
        }else{
            startActivity(Intent(this,SplashActivity::class.java))
            finish()
        }

        btnActivityBottomNavigation.setOnClickListener{
            startActivity(Intent(this,BottomNavigationActivity::class.java))
            finish()
        }
        btnCheckVariableValue.text=" This is Dynamic button" // showing text in button in kotlin
        btnCheckNullSafety.setOnClickListener{// here we have use kotlin extension to find id from xml
            var a = (5 + 5 + 5 + 5 + 5 + 5 + 5
                    +7 + 4)  /*this is to test line breaking. when there is long line and you want to wrap it put bracket in line to consider entire sentence othewise
                                it will contain just first line.*/

            var valNull:  String? = null // here we have put ? after String keyword - means variable will allow null value to accept
                                         // if we have not put ? after String keyword "Null can not be a value of non-null type" compilation error occur bcoz kotline
                                        // doesn't support to accept null value by default.
            val number: Int? = null
            Log.e(" valNull "," check null pointer exception : ${valNull} : $number ") // if it has been java it will crash but here will not crash

            val stringNumber = number?.toString() ?: "Number is null" // use of Elvis operator - conditional operator like c language
            Log.e(" stringNumber "," check null pointer exception : $stringNumber ")

            // THE !! operator to forcefully throw NullPointerException in kotlin is the only way
            val numberNullpointerThrow : Int? = null
            // commented temporerly Log.e(" nullpointer throw "," check null pointer exception : ${numberNullpointerThrow!!.toString()}") // this will throw nullpointer


            Toast.makeText(this," click on nullpointer exception : "+a+" print static field "+Helper.strUrl,Toast.LENGTH_SHORT).show()
            firstFun()
        }

        btnCheckCasting.setOnClickListener{
            val number: Int = 10
            val str: String

            //val casting: String = number as String
            //Log.e(" int to string "," check casting of value : $casting") // it will crash and throw ClassCastException bcoz it's Unsafe casting
            // There is "Safe" cast operator that return null value instead of throwing an exception:

            val casting: String = number.toString()
            Log.e(" int to string "," check casting of value : ${casting}") // it will not crash becoze of use " ?  "

            val strToInt: String = "100"
            val intTest: Int = strToInt.toInt()
            Log.e(" string to int "," check casting of value : $intTest")
        }

        btnWhenCase.setOnClickListener{
            var number: Int = 11

            when (number){
                0 -> Toast.makeText(this," 0 is selected ",Toast.LENGTH_SHORT).show()
                1 -> Toast.makeText(this," 1 is selected ",Toast.LENGTH_SHORT).show()
                2 -> Toast.makeText(this," 2 is selected ",Toast.LENGTH_SHORT).show()
                3 -> Toast.makeText(this," 3 is selected ",Toast.LENGTH_SHORT).show()
                4 -> Toast.makeText(this," 4 is selected ",Toast.LENGTH_SHORT).show()
                else ->
                    Toast.makeText(this," Nothing is selected ",Toast.LENGTH_SHORT).show()

            }

            /* assign result to variable directly */
            var result = when(number) {
                0 -> "Invalid number"
                1, 2 -> "Number too low"
                3 -> "Number correct"
                4 -> "Number too high, but acceptable"
                else -> "Number too high"
            }

            var result2 = when(number) {
                0 -> "Invalid number"
                1, 2 -> "Number too low"
                3 -> "Number correct"
                in 4..10 -> "Number too high, but acceptable"
                !in 100..Int.MAX_VALUE -> "Number too high, but solvable"
                else -> "Number too high"
            }
            Toast.makeText(this,result2+"",Toast.LENGTH_SHORT).show()
        }

        btnTypeChecking.setOnClickListener{
            /* Type Checking is the way to check the type of a data at runtime. In Java, we have the instanceof keyword to check the type.
                Kotlin uses is and !is keywords to check whether a property is and is not of a certain type respectively
             */
            var myProperty = "xyz"
            var otherProperty = 1

            if(myProperty is String){
                Toast.makeText(this," Type is string ",Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this," unknown type ",Toast.LENGTH_SHORT).show()
            }


            /* create a list of Any objects and check the type of each element
                 use the when statement to check the type of each element in a for loop.
            * */
            val anyList: List<Any> = listOf("JournalDev.com", "Java", 101, 12.5f,12.5456789,false)
            for(value in anyList) {
                when (value) {
                    is String ->
                        Log.e(" string ","String: '$value'. Capitalize :${value.capitalize()}")
                    is Int ->
                        Log.e(" integer ","Integer: '$value'")
                    is Double ->
                        Log.e(" double ","Double: '$value'")
                    is Float ->
                        Log.e(" float","Float: '$value'")
                    else ->
                        Log.e(" unknown ","Unknown Type")
                }
            }



        }
        btnWithFunction.setOnClickListener{
            /*  access many properties of an object */

            with(btnWithFunction){
                text = " with() function "
            }

        }

        btnModelClas.setOnClickListener{
                val person1 : Person = Person("name", 25, "email@gmail.com", 555544448)
                val person2 : Person = Person("bhavik",30,"bhavik@b.com",9624300677)
                Log.e(" model model "," Checking model class : $person2.age")

                val personM : Person = Person()
            Log.e(" model model "," Checking model class : ${personM.returnME()}")
        }

        btnCheckArraylist.setOnClickListener{
            // Immutable list, read only
            var list = listOf(1,2,3,4)
            Log.e(" simple array "," check various arraylist : "+list)

            var anyList = listOf(10,10.5,"Bhavik",0.0000)
            Log.e(" various array "," check various arraylist : "+ anyList)

            var intList = listOf<Int>(10,20,30)
            Log.e(" integer array "," check various arraylist : "+ intList)

            // Initialize a new mutable list
            // Read and write both allowed
            var mutableList = mutableListOf<Int>(10,20,30)
            mutableList.add(60)

            Log.e(" mutable  array "," check various arraylist : "+ mutableList)

            var arrylist = arrayListOf<Int>()
            arrylist.add(10)
            arrylist.add(20)
            arrylist.add(30)
            Log.e(" arraylist  array "," check various arraylist : $arrylist ")

            var arrylistMain = ArrayList<String>()
            arrylistMain.add("om")
            arrylistMain.add("ram")
            Log.e(" arraylist  main "," check various arraylist : $arrylistMain ")

            var arralistCustom = ArrayList<Person>()

            arralistCustom.add(Person("Dharmesh",25,"dham@d.com",7600292519))
            arralistCustom.add(Person("Bhavik",30,"dham@d.com",9624300677))
            arralistCustom.add(Person("Mehul",35,"dham@d.com",7656894512))
            arralistCustom.add(Person("Roshan",26,"dham@d.com",8989898956))

            val myArray = arrayOf(4, 5, 7, 3, "Chike", false)

            Log.e(" arraylist  custom "," check various arraylist : $arralistCustom")

            // for loop example
            for (item in arralistCustom){
                Log.e(" arraylist custom "," check various arraylist : "+item.name)
            }

            for (index in 1 .. 100){
                Log.e(" for loop tag","Hello World $index")
            }

            for (i in 0 until 10 step 3) {
                if (i == 6) continue
                Log.e(" for loop continue sate"," for loop with continue statement $i")
            }

            for (i in 0 until 10 step 3) {
                if (i == 6) break
                Log.e(" for loop break sate"," for loop with break statement $i")
            }

            // for each loop
            arralistCustom.forEach(){
                Log.e(" for loop break sate"," for each loop  ${it.email}")
            }

            var array = arrayListOf<String>(" aryan ","Bhavik","ajay","Mayur")
            array.forEach {
                Log.e(" ##### "," for each for array : "+it)
            }

            (0..10).forEach(){
                Log.e(" for each "," check various arraylist : "+it)
            }


        }

        btnStartActivity.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            intent.putExtra("message","mainactivity")
            startActivity(intent)
            finish()
        }

        btnCallFragment.setOnClickListener{
            supportFragmentManager
                    // 3
                    .beginTransaction()
                    // 4
                    .add(R.id.frame_container, FirstFragment.newInstance("",""), "dogList")
                    .addToBackStack(null)
                    // 5
                    .commit()
        }

        stringVariable = btnCheckVariableValue.text as String
        btnCheckVariableValue.setOnClickListener {
            Log.e(""," getting value from button in string variable : $stringVariable , $integerVariable , $readOnlyVariable")
        }

        btnCheckNetworkConnection.setOnClickListener{ // this is to check network connection and alos compain object to access static field bcoz there is not STATIC in kotlin
            if(Helper.isNetworkAvailable(this)){
                Toast.makeText(this,"Network is available : ",Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this,"Network is not available : ",Toast.LENGTH_SHORT).show()
            }
        }
        buttonSum.setOnClickListener {
            Toast.makeText(this, "This answer is from Kotlin function -->"+giveAddition(2,3), Toast.LENGTH_LONG).show()
        }
        btnCheckPermission.setOnClickListener{
                if(checkAndRequestPermissions()){
                    Toast.makeText(this," All Permission are granted : ",Toast.LENGTH_SHORT).show()
                }else{

                }
        }

        btnHashmap.setOnClickListener{
            val hashMap :  HashMap<Int,String> = HashMap()
            hashMap.put(1,"Sachin")
            hashMap.put(2,"Sehvag")
            hashMap.put(3,"Sourav")
            hashMap.put(4,"Dravid")
            hashMap.put(5,"Dhoni")
            hashMap.put(6,"Yuvraj")

            for (key in hashMap){
                Log.e(" hashmap button "," Element at key = $key ")
            }

            val builder = StringBuilder()

            // Initialize a new empty mutable hash map
            val colors = mutableMapOf<String,String>()
            // Put some key value pairs to hash map
            colors.put("INDIANRED","#CD5C5C")
            colors.put("CRIMSON","#DC143C")
            colors.put("SALMON","#FA8072")
            colors.put("LIGHTCORAL","#F08080")

            builder.append("Loop through the mutable hash map")
            colors.forEach{key,value ->
                builder.append("\n$key,$value")
            }

            colors.remove("CRIMSON")
            builder.append("\n\n After remove an item")
            for ((key,value) in colors){
                builder.append("\n$key:$value")
            }


            // Replace/update a value
            colors.put("SALMON","NEW VALUE")
            builder.append("\n\n After updating a value")
            colors.forEach{key,value ->
                builder.append("\n$key,$value")
            }

            // Check whether hash map is empty
            builder.append("\n\nHashMap is empty? : ${colors.isEmpty()}")

            // Get value by key from hash map
            val value = colors.get("LIGHTCORAL")
            builder.append("\n\nLIGHTCORAL value $value")

            // Initialize a new hash map with key and value pairs
            val reds = mutableMapOf("RED" to "#FF0000", "FIREBRICK" to "#B22222", "CRIMSON" to "#DC143C")

            // Loop through the map
            builder.append("\n\nLoop through the new mutable hash map")
            reds.forEach{key,value->
                builder.append("\n$key : $value")
            }

            Log.e(" check hashmap "," ${builder.toString()}")


        }
        btnShowAlertDialog.setOnClickListener{
            showAlertDialog("This is Sample AlertDialog",
                    DialogInterface.OnClickListener { dialog, which ->
                        when (which) {
                            DialogInterface.BUTTON_POSITIVE -> dialog.cancel()
                            DialogInterface.BUTTON_NEGATIVE ->
                                // proceed with logic by disabling the related features or quit the app.
                                dialog.cancel()
                        }
                    })
        }
        btnLoops.setOnClickListener{
            outer@ for (n in 2..100) {
                for (d in 2 until n) {
                    if (n % d == 0) continue@outer
                }
                println("$n is prime")
            }
        }


    }
    // function with void value without parameter
    fun firstFun(){
        var strNullAccept : List<String> = test2.split("=|-".toRegex())
        Log.e(" button click "," print value of null and non null :  $test,$test2,$strNullAccept")

        val nums = arrayOf(1, 2, 3, 4, 5) // array of int
        val arrayString = arrayOf("bhavik","aryan","vijay","sidharth","ashish","hitesh")
        println(Arrays.toString(nums)+ " size : "+nums.size+ arrayString[5])
    }
    // function returning value (with parameter)
    fun giveAddition(a: Int, b: Int): Int {
        return a + b
    }
    private fun checkAndRequestPermissions(): Boolean { // return type is boolean
        val camerapermission = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
        val writepermission = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
        val permissionLocation = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
        val permissionRecordAudio = ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO)


        val listPermissionsNeeded = ArrayList<String>()

        if (camerapermission != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.CAMERA)
        }
        if (writepermission != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.WRITE_EXTERNAL_STORAGE)
        }
        if (permissionLocation != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.ACCESS_FINE_LOCATION)
        }
        if (permissionRecordAudio != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.RECORD_AUDIO)
        }
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(this, listPermissionsNeeded.toTypedArray(), REQUEST_ID_MULTIPLE_PERMISSIONS)
            return false
        }
        return true
    }
    private fun showDialogOK(message: String, okListener: DialogInterface.OnClickListener) {
        AlertDialog.Builder(this)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", okListener)
                .create()
                .show()
    }
    private fun explain(msg: String) {
        val dialog = androidx.appcompat.app.AlertDialog.Builder(this)
        dialog.setMessage(msg)
                .setPositiveButton("Yes") { paramDialogInterface, paramInt ->
                    //  permissionsclass.requestPermission(type,code);
                    startActivity(Intent(android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS, Uri.parse("package:com.example.parsaniahardik.kotlin_marshmallowpermission")))
                }
                .setNegativeButton("Cancel") { paramDialogInterface, paramInt -> finish() }
        dialog.show()
    }

    companion object {

        val REQUEST_ID_MULTIPLE_PERMISSIONS = 1
        private val SPLASH_TIME_OUT = 2000
        val TAG =" Main Activity ";
    }

    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<String>, grantResults: IntArray) {
        Log.d(TAG, "Permission callback called-------")
        when (requestCode) {
            REQUEST_ID_MULTIPLE_PERMISSIONS -> {

                val perms = HashMap<String, Int>()
                // Initialize the map with both permissions
                perms[Manifest.permission.CAMERA] = PackageManager.PERMISSION_GRANTED
                perms[Manifest.permission.WRITE_EXTERNAL_STORAGE] = PackageManager.PERMISSION_GRANTED
                perms[Manifest.permission.ACCESS_FINE_LOCATION] = PackageManager.PERMISSION_GRANTED
                perms[Manifest.permission.RECORD_AUDIO] = PackageManager.PERMISSION_GRANTED
                // Fill with actual results from user
                if (grantResults.size > 0) {
                    for (i in permissions.indices)
                        perms[permissions[i]] = grantResults[i]
                    // Check for both permissions
                    if (perms[Manifest.permission.CAMERA] == PackageManager.PERMISSION_GRANTED
                            && perms[Manifest.permission.WRITE_EXTERNAL_STORAGE] == PackageManager.PERMISSION_GRANTED
                            && perms[Manifest.permission.ACCESS_FINE_LOCATION] == PackageManager.PERMISSION_GRANTED
                            && perms[Manifest.permission.RECORD_AUDIO] == PackageManager.PERMISSION_GRANTED) {
                        Log.d(TAG, "sms & location services permission granted")

                        //else any one or both the permissions are not granted
                    } else {
                        Log.d(TAG, "Some permissions are not granted ask again ")
                        //permission is denied (this is the first time, when "never ask again" is not checked) so ask again explaining the usage of permission
                        //                        // shouldShowRequestPermissionRationale will return true
                        //show the dialog or snackbar saying its necessary and try again otherwise proceed with setup.
                        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)
                                || ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                                || ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)
                                || ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.RECORD_AUDIO)) {
                            showDialogOK("Service Permissions are required for this app",
                                    DialogInterface.OnClickListener { dialog, which ->
                                        when (which) {
                                            DialogInterface.BUTTON_POSITIVE -> checkAndRequestPermissions()
                                            DialogInterface.BUTTON_NEGATIVE ->
                                                // proceed with logic by disabling the related features or quit the app.
                                                finish()
                                        }
                                    })
                        } else {
                            explain("You need to give some mandatory permissions to continue. Do you want to go to app settings?")
                            //                            //proceed with logic by disabling the related features or quit the app.
                        }//permission is denied (and never ask again is  checked)
                        //shouldShowRequestPermissionRationale will return false
                    }
                }
            }
        }

    }

}
