package com.example.template

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem

class Creditos : templateMenu() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_creditos)
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        return currentItem(menu,R.id.salir)
    }

}