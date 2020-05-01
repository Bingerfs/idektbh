package com.example.examn

class SignUpRepository {
    fun saveUSer(name:String, lastname:String, email:String): Boolean = name != "" && email !=""
}