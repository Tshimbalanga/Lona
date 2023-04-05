package com.example.lona.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.lona.MainActivity
import com.example.lona.PlantRepository.Singleton.plantList
import com.example.lona.R
import com.example.lona.adapter.PlanItemDecoration
import com.example.lona.adapter.PlantAdapter

class HomeFragment(private val context: MainActivity) : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater?.inflate(R.layout.fragment_home, container, false)

        //Recuperer le recycleView
        val verticalRecyclerView = view?.findViewById<RecyclerView>(R.id.vertical_recycler_view)
        if (verticalRecyclerView != null) {
            verticalRecyclerView.adapter = PlantAdapter(context, plantList,R.layout.item_vertical_plant)
            verticalRecyclerView.addItemDecoration(PlanItemDecoration())
        }

        return view
    }
}