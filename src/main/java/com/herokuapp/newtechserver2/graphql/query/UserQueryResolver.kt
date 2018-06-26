package com.herokuapp.newtechserver2.graphql.query

import com.coxautodev.graphql.tools.GraphQLQueryResolver
import com.herokuapp.newtechserver2.dao.UserDao
import org.springframework.stereotype.Component

@Component
class UserQueryResolver(
        private val userDao: UserDao
): GraphQLQueryResolver {
    fun user(id: String) =
            userDao.getUserById(id)

//    fun userByNames(name: String) =
//            userDao.getUserByName(name)

    fun userByEmail(email: String) =
            userDao.getUserByEmail(email)

    fun userForLogin(email: String, password: String) =
            userDao.getUserForLogin(email, password)
}