package com.example.lona

import com.example.lona.PlantRepository.Singleton.databaseRef
import com.example.lona.PlantRepository.Singleton.plantList
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import javax.security.auth.callback.Callback

class PlantRepository {
    object Singleton {
        //se connecter a la reference plants
        val databaseRef = FirebaseDatabase.getInstance().getReference("plants")

        //creer une liste qui va contenir nos plantes
        val plantList= arrayListOf<PlantModel>()
    }

    fun updateDate(callback: ()-> Unit){
        //absorber les donnees depuis la de base de donnees
        databaseRef.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                //retirer les anciennes
                plantList.clear()
                //recolter la liste
                for (ds in snapshot.children) {
                    //construire un objet plant
                    val plant=ds.getValue(PlantModel::class.java)

                    //verifier que la plante n'est pas null
                    if (plant!=null){
                        // ajouter la plante a notre liste
                        plantList.add(plant)
                    }
                }
                //actionner le callaback
                callback()
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
    }

}