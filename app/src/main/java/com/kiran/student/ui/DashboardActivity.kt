package com.kiran.student.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.kiran.student.R

class DashboardActivity : AppCompatActivity() {

    private lateinit var btn1:Button
    private lateinit var btn2:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        btn1=findViewById(R.id.btn1)
        btn2=findViewById(R.id.btn2)

        btn1.setOnClickListener {
            startActivity(Intent(this,AddstudentActivity::class.java))
        }

        btn2.setOnClickListener {
            startActivity(Intent(this,ViewStudentActivity::class.java))
        }

    }
}