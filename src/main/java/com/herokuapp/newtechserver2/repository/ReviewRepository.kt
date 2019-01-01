package com.herokuapp.newtechserver2.repository

import com.herokuapp.newtechserver2.data.Review
import org.springframework.data.mongodb.repository.MongoRepository

import org.springframework.stereotype.Repository

@Repository
interface ReviewRepository : MongoRepository<Review, Any> {
    fun findByProductIdLike(productId: String): List<Review>
}