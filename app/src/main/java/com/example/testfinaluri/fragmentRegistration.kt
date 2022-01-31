package com.example.testfinaluri

import android.icu.text.IDNA
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class fragmentRegistration:Fragment(R.layout.fragment_registration) {

    private val firebaseauth = FirebaseAuth.getInstance()
    private val dataBaseUserInfo: DatabaseReference = FirebaseDatabase.getInstance().getReference("UserInfo")


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_registration, container, false)

        val etName = v.findViewById<EditText>(R.id.etName)

        val etSurName = v.findViewById<EditText>(R.id.etSurName)

        val etEmail1 = v.findViewById<EditText>(R.id.etEmail1)

        val etPass1 = v.findViewById<EditText>(R.id.etPass1)

        val tvAuth = v.findViewById<TextView>(R.id.tvAuth)

        val btnReg = v.findViewById<Button>(R.id.btnReg)

        btnReg.setOnClickListener {

            val etEmail1Text = etEmail1.text.toString().trim()

            val etPass1Text = etPass1.text.toString().trim()

            val et1Name = etName.text.toString().trim()

            val et1surName = etSurName.text.toString().trim()

            if(etName.text.isEmpty() or etSurName.text.isEmpty() or etEmail1.text.isEmpty() or etPass1.text.isEmpty()) {

                Toast.makeText(activity,"Empty!!", Toast.LENGTH_SHORT).show()

            }
            else if(etName.text.length < 2) {

                Toast.makeText(activity,"Name Less Than 2", Toast.LENGTH_SHORT).show()

            }

            else if(etSurName.text.length < 5) {

                Toast.makeText(activity,"Surname Less Than 5", Toast.LENGTH_SHORT).show()

            }

            else if(etEmail1.text.length < 8) {

                Toast.makeText(activity,"Gmail Less Than 8", Toast.LENGTH_SHORT).show()

            }

            else if(!etEmail1.text.contains("@") or !etEmail1.text.contains(".")) {

                Toast.makeText(activity,"Gmail Without @ Or.", Toast.LENGTH_SHORT).show()

            }

            else if(etPass1.text.length < 8) {

                Toast.makeText(activity,"Password Less Than 8", Toast.LENGTH_SHORT).show()

            }

            else {

                FirebaseAuth.getInstance().createUserWithEmailAndPassword(etEmail1Text,
                    etPass1Text
                ).
                addOnCompleteListener {task ->
                    if(task.isSuccessful){
                        val userInfo = Info(et1Name, et1surName)
                        dataBaseUserInfo.child(firebaseauth.currentUser?.uid!!).setValue(userInfo)
                        etName.setText("")
                        etSurName.setText("")
                        findNavController().navigate(R.id.action_fragmentRegistration_to_authorization)
                        Toast.makeText(activity,"Successful Registration", Toast.LENGTH_SHORT).show()

                    }
                    else{
                        Toast.makeText(activity, "Wrong!!!", Toast.LENGTH_SHORT).show()
                    }

                }

            }

        }

        tvAuth.setOnClickListener{

            findNavController().navigate(R.id.action_fragmentRegistration_to_authorization)

        }

        return v
    }

}