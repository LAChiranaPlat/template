package com.example.template

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.template.databinding.TemplateItemPokemonBinding

class adapterPokemon(var itemsPokemon:ArrayList<Pokemones>):RecyclerView.Adapter<adapterPokemon.ViewHolder>() {

    lateinit var ct:Context

    class ViewHolder(v:TemplateItemPokemonBinding):RecyclerView.ViewHolder(v.root) {

        val nombre:TextView=v.txtNombre
        val especie=v.txtEspecie
        val ataques=v.txtAtaques

        val fPokemon=v.imgPokemon
        val icon=v.imgMore

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): adapterPokemon.ViewHolder {

        ct=parent.context

        val layout=TemplateItemPokemonBinding.inflate(LayoutInflater.from(ct),parent,false)
        return ViewHolder(layout)


    }

    override fun onBindViewHolder(holder: adapterPokemon.ViewHolder, position: Int) {

        var itemActual=itemsPokemon.get(position)

        holder.apply {
            nombre.text=itemActual.nombres
            especie.text=itemActual.especie
            ataques.text=itemActual.ataque

            Glide
                .with(ct)
                .load(itemActual.avatar)
                .placeholder(R.drawable.wait)
                .into(fPokemon)
        }

    }

    override fun getItemCount()=itemsPokemon.size

}