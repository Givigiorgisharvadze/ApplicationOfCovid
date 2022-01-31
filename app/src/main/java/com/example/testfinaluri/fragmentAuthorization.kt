package com.example.testfinaluri

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
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

class fragmentAuthorization : Fragment(R.layout.activity_main3) {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.activity_main3, container, false)

        val etEmail = v.findViewById<EditText>(R.id.etEmail)

        val etPass = v.findViewById<EditText>(R.id.etPass)

        val tvReg = v.findViewById<TextView>(R.id.tvReg)

        val tvPassRec = v.findViewById<TextView>(R.id.tvPassRec)

        val btnAuth = v.findViewById<Button>(R.id.btnAuth)

        btnAuth.setOnClickListener{

            val etEmailText = etEmail.text.toString().trim()

            val etPasswordText = etPass.text.toString().trim()

            if(etEmail.text.isEmpty() or etPass.text.isEmpty()) {

                Toast.makeText(activity,"Empty!!", Toast.LENGTH_SHORT).show()

            }

            else if(etEmail.text.length < 8) {

                Toast.makeText(activity,"Gmail Less Than 8", Toast.LENGTH_SHORT).show()

            }

            else if(!etEmail.text.contains("@") or !etEmail.text.contains(".")) {

                Toast.makeText(activity,"Gmail Without @ Or .", Toast.LENGTH_SHORT).show()

            }

            else if(etPass.text.length < 8) {

                Toast.makeText(activity,"Password Less than 8", Toast.LENGTH_SHORT).show()

            }

            else {

                FirebaseAuth.getInstance().signInWithEmailAndPassword(etEmailText,etPasswordText).addOnCompleteListener{
                        task ->
                    if (task.isSuccessful){
                        etPass.text.clear()
                        etEmail.text.clear()
                        Toast.makeText(activity,"Successful Authentication", Toast.LENGTH_SHORT).show()
                        Handler().postDelayed({

                            findNavController().navigate(R.id.action_authorization_to_home)

                        }, 2000)

                    } else {

                        Toast.makeText(activity, "Registration Is Required", Toast.LENGTH_SHORT).show()

                    }


                }

            }

        }

        tvReg.setOnClickListener{

            findNavController().navigate(R.id.action_authorization_to_fragmentRegistration)

        }

        tvPassRec.setOnClickListener{

            findNavController().navigate(R.id.action_authorization_to_fragmentPasswordRecovery)

        }

        return v
    }

}