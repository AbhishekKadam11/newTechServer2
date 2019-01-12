package com.herokuapp.newtechserver2.repository

import com.herokuapp.newtechserver2.data.Orders
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface OrdersRepository : MongoRepository<Orders, Any> {
    fun findByOrderIdLike(productId: String): List<Orders>
}