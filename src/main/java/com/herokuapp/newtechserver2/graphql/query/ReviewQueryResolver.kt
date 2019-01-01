package com.herokuapp.newtechserver2.graphql.query

import com.coxautodev.graphql.tools.GraphQLQueryResolver
import com.herokuapp.newtechserver2.dao.Reviewdao
import org.springframework.stereotype.Component

@Component
class ReviewQueryResolver(
        private val reviewDao: Reviewdao
): GraphQLQueryResolver {
    fun getCustomerReviewData(productId: String) =
            reviewDao.getCustomerReviewData(productId)
}