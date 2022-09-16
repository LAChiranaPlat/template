package com.example.template

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.SearchView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.template.databinding.ActivityListaPokemonBinding

class ListaPokemon : AppCompatActivity(), adapterMyRecycler.OnItemClickListener {

    lateinit var layout: ActivityListaPokemonBinding
    lateinit var lstItems:ArrayList<Usuario>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        layout= ActivityListaPokemonBinding.inflate(layoutInflater)
        setContentView(layout.root)

        layout.apply {

             lstItems= ArrayList()

            lstItems.add(Usuario("Miguel","Computación","Soporte"))
            lstItems.add(Usuario("Manuel","Computación","Forense"))
            lstItems.add(Usuario("Michell","Computación","Developer"))
            lstItems.add(Usuario("Manolo","Computación","Ventas"))
            lstItems.add(Usuario("Manola","Computación","Soporte"))


            val myAdapter=adapterMyRecycler(lstItems,this@ListaPokemon)
            val struct=LinearLayoutManager(this@ListaPokemon)
            val lineaDecorativa=DividerItemDecoration(this@ListaPokemon,struct.orientation)

            lista.layoutManager=struct
            lista.addItemDecoration(lineaDecorativa)
            //lista.layoutManager=GridLayoutManager(this@ListaPokemon,2)
            lista.adapter=myAdapter

            Log.i("result","Lista: $lstItems")

            btnSave.setOnClickListener {
                lstItems.add(Usuario("Mijares","Computación","Developer"))
                myAdapter.notifyDataSetChanged()
            }

            btnDelete.setOnClickListener {
                lstItems.removeAt(3)
                myAdapter.notifyDataSetChanged()
            }

            svFiltro.setOnQueryTextListener(object :SearchView.OnQueryTextListener{
                override fun onQueryTextSubmit(p0: String?): Boolean {
                    TODO("Not yet implemented")
                }

                override fun onQueryTextChange(p0: String?): Boolean {
                    Log.i("result",p0.toString())
                    myAdapter.filtrar(p0.toString())
                    return false
                }

            })

        }
    }

    override fun myOnItemClick(position: Int) {
        Log.i("result",lstItems.get(position).toString())
    }
}