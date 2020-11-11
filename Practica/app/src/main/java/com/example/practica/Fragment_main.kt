package com.example.practica

import ApiInterface.Interface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.core.view.get
import com.example.practica.model.Weather
import kotlinx.android.synthetic.main.fragment_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

    var Citys = arrayOf("Moscow", "London", "Paris", "Volgograd", "Samara", "Voronezh")

class Fragment_main : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val arrayAdapter = activity?.let { ArrayAdapter (it, android.R.layout.simple_spinner_item, Citys) }

        cities.adapter = arrayAdapter

        cities.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                fragcity.text = (view as TextView).text
            }
        }
        sendNetworkRequest()
    }

    companion object {

        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Fragment_main().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
    fun  sendNetworkRequest() {
        val builder = Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/data/2.5/")
            .addConverterFactory(GsonConverterFactory.create())
        val retrofit = builder.build()
        val apiInterface: Interface = retrofit.create<Interface>(Interface::class.java)
        val call: retrofit2.Call<Weather> = apiInterface.getFile(/*"London"*/)
        call.enqueue(object : Callback<Weather> {
            override fun onFailure(call: retrofit2.Call<Weather>, t: Throwable) {
                Log.i("LOL", t.message.toString())
            }

            override fun onResponse(call: Call<Weather>, response: Response<Weather>) {
                val statusResponse = response.body()!!
                var i = 0;
                var bol = true
                while (bol) {
                    try {
                        i++
                        activity?.runOnUiThread {
                            temp.text = statusResponse?.main?.temp.toString()
                            Log.i("LOL",statusResponse?.main?.temp.toString())
                            bol = false
                        }
                    } catch (e: Exception) {
                        Log.i("LOL", e.message.toString())
                        bol = false
                    }
                }
            }
        })
    }
}