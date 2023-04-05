package com.example.lona

import android.app.Dialog
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.view.Window
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.lona.adapter.PlantAdapter


class PlantPopup(private val adapter: PlantAdapter,
                 private val currentPlant: PlantModel) : Dialog(adapter.context) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.popup_plants_details)
        setupComponents()
    }
    private fun setupComponents(){
        val plantImage = findViewById<ImageView>(R.id.view_separation2)
        Glide.with(adapter.context).load(Uri.parse(currentPlant.image)).into(plantImage)
        findViewById<TextView>(R.id.name_plant).text=currentPlant.nom
        findViewById<TextView>(R.id.popup_plant_petite).text=currentPlant.description

    }
}