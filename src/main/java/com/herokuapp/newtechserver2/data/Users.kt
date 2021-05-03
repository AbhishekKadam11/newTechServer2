package com.herokuapp.newtechserver2.data

import org.springframework.data.mongodb.core.mapping.Document
import javax.persistence.Column
import javax.persistence.Id

@Document(collection="users")
data class Users (
        @Id
        val id: String? = null,

        var profilename: String? = null,

        var email: String,

        val password: String? = null,

        var extraaddon: String? = null,

        var gender: String? = null,

        var firstName: String? = null,

        var middleName: String? = null,

        var lastName: String? = null,

        var profilePic: String? = null,

        var token: String? = null,

        var address: String? = null,

        var mobileno: String? = null,

        var state: String? = null,

        var city: String? = null


)
