package com.herokuapp.newtechserver2.graphql

import com.coxautodev.graphql.tools.GraphQLMutationResolver
import com.herokuapp.newtechserver2.dao.UserDao
import com.herokuapp.newtechserver2.graphql.input.UserInput
import org.springframework.stereotype.Component

@Component
class Mutation(
        private val userDao: UserDao
): GraphQLMutationResolver {
    fun signup(input: UserInput) =
            userDao.createUser(input.email, input.password, input.profilename)
}