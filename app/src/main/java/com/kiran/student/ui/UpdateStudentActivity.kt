package com.kiran.student.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.kiran.student.R
import com.kiran.student.api.ServiceBuilder
import com.kiran.student.entity.Student
import com.kiran.student.repository.StudentRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class UpdateStudentActivity : AppCompatActivity() {

private lateinit var etFullname:EditText
private lateinit var etAge:EditText
private lateinit var btnUpdate:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_student)
        etFullname =findViewById(R.id.etFullName)
        etAge=findViewById(R.id.etAge)
        btnUpdate=findViewById(R.id.btnUpdate)

        val intent = intent.getParcelableExtra<Student>("student")
if(intent!=null){
    etFullname.setText(intent.fullname)
    etAge.setText(intent.age.toString())
}
        btnUpdate.setOnClickListener{
            val student =Student(fullname = etFullname.text.toString(),
            age = etAge.text.toString().toInt())
            student.stdId=intent!!.stdId

            CoroutineScope(Dispatchers.IO).launch{
                val repository =StudentRepository()
                try {
                    val response=repository.updateStudent()
                    if (response.success==true)
                        ServiceBuilder.token=="Bearer" +response
                }catch (ex:Exception){
                    withContext(Main){
                        startActivity(Intent(this@UpdateStudentActivity,ViewStudentActivity::class.java))

              }
            }
        }

    }
}