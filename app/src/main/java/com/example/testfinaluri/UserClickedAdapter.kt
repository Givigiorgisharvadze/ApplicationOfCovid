package com.example.testfinaluri

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class UserClickedAdapter(private val users: List<CarSave>) :

    RecyclerView.Adapter<UserClickedAdapter.UserClickedViewHolder>() {

    override fun getItemCount(): Int {
        return users.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserClickedViewHolder {
        return UserClickedViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_clicked, parent, false)

        )

    }

    override fun onBindViewHolder(holder: UserClickedViewHolder, position: Int) {

        val user = users[position]
        holder.userClickedName.text = user.name
        holder.setData(user)

    }

    inner class UserClickedViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val userClickedName: TextView = itemView.findViewById(R.id.title)
        val userClickedImage: ImageView = itemView.findViewById(R.id.image)
        val userClickedButton : Button = itemView.findViewById(R.id.remove)

        fun setData(user: CarSave) {

            Glide.with(itemView.context).load(user.url).centerCrop().into(userClickedImage)

            userClickedButton.setOnClickListener {

                val id = user.id

                val dbSavedInfo: DatabaseReference =
                    FirebaseDatabase.getInstance().getReference("Save")
                val auth = FirebaseAuth.getInstance()
                dbSavedInfo.child(auth.currentUser?.uid!!).child(id.toString()).removeValue()

            }

            itemView.setOnClickListener {

                val imageViewUrl = user.url

                val secondImageViewUrl = user.secondUrl

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

                val action = SaveDirections.actionSaveToSecondFragment(imageViewUrl,nameView,model,year,mileage,bodyType,engineType,engineVol,exteriorColor,secondImageViewUrl,drive)
                navController.navigate(action)

            }

        }

    }

}