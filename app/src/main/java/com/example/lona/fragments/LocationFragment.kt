package com.example.lona.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lona.MainActivity
import com.example.lona.PlantRepository.Singleton.plantList
import com.example.lona.R
import com.example.lona.adapter.PlanItemDecoration
import com.example.lona.adapter.PlantAdapter

class LocationFragment (
    private val context: MainActivity
        ) : Fragment(){
    override fun onCreateView(inflater: LayoutInflater,container: ViewGroup?,savedInstanceState: Bundle?
    ): View? {
        val view =inflater?.inflate(R.layout.fragment_location,container,false)
        val collectionRecyclerView= view!!.findViewById<RecyclerView>(R.id.collection_Location)
        collectionRecyclerView.adapter=PlantAdapter(context, plantList,R.layout.item_vertical_plant)
        collectionRecyclerView.layoutManager=LinearLayoutManager(context)
        collectionRecyclerView.addItemDecoration(PlanItemDecoration())
        return view
    }
}