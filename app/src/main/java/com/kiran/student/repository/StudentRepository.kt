package com.kiran.student.repository

import com.kiran.student.api.MyApiRequest
import com.kiran.student.api.ServiceBuilder
import com.kiran.student.api.StudentAPI
import com.kiran.student.api.UserAPI
import com.kiran.student.entity.Student
import com.kiran.student.response.AddStudentResponse
import com.kiran.student.response.DeleteStudentResponse
import com.kiran.student.response.GetAllStudentResponse
import com.kiran.student.response.UpdateStudentResponse

class StudentRepository : MyApiRequest() {
    private val studentAPI =
        ServiceBuilder.buildService(StudentAPI::class.java)

    //Add student
    suspend fun addStudent(student: Student): AddStudentResponse {
        return apiRequest {
            studentAPI.addStudent(
                ServiceBuilder.token!!,student
            )
        }
    }

    // get student
    suspend fun getAllStudents():GetAllStudentResponse {
        return apiRequest {
            studentAPI.getAllStudents(
                ServiceBuilder.token!!
            )
        }
    }

    // delete student
    suspend fun deleteStudent(id : String):DeleteStudentResponse{
        return apiRequest {
            studentAPI.deleteStudent(
                ServiceBuilder.token!!,id
            )
        }
    }
     //update student
  suspend fun updateStudent(id: String): UpdateStudentResponse {
return apiRequest {
    studentAPI.updateStudent(
        ServiceBuilder.token!!,id
    )
}
    }


}