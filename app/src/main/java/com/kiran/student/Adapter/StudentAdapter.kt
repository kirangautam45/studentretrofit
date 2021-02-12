package com.kiran.student.Adapter

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.kiran.student.R
import com.kiran.student.entity.Student
import com.kiran.student.repository.StudentRepository
import com.kiran.student.ui.UpdateStudentActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class StudentAdapter (
    private val context: Context,
    private val lstStudents: MutableList<Student>
) : RecyclerView.Adapter<StudentAdapter.StudentViewHolder>() {
    class StudentViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvFname: TextView = view.findViewById(R.id.tvFullName)
        val tvaddress: TextView = view.findViewById(R.id.tvaddress)
        val tvAge: TextView = view.findViewById(R.id.tvAge)
        val tvgender:TextView=view.findViewById(R.id.tvgender)
        val btnUpdate: ImageButton = view.findViewById(R.id.btnUpdate)
        val btnDelete: ImageButton = view.findViewById(R.id.btnDelete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.custom_student_layout, parent, false)
        return StudentViewHolder(view)
    }
    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        val student = lstStudents[position]
        holder.tvFname.text = student.fullname
        holder.tvaddress.text =student.address
        holder.tvgender.text=student.gender
        holder.tvAge.text = student.age.toString()

        holder.btnUpdate.setOnClickListener {
            val intent = Intent(context, UpdateStudentActivity::class.java)
            intent.putExtra("student",student)
            context.startActivity(intent)
        }

        holder.btnDelete.setOnClickListener {

            val builder = AlertDialog.Builder(context)
            builder.setTitle("Delete student")
            builder.setMessage("Are you sure you want to delete ${student.fullname} ??")
            builder.setIcon(android.R.drawable.ic_dialog_alert)
            builder.setPositiveButton("Yes") { _, _ ->
                deleteStudent(student)
            }
            builder.setNegativeButton("No") { _, _ ->
                Toast.makeText(context, "Action cancelled", Toast.LENGTH_SHORT).show()
            }
            val alertDialog: AlertDialog = builder.create()
            alertDialog.setCancelable(false)
            alertDialog.show()
        }

    }

    private fun deleteStudent(student: Student) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val studentRepository = StudentRepository()
                val response = studentRepository.deleteStudent(student._id!!)
                if (response.success == true) {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(context, "Student deleted", Toast.LENGTH_SHORT)
                            .show()
                    }
                    withContext(Main) {
                        lstStudents.remove(student)
                        notifyDataSetChanged()
                    }
                }
            } catch (ex: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(
                        context,
                        "Error ${ex.localizedMessage}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }

    }

    override fun getItemCount(): Int {
        return lstStudents.size
    }
}
