package com.example.template

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.template.databinding.TemplateItemBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import org.w3c.dom.Text

class adapterMyRecycler(private var items:ArrayList<Usuario>,private var listener: OnItemClickListener):RecyclerView.Adapter<adapterMyRecycler.ContentViews>() {

    private var listaOriginal:ArrayList<Usuario> = ArrayList()

    init {
        listaOriginal.addAll(items)
    }

    inner class ContentViews(view: TemplateItemBinding):RecyclerView.ViewHolder(view.root), View.OnClickListener {

        val titulo:TextView
        val carrera:TextView
        val esp:TextView

        val btn:Button

        init{
            titulo=view.txtHeader
            carrera=view.txtCarrera
            esp=view.txtEsp

            btn=view.btnGo
            titulo.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
           listener.myOnItemClick(adapterPosition)
        }


    }

    interface OnItemClickListener {

        fun myOnItemClick(position: Int)

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): adapterMyRecycler.ContentViews {
        val layout=TemplateItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ContentViews(layout)
    }

    override fun onBindViewHolder(holder: adapterMyRecycler.ContentViews, position: Int) {

        var itemActual=items.get(position)

        holder.apply {
            titulo.text=itemActual.nombres
            carrera.text=itemActual.carrera
            esp.text=itemActual.especialidad

            btn.setOnClickListener {
               /* MaterialAlertDialogBuilder(it.context)
                    .setTitle("Confirmacion de Accion")
                    .setPositiveButton("Si, Eliminar"){x,y->
                        items.removeAt(position)
                        notifyDataSetChanged()
                        Toast.makeText(it.context,"Item Eliminado",Toast.LENGTH_LONG).show()
                    }
                    .setNegativeButton("No"){x,y->

                    }
                    .show()*/

            val contexto=it.context

            contexto.startActivity(
                Intent(contexto,Creditos::class.java)
            )


            }

        }
    }

    override fun getItemCount()=items.size

    fun filtrar(str:String){

        if(str.isEmpty())
        {
            items.clear()
            items.addAll(listaOriginal)
        }
        else{
            var nList:ArrayList<Usuario> = ArrayList()

            for(item in items)
            {
                if( item.nombres.lowercase().contains(str.lowercase())) {
                    nList.add(item)
                }
            }

            items.clear()
            items.addAll(nList)
        }

        notifyDataSetChanged()

    }

}