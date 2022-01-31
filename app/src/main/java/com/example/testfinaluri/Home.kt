package com.example.testfinaluri

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testfinaluri.databinding.ActivityMainBinding
import com.google.firebase.database.*

class Home : Fragment(R.layout.activity_main) {

    private lateinit var carArrayList : ArrayList<Car>

    private var dbUserInfo = FirebaseDatabase.getInstance().getReference("Users")

    private lateinit var binding : ActivityMainBinding

    private lateinit var main_recyclerview : RecyclerView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        main_recyclerview = view.findViewById(R.id.main_recyclerview)

        carArrayList = arrayListOf<Car>()

        getInfo()

    }

    private fun getInfo() {

        dbUserInfo.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()) {

                    for(i in snapshot.children) {

                        val getCar = i.getValue(Car::class.java)
                        carArrayList.add(getCar!!)

                    }

                    main_recyclerview.adapter = UserAdapter(carArrayList)
                    main_recyclerview.layoutManager = LinearLayoutManager(context)
                }
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })

    }

}