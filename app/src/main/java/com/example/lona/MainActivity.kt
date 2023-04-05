package com.example.lona

import android.content.pm.PackageManager
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.lona.adapter.PlantAdapter
import com.example.lona.fragments.HomeFragment
import com.example.lona.fragments.LocationFragment
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.tasks.Task
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var userArraList: ArrayList<PlantModel>
    private lateinit var myAdapter: PlantAdapter

    lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadFragment(HomeFragment(this ))

        // importer la botttomnavigationview
        val navigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        navigationView.setOnNavigationItemSelectedListener{
            when(it.itemId)
            {
                R.id.home ->{
                    loadFragment(HomeFragment(this))
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.location ->{
                    loadFragment(LocationFragment(this))
                    return@setOnNavigationItemSelectedListener true
                }
                else -> false
            }
        }
        loadFragment(HomeFragment(this))

        fusedLocationProviderClient= LocationServices.getFusedLocationProviderClient(this)
        fetchLocation()
    }

    private fun loadFragment(fragment: Fragment) {
        val repo=PlantRepository()
        //mettre a jour la liste de plantes
        repo.updateDate{
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container, fragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }
    }

    private fun fetchLocation(){
        val task: Task<Location> = fusedLocationProviderClient.lastLocation

        if (ActivityCompat.checkSelfPermission(this,android.Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION)!= PackageManager.PERMISSION_GRANTED
        ){
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),101)
            return
        }
        task.addOnSuccessListener {
            if(it != null){
                Toast.makeText(applicationContext,"${it.latitude}${it.longitude}", Toast.LENGTH_SHORT).show()
            }

        }
    }
}