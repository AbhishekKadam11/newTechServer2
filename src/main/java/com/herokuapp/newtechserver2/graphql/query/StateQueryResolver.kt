package com.herokuapp.newtechserver2.graphql.query

import com.coxautodev.graphql.tools.GraphQLQueryResolver
import com.herokuapp.newtechserver2.dao.StateDao
import org.springframework.stereotype.Component

@Component
class StateQueryResolver(
        private val stateDao: StateDao
): GraphQLQueryResolver {

    fun stateList() =
            stateDao.getStateListData()

    fun cityList(state: String) =
            stateDao.getCityListData(state)
}