package com.kiran.student.repository

import com.kiran.student.api.MyApiRequest
import com.kiran.student.api.ServiceBuilder
import com.kiran.student.api.UserAPI
import com.kiran.student.entity.User
import com.kiran.student.response.LoginResponse

class UserRepository :
    MyApiRequest() {
        private val userAPI = ServiceBuilder.buildService(UserAPI::class.java)

    //Register User
    suspend fun registerUser(user: User) : LoginResponse{
        return apiRequest {
            userAPI.registerUser(user)
        }
    }

    //login User
    suspend fun loginUser(username:String, password:String):LoginResponse{
        return apiRequest {
            userAPI.checkUser(username, password)
        }
    }
    //dao goes here

}