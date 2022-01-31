package com.example.testfinaluri

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testfinaluri.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class Save : Fragment(R.layout.activity_main2) {

    private lateinit var carArray : ArrayList<CarSave>

    private var dbUserInfo = FirebaseDatabase.getInstance().getReference("Save")

    private lateinit var recyclerview : RecyclerView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerview = view.findViewById(R.id.recyclerview)

        carArray = arrayListOf<CarSave>()

        getInfo()

    }

    private fun getInfo() {

        dbUserInfo.child(FirebaseAuth.getInstance().currentUser?.uid!!).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()) {

                    for(i in snapshot.children) {

                        val getCar = i.getValue(CarSave::class.java)
                        carArray.add(getCar!!)

                    }

                    recyclerview.adapter = UserClickedAdapter(carArray)
                    recyclerview.layoutManager = LinearLayoutManager(context)
                }
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })

    }

}