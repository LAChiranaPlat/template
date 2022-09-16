package com.example.template

import android.app.Activity
import android.content.Intent
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity

open class templateMenu:AppCompatActivity() {

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        val inflador=menuInflater
        inflador.inflate(R.menu.main,menu)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

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
            else->super.onOptionsItemSelected(item)
        }

    }

    fun currentItem(menu: Menu?,idItem:Int):Boolean{
        var item=menu?.findItem(idItem)
        item?.isVisible=false
        return true
    }

    fun launchAct(_class:Class<*>,input:String=""):Boolean{
        startActivity(Intent(this,_class).apply {
            putExtra("data1",input)
        })
        return true
    }

}