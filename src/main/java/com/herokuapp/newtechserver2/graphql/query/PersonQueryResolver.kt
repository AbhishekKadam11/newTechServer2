package com.herokuapp.newtechserver2.graphql.query

import com.coxautodev.graphql.tools.GraphQLQueryResolver
import com.herokuapp.newtechserver2.dao.PersonDao
import org.springframework.stereotype.Component

@Component
class PersonQueryResolver(
        private val personDao: PersonDao
): GraphQLQueryResolver {
    fun person(id: String) =
            personDao.getPersonById(id)

    fun personsByName(name: String) =
            personDao.getPersonsByName(name)

//    fun personsByEmail(email: String) =
//            personDao.getPersonsByEmail(email)
}