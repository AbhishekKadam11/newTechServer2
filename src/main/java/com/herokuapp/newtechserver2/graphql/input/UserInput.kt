package com.herokuapp.newtechserver2.graphql.input

data class UserInput(
        val email: String,
        val password: String,
        val profilename: String
)