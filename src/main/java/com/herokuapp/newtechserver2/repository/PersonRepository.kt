package com.herokuapp.newtechserver2.repository


import com.herokuapp.newtechserver2.data.Person
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface PersonRepository : MongoRepository<Person, String> {
    fun findByNameLike(name: String): List<Person>

//    fun findByEmailLike(email: String): List<Person>
}