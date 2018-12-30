package com.herokuapp.newtechserver2.graphql.input

data class UserInput(
        val email: String,
        val password: String,
        val profilename: String,
        val extraaddon: String?,
        val gender: String?,
        val firstName: String?,
        val middleName: String?,
        val lastName: String?,
        val profilePic: String?,
        val address: String?,
        val mobileno: String?
)