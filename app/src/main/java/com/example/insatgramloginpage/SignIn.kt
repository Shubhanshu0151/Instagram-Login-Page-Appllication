package com.example.insatgramloginpage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SignIn : AppCompatActivity() {

    private lateinit var databaseReference: DatabaseReference

//    companion object {
//        const val KEY1 = "com.example.insatgramloginpage.SignIn.name"
//        const val KEY2 = "com.example.insatgramloginpage.SignIn.mail"
//        const val KEY3 = "com.example.insatgramloginpage.SignIn.id"
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)


        val userSignIn = findViewById<TextInputEditText>(R.id.etUserSingIn)
        val btnSignIn = findViewById<Button>(R.id.btnSignIn)

        btnSignIn.setOnClickListener {
            val uniqueId = userSignIn.text.toString()
            if (uniqueId.isNotEmpty()) {
                readData(uniqueId)
            } else {
                Toast.makeText(this, "Please Enter User Id", Toast.LENGTH_SHORT).show()
            }
        }
    }


    private fun readData(uniqueId: String) {
        databaseReference = FirebaseDatabase.getInstance().getReference("SignUp")
        databaseReference.child(uniqueId).get().addOnSuccessListener {
            if (it.exists()) {
                val name = it.child("name").value
                val mail = it.child("mail").value
                val userId = it.child("uniqueId").value

                val intentHome = Intent(this, HomePage::class.java)
                Toast.makeText(this, "Welcome Home", Toast.LENGTH_SHORT).show()
                startActivity(intentHome)

            } else {
                Toast.makeText(this, "User Does not exist", Toast.LENGTH_SHORT).show()
            }
        }.addOnFailureListener {
            Toast.makeText(this, "Failed,Error In DataBase", Toast.LENGTH_SHORT).show()
        }
    }
}