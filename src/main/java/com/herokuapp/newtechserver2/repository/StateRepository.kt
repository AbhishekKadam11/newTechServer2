package com.herokuapp.newtechserver2.repository

import com.herokuapp.newtechserver2.data.Cities
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository


@Repository
interface StateRepository : MongoRepository<Cities, Any> {
     fun findByStateLike(state: String): List<Cities>
}