package com.example.practica

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_main.*

const val URL = "api.openweathermap.org/data/2.5/weather?q="
const val APIKEY = "bc06456dbcab3bbd3e95b93b559691eb"

var Citys = arrayOf("Москва", "Самара", "Вологда", "Волгоград", "Саратов", "Воронеж")

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        NavigationUI.setupWithNavController(bottomNavigationView, navController)

        val arrayAdapter = ArrayAdapter (this, android.R.layout.simple_spinner_item, Citys)

        cities.adapter = arrayAdapter

        cities.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                asd.text = cities[position].toString()
            }
        }
    }
}