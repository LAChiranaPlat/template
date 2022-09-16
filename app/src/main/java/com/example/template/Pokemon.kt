package com.example.template

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.template.databinding.ActivityPokemonBinding
import org.json.JSONArray
import org.json.JSONObject

class Pokemon : AppCompatActivity() {

    lateinit var layout:ActivityPokemonBinding
    lateinit var colaPet:RequestQueue

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        layout= ActivityPokemonBinding.inflate(layoutInflater)
        colaPet=Volley.newRequestQueue(this)

        setContentView(layout.root)

        var url="https://pokeapi.co/api/v2/pokemon/"

        val request=JsonObjectRequest(
            Request.Method.GET,
            url,null,
            Response.Listener{
                val arrayPokemon=JSONArray(it.getString("results").toString())
                for(index in 0 until arrayPokemon.length()){

                    var objPokemon=JSONObject(arrayPokemon.get(index).toString())
                    var name=objPokemon.getString("name")
                    var nUrl=url+name

                    val reqPok=JsonObjectRequest(
                        Request.Method.GET,
                        nUrl,null,
                        Response.Listener {
                            //ESPECIE
                            val array=JSONArray(it.getString("types"))
                            val obj=JSONObject(array.get(0).toString())
                            val obj2=JSONObject(obj.getString("type").toString())
                            Log.i("result","$name: ${obj2.getString("name")}")
                            Log.i("result","Ataques:")
                            //ATAQUES
                            val movimientos=JSONArray(it.getString("moves"))
                            var listAtaques=""
                            for (indice in 0 until movimientos.length())
                            {
                                val objAtac=JSONObject(movimientos.get(indice).toString())
                                val ataques=JSONObject(objAtac.getString("move"))
                                listAtaques+=ataques.getString("name")+", "

                            }
                            Log.i("result",listAtaques)
                            Log.i("result","========================================================================================")

                        },
                        Response.ErrorListener {  }
                    )

                    colaPet.add(reqPok)


                }
            },
            Response.ErrorListener {
                Log.i("result",it.message.toString())
            }
        )

        colaPet.add(request)

    }
}