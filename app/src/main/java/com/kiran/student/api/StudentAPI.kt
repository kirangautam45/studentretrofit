package com.kiran.student.api

import androidx.room.Update
import com.kiran.student.entity.Student
import com.kiran.student.response.*
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.*

interface StudentAPI {
    //Add Student
    @POST("student/")
    suspend fun addStudent(
        @Header("Authorization") token:String,
        @Body student:Student
    ):Response<AddStudentResponse>

    //GET all student
    @GET("student/")
    suspend fun getAllStudents(
        @Header("Authorization") token : String,
    ):Response<GetAllStudentResponse>

    //Delete student
    @DELETE("student/{id}")
    suspend fun deleteStudent(
        @Header("Authorization") token: String,
        @Path ("id") id : String
    ):Response<DeleteStudentResponse>

    // student update //
   @PUT("student/{id}")
    suspend fun updateStudent(
        @Header("Authorization") token: String,
        @Path("id")id: String
    ):Response<UpdateStudentResponse>
    @Multipart
    @PUT("student/{id}/photo")
    suspend fun uploadImage(
        @Header("Authorization") token: String,
        @Path("id") id: String,
        @Part file: MultipartBody.Part
    ): Response<ImageResponse>
}
