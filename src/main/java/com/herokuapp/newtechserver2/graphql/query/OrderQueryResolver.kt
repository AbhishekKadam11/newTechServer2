package com.herokuapp.newtechserver2.graphql.query

import com.coxautodev.graphql.tools.GraphQLQueryResolver
import com.herokuapp.newtechserver2.dao.Ordersdao
import com.herokuapp.newtechserver2.data.Orders
import org.springframework.stereotype.Component

@Component
class OrderQueryResolver(
        private val orderDao: Ordersdao
): GraphQLQueryResolver {

    fun customerOrderDetails(): List<Orders> =
            orderDao.getCustomerOrderDetails()


}