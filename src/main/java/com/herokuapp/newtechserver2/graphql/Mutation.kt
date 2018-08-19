package com.herokuapp.newtechserver2.graphql

import com.coxautodev.graphql.tools.GraphQLMutationResolver
import com.herokuapp.newtechserver2.dao.UserDao
import com.herokuapp.newtechserver2.data.Users
import com.herokuapp.newtechserver2.graphql.input.UserInput
import org.springframework.stereotype.Component
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletResponse;

@Component
class Mutation(
        private val userDao: UserDao
): GraphQLMutationResolver {


    fun signup(input: UserInput): Users? {
        val userData =  userDao.createUser(input.email, input.password, input.profilename)
        return userData
    }
}