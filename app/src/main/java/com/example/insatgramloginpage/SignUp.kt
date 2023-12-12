package com.example.insatgramloginpage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.insatgramloginpage.databinding.ActivitySignUpBinding
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SignUp : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding

    lateinit var database : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val fullName = findViewById<TextInputEditText>(R.id.etFullName)
        val userMail = findViewById<TextInputEditText>(R.id.etEmail)
        val userId = findViewById<TextInputEditText>(R.id.etName)
        val userPassword = findViewById<TextInputEditText>(R.id.etPassword)
        val btnSignUp = findViewById<Button>(R.id.btnSignup)

        btnSignUp.setOnClickListener {
            val name = fullName.text.toString()
            val mail = userMail.text.toString()
            val uniqueId = userId.text.toString()
            val password = userPassword.text.toString()

            val user = User(name, mail, uniqueId, password)
            database = FirebaseDatabase.getInstance().getReference("SignUp")

            database.child(uniqueId).setValue(user).addOnSuccessListener {
                userId.text?.clear()
                userPassword.text?.clear()
                userMail.text?.clear()
                fullName.text?.clear()
                Toast.makeText(this, "User Registered", Toast.LENGTH_SHORT).show()

            }.addOnFailureListener {
                Toast.makeText(this, "User Not Registered", Toast.LENGTH_SHORT).show()
            }
        }
        val signIntext = findViewById<TextView>(R.id.tvSignIN)
        signIntext.setOnClickListener {
            val openSignInActivity = Intent(this, SignIn::class.java)
            startActivity(openSignInActivity)
        }
    }
}