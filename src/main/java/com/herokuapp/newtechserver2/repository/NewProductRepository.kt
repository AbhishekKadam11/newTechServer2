package com.herokuapp.newtechserver2.repository

import com.herokuapp.newtechserver2.data.Products

import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface NewProductRepository : MongoRepository<Products, Any> {

}