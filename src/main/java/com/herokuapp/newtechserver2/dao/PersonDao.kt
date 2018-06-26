package com.herokuapp.newtechserver2.dao

import com.herokuapp.newtechserver2.repository.PersonRepository
import org.springframework.stereotype.Component

@Component
class PersonDao(
        private val personRepository: PersonRepository
) {
    fun getPersonById(id: String) =
            personRepository.findById(id)

    fun getPersonsByName(name: String) =
            personRepository.findByNameLike(name)

//    fun getPersonsByEmail(email: String) =
//            personRepository.findByEmailLike(email)
}