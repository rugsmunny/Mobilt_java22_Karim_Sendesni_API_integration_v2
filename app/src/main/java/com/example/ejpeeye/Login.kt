package com.example.ejpeeye

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.ComponentActivity
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class Login : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val db = Firebase.firestore


        val loginBtn: Button = findViewById(R.id.login_button)
        val registerBtn: Button = findViewById(R.id.new_user)

        loginBtn.setOnClickListener {
            val email = findViewById<EditText>(R.id.email).text.toString()
            val password = findViewById<EditText>(R.id.password).text.toString()


                db.collection("users")
                    .whereEqualTo("email", email)
                    .whereEqualTo("password", password)
                    .get()
                    .addOnSuccessListener { querySnapshot ->
                        if (querySnapshot.isEmpty) {
                            Toast.makeText(this,"User not found", Toast.LENGTH_SHORT).show();
                        } else {
                            val intent = Intent(this, LandingPage::class.java)
                            intent.putExtra("email", email)
                            intent.putExtra("password", password)
                            startActivity(intent)
                        }
                    }.addOnFailureListener { e ->
                        Toast.makeText(
                        this, "Error during login: ${e.message}", Toast.LENGTH_SHORT).show()
                    }
            }



        registerBtn.setOnClickListener {
            val i = Intent(this@Login, Registration::class.java)
            startActivity(i)
        }

    }

}


