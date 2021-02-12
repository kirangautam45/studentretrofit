package com.kiran.student.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kiran.student.Adapter.StudentAdapter
import com.kiran.student.R
import com.kiran.student.repository.StudentRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ViewStudentActivity : AppCompatActivity() {

    private lateinit var recyclerview:RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_student)

        recyclerview = findViewById(R.id.recyclerview)
        loadstudent()
    }

    private fun loadstudent() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val studentRepository = StudentRepository()
                val response = studentRepository.getAllStudents()
                if (response.success == true) {
                    // Put all the student details in lstStudents
                    val lstStudents = response.data
                    withContext(Main) {
                        recyclerview.adapter =
                            StudentAdapter(this@ViewStudentActivity, lstStudents!!)
                        recyclerview.layoutManager =
                            LinearLayoutManager(this@ViewStudentActivity)
                    }
                }
            } catch (ex: Exception) {
                withContext(Main) {
                    Toast.makeText(
                        this@ViewStudentActivity,
                        "Error : ${ex.toString()}", Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
}