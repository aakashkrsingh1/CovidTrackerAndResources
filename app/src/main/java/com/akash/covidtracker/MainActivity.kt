package com.akash.covidtracker

import android.app.DownloadManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {
    lateinit var  worldCasesTV:TextView
    lateinit var  worldRecoveredTV:TextView
    lateinit var  worldDeathTV:TextView
    lateinit var  countryDeathsTV:TextView
    lateinit var  countryCasesTV:TextView
    lateinit var  countryRecoveredTV: TextView
    lateinit var stateRV: RecyclerView
    lateinit var  stateRVAdapter: StateRVAdapter
    lateinit var stateList: List<StateModal>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        worldCasesTV=findViewById(R.id.idTVWorldCases)
        worldRecoveredTV=findViewById(R.id.idTVWorldRecovered)
        worldDeathTV=findViewById(R.id.idTVWorldDeaths)
        countryCasesTV=findViewById(R.id.idTVIndiaCases)
        countryRecoveredTV=findViewById(R.id.idTVIndiaRecovered)
        countryDeathsTV=findViewById(R.id.idTVIndiaDeaths)
        stateRV=findViewById(R.id.idRVStates)
        stateList=ArrayList<StateModal>()
        getStateInfo()
        getWorldInfo()




    }
    private fun getStateInfo()
    {
        val url ="https://api.covid19india.org/data.json"
        val queue= Volley.newRequestQueue(this@MainActivity)
        val request=
        JsonObjectRequest(Request.Method.GET,url,null,
                {response->
                    try {
                        //val dataObj=response.getJSONObject("statewise")
                        //val summaryObj=response.getJSONObject("summary")
//                        val cases:Int=summaryObj.getInt("total")
//                        val recovered:Int=summaryObj.getInt("discharged")
//                        val deaths:Int=summaryObj.getInt("deaths")
//                        countryCasesTV.text=cases.toString()
//                        countryRecoveredTV.text=recovered.toString()
//                        countryDeathsTV.text=deaths.toString()
                        val regionalArray=response.getJSONArray("statewise")
                        val dataObj=regionalArray.getJSONObject(0)
                        val cases:Int=dataObj.getInt("confirmed")
                        val recovered:Int=dataObj.getInt("recovered")
                        val deaths:Int=dataObj.getInt("deaths")
                        countryCasesTV.text=cases.toString()
                        countryRecoveredTV.text=recovered.toString()
                        countryDeathsTV.text=deaths.toString()

                        for(i in 1 until regionalArray.length())
                        {
                            val regionalObj=regionalArray.getJSONObject(i)
                            val stateName:String=regionalObj.getString("state")
                            val cases:Int=regionalObj.getInt("confirmed")
                            val deaths:Int=regionalObj.getInt("deaths")
                            val recovered:Int=regionalObj.getInt("recovered")
                            val stateModal=StateModal(stateName,recovered,deaths,cases)
                            stateList=stateList+stateModal
                        }
                        stateRVAdapter= StateRVAdapter(stateList)
                        stateRV.layoutManager=LinearLayoutManager(this)
                        stateRV.adapter=stateRVAdapter


                    }
                    catch (e:JSONException)
                    { e.printStackTrace()}
                }
                ,{ error->
                    Toast.makeText(this,"Failed to get data",Toast.LENGTH_SHORT).show()
        })
        queue.add(request)
    }
    private fun getWorldInfo()
    {
        val url="https://corona.lmao.ninja/v3/covid-19/all"
        val queue=Volley.newRequestQueue(this@MainActivity)
        val request=
            JsonObjectRequest(Request.Method.GET,url,null,{response->
                try{val worldCases:Int=response.getInt("cases")
                val worldRecovered:Int=response.getInt("recovered")
                val worldDeaths:Int=response.getInt("deaths")
                worldRecoveredTV.text=worldRecovered.toString()
                worldCasesTV.text=worldCases.toString()
                worldDeathTV.text=worldDeaths.toString()}
                catch (e:JSONException)
                {e.printStackTrace()}

            }
                    ,{error->
                Toast.makeText(this,"Failed to Load World Data, Check World Data API",Toast.LENGTH_SHORT).show()

            })
        queue.add(request)
    }
}