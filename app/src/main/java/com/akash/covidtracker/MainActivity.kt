package com.akash.covidtracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {
    lateinit var  worldCasesTV:TextView
    lateinit var  worldRecoveredTV:TextView
    lateinit var  worldDeathTV:TextView
    lateinit var  countryDeathTV:TextView
    lateinit var  countryCasesTV:TextView
    lateinit var  coutnryRecoverdTV: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        worldCasesTV=findViewById(R.id.idTVWorldCases)
        worldRecoveredTV=findViewById(R.id.idTVWorldRecovered)
        worldDeathTV=findViewById(R.id.idTVWorldDeaths)
        countryCasesTV=findViewById(R.id.idTVIndiaCases)
        countryCasesTV=findViewById(R.id.idTVWorldRecovered)
        countryCasesTV=findViewById(R.id.idTVWorldDeaths)


    }
}