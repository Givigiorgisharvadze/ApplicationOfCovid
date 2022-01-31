package com.example.testfinaluri

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

class fragmentPasswordRecovery: Fragment(R.layout.fragment_password_recovery) {

    private lateinit var Auth1: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_password_recovery, container, false)

        val et1NewPass = v.findViewById<EditText>(R.id.et1NewPass)

        val tvAuth2 = v.findViewById<TextView>(R.id.tvAuth2)

        val btnSave1 = v.findViewById<Button>(R.id.btnSave1)

        btnSave1.setOnClickListener {

            val et1NewPassText = et1NewPass.text.toString().trim()

            if (android.util.Patterns.EMAIL_ADDRESS.matcher(et1NewPassText).matches()) {
                Auth1.sendPasswordResetEmail(et1NewPassText).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(activity, "Check Gmail", Toast.LENGTH_SHORT).show()
                        findNavController().navigate(R.id.action_fragmentPasswordRecovery_to_authorization)

                    } else {
                        Toast.makeText(activity, "Incorrect Gmail", Toast.LENGTH_SHORT).show()

                    }

                }

            }
        }

        tvAuth2.setOnClickListener {

            findNavController().navigate(R.id.action_fragmentPasswordRecovery_to_authorization)

        }

        return v
    }

}