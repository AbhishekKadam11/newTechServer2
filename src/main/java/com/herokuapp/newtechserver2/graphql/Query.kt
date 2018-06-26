package com.herokuapp.newtechserver2.graphql

import com.coxautodev.graphql.tools.GraphQLQueryResolver
import org.springframework.stereotype.Component

@Component
class Query: GraphQLQueryResolver {
    fun version() = "1.0.0"
}