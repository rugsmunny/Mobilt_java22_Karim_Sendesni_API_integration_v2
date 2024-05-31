package com.example.ejpeeye

import android.content.Intent
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.RadioGroup
import android.widget.RelativeLayout
import android.widget.SeekBar
import android.widget.Switch
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat
import androidx.core.graphics.component1
import androidx.core.graphics.toColor
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class Registration : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)
        val db = Firebase.firestore
        val submitBtn: Button = findViewById(R.id.submitBtn)
        val backBtn: Button = findViewById(R.id.backBtn)
        val displayModeSwitch = findViewById<Switch>(R.id.switchDisplayMode)


        displayModeSwitch.setOnCheckedChangeListener { _, isChecked ->
            val registrationLayout = findViewById<LinearLayout>(R.id.registration_layout)
            if (isChecked) {
                registrationLayout.setBackgroundColor(ContextCompat.getColor(this, R.color.black))
            } else {
                registrationLayout.setBackgroundResource(R.drawable.login_background)
            }
        }


        submitBtn.setOnClickListener {
            val emailET = findViewById<EditText>(R.id.email)
            val passwordET = findViewById<EditText>(R.id.password)
            val licenseRG = findViewById<RadioGroup>(R.id.radioGroup)
            val modeSDM = findViewById<Switch>(R.id.switchDisplayMode)

            val email = emailET
            val password = passwordET
            val license = licenseRG
            val mode = modeSDM

            Toast.makeText(this@Registration, "email: ${email.text}, password: ${password.text}, license: ${license.checkedRadioButtonId}, mode: ${mode.isChecked}", Toast.LENGTH_SHORT).show()

            val user = hashMapOf(
                "email" to email.text.toString(),
                "password" to password.text.toString(),
                "license" to license.checkedRadioButtonId.toString(),
                "mode" to mode.isChecked
            )
            db.collection("users")
                .add(user)
                .addOnSuccessListener {documentReference ->
                    Toast.makeText(this@Registration, "Your account was successfully created", Toast.LENGTH_SHORT).show()

                    val i = Intent(this@Registration, Login::class.java)
                    startActivity(i)
                }
                .addOnFailureListener { e ->
                    Toast.makeText(
                        this.applicationContext, "Unable to create your account: ${e.message}", Toast.LENGTH_SHORT).show()
                }
        }

        backBtn.setOnClickListener {
            val i = Intent(this, Login::class.java)
            startActivity(i)
        }
    }

}
