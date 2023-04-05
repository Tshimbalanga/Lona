package com.example.lona.adapter

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.lona.MainActivity
import com.example.lona.PlantModel
import com.example.lona.PlantPopup
import com.example.lona.R

class PlantAdapter(
    val context: MainActivity,
    private val plantList: List<PlantModel>,
    private val layoutId: Int) : RecyclerView.Adapter<PlantAdapter.ViewHolder>(){

    //boîte pour ranger tous les composants à controler
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val plantImage:ImageView=view.findViewById(R.id.image_item)
        val plantName: TextView =view.findViewById(R.id.name_item)
        val plantDescription: TextView =view.findViewById(R.id.description_item)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater
            .from(parent.context).
            inflate(layoutId, parent, false)
        return ViewHolder(view)
    }
    // Interaction lors du clic sur une plante
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //val currentPlant = plantList[position]
        val currentPlant = plantList[position]
        //Recuperation des images
        Glide.with(context).load(Uri.parse(currentPlant.image)).into(holder.plantImage)
        //recuperation du nom et description
        holder.plantName.text=currentPlant.nom
        holder.plantDescription.text=currentPlant.description
        holder.itemView.setOnClickListener{
            //Afficher la popup
            PlantPopup(this,currentPlant).show()
        }

    }

    override fun getItemCount(): Int = plantList.size
}