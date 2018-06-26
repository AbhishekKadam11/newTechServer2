package com.herokuapp.newtechserver2.repository

import com.herokuapp.newtechserver2.data.Users
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : MongoRepository<Users, Any> {
   // fun findByNameLike(profilename: String): List<Users>

    fun findByEmailLike(email: String): List<Users>
    fun findByEmail(email: String, password: String): List<Users>
}