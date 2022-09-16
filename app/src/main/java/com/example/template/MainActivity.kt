package com.example.template

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.preference.PreferenceManager
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide


import com.example.template.databinding.ActivityMainBinding
import org.json.JSONArray
import org.json.JSONObject

class MainActivity : templateMenu() {

    lateinit var layout: ActivityMainBinding
    lateinit var colaReq:RequestQueue

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        layout= ActivityMainBinding.inflate(layoutInflater)
        setContentView(layout.root)

        colaReq=Volley.newRequestQueue(this)



        layout.btnRequest.setOnClickListener{

            /*
            * STRING= STRINGREQUEST     "lISTO, OK, MENSAJE"
            * 1 OBJ JSON= JSONREQUEST
            * * OBJ JSON= ARRAYREQUEST
            * */

            var url="https://restcountries.com/v3.1/name/"+layout.tilNamePais.editText?.text.toString()

           /* val request= StringRequest(
                Request.Method.GET,
                url,
                Response.Listener {
                    Log.i("result",it.toString())
                },
                Response.ErrorListener {
                    Log.i("result",it.message.toString())
                }
                )*/
            var obj:JSONObject
            var array:JSONArray

            val request=JsonArrayRequest(
                url,
                Response.Listener {

                    for (indice in 0 until it.length()){

                        //NOMBRE DEL PAIS
                        obj=JSONObject(it.get(indice).toString())
                        var objNombrePais=JSONObject(obj.getString("name").toString())


                        layout.txtNameOficial.text=objNombrePais.getString("official")

                        //MONEDA DEL PAIS
                        var objMonedaPais=JSONObject(obj.getString("currencies").toString())
                        var keysMoneda=objMonedaPais.keys()

                        for (indice in keysMoneda)
                        {
                            var objMon=JSONObject(objMonedaPais.getString(indice).toString())
                            layout.txtMonedas.text=objMon.getString("symbol") +" "+objMon.getString("name")
                        }

                        //CAPITAL DEL PAIS
                        var capital=JSONArray(obj.getString("capital"))
                        layout.txtCapital.text= capital.get(0).toString()

                        //IDIOMAS DEL PAIS
                        var objIdiomas=JSONObject(obj.getString("languages").toString())
                        var keysIdiomas=objIdiomas.keys()

                        layout.txtIdiomas.text=""
                        for (indice in keysIdiomas)
                        {
                            //var objLang=objIdiomas.getString(indice).toString())
                            layout.txtIdiomas.append(objIdiomas.getString(indice).toString()+", ")
                        }

                        //BANDERA DEL PAIS
                        var bandera=JSONObject(obj.getString("flags").toString())
                        Glide
                            .with(this)
                            .load(bandera.getString("png"))
                            .placeholder(R.drawable.wait)
                            .into(layout.imgBandera)

                        var limites=JSONArray(obj.getString("borders"))
                        for (item in 0 until limites.length()){
                            layout.txtLimites.append(limites[item].toString()+", ")
                        }

                        layout.txtRegion.text=obj.getString("region")
                        layout.txtSubRegion.text=obj.getString("subregion")

                    }

                },
                Response.ErrorListener {
                    Log.i("result",it.message.toString())
                }
            )

            /*val request=JsonObjectRequest(
                Request.Method.GET,
                url,null,
                Response.Listener{ json->
                    Log.i("result",json.getString("name").toString())
                },
                Response.ErrorListener {
                    Log.e("result",it.message.toString())
                }

            )*/


            colaReq.add(request)

        }

      /*
      var manager=PreferenceManager.getDefaultSharedPreferences(this@MainActivity)

      layout.apply {

            txtTag.text=manager.getString("str","Hello World!")

            btnChange.setOnClickListener {


                var editor=manager.edit()

                editor.putString("str","Nuevo Valor")
                editor.apply()

                txtTag.text="Nuevo Valor"
            }

            btnDelete.setOnClickListener {
                val editor=manager.edit()
                editor.remove("str")
                editor.apply()
            }
*/
        }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        val inflador=menuInflater
        inflador.inflate(R.menu.main,menu)
        super.onCreateContextMenu(menu, v, menuInfo)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId){
            R.id.inicio->{
                launchAct(MainActivity::class.java,"123")
            }
            R.id.configuracion->{
                launchAct(Configuracion::class.java)
            }
            R.id.salir->{
                launchAct(Creditos::class.java)
            }
            else->super.onContextItemSelected(item)
        }

    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        return currentItem(menu,R.id.inicio)
    }

}