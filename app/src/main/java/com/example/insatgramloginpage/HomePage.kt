package com.example.insatgramloginpage

import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.insatgramloginpage.databinding.ActivityHomePageBinding

class HomePage : AppCompatActivity() {

    private lateinit var binding: ActivityHomePageBinding

    lateinit var dialog: Dialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomePageBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        dialog = Dialog(this)
        dialog.setContentView(R.layout.dialogbox)

        binding.appTooGood.setOnClickListener {
            Toast.makeText(this,"Thank You", Toast.LENGTH_SHORT).show()
            dialog.show()
        }

        binding.sendFeedback.setOnClickListener {
            val intentFeedback = Intent(this,feedback::class.java)
            Toast.makeText(this,"Welcome to Feedback", Toast.LENGTH_SHORT).show()
            startActivity(intentFeedback)
        }
//        val  name = intent.getStringExtra(SignIn.KEY1)
//        val  mail = intent.getStringExtra(SignIn.KEY2)
//        val  userID = intent.getStringExtra(SignIn.KEY3)
//
//        val welcomeText = findViewById<TextView>(R.id.tVWelcome)
//        val mailText = findViewById<TextView>(R.id.tvMail)
//        val idText = findViewById<TextView>(R.id.tvUnique)
//
//        welcomeText.text = "welcome $name"
//        mailText.text = "mail $mail"
//        idText.text = "userId $userID"

    }
}