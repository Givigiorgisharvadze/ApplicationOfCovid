package com.example.testfinaluri

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth

class fragmentChangePassword: Fragment(R.layout.fragment_change_password) {

    private lateinit var passwordChange: EditText

    private lateinit var btnChange: Button

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        passwordChange = view.findViewById(R.id.passwordChange)
        btnChange = view.findViewById(R.id.btnChange)

        btnChange.setOnClickListener {

            val newPassword = passwordChange.text.toString()

            if(newPassword.isEmpty() || newPassword.length < 8) {

                Toast.makeText(context, "Incorrect Pass", Toast.LENGTH_SHORT).show()

            }

            else {

                FirebaseAuth.getInstance().currentUser?.updatePassword(newPassword)?.addOnCompleteListener { task ->

                    if(task.isSuccessful) {

                        Toast.makeText(context,"Successfully change", Toast.LENGTH_SHORT).show()
                        passwordChange.setText("")

                    }

                    else {

                        Toast.makeText(context,"Unsuccessfully change", Toast.LENGTH_SHORT).show()
                        passwordChange.setText("")

                    }

                }

            }

        }

    }

}