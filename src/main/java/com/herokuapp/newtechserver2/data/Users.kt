package com.herokuapp.newtechserver2.data

import org.springframework.data.mongodb.core.mapping.Document
import java.util.*
import javax.persistence.Id

@Document(collection="users")
data class Users (
        @Id
        val id: String = UUID.randomUUID().toString(),
        val profilename: String,
        val email: String,
        val password: String,
        val extraaddon: String,
        val gender: String,
        val firstName: String,
        val lastName: String,
        val profilePic: String
)

