package com.example.insatgramloginpage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.example.insatgramloginpage.databinding.ActivityFeedbackBinding
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class feedback : AppCompatActivity() {

    lateinit var database : DatabaseReference

    private lateinit var binding: ActivityFeedbackBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFeedbackBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val fbUser = findViewById<TextInputEditText>(R.id.fbUserName)
        val fbNumberMail = findViewById<TextInputEditText>(R.id.fbNumberEmail)
        val fbInput = findViewById<TextInputEditText>(R.id.etInputFeedback)
        val btnSubmit = findViewById<Button>(R.id.feedbackSubmit)

        btnSubmit.setOnClickListener {
            val fbName = fbUser.text.toString()
            val fbNoMail = fbNumberMail.text.toString()
            val feedback = fbInput.text.toString()

            val ufb = UserFeedback(fbName,fbNoMail,feedback)
            database = FirebaseDatabase.getInstance().getReference("FeedBack")

            database.child(fbName).setValue(ufb).addOnSuccessListener {
                fbUser.text?.clear()
                fbNumberMail.text?.clear()
                fbInput.text?.clear()
                Toast.makeText(this, "Thank you for your feedback.", Toast.LENGTH_SHORT).show()
            }.addOnFailureListener {
                Toast.makeText(this, "Feedback not submitted.", Toast.LENGTH_SHORT).show()
            }
        }
//        binding.feedbackSubmit.setOnClickListener {
//            binding.fbUserName.text.toString()
//            binding.fbNumberEmail.text.toString()
//
//            val feedback = UserFeedback(binding.fbUserName, binding.fbNumberEmail, binding.etInputFeedback)
//            database = FirebaseDatabase.getInstance().getReference("Feedback")
//
//            database.child(binding.fbUserName.toString()).setValue(feedback).addOnSuccessListener {
//                binding.fbUserName.text?.clear()
//                binding.fbNumberEmail.text?.clear()
//                binding.etInputFeedback.text?.clear()
//                Toast.makeText(this, "Thank you for your feedback.", Toast.LENGTH_SHORT).show()
//
//            }.addOnFailureListener {
//                Toast.makeText(this, "Feedback not submitted.", Toast.LENGTH_SHORT).show()
//            }
//        }
    }
}