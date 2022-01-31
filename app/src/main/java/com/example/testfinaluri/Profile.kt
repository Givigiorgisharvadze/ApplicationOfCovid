package com.example.testfinaluri

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class Profile: Fragment(R.layout.profile_fragment) {

    private val authentication = FirebaseAuth.getInstance()
    private val databaseUserInfo: DatabaseReference = FirebaseDatabase.getInstance().getReference("UserInfo")
    private lateinit var saxeli: TextView
    private lateinit var gvari: TextView
    private lateinit var photo: ImageView
    private lateinit var avatar: EditText
    private lateinit var btnSubmit: Button
    private lateinit var arrayListOfInfo : ArrayList<Avatar>
    val dbSavedInfo: DatabaseReference = FirebaseDatabase.getInstance().getReference("SavedInfo")
    val auth = FirebaseAuth.getInstance()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        saxeli = view.findViewById(R.id.saxeli)
        gvari = view.findViewById(R.id.gvari)
        photo = view.findViewById(R.id.photo)
        avatar = view.findViewById(R.id.avatar)
        btnSubmit = view.findViewById(R.id.btnSubmit)

        arrayListOfInfo = arrayListOf<Avatar>()

            btnSubmit.setOnClickListener {

                if (avatar.text.toString().isNotEmpty()) {

                    val userInfo = Avatar(avatar.text.toString())
                    dbSavedInfo.child(auth.currentUser?.uid!!).setValue(userInfo)

                    dbSavedInfo.child(authentication.currentUser?.uid!!).addValueEventListener(object  :
                        ValueEventListener {

                        override fun onDataChange(snapshot: DataSnapshot) {
                            val userSavedInfo = snapshot.getValue(Avatar::class.java) ?: return

                            val avatarisImage = userSavedInfo.avatarisLinki

                            Glide.with(this@Profile).load(avatarisImage).centerCrop().into(photo)

                            avatar.setText("")

                        }

                        override fun onCancelled(error: DatabaseError) {

                        }

                    })

                }

                else {

                    Toast.makeText(context,"Empty!!!", Toast.LENGTH_SHORT).show()

                }

            }

        databaseUserInfo.child(authentication.currentUser?.uid!!).addValueEventListener(object :
            ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val userinfo = snapshot.getValue(UserInfo::class.java) ?: return
                saxeli.text = userinfo.saxeli
                gvari.text = userinfo.gvari

            }

            override fun onCancelled(error: DatabaseError) {

            }

        })

    }

}