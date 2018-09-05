package com.herokuapp.newtechserver2.data

import org.springframework.data.mongodb.core.mapping.Document
import javax.persistence.Column
import javax.persistence.Id

@Document(collection="users")
data class Users (
        @Id
        val id: String? = null,
        @Column(nullable = true)
        val profilename: String? = null,
        val email: String,
        val password: String,
        @Column(nullable = true)
        val extraaddon: String? = null,
        @Column(nullable = true)
        val gender: String? = null,
        @Column(nullable = true)
        val firstName: String? = null,
        @Column(nullable = true)
        val lastName: String? = null,
        @Column(nullable = true)
        val profilePic: String? = null,
        var token: String? = null
)
