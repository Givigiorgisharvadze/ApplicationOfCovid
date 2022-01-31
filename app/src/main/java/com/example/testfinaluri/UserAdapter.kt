package com.example.testfinaluri

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.auth.User

class UserAdapter(private val users: List<Car>) :
    RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    override fun getItemCount(): Int {
        return users.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.clicked_item, parent, false)
        )

    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {

        val user = users[position]
        holder.setData(user)
        holder.userName.text = user.name
    }

    inner class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val userName: TextView = itemView.findViewById(R.id.title)
        val userImage: ImageView = itemView.findViewById(R.id.image)
        val userButton : Button = itemView.findViewById(R.id.button)

        fun setData(user:Car) {

            Glide.with(itemView.context).load(user.url).centerCrop().into(userImage)

            userButton.setOnClickListener {

                val db : DatabaseReference = FirebaseDatabase.getInstance().getReference("Save")

                val name = user.name

                var id = user.id

                val secondUrl = user.secondUrl

                var bodyType = user.bodyType
                
                var year = user.year
                
                var mileage = user.mileage
                
                var engineType = user.engineType
                
                var engineVol = user.engineVol

                var model = user.model

                val drive = user.drive

                val url = user.url

                val color = user.color

                val save = CarSave(color,id,name,model,year,mileage,engineType,engineVol,bodyType,url,secondUrl,drive)

                db.child(FirebaseAuth.getInstance().currentUser?.uid!!).child(id.toString()).setValue(save)

            }

            itemView.setOnClickListener {

                val imageViewUrl = user.url

                val secondUrl = user.secondUrl

                val nameView = user.name

                val model = user.model

                val year = user.year

                val mileage = user.mileage

                val bodyType = user.bodyType

                val engineType = user.engineType

                val engineVol = user.engineVol

                val drive = user.drive

                val exteriorColor = user.color

                val navController = Navigation.findNavController(itemView)

                val action = HomeDirections.actionHomeToSecondFragment(imageViewUrl,nameView,model,year,mileage,bodyType,engineType,engineVol,exteriorColor,secondUrl,drive)
                navController.navigate(action)

            }

        }

    }

}