package com.example.buah

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AlertDialog

class aboutMe : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about_me)


        val buttonkembali: Button = findViewById(R.id.buttonkembali)
        buttonkembali.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.buttonkembali -> {
                val pindah = Intent(this, MainActivity::class.java)
                startActivity(pindah)
            }
        }
    }
}